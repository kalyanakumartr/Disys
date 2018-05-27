package org.hbs.disys.model;

import java.sql.Timestamp;
import java.util.Set;

import org.hbs.disys.util.EnumInterface;

public interface IUsers extends ICommonBeanFields
{
	public enum EBean implements EnumInterface
	{
		User, UserLog, Roles, UserRoles, DefaultImageUpload, DefaultImageDownload, ImageUpload, ImageDownload, UserAddress
	}

	public IUserLog _getLastLoginInformation();

	public String getUsDob();

	public String getUsEmployeeId();

	public Set<IUserLog> getUserLogs();

	public String getUsLastName();

	public String getUsSex();

	public String getUsUserId();

	public String getUsUserName();

	public String getUsUserPwd();

	public Timestamp getUsUserPwdModDate();

	public Boolean getUsUserPwdModFlag();

	public String getUsUserStatus();

	public String getUsUsersType();

	public void setUsDob(String usDob);

	public void setUsEmployeeId(String usEmployeeId);

	public void setUserLogs(Set<IUserLog> userLogs);

	public void setUsLastName(String usLastName);

	public void setUsSex(String usSex);

	public void setUsUserId(String usUserId);

	public void setUsUserName(String usUserName);

	public void setUsUserPwd(String usUserPwd);

	public void setUsUserPwdModDate(Timestamp usUserPwdModDate);

	public void setUsUserPwdModFlag(Boolean usUserPwdModFlag);

	public void setUsUserStatus(String usUserStatus);

	public void setUsUsersType(String usUsersType);

}