package org.hbs.disys.model;

import java.sql.Timestamp;

import org.hbs.disys.util.Param;

public class UserParam extends Param
{

	private static final long	serialVersionUID	= 2950016171013727276L;
	public String				usEmployeeIds;								// Comma_Separate_Can_Be_Used
	public String				status;
	public IUsers				user;
	public String				usUserIds;									// Comma_Separate_Can_Be_Used
	public String				usUserName;
	public String				userId;
	public String				password;
	public String				newPassword;
	public String				oldPassword;
	public String				confirmPassword;
	public String				action;
	public String				modifiedBy;
	public Timestamp			modifiedDate;
	public Timestamp			usUserPwdModDate;

	public UserParam()
	{

	}

	public UserParam(IUsers user)
	{
		this.user = user;
	}

	public UserParam(String usEmployeeIds)
	{
		this.usEmployeeIds = usEmployeeIds;

	}

	public UserParam(String usEmployeeIds, String status, String usUserName)
	{
		this.usEmployeeIds = usEmployeeIds;
		this.status = status;
		this.usUserName = usUserName;
	}
}
