package com.oladapo.EasySchool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg")
                        .ignoringRequestMatchers("/public/**"))
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/", "/home",
                                        "/holidays/**",
                                        "/contact",
                                        "/saveMsg",
                                        "/courses",
                                        "/about",
                                        "/assets/**").permitAll().
                                requestMatchers("/public/**").permitAll().
                                requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers("/displayMessages").hasRole("ADMIN").
                                requestMatchers("/admin/**").hasRole("ADMIN").
                                requestMatchers("/closeMsg/**").hasRole("ADMIN").
                                requestMatchers("/student/**").hasRole("STUDENT").
                                requestMatchers("/displayProfile").authenticated().
                                requestMatchers("/updateProfile").authenticated().
                                requestMatchers("/dashboard").authenticated()
                                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
