package com.elekuvanje.elekuvanje.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomPatientAuthenticationProvider customPatientAuthenticationProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder,CustomPatientAuthenticationProvider customPatientAuthenticationProvider){
        this.passwordEncoder=passwordEncoder;
        this.customPatientAuthenticationProvider=customPatientAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.customPatientAuthenticationProvider);
    }
}
