package org.frangoro.booksharing.configuration;

import javax.sql.DataSource;

import org.frangoro.booksharing.controller.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
    
	@Autowired
	DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/signup",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**").permitAll()
					.antMatchers("/admin/**").hasAuthority("ADMIN")
					.antMatchers("/**").hasAnyAuthority("ADMIN", "USER")
                    .anyRequest().denyAll()
                .and()
                	.formLogin()
                    	.loginPage("/login")
                    	.permitAll()
                    	.usernameParameter("username").passwordParameter("password")
                .and()
	                .logout()
	                    .invalidateHttpSession(true)
	                    .clearAuthentication(true)
	                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                    .logoutSuccessUrl("/login?logout")
	                    .permitAll()
                .and()
                	.exceptionHandling()
                		.accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//TODO Allow social media authentication
    	
    	auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username, password, enabled from user where username = ?")
		.authoritiesByUsernameQuery(
			"select u.username, r.role from role r, user u where r.user_id=u.id AND u.username = ?");
    }

}