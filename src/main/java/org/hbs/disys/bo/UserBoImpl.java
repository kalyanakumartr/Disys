package org.hbs.disys.bo;

import java.util.List;

import org.hbs.disys.dao.UserDAO;
import org.hbs.disys.model.IUsers;
import org.hbs.disys.model.IUsers.EBean;
import org.hbs.disys.model.UserFile;
import org.hbs.disys.model.UserParam;
import org.hbs.disys.util.CommonValidator;
import org.hbs.disys.util.IParam.ENamed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBoImpl implements UserBo
{
	private static final long	serialVersionUID	= 2685542022188828413L;

	@Autowired
	protected UserDAO			userDAO;

	public void authenticateUser(UserParam userParam) throws Exception
	{

		ENamed.EqualTo.param_AND(userParam, "usUserId", userParam.userId);
		userParam.user = userDAO.getUserForLogin(userParam, EBean.User);

	}

	public IUsers getUser(UserParam userParam)
	{
		userParam = userDAO.getUsersList(userParam);
		if (CommonValidator.isListFirstNotEmpty(userParam.dataList))
			return (IUsers) userParam.dataList.iterator().next();
		return null;
	}

	public IUsers getUserName(String userName)
	{
		UserParam userParam = new UserParam();

		ENamed.EqualTo.param_AND(userParam, "usUserId", userName);

		ENamed.EqualTo.param_AND(userParam, "status", true);

		userParam._OrderBy = ENamed.OrderBy.param("createdDate") + " Desc";

		return userParam.user = userDAO.getUserName(userParam, EBean.User);
	}

	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	@Override
	public List<IUsers> getUsersList(UserParam userParam)
	{
		return null;
	}

	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public void userLogAtLogin(IUsers user, String ipAddr)
	{
		userDAO.userLogAtLogin(user, ipAddr);

	}

	public void userLogAtLogOut(UserParam userParam)
	{
		userDAO.userLogAtLogOut(userParam);
	}

	@Override
	public boolean saveUserFile(UserFile userFile)
	{
		return userDAO.saveUserFile(userFile);
	}

}
