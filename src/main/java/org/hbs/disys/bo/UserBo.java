package org.hbs.disys.bo;

import java.io.Serializable;
import java.util.List;

import org.hbs.disys.model.IUsers;
import org.hbs.disys.model.UserFile;
import org.hbs.disys.model.UserParam;

public interface UserBo extends Serializable
{
	public void authenticateUser(UserParam userParam) throws Exception;

	public IUsers getUser(UserParam userParam);

	public List<IUsers> getUsersList(UserParam userParam);

	public void userLogAtLogin(IUsers user, String ipAddr);

	public void userLogAtLogOut(UserParam userParam);

	public IUsers getUserName(String name);

	public boolean saveUserFile(UserFile userFile);

}
