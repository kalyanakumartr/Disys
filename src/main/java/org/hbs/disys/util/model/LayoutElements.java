package org.hbs.disys.util.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "layoutelements")
public class LayoutElements implements ILayoutElements
{
	private static final long	serialVersionUID	= -8827883952227378070L;

	private String				ieElementId;

	private String				ieColumnType;
	private String				ieDataType;
	private String				ieBeanName;
	private String				ieDisplayName;
	private String				ieDisplayProperty;
	private String				ieComboSearchProperty;
	private String				ieDisplayPropertyGetter;
	private String				ieRender;

	public LayoutElements()
	{
		super();
	}

	@Column(name = "ieBeanName")
	public String getIeBeanName()
	{
		return ieBeanName;
	}

	@Column(name = "ieColumnType")
	public String getIeColumnType()
	{
		return ieColumnType;
	}

	@Column(name = "ieComboSearchProperty")
	public String getIeComboSearchProperty()
	{
		return ieComboSearchProperty;
	}

	@Column(name = "ieDataType")
	public String getIeDataType()
	{
		return ieDataType;
	}

	@Column(name = "ieDisplayName")
	public String getIeDisplayName()
	{
		return ieDisplayName;
	}

	@Column(name = "ieDisplayProperty")
	public String getIeDisplayProperty()
	{
		return ieDisplayProperty;
	}

	@Column(name = "ieDisplayPropertyGetter")
	public String getIeDisplayPropertyGetter()
	{
		return ieDisplayPropertyGetter;
	}

	@Id
	@GeneratedValue
	@Column(name = "ieElementId")
	public String getIeElementId()
	{
		return ieElementId;
	}

	@Column(name = "ieRender")
	public String getIeRender()
	{
		return ieRender;
	}

	public void setIeBeanName(String ieBeanName)
	{
		this.ieBeanName = ieBeanName;
	}

	public void setIeColumnType(String ieColumnType)
	{
		this.ieColumnType = ieColumnType;
	}

	public void setIeComboSearchProperty(String ieComboSearchProperty)
	{
		this.ieComboSearchProperty = ieComboSearchProperty;
	}

	public void setIeDataType(String ieDataType)
	{
		this.ieDataType = ieDataType;
	}

	public void setIeDisplayName(String ieDisplayName)
	{
		this.ieDisplayName = ieDisplayName;
	}

	public void setIeDisplayProperty(String ieDisplayProperty)
	{
		this.ieDisplayProperty = ieDisplayProperty;
	}

	public void setIeDisplayPropertyGetter(String ieDisplayPropertyGetter)
	{
		this.ieDisplayPropertyGetter = ieDisplayPropertyGetter;
	}

	public void setIeElementId(String ieElementId)
	{
		this.ieElementId = ieElementId;
	}

	public void setIeRender(String ieRender)
	{
		this.ieRender = ieRender;
	}
}
