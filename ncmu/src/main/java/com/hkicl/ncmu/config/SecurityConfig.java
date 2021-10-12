package com.hkicl.ncmu.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hkicl.ncmu.filter.VerifyCodeFilter;
import com.hkicl.ncmu.handler.UrlAuthenticationSuccessHandler;
import com.hkicl.ncmu.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	protected Logger logger = LogManager.getRootLogger();
	@Autowired
	DataSource dataSource;
	@Autowired
	VerifyCodeFilter verifyCodeFilter;
	@Autowired
	protected CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		try {
			//auth.userDetailsService(userDetailsService).passwordEncoder(this.encoder);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()//
				.authorizeRequests().antMatchers("/", "/errors", "/403", "/login", "/vercode").permitAll()//
				.antMatchers("/welcome", "/users").authenticated()//
				.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")//
				.and().formLogin()//
				.loginPage("/login")//
				.loginProcessingUrl("/login")//
				.usernameParameter("username")//
				.passwordParameter("password")//
				.failureUrl("/login?error")//
				.defaultSuccessUrl("/")//
				.permitAll()//
				.and()//
				.logout()//
				.logoutUrl("/logout")//
				.logoutSuccessUrl("/")//
				.deleteCookies("remember-me")//
				.deleteCookies("SESSION").invalidateHttpSession(true)//
				.and()//
				.rememberMe()//
				.and().sessionManagement()//
				.maximumSessions(1);
		http.addFilterBefore(this.verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * @MethodName:encoder
	 * @return
	 */
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	protected UrlAuthenticationSuccessHandler getUrlAuthenticationSuccessHandler() {
		return new UrlAuthenticationSuccessHandler();
	}
}