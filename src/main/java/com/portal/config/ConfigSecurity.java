package com.portal.config;

import com.portal.security.JwtAuthenticationFilter;
import com.portal.service.UserDetailsServiceImpl;
import com.portal.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
public class ConfigSecurity {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final CorsConfigurationSource corsConfigurationSource;

    public ConfigSecurity(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService, CorsConfigurationSource corsConfigurationSource) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .csrf(csrf -> csrf.disable())  // Disable CSRF (not needed for JWT)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll() // Allow authentication endpoints
                .requestMatchers(HttpMethod.POST, "/api/jobs/post").hasRole("ADMIN") // 🔒 Only admins
                .requestMatchers(HttpMethod.GET, "/api/jobs").permitAll() // 🟢 Everyone can view jobs
                .anyRequest().authenticated() // Secure all other APIs
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Unauthorized: Invalid credentials");
                })
            )
            // 🔹 Add JWT Filter before UsernamePasswordAuthenticationFilter
            .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // ✅ Correctly inject UserDetailsService
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder()); // ✅ Ensure password encoding
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(authenticationProvider()));
    }
}