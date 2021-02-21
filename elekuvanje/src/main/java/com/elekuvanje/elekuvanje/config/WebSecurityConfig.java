package com.elekuvanje.elekuvanje.config;


import com.elekuvanje.elekuvanje.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {

    @Order(1)
    @Configuration
    public static class PatientConfigurationAdapter extends WebSecurityConfigurerAdapter{
        private final CustomPatientAuthenticationProvider customPatientAuthenticationProvider;
        private final PasswordEncoder passwordEncoder;
        public PatientConfigurationAdapter(CustomPatientAuthenticationProvider customPatientAuthenticationProvider,PasswordEncoder passwordEncoder){
            this.customPatientAuthenticationProvider=customPatientAuthenticationProvider;
            this.passwordEncoder=passwordEncoder;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(this.customPatientAuthenticationProvider);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
          //  http.antMatcher("/termini*").authenticationProvider(this.customPatientAuthenticationProvider).authorizeRequests().anyRequest().permitAll();
           //http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/termini").authenticated();
            http.csrf().disable().authorizeRequests().antMatchers("/","/doctor").permitAll()
                    .antMatchers("/termini")
                    .hasRole("DOCTOR")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login");

        }
    }


    @Order(2)
    @Configuration
    public static class DoctorConfigurationAdapter extends WebSecurityConfigurerAdapter{
       private final UserService userService;
        public DoctorConfigurationAdapter(UserService userService){
            this.userService=userService;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
          // http.authorizeRequests().mvcMatchers("/doctor").hasRole("DOCTOR").mvcMatchers("/termini").permitAll().and().x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)").userDetailsService(userService);
          http.csrf().disable().authorizeRequests()
                    .antMatchers("/","/termini").permitAll()
                   .antMatchers("/doctor").authenticated()
                    .and()
                    .x509()
                    .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                    .userDetailsService(this.userService);




        }
    }








}
