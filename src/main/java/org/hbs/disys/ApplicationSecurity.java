package org.hbs.disys;

import java.io.Serializable;

import org.hbs.disys.controller.IProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter implements Serializable, IProject
{
	private static final long		serialVersionUID	= 1780469792513903551L;

	@Autowired
	private UserDetailsService		userDetailsService;

	@Autowired
	private BCryptPasswordEncoder	bCryptPasswordEncoder;

	@Autowired
	private CustomLoginHandler		loginHandler;

	@Autowired
	private CustomLogoutHandler		logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		// ERestPath.getSecureDynaPaths()
		http.authorizeRequests().antMatchers(INDEX).permitAll().anyRequest().authenticated();
		http.csrf().disable();
		http.formLogin().loginPage(LOGIN).permitAll().failureUrl(LOGIN + "?error=true").defaultSuccessUrl(HOME, true).usernameParameter("userId").passwordParameter("password")
				.successHandler(loginHandler).and().logout().addLogoutHandler(logoutHandler).logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT)).logoutSuccessUrl(INDEX).and().exceptionHandling()
				.accessDeniedPage(ACCESS_DENIED).and().rememberMe().rememberMeParameter("rememberMe");

	}

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resources/**", "/static/**", "/assets/**", "/css/**", "/js/**", "/images/**");
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception
	{
		authBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	public UserDetailsService getUserDetailsService()
	{
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

}
