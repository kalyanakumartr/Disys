package org.hbs.disys.util.model;

/**
 * @author Ananth Balasubramanian
 */
public interface ILayoutElements extends java.io.Serializable
{
	public String getIeBeanName();

	public String getIeColumnType();

	public String getIeComboSearchProperty();

	public String getIeDataType();

	public String getIeDisplayName();

	public String getIeDisplayProperty();

	public String getIeDisplayPropertyGetter();

	public String getIeElementId();

	public String getIeRender();

	public void setIeBeanName(String ieBeanName);

	public void setIeColumnType(String ieColumnType);

	public void setIeComboSearchProperty(String ieComboSearchProperty);

	public void setIeDataType(String ieDataType);

	public void setIeDisplayName(String ieDisplayName);

	public void setIeDisplayProperty(String ieDisplayProperty);

	public void setIeDisplayPropertyGetter(String ieDisplayPropertyGetter);

	public void setIeElementId(String ieElementId);

	public void setIeRender(String ieRender);
}
