package ch.fhnw.webeC;

import ch.fhnw.webeC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository repo;

    @Autowired
    UserDetailsService userDetailsService;

    public WebSecurityConfig(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// For testing, not recommended in production
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/uploads/**").permitAll()
                .antMatchers("/download/**").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/news").permitAll()
                .antMatchers("/news/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/recipe").permitAll()
                .antMatchers("/recipe/add").hasAnyAuthority("ADMIN", "AUTHOR")
                .antMatchers("/recipe/*/edit").hasAnyAuthority("ADMIN", "AUTHOR")
                .antMatchers("/recipe/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login-error")
                .defaultSuccessUrl("/", true);
    }
}
