package org.hbs.disys.util;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonValidator {
	public static boolean isArrayFirstNotNull(Object[] objArr) {
		return isNotNullNotEmpty(objArr) && objArr.length > 0
				&& isNotNullNotEmpty(objArr[0]);
	}

	public static boolean isCollectionFirstNotEmpty(Collection<?> collectionObj) {
		return isNotNullNotEmpty(collectionObj)
				&& isNotNullNotEmpty(collectionObj.iterator().next());
	}

	public static boolean isEqual(EnumInterface strObject, EnumInterface eqParam) {
		return isNotNullNotEmpty(strObject) && isNotNullNotEmpty(eqParam)
				&& strObject.name().equalsIgnoreCase(eqParam.name());
	}

	public static boolean isEqual(String strObject, EnumInterface eqParam) {
		return isNotNullNotEmpty(strObject) && isNotNullNotEmpty(eqParam)
				&& strObject.equalsIgnoreCase(eqParam.name());
	}

	public static boolean isEqual(String strObject, String eqParam) {
		return isNotNullNotEmpty(strObject) && isNotNullNotEmpty(eqParam)
				&& strObject.equalsIgnoreCase(eqParam);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isListFirstNotEmpty(List list) {
		return isNotNullNotEmpty(list)
				&& isNotNullNotEmpty(list.iterator().next());
	}

	public static boolean isNotEqual(EnumInterface strObject,
			EnumInterface eqParam) {
		return isNotNullNotEmpty(strObject) && isNotNullNotEmpty(eqParam)
				&& strObject.name().equalsIgnoreCase(eqParam.name()) == false;
	}

	public static boolean isNotEqual(String strObject, EnumInterface eqParam) {
		return isNotNullNotEmpty(strObject) && isNotNullNotEmpty(eqParam)
				&& strObject.equalsIgnoreCase(eqParam.name()) == false;
	}

	public static boolean isNotEqual(String strObject, String eqParam) {
		return isNotNullNotEmpty(strObject) && isNotNullNotEmpty(eqParam)
				&& strObject.equalsIgnoreCase(eqParam) == false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotNullNotEmpty(Object object) {
		if (object instanceof String) {
			String objStr = (String) object;
			return objStr != null && objStr.trim().equals("") == false;
		} else if (object instanceof String[]) {
			String[] objStr = (String[]) object;

			for (String stArr : objStr) {
				if (isNotNullNotEmpty(stArr) == false) {
					return false;
				}
			}
			return objStr.length > 0;
		} else if (object instanceof Integer || object instanceof Long
				|| object instanceof Double) {
			return isNotNullNotEmpty(String.valueOf(object));
		} else if (object instanceof List) {
			List objList = (List) object;
			return objList != null && objList.isEmpty() == false;
		} else if (object instanceof Set) {
			Set objSet = (Set) object;
			return objSet != null && objSet.isEmpty() == false;
		} else if (object instanceof Map) {
			Map objMap = (Map) object;
			return objMap != null && objMap.isEmpty() == false;
		} else if (object instanceof Collection<?>) {
			Collection<?> objSet = (Collection<?>) object;
			return objSet != null && objSet.isEmpty() == false;
		} else if (object instanceof Hashtable<?, ?>) {
			Hashtable<?, ?> objHTab = (Hashtable<?, ?>) object;
			return objHTab != null && objHTab.isEmpty() == false;
		} else {
			return object != null;
		}
	}

	public static boolean isNotNullNotEmpty(Object object, Object nestedObject) {
		if (isNotNullNotEmpty(object)) {
			return isNotNullNotEmpty(nestedObject);
		}
		return false;
	}

	public static boolean isNotNullNotEmpty(Object object, Object nestedObject,
			Object nestedObjectNestedObject) {
		if (isNotNullNotEmpty(object)) {
			if (isNotNullNotEmpty(nestedObject)) {
				return isNotNullNotEmpty(nestedObjectNestedObject);
			}
		}
		return false;
	}

	public static boolean isNullOrEmpty(Object object) {
		return isNotNullNotEmpty(object) == false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isSetFirstNotEmpty(Set set) {
		return isNotNullNotEmpty(set)
				&& isNotNullNotEmpty(set.iterator().next());
	}
}
