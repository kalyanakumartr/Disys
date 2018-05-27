package org.hbs.disys;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbs.disys.bo.UserBo;
import org.hbs.disys.controller.IProject;
import org.hbs.disys.model.IUsers.EBean;
import org.hbs.disys.model.UserParam;
import org.hbs.disys.model.Users;
import org.hbs.disys.util.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler, IProject
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
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	{
		try
		{
			Users user = (Users) request.getSession().getAttribute(EBean.User.name());

			if (CommonValidator.isNotNullNotEmpty(user) && CommonValidator.isNotNullNotEmpty(authentication, authentication.getDetails()))
			{
				userBo.userLogAtLogOut(new UserParam(user));

				request.getSession().invalidate();
			}

			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(request.getServletContext().getContextPath() + INDEX);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				response.sendRedirect(request.getServletContext().getContextPath() + INDEX);
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}

}
