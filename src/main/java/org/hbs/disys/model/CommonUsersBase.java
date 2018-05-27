package org.hbs.disys.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hbs.disys.util.CommonValidator;
import org.hibernate.annotations.Where;

@MappedSuperclass
public abstract class CommonUsersBase extends CommonBeanFields implements IUsers
{

	private static final long	serialVersionUID	= -7527216318945401365L;

	protected String			usEmployeeId;

	protected String			usUserId;

	protected String			usUserName;

	protected String			usLastName;

	protected String			usUserPwd;

	protected Boolean			usUserPwdModFlag;

	protected Timestamp			usUserPwdModDate;

	protected String			usDob;

	protected String			usSex;

	protected String			usUsersType;

	protected String			usUserStatus;

	protected Set<IUserLog>		userLogs			= new LinkedHashSet<IUserLog>(0);

	public CommonUsersBase()
	{
		super();
	}

	@Override
	public IUserLog _getLastLoginInformation()
	{
		if (CommonValidator.isSetFirstNotEmpty(userLogs))
		{
			return userLogs.iterator().next();
		}
		return null;
	}

	@Column(name = "usDob")
	public String getUsDob()
	{
		return usDob;
	}

	@Id
	@Column(name = "usEmployeeId")
	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	/*
	 * @OneToMany(targetEntity = UserLog.class, mappedBy = "users")
	 * @Where(clause = "ulFetchBlock = false")
	 * @OrderBy("ulUserLoginTime DESC") public Set<IUserLog> getUserLogs() { return userLogs; }
	 */

	@OneToMany(targetEntity = UserLog.class, fetch = FetchType.EAGER, mappedBy = "users")
	@Where(clause = "ulFetchBlock = true")
	@OrderBy("ulUserLoginTime DESC")
	public Set<IUserLog> getUserLogs()
	{
		return userLogs;
	}

	@Column(name = "usLastName")
	public String getUsLastName()
	{
		return usLastName;
	}

	@Column(name = "usSex")
	public String getUsSex()
	{
		return usSex;
	}

	@Column(name = "usUserId")
	public String getUsUserId()
	{
		return usUserId;
	}

	@Column(name = "usUserName")
	public String getUsUserName()
	{
		return usUserName;
	}

	@Column(name = "usUserPwd")
	public String getUsUserPwd()
	{
		return usUserPwd;
	}

	@Column(name = "usUserPwdModDate")
	public Timestamp getUsUserPwdModDate()
	{
		return usUserPwdModDate;
	}

	@Column(name = "usUserPwdModFlag")
	public Boolean getUsUserPwdModFlag()
	{
		return usUserPwdModFlag;
	}

	@Column(name = "usUserStatus")
	public String getUsUserStatus()
	{
		return usUserStatus;
	}

	@Column(name = "usUsersType")
	public String getUsUsersType()
	{
		return usUsersType;
	}

	public void setUsDob(String usDob)
	{
		this.usDob = usDob;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUserLogs(Set<IUserLog> userLogs)
	{
		this.userLogs = userLogs;
	}

	public void setUsLastName(String usLastName)
	{
		this.usLastName = usLastName;
	}

	public void setUsSex(String usSex)
	{
		this.usSex = usSex;
	}

	public void setUsUserId(String usUserId)
	{
		this.usUserId = usUserId;
	}

	public void setUsUserName(String usUserName)
	{
		this.usUserName = usUserName;
	}

	public void setUsUserPwd(String usUserPwd)
	{
		this.usUserPwd = usUserPwd;
	}

	public void setUsUserPwdModDate(Timestamp usUserPwdModDate)
	{
		this.usUserPwdModDate = usUserPwdModDate;
	}

	public void setUsUserPwdModFlag(Boolean usUserPwdModFlag)
	{
		this.usUserPwdModFlag = usUserPwdModFlag;
	}

	public void setUsUserStatus(String usUserStatus)
	{
		this.usUserStatus = usUserStatus;
	}

	public void setUsUsersType(String usUsersType)
	{
		this.usUsersType = usUsersType;
	}

}