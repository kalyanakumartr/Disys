package org.hbs.disys.util;

import java.io.Serializable;

public class LabelValueBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String value;
	private String label;

	public LabelValueBean(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
