package org.hbs.disys.util;

public enum CompetencyType {
	WORK("Work"), TECH("Tech"), LEAD("Lead");

	private String value;

	CompetencyType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
