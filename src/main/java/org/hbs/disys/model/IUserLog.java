package org.hbs.disys.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * UserLog entity. @author MyEclipse Persistence Tools
 */

public interface IUserLog extends Serializable
{
	public Integer getUlAutoId();

	public String getUlIpaddress();

	public Timestamp getUlUserLoginTime();

	public Timestamp getUlUserLogoutTime();

	public IUsers getUsers();

	public boolean isUlFetchBlock();

	public void setUlAutoId(int ulUserLogAutoId);

	public void setUlFetchBlock(boolean ulFetchBlock);

	public void setUlIpaddress(String ulIpaddress);

	public void setUlUserLoginTime(Timestamp ulUserLoginTime);

	public void setUlUserLogoutTime(Timestamp ulUserLogoutTime);

	public void setUsers(IUsers users);

}