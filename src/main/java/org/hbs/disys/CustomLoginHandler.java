package org.hbs.disys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbs.disys.bo.UserBo;
import org.hbs.disys.controller.IProject;
import org.hbs.disys.model.IUsers.EBean;
import org.hbs.disys.model.UserParam;
import org.hbs.disys.util.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginHandler implements AuthenticationSuccessHandler, IProject
{

	private static final long	serialVersionUID	= 407818492923421819L;

	@Autowired
	protected UserBo			userBo;

	public UserBo getUserBo()
	{
		return userBo;
	}

	public void setUserBo(UserBo userBo)
	{
		this.userBo = userBo;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
	{

		/*
		 * Authentication auth = SecurityContextHolder.getContext().getAuthentication(); String name
		 * = auth.getName(); // get logged in username /user id UserParam userParam = new
		 * UserParam(); userParam.userId = name;
		 */

		User user = (User) authentication.getPrincipal();
		UserParam userParam = new UserParam();
		userParam.userId = user.getUsername();
		try
		{
			// ENamed.EqualTo.param_AND(userParam, "usUserId", userParam.userId);
			// ENamed.EqualTo.param_AND(userParam, "status", true);
			userParam.user = userBo.getUser(userParam);
			if (CommonValidator.isNotNullNotEmpty(userParam.user))
			{
				userBo.userLogAtLogin(userParam.user, request.getRemoteAddr());

				request.getSession().setAttribute(EBean.User.name(), userParam.user);
			}
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(request.getServletContext().getContextPath() + HOME);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			response.sendRedirect(request.getServletContext().getContextPath() + INDEX);
		}
	}

}
