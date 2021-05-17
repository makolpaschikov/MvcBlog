package com.example.winterblog.config;

import com.example.winterblog.repository.UserDAO;
import com.example.winterblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringSecurity configuration
 * @author makolpaschikov
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityCfg extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    /**
     * Users repository
     * @see UserDAO
     */
    @Autowired
    private UserService userService;

    /**
     * Changes the view for the endpoint login
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login"); // custom login page
    }

    /**
     * Sets the level of access to different endpoints
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/", "signup").permitAll() // access to home and registration pages
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/blog") // login page
                .and()
                    .logout().logoutSuccessUrl("/").permitAll(); // redirect to home page after logout
    }

    /**
     * Installs user service
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance()); // user list for AuthenticationManager
    }
}
