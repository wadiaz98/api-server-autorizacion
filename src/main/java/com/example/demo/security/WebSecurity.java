package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity {

	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;

	// Permite configurar y decir que a la API le de la autorizacion
	// Permite que la capacidad o URL que se da sea publica
	// /API/v1.0/seguridad/autorizaciones/jwt/**
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/autorizaciones/jwt/**").permitAll()
				.antMatchers("/autorizaciones/jwt/**").permitAll().anyRequest().authenticated();

		http.authenticationProvider(authenticationProvider());

		// http.addFilterBefore(authenticationJwtTokenFilter(),
		// UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// Para el acceso a datos (Dao)
	// Relación directa con la autenticación-> los 3 metodos
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(detailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	// -------------------------Estándar----------------------------
	// permite la autenticacion en la API de obtener token
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	// Define el algoritmo con el que se encripta el password en la base de datos
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
