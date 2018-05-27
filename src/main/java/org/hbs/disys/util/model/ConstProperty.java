package org.hbs.disys.util.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "constproperty")
public class ConstProperty implements java.io.Serializable
{
	private static final long	serialVersionUID	= -8065112006200733126L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "constId")
	private int					constId;

	@Column(name = "constKey")
	private String				constKey;

	@Column(name = "constValue")
	private String				constValue;

	@Column(name = "constGroup")
	private String				constGroup;

	@Column(name = "constActive")
	private boolean				constActive;

	@Column(name = "constAllowedValue")
	private String				constAllowedValue;

	@Column(name = "enumKey")
	private String				enumKey;

	public String getConstAllowedValue()
	{
		return constAllowedValue;
	}

	public String getConstGroup()
	{
		return constGroup;
	}

	public int getConstId()
	{
		return constId;
	}

	public String getConstKey()
	{
		return constKey;
	}

	public String getConstValue()
	{
		return constValue;
	}

	public String getEnumKey()
	{
		return enumKey;
	}

	public boolean isConstActive()
	{
		return constActive;
	}

	public void setConstActive(boolean constActive)
	{
		this.constActive = constActive;
	}

	public void setConstAllowedValue(String constAllowedValue)
	{
		this.constAllowedValue = constAllowedValue;
	}

	public void setConstGroup(String constGroup)
	{
		this.constGroup = constGroup;
	}

	public void setConstId(int constId)
	{
		this.constId = constId;
	}

	public void setConstKey(String constKey)
	{
		this.constKey = constKey;
	}

	public void setConstValue(String constValue)
	{
		this.constValue = constValue;
	}

	public void setEnumKey(String enumKey)
	{
		this.enumKey = enumKey;
	}
}
