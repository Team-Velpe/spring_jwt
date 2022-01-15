package com.velpe.jwtAuth.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .cors()
        .and()
            .csrf()
                .disable()
            .formLogin()
                .disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
        .and()
            .addFilterBefore(
                    jwtAuthFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        http
            .authorizeRequests()
                .mvcMatchers(
                        "/"
                ).permitAll()
                .mvcMatchers(
                        "/api/v1/articles/**"
                ).authenticated();




    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * CORS Setting Bean
     * @return Bean
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();

//        list of origins for which cross-origin requests are allowed
        corsConfiguration.setAllowedOrigins(List.of("*"));
//        set the HTTP method to allow
        corsConfiguration.setAllowedMethods(List.of("GET","OPTION","POST","PUT","HEAD","DELETE"));
//      set  the list of headers that pre-flight request can list as allowed for use during an actual request
        corsConfiguration.setAllowedHeaders(List.of("*"));
//      whether user credentials are supported
        corsConfiguration.setAllowCredentials(true);

        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
