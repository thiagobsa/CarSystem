package com.example.carsystem.service;

import java.time.Instant;
import java.util.Arrays;
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
import com.example.carsystem.dto.UserCreated;
import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.exceptions.ValidateExceptions;
import com.example.carsystem.map.Mapper;
import com.example.carsystem.map.UserCreatedUserMapper;
import com.example.carsystem.model.UserEntity;
import com.example.carsystem.repository.UserRepository;

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
    private Mapper<UserEntity, UserCreated> userEntityToUserResponseCreateMapper;

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
        String userName = userEntity.getUsername();
        String password = userEntity.getPassword();

        boolean isMatcherPassword = bCryptPasswordEncoder.matches(signinRequest.getPassword(), password);
        if (!isMatcherPassword) {
            throw new ValidateExceptions("Invalid login or password");
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
    public UserCreated create(UserRequest userRequest) {
        validateAttributes(userRequest);
        UserEntity userEntity = userRequestToUserEntityMapper.map(userRequest);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        return UserCreatedUserMapper.map(repository.save(userEntity));
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
        validateAttributes(userRequest);
        UserEntity userEntity = repository.getOne(id);
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setBirthday(userRequest.getBirthday());
        userEntity.setLogin(userRequest.getLogin());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        userEntity.setPhone(userRequest.getPhone());
        return userEntityToUserResponseMapper.map(repository.save(userEntity));
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
            return userEntityToUserResponseMapper.map(userEntity);
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                throw new ValidateExceptions("Unauthorized - invalid session");
            }
        }
        return null;
    }

    public SigninResponse authenticateUser1(SigninRequest signinRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    public UserResponse create1(UserRequest userRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserEntity findUserById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public UserResponse update1(Long id, UserRequest userRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void validateAttributes(UserRequest userRequest) {
        // TODO Auto-generated method stub
    }
}