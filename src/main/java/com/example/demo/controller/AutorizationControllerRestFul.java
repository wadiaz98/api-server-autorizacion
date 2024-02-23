package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JWTUtils;
import com.example.demo.service.to.UsuarioTo;

@RestController
@RequestMapping("/autorizaciones")
public class AutorizationControllerRestFul {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUtils jwt;

	@GetMapping(path = "/jwt", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String obtenerToken(@RequestBody UsuarioTo usuario) {
		// Autorizacion
		// Validar que el usuario y password sean correctos
		this.autentication(usuario.getNombre(), usuario.getPassword());
		// Si es correcta la autenticación se retorna el token

		return new JWTUtils().buildTokenJWT(usuario.getNombre());
	}

	private void autentication(String usuario, String password) {
		UsernamePasswordAuthenticationToken usuarioToken = new UsernamePasswordAuthenticationToken(usuario, password);
		// De security core
		Authentication autenticacion = this.authenticationManager.authenticate(usuarioToken);
		SecurityContextHolder.getContext().setAuthentication(autenticacion);
	}
}
