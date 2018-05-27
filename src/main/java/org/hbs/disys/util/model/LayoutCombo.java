package org.hbs.disys.util.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class LayoutCombo implements ILayoutCombo {
	private static final long serialVersionUID = -930219662136129004L;

	@Id
	@GeneratedValue
	@Column(name = "autoId")
	private Integer autoId;

	private ILayoutElements layoutElements;

	@Column(name = "comboBean")
	private String comboBean;
	@Column(name = "comboKey")
	private String comboKey;
	@Column(name = "comboText")
	private String comboText;
	@Column(name = "comboWhereClause")
	private String comboWhereClause;

	public LayoutCombo() {
	}

	public LayoutCombo(ILayoutElements layoutElements) {
		this.layoutElements = layoutElements;
	}

	public Integer getAutoId() {
		return autoId;
	}

	public String getComboBean() {
		return comboBean;
	}

	public String getComboKey() {
		return comboKey;
	}

	public String getComboText() {
		return comboText;
	}

	public String getComboWhereClause() {
		return comboWhereClause;
	}

	public ILayoutElements getLayoutElements() {
		return layoutElements;
	}

	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}

	public void setComboBean(String comboBean) {
		this.comboBean = comboBean;
	}

	public void setComboKey(String comboKey) {
		this.comboKey = comboKey;
	}

	public void setComboText(String comboText) {
		this.comboText = comboText;
	}

	public void setComboWhereClause(String comboWhereClause) {
		this.comboWhereClause = comboWhereClause;
	}

	public void setLayoutElements(ILayoutElements layoutElements) {
		this.layoutElements = layoutElements;
	}

}