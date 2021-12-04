package com.duan.kingfragrance.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.duan.kingfragrance.model.User;
import com.duan.kingfragrance.repository.UserRepository;
import com.duan.kingfragrance.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final String JWT_SECRET = "hihi";
	private final long JWT_EXPIRATION = 604800000L;
	@Autowired
	private UserRepository userRepo;
	public String generateToken(User user) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
		    String token = JWT.create()
		    		.withSubject(user.getId())
		        .withIssuer("auth0")
		        .sign(algorithm);
	
		    return token;
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
			return null;
		}
		
    }
	public String verifyToken(String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("auth0")
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    return jwt.getSubject();
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			return null;
		}
	}
	@Override
	public String findUserAndcreateJWT(String username, String password) {
		Optional<User> op = userRepo.findUser(username, password);
		if (op.isPresent()) {
			User u = op.get();
			String jwt = generateToken(u);
			return jwt;
		} else {
			return null;
		}	
	}

	@Override
	public User createUser(User user) {
		Optional<User> op = userRepo.findUserByUsername(user.getUsername());
		if (op.isPresent()) {
			return null;
		} else {
			return userRepo.save(user);
		}
//		return userRepo.save(user);
	}
	@Override
	public Boolean verifyUser(String token) {
		String id = verifyToken(token);
		if (id != null) {
			return true;
		} else {
			return false;
		}
	}

}
