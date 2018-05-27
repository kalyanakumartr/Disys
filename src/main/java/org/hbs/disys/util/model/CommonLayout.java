package org.hbs.disys.util.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public abstract class CommonLayout implements ICommonLayout
{
	protected static final long	serialVersionUID	= 4330030866270635106L;

	@ManyToOne
	protected ILayoutElements	layoutElements;

	@Column(name = "displayOrder")
	protected int				displayOrder;

	@Column(name = "displayName")
	protected String			displayName;

	@Column(name = "displayWidth")
	protected int				displayWidth;

	@Column(name = "orderable")
	protected boolean			orderable			= true;

	@Column(name = "visible")
	protected boolean			visible				= true;

	@Column(name = "searchable")
	protected boolean			searchable			= true;

	@Column(name = "render")
	protected String			render				= null;

	@Column(name = "cssClassName")
	protected String			cssClassName;

	@Column(name = "defaultContent")
	protected String			defaultContent;

	public String getCssClassName()
	{
		return cssClassName;
	}

	public String getDefaultContent()
	{
		return defaultContent;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public int getDisplayOrder()
	{
		return displayOrder;
	}

	public int getDisplayWidth()
	{
		return displayWidth;
	}

	public ILayoutElements getLayoutElements()
	{
		return layoutElements;
	}

	public String getRender()
	{
		return render;
	}

	public boolean isOrderable()
	{
		return orderable;
	}

	public boolean isSearchable()
	{
		return searchable;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setCssClassName(String cssClassName)
	{
		this.cssClassName = cssClassName;
	}

	public void setDefaultContent(String defaultContent)
	{
		this.defaultContent = defaultContent;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public void setDisplayOrder(int displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public void setDisplayWidth(int displayWidth)
	{
		this.displayWidth = displayWidth;
	}

	public void setLayoutElements(ILayoutElements layoutElements)
	{
		this.layoutElements = layoutElements;
	}

	public void setOrderable(boolean orderable)
	{
		this.orderable = orderable;
	}

	public void setRender(String render)
	{
		this.render = render;
	}

	public void setSearchable(boolean searchable)
	{
		this.searchable = searchable;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

}
