package com.example.demo.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;

@Component
public class JWTUtils {

	@Value("${app.jwtSemilla}")
	private String jwtSemilla;
	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String buildTokenJWT(String nombre) {
		try {
			return Jwts.builder().setSubject(nombre).setSubject("Hola mundo").setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
					.signWith(SignatureAlgorithm.HS512, "semillaaaasadfgsfgfdfgfdhtrsdfgsgdfdrsgsgdfhdfhrtdhfhftjhhtdsgsdfgsdgsdfdfdfsffg").compact();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
