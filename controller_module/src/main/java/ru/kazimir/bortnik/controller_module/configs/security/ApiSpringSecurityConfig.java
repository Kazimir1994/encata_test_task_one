package ru.kazimir.bortnik.controller_module.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import static ru.kazimir.bortnik.controller_module.constant.ApiURLConstants.API_403_URL;
import static ru.kazimir.bortnik.controller_module.constant.ApiURLConstants.API_ADMIN_URL;
import static ru.kazimir.bortnik.controller_module.constant.ApiURLConstants.API_USER_URL;
import static ru.kazimir.bortnik.controller_module.constant.RoleConstants.ADMIN_ROLE_NAME;
import static ru.kazimir.bortnik.controller_module.constant.RoleConstants.USER_ROLE_NAME;

@Configuration
public class ApiSpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AccessDeniedHandler accessDeniedHandler;
    private final UserDetailsService userDetailsService;

    @Autowired
    public ApiSpringSecurityConfig(PasswordEncoder passwordEncoder,
                                   AccessDeniedHandler accessDeniedHandler,
                                   @Qualifier("appUserDetailsService") UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(API_403_URL).permitAll()
                .antMatchers(API_ADMIN_URL).hasAuthority(ADMIN_ROLE_NAME)
                .antMatchers(API_USER_URL).hasAuthority(USER_ROLE_NAME)
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .csrf()
                .disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
