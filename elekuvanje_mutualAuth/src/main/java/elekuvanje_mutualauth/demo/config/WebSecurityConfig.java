package elekuvanje_mutualauth.demo.config;

import elekuvanje_mutualauth.demo.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    public WebSecurityConfig(UserService userService){
        this.userService=userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeRequests().mvcMatchers("/doctor").hasRole("DOCTOR").mvcMatchers("/termini").permitAll().and().x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)").userDetailsService(userService);
        http.csrf().disable().authorizeRequests()
                .antMatchers("/","/patient/**","/css/**", "/images/**", "/js/**").permitAll()
                .antMatchers("/doctor/**").hasRole("DOCTOR").anyRequest().authenticated()
                .and()
                .x509()
                .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                .userDetailsService(this.userService);




    }
}
