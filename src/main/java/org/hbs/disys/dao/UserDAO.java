package org.hbs.disys.dao;

import org.hbs.disys.model.IUsers;
import org.hbs.disys.model.UserFile;
import org.hbs.disys.model.UserParam;
import org.hbs.disys.util.EnumInterface;

public interface UserDAO
{
	public IUsers getUserForLogin(UserParam userParam, EnumInterface enumInterface);

	public UserParam getUsersList(UserParam userParam);

	public boolean userLogAtLogin(IUsers user, String ipAddr);

	public boolean userLogAtLogOut(UserParam userParam);

	public IUsers getUserName(UserParam userParam, EnumInterface enumInterface);

	public boolean saveUserFile(UserFile userFile);

}
