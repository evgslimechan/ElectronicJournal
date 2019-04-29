package com.slimechan.journal;

import com.slimechan.journal.server.models.managers.AuthManager;
import com.slimechan.journal.server.models.session.AuthProvider;
import com.slimechan.journal.server.models.session.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthManager auth;
	@Autowired
	private AuthProvider provider;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserDetailService detailService;

	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
				.cors().and().csrf().disable()
	            .authorizeRequests()
	                .antMatchers(HttpMethod.GET, "/", "/index").permitAll()
	                .and()
				.authorizeRequests()
					.antMatchers("/admin/**")
					.hasRole("ADMIN")
					.antMatchers("/api/**")
					.hasRole("ADMIN")
					.antMatchers("/home")
					.authenticated()
					.and()
	            .formLogin()
					.usernameParameter("login")
					.passwordParameter("password")
	                .loginPage("/login").successHandler(auth)
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll();
	        http
					.exceptionHandling()
						.accessDeniedPage("/error?type=404");
	    }
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.authenticationProvider(provider)
				.userDetailsService(detailService)
				.passwordEncoder(encoder)
				;
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
