package com.example.txbank.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**", "/swagger-ui/**", "/v3/api-docs/**")) // Отключаем CSRF для API
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/images/**", "/css/**", "/js/**", "/welcome", "/register", "/logout", "/infoWindow").permitAll()
                .requestMatchers("/myProfile").hasRole("USER") // Используем requestMatchers для определения прав доступа
                .anyRequest().authenticated()) // Все остальные запросы требуют аутентификации
            .formLogin(form -> form
                .loginPage("/login") // Указывает на твою страницу логина
                .loginProcessingUrl("/login") // Обрабатывает POST-запросы на этот URL
                .defaultSuccessUrl("/myProfile", true) // Куда перенаправлять после успешного входа
                .failureUrl("/login?error") // Куда перенаправлять при ошибке
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout") // URL для выхода
                .logoutSuccessUrl("/welcome") // Перенаправление на страницу логина после выхода
                .permitAll()); // Разрешаем доступ к функции выхода всем

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT login, password, status FROM user_bank WHERE login = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT login, role_user FROM user_bank WHERE login = ?");
        return manager;
    }
}
