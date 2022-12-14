package com.openclassrooms.poseidon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    // Temporaire pour 1ers Tests - A désactiver au final
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("springuser").password(passwordEncoder().encode("spring123"))
                .roles("USER")
                .and()
                .withUser("springadmin").password(passwordEncoder().encode("admin123"))
                .roles("ADMIN", "USER");
    }

    // Traitement des requêtes http en entrée
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Formulaire de LOGIN
        // Mise en place chaine de filtres poir gestion de l'accès aux pages selon le rôle, pour chaque requête
        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/user/*").hasAuthority("ADMIN")
                // Toutes les autres requêtes nécessitent l'authetification par Login Form
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

