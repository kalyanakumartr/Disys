package org.hbs.disys.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface IConstProperty extends Serializable {
	public enum EPage implements EnumInterface {
		Failure, Success
	}

	public enum ERestPath implements EnumInterface {
		resources, images, js, b5fb8c9453111565cf49a070c2fb81ca8d755155, f62b08bcd68a1b0a7af531f2ad7122bffb200a13, c85f98fce8bb61e7be3bea9d00c1b24dd36ebe7e, ec830f2deda896547836d9473dc910723174076a, b9162e50accd6d8ff5f2e50b6d1a76474f50a59c, a19efed796bf0b83b20173087109ed85e61fb445;

		public static String[] getSecureDynaPaths() {
			List<String> paths = new ArrayList<String>(
					ERestPath.values().length);
			for (ERestPath rest : ERestPath.values())
				paths.add("/" + rest + "/**");
			return paths.toArray(new String[paths.size()]);
		}
	}

	public String ALL = "all";
	public String ONE = "one";
	public String TWO = "two";
	public String THREE = "three";
	public String EMPTY = "";
	public String SLASH = "/";
	public String DBL_SLASH = "//";
	public String BACKSLASH = "\\";
	public String HASH = "#";
	public String SET = "set";
	public String GET = "get";
	public String IS = "is";
	public String ENABLED = "enabled";
	public String DISABLED = "disabled";
	public String YES = "yes";
	public String NO = "no";
	public String TRUE = "true";
	public String FALSE = "false";
	public String STATUS = "status";
	public String LOGINATTEMPT = "loginAttempt";
	public String LASTLOGINID = "lastLoginId";
	public String DEFAULT = "default";
	public String COMMA_SPACE = ", ";
	public String NOT_EQL_TO = " <> ";
	public String IS_NULL = " IS NULL ";
	public String ED_BRACE = " ) ";
	public String ST_BRACE = " ( ";
	public String OR = " OR ";
	public String EQUALTO = " = ";
	public String GRTNEQLTO = " >= ";
	public String LSTNEQLTO = " <= ";
	public String GRTN = " > ";
	public String LSTN = " < ";
	public String AND = " AND ";
	public String LIKE = " Like ";
	public String IN = " In ";
	public String BETWEEN = " Between ";
	public String FROM = " From ";
	public String SELECT_DISTINCT = " Select Distinct ";
	public String UPDATE = " Update ";
	public String SPACE = " ";
	public String DOT = ".";
	public String WHERE_1_1 = " Where 1=1 ";
	public String HYPEN = "-";
	public String UTF_8 = "UTF-8";
	public String SHA = "SHA";
	public String UNDERSCORE = "_";
	public String ISO_ENCODE = "ISO-8859-1";
	public String HTTP = "http";
	public String COLON = ":";
	public String PLUS = "+";
	public String CONTENT = "/content";
	public String DAYS = "Days";
	public String HOURS = "Hours";
	public String MINUTES = "Minutes";
	public String SECONDS = "Seconds";
	public String IST = "IST";
	public String ORDER_BY = " Order By ";
	public String ASC = " ASC ";
	public String DESC = " DESC ";
	public String QUESTION_MARK = "?";
	public String AMPERSAND = "&";
	public String AT_SIGN = "@";
	public String BYTES = " bytes ";
	public String KILO_BYTE = " KB ";
	public String MEGA_BYTE = " MB ";
	public String GIGA_BYTE = " GB ";
	public String SELECT = " Select ";
	public String DATE_FORMAT_DD_MM_YYYY_HH_MM = "dd-MM-yyyy HH:mm";
	public String DATE_FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";
	public String DATE_FORMAT_DD_MMM_YYYY_SPACE = "dd MMM yyyy";

	public String DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS_AM_PM = "dd-MMM-yyyy hh:mm:ss a";
	public String DATE_FORMAT_DD_MMM_YYYY_HH_MM_AM_PM = "dd-MMM-yyyy HH:mm a";
	public String DATE_FORMAT_MM_DD_YYYY = "MM-dd-yyyy";
	public String DATE_FORMAT_MM_DD_YYYY_HH_MM = "MM-dd-yyyy HH:mm";
	public String DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_AM_PM = "MM-dd-yyyy hh:mm:ss a";
	public String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd hh:mm";
	public String DATE_FORMAT_YYYY_MM_DD_HH_MM_24 = "yyyy-MM-dd HH:mm";
	public String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
	public String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24 = "yyyy-MM-dd HH:mm:ss";
	public String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd hh:mm:ss.SSS";
	public String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_24 = "yyyy-MM-dd HH:mm:ss.SSS";
	public String DATE_FORMAT_YYYY_MMM_DD = "yyyy-MMM-dd";
	public String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

	public String DATE_FORMAT_DD_MM_YYYY_1 = "dd-MM-YYYY";

	public String DATE_FORMAT_DD_MMMM_YYYY_HH_MM_AM_PM = "dd MMMM yyyy - hh:mm a";

}
