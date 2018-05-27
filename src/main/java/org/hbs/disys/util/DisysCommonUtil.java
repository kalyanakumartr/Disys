package org.hbs.disys.util;

import java.util.ArrayList;
import java.util.List;

public class DisysCommonUtil extends CommonUtil implements IConstProperty {

	private static final long serialVersionUID = 1L;

	public static List<LabelValueBean> getLabelValueBeanList(
			List<String> objectList, String filedName) {

		List<LabelValueBean> lableValueList = new ArrayList<LabelValueBean>(0);

		if (CommonValidator.isNotNullNotEmpty(filedName)) {
			lableValueList.add(new LabelValueBean("", SELECT.trim() + SPACE
					+ filedName));
		}
		for (String strLable : objectList) {
			lableValueList.add(new LabelValueBean(strLable, strLable));
		}
		return lableValueList;

	}

}
