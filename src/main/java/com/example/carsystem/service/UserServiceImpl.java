package com.example.carsystem.service;

import java.security.InvalidKeyException;
import java.time.Instant;
import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.example.carsystem.dto.CarRequest;
import com.example.carsystem.dto.SigninRequest;
import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.exceptions.ValidateExceptions;
import com.example.carsystem.map.Mapper;
import com.example.carsystem.map.SigninResponseUserMap;
import com.example.carsystem.model.UserEntity;
import com.example.carsystem.repository.UserRepository;


import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private CarService carService;

    @Autowired
    private Mapper userResponseUserMapper;

    @Autowired
    private Mapper userCreatedUserMapper;

    @Autowired
    private Mapper userRequestToUserEntityMapper;

    @Autowired
    private Mapper signinResponseUserMap;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByEmail(username);
        if (user == null) {
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
        if (userEntity == null) {
            throw new ValidateExceptions("Invalid login or password");
        }

        String password = userEntity.getPassword();
        if (!bCryptPasswordEncoder.matches(signinRequest.getPassword(), password)) {
            throw new ValidateExceptions("Invalid login or password");
        }

        String userName = userEntity.getUsername();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateToken(authentication);

        userEntity.setLastLogin(Instant.now());
        userEntity = repository.save(userEntity);

        SigninResponse signinResponse;
		try {
			signinResponse = (SigninResponse) signinResponseUserMap.map(userEntity);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        signinResponse.setAccessToken(accessToken);

        return signinResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Object> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(userResponseUserMapper::map);
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        validateAttributes(userRequest);

        UserEntity userEntity = (UserEntity) userRequestToUserEntityMapper.map(userRequest);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));

        return (UserResponse) userCreatedUserMapper.map(repository.save(userEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id));
        return (UserResponse) userResponseUserMapper.map(userEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserRequest userRequest) {
        validateAttributes(userRequest);

        UserEntity userEntity = repository.getOne(id);
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setBirthday(userRequest.getBirthday());
        userEntity.setLogin(userRequest.getLogin());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        userEntity.setPhone(userRequest.getPhone());

        return (UserResponse) userResponseUserMapper.map(repository.save(userEntity));
    }

    @Override
    @Transactional
    public void validateAttributes(UserRequest userRequest) {
        if (repository.existsByEmail(userRequest.getEmail())) {
            throw new ValidateExceptions("Email already exists");
        }

 
        if (repository.existsByLogin(userRequest.getLogin())) {
            throw new ValidateExceptions("Login already exists");
        }

        String firstName = userRequest.getFirstName();
        String lastName = userRequest.getLastName();
        String email = userRequest.getEmail();
        Date birthday = userRequest.getBirthday();
        String login = userRequest.getLogin();
        String password = userRequest.getPassword();
        String phone = userRequest.getPhone();

        if (firstName == null || firstName.isEmpty() || firstName.isBlank()
                || lastName == null || lastName.isEmpty() || lastName.isBlank()
                || email == null || email.isEmpty() || email.isBlank()
                || birthday == null
                || login == null || login.isEmpty() || login.isBlank()
                || password == null || password.isEmpty() || password.isBlank()
                || phone == null || phone.isEmpty() || phone.isBlank()) {
            throw new ValidateExceptions("Missing fields");
        }

        userRequest.getCars().forEach(car -> carService.validateAttributes(car));
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findAuthenticateUser(String token) {
        if (token == null || token.isEmpty() || token.isBlank()) {
            throw new ValidateExceptions("Unauthorized");
        }

        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

            String username = claims.getSubject();

            UserEntity userEntity = repository.findByEmail(username);

            return (UserResponse) userResponseUserMapper.map(userEntity);

        } catch (ValidateExceptions e) {
            throw new ValidateExceptions("Unauthorized - invalid session");
        } catch (InvalidKeyException e) {
            throw new ValidateExceptions("Invalid JWT token");
        } catch (Exception e) {
            throw new ValidateExceptions("Unauthorized");
        }
    }
}
