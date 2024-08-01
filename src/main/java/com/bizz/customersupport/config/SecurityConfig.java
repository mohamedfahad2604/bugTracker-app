package com.bizz.customersupport.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bizz.customersupport.services.UserService;
/*import com.bizz.customersupport.util.ExcelToHtmlConverter;*/

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void configure(HttpSecurity http) {
		try {
			http.authorizeRequests()
					.antMatchers("/registration**", "/bcshtml/**", "/assets/**", "/js/**", "/css/**", "/img/**",
							"/webjars/**","/proposalResponse/accept*","/proposalResponse/reject*","/proposalResponse/hold*")
					.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
					.logout().invalidateHttpSession(true).clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
					.permitAll();
			http.authorizeRequests().and().sessionManagement().maximumSessions(1).expiredUrl("/login?expire")
					.sessionRegistry(sessionRegistry);
		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));

		}

	}

	@Bean(name = "sessionRegistry")
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Autowired
	@Lazy
	private SessionRegistry sessionRegistry;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

}
