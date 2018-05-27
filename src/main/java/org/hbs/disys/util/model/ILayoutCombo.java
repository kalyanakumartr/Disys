package org.hbs.disys.util.model;

import java.io.Serializable;

public interface ILayoutCombo extends Serializable {

	public Integer getAutoId();

	public String getComboBean();

	public String getComboKey();

	public String getComboText();

	public String getComboWhereClause();

	public ILayoutElements getLayoutElements();

	public void setAutoId(Integer autoId);

	public void setComboBean(String comboBean);

	public void setComboKey(String comboKey);

	public void setComboText(String comboText);

	public void setComboWhereClause(String comboWhereClause);

	public void setLayoutElements(ILayoutElements layoutElements);

}