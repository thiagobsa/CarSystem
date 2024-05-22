package com.example.carsystem.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.carsystem.dto.SigninRequest;
import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.dto.UserResponseCreate;
import com.example.carsystem.exceptions.EmailException;
import com.example.carsystem.exceptions.LoginAlreadyException;
import com.example.carsystem.exceptions.LoginPasswordException;
import com.example.carsystem.exceptions.MissingFieldsException;
import com.example.carsystem.exceptions.TokenJWTExeption;
import com.example.carsystem.map.Mapper;
import com.example.carsystem.models.UserEntity;
import com.example.carsystem.repositories.UserRepository;
import com.example.carsystem.utils.JwtTokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private CarService carService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private Mapper<UserEntity, UserResponse> userEntityToUserResponseMapper;

	@Autowired
	private Mapper<UserEntity, UserResponseCreate> userEntityToUserResponseCreateMapper;


	@Autowired
	private Mapper<UserRequest, UserEntity> userRequestToUserEntityMapper;

	@Autowired
	private Mapper<UserEntity, SigninResponse> userEntityToSigninResponseMapper;

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = repository.findByEmail(username);
		
		if(user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		
		logger.info("User found: " + username);
		return user; 
	}

	@Override
	@Transactional
	public SigninResponse authenticateUser(SigninRequest signinRequest) {

		UserEntity userEntity = repository.findByLogin(signinRequest.getLogin());
		if(userEntity == null){
			throw new LoginPasswordException("Invalid login or password");
		}

		String userName = userEntity.getUsername();
		String password = userEntity.getPassword();

		boolean isMatcherPassword = bCryptPasswordEncoder.matches(signinRequest.getPassword(), password);
		if(!isMatcherPassword){
			throw new LoginPasswordException("Invalid login or password");
		}

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, signinRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String accessToken = jwtTokenProvider.generateToken(authentication);

		userEntity.setLastLogin(Instant.now());
		userEntity = repository.save(userEntity);

		SigninResponse signinResponse = userEntityToSigninResponseMapper.map(userEntity);

		signinResponse.setAccessToken(accessToken);

		return signinResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserResponse> findAllPaged(Pageable pageable) {

		return repository.findAll(pageable).map(userEntityToUserResponseMapper::map);
	}

	@Override
	@Transactional
	public UserResponseCreate create(UserRequest userRequest) {

		validateAtributtes(userRequest);

		UserEntity userEntity = userRequestToUserEntityMapper.map(userRequest);
		userEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));

		return userEntityToUserResponseCreateMapper.map(repository.save(userEntity));
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findById(Long id) {

		UserEntity userEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found: " + id));
		return userEntityToUserResponseMapper.map(userEntity);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		repository.deleteById(id);
	}

	@Override
	@Transactional
	public UserResponse update(Long id, UserRequest userRequest) {

		validateAtributtes(userRequest);

		UserEntity userEntity = repository.getOne(id);

		userEntity.setFirstName(userRequest.getFirstName());
		userEntity.setLastName(userRequest.getLastName());
		userEntity.setEmail(userRequest.getEmail());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        userEntity.setBirthday(dateFormat.parse(userRequest.getBirthday().toString()));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		userEntity.setLogin(userRequest.getLogin());
		userEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		userEntity.setPhone(userRequest.getPhone());

		return userEntityToUserResponseMapper.map(repository.save(userEntity));
	}

	public void validateAtributtes(UserRequest userRequest) {

	    boolean existsEmail = repository.existsByEmail(userRequest.getEmail());
	    if(existsEmail){
	        throw new EmailException("Email already exists");
	    }

	    boolean existsLogin = repository.existsByLogin(userRequest.getLogin());
	    if(existsLogin){
	        throw new LoginAlreadyException("Login already exists");
	    }

	    String firstName = userRequest.getFirstName();
	    String lastName = userRequest.getLastName();
	    String email = userRequest.getEmail();
	    String birthday = userRequest.getBirthday(); // use userRequest.getBirthday() instead of userRequest.getBirthday().toString()
	    String login = userRequest.getLogin();
	    String password = userRequest.getPassword();
	    String phone = userRequest.getPhone();

	    if( (firstName == null || firstName.isEmpty() || firstName.isBlank())
	            || (lastName == null || lastName.isEmpty() || lastName.isBlank())
	            || (email == null || email.isEmpty() || email.isBlank())
	            || birthday == null
	            || (login == null || login.isEmpty() || login.isBlank())
	            || (password == null || password.isEmpty() || password.isBlank())
	            || (phone == null || phone.isEmpty() || phone.isBlank())){

	        throw new MissingFieldsException("Missing fields");
	    }

	    userRequest.getCars().forEach(car -> {
	        carService.validateAtributtes(car);
	    });
	}

	@Override
	@Transactional
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findAuthenticateUser(String token) {

		if(token == null || token.isEmpty() || token.isBlank()){
			throw new TokenJWTExeption("Unauthorized");
		}

		try{
			Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

			String username = claims.getSubject();

			UserEntity userEntity = repository.findByEmail(username);

			return userEntityToUserResponseMapper.map(userEntity);

		} catch (Exception e){
			if(e instanceof ExpiredJwtException){
				throw new TokenJWTExeption("Unauthorized - invalid session");
			}
		}
		return null;
	}

}
