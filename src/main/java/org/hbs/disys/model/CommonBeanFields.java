package org.hbs.disys.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hbs.disys.util.CommonValidator;

@MappedSuperclass
public abstract class CommonBeanFields implements ICommonBeanFields
{
	private static final long	serialVersionUID	= -4784531727752023870L;

	protected IUsers			createdUser;

	protected Timestamp			createdDate;

	protected IUsers			modifiedUser;

	protected Timestamp			modifiedDate;

	protected Boolean			status				= true;

	public CommonBeanFields()
	{
		super();
	}

	public CommonBeanFields(IUsers createdUser, Timestamp createdDate, IUsers modifiedUser, Timestamp modifiedDate, Boolean status)
	{
		super();
		this.createdUser = createdUser;
		this.createdDate = createdDate;
		this.modifiedUser = modifiedUser;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}

	@Column(name = "createdDate")
	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	@ManyToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "createdBy")
	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	@Column(name = "modifiedDate")
	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	@ManyToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "modifiedBy")
	public IUsers getModifiedUser()
	{
		return modifiedUser;
	}

	@Column(name = "status")
	public Boolean getStatus()
	{
		return status;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setCreatedUser(IUsers createdUser)
	{
		/*
		 * if (CommonValidator.isNullOrEmpty(this.createdUser)) { setCreatedDate(new Timestamp(new
		 * Date().getTime())); }
		 */
		this.createdUser = createdUser;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public void setModifiedUser(IUsers modifiedUser)
	{
		this.modifiedUser = modifiedUser;
		if (CommonValidator.isNotNullNotEmpty(this.modifiedUser))
		{
			setModifiedDate(new Timestamp(new Date().getTime()));
		}

	}

	public void setStatus(Boolean status)
	{
		this.status = status;
	}

}
