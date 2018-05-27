package org.hbs.disys.util.model;

import java.io.Serializable;

public interface ICommonLayout extends Serializable
{
	public abstract String getCssClassName();

	public String getDefaultContent();

	public abstract String getDisplayName();

	public abstract int getDisplayOrder();

	public abstract int getDisplayWidth();

	public abstract ILayoutElements getLayoutElements();

	public abstract String getRender();

	public abstract boolean isOrderable();

	public abstract boolean isSearchable();

	public abstract boolean isVisible();

	public void setCssClassName(String saCssClassName);

	public void setDefaultContent(String defaultContent);

	public abstract void setDisplayName(String displayName);

	public abstract void setDisplayOrder(int displayOrder);

	public abstract void setDisplayWidth(int displayWidth);

	public abstract void setLayoutElements(ILayoutElements layoutElements);

	public abstract void setOrderable(boolean orderable);

	public abstract void setRender(String render);

	public abstract void setSearchable(boolean searchable);

	public abstract void setVisible(boolean visible);
}