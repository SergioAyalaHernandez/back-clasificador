package com.example.ssjava.demo.controller.config;

import com.example.ssjava.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.HashSet;
import java.util.Set;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
  private final JwtFilter jwtFilter;

  @Autowired
  public SecurityConfig(@Lazy JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                    .requestMatchers("/api/tipos-usuario/**").permitAll() // Permitir acceso público a tipos-usuario
                    .requestMatchers("/api/user/**").permitAll() // Permitir acceso público a user
                    .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("alumno", "admin", "agente")
                    .requestMatchers(HttpMethod.PUT, "/api/**").hasAnyRole("admin", "agente") // Cambiado de hasRole("A")
                    .requestMatchers("/api/orders/**").hasRole("admin") // Cambiado de hasRole("A")
                    .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/prqs").permitAll()
                    .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/api/auth/**", "/api/**")
            )
            .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:5173"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> {
      com.example.ssjava.demo.entity.User user = userRepository.findByEmail(username)
              .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

      Set<GrantedAuthority> authorities = new HashSet<>();
      // Convertir el TipoUsuario a un rol de Spring Security
      authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getTipoUsuario().getNombre().toLowerCase()));

      return new org.springframework.security.core.userdetails.User(
              user.getEmail(),
              user.getPassword(),
              authorities
      );
    };
  }
}
