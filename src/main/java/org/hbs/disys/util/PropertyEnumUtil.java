package org.hbs.disys.util;

public interface PropertyEnumUtil {

	public enum EEltType implements EnumInterface {
		BOOLEAN, COMBO, CURRENCY, DATE, DATETIME, EMAIL, HIDDEN, NUMBER, NUMERIC, PERIOD, TEXT, IMAGE, SESSION_USER;
	}

	public enum EGeneral implements EnumInterface {
		Base_Time_Zone, Display_Time_Zone, Authentication_Type, Users_Reminder_Queries_Questions
	}

	public enum EWrap implements EnumInterface {
		Brace("()"), Percent("%"), Quote("'"), QuotePercent("");

		private String eWrap;

		private EWrap(String eWrap) {
			this.eWrap = eWrap;
		}

		public String enclose(Object data) {
			if (data != null) {
				String encData = String.valueOf(data);
				if (eWrap.equals("")) {
					return Quote.eWrap + Percent.eWrap + encData.trim()
							+ Percent.eWrap + Quote.eWrap;
				} else if (eWrap.equals("()")) {
					return "(" + encData.trim() + ")";
				} else {
					return eWrap + encData.trim() + eWrap;
				}
			} else if (eWrap.equals("")) {
				return Quote.eWrap + Percent.eWrap + Percent.eWrap
						+ Quote.eWrap;
			}

			return "";
		}

		public String enclose(Object[] dataArr) {
			String dataQt = "";
			if (CommonValidator.isNotNullNotEmpty(dataArr)) {
				if (dataArr[0] instanceof String
						|| dataArr[0] instanceof Integer
						|| dataArr[0] instanceof Long
						|| dataArr[0] instanceof Float
						|| dataArr[0] instanceof Double
						|| dataArr[0] instanceof Boolean) {
					for (Object datum : dataArr) {
						dataQt += enclose(datum) + ", ";
					}
					if (dataQt.indexOf(", ") > 0) {
						dataQt = dataQt.substring(0, dataQt.lastIndexOf(", "));
					}
				}
			}
			return dataQt;
		}
	}

}
