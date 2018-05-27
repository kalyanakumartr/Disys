package org.hbs.disys.util;

import java.util.LinkedHashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.hbs.disys.util.model.LabelValueBean;

public interface IParam {

	public enum ENamed implements EnumInterface {
		Like(" Like :"), EqualTo(" = :"), NotEqualTo(" <> :"), GreaterThan(
				" > :"), LessThan(" < :"), GreaterThanEqualTo(" >= :"), LessThanEqualTo(
				" <= :"), Between(" Between "), Is_Null(""), In(" In "), NotIn(
				" Not In "), OrderBy(" Order By ");

		public static String create(String param) {
			return param.replaceAll("\\.", "").trim();
		}

		public static void proc(IParam param, String condition, Object value) {
			param.getSearchValueMap().put(condition, value);
		}

		public static void remove(IParam param, String key) {
			param.getSearchCondtionMap().remove(key);
			param.getSearchValueMap().remove(key);
		}

		private String eWrap;

		private ENamed(String eWrap) {
			this.eWrap = eWrap;
		}

		private LabelValueBean create(IParam param, String property) {
			String key = property;

			while (param.getSearchCondtionMap().containsKey(key)) {
				key = key + new Random().nextInt(10) + 1;
			}

			return new LabelValueBean(create(key), key);
		}

		public String param(String property) {
			String condition = paramCreator(property);
			if (CommonValidator.isNotNullNotEmpty(condition)) {
				if (condition.indexOf(ENamed.OrderBy.paramCode()) >= 0)
					return condition;
				return condition;
			}
			return "";
		}

		public void param_AND(IParam param, String condition, Object value) {
			param_AND(param, condition, value, null);
		}

		public void param_AND(IParam param, String condition, Object value,
				IWrap iWrap) {
			updateConditionValueMap(param, condition, value, IWrap.AND, iWrap);
		}

		public String param_AND(String property) {
			String condition = paramCreator(property);
			if (CommonValidator.isNotNullNotEmpty(condition)) {
				if (condition.indexOf(ENamed.OrderBy.paramCode()) >= 0)
					return condition;
				return IWrap.AND.get() + condition;
			}
			return "";
		}

		public void param_OR(IParam param, String condition, Object value) {
			// param_AND(param, condition, value, null);
			param_OR(param, condition, value, null);
		}

		public void param_OR(IParam param, String condition, Object value,
				IWrap iWrap) {
			updateConditionValueMap(param, condition, value, IWrap.OR, iWrap);
		}

		public String param_OR(String property) {
			String condition = paramCreator(property);
			if (CommonValidator.isNotNullNotEmpty(condition)) {
				if (condition.indexOf(ENamed.OrderBy.paramCode()) >= 0)
					return condition;
				return IWrap.OR.get() + condition;
			}
			return "";
		}

		public String paramCode() {
			return eWrap;
		}

		private LabelValueBean paramCreator(IParam param, String property) {
			if (CommonValidator.isNotNullNotEmpty(property)) {
				LabelValueBean lbBean = create(param, property);

				if (eWrap.equals(Between.paramCode())) {
					String conQuery = property + eWrap + ":From_"
							+ lbBean.getValue() + " AND :To_"
							+ lbBean.getValue();
					return new LabelValueBean(conQuery, lbBean.getLabel());
				} else if (eWrap.equals(OrderBy.paramCode())) {
					return new LabelValueBean(eWrap + property, property);
				} else if (eWrap.equals(In.paramCode())
						|| eWrap.equals(NotIn.paramCode())) {
					String conQuery = property + eWrap + "( :"
							+ lbBean.getValue() + " )";
					return new LabelValueBean(conQuery, lbBean.getLabel());
				} else {
					return new LabelValueBean(property + eWrap
							+ lbBean.getValue(), lbBean.getLabel());
				}
			}
			return null;
		}

		private String paramCreator(String property) {
			if (CommonValidator.isNotNullNotEmpty(property)) {
				String encData = create(property);
				if (eWrap.equals(Between.paramCode())) {
					return property + eWrap + ":From_" + encData + " AND :To_"
							+ encData;
				} else if (eWrap.equals(OrderBy.paramCode())) {
					return eWrap + property;
				} else if (eWrap.equals(In.paramCode())
						|| eWrap.trim().equals(NotIn.paramCode())) {
					return property + eWrap + "( :" + encData + " )";
				} else {
					return property + eWrap + encData.trim();
				}
			}
			return "";
		}

		private void updateConditionValueMap(IParam param, String property,
				Object value, IWrap _AndOr, IWrap iWrap) {
			LabelValueBean lbParam = paramCreator(param, property);
			if (CommonValidator.isNotNullNotEmpty(lbParam)) {
				if (CommonValidator.isNotNullNotEmpty(iWrap)) {
					switch (iWrap) {
					case ST_BRACE1:
					case ST_BRACE2:
					case ST_BRACE3:
					case ST_BRACE4:
					case ST_BRACE5: {
						String qCondition = _AndOr.get() + iWrap.get()
								+ lbParam.getValue();
						param.getSearchCondtionMap().put(lbParam.getLabel(),
								qCondition);
						param.getSearchValueMap()
								.put(lbParam.getLabel(), value);
						break;
					}
					case ED_BRACE1:
					case ED_BRACE2:
					case ED_BRACE3:
					case ED_BRACE4:
					case ED_BRACE5: {
						String qCondition = _AndOr.get() + lbParam.getValue()
								+ iWrap.get();
						param.getSearchCondtionMap().put(lbParam.getLabel(),
								qCondition);
						param.getSearchValueMap()
								.put(lbParam.getLabel(), value);
						break;
					}
					default:
						break;
					}
				} else if (lbParam.getValue().indexOf(
						ENamed.OrderBy.paramCode()) >= 0) {
					param.getSearchCondtionMap().put(lbParam.getLabel(),
							lbParam.getValue());
				} else {
					String qCondition = _AndOr.get() + lbParam.getValue();
					param.getSearchCondtionMap().put(lbParam.getLabel(),
							qCondition);
					param.getSearchValueMap().put(lbParam.getLabel(), value);
				}
			}
		}
	}

	public enum ESession implements EnumInterface {
		MaMenuHTML("MaMenuHTML"), UserObject("UserObject"), EmployeeId(
				"EmployeeId");

		private String eSessAttr;

		private ESession(String eSessAttr) {
			this.eSessAttr = eSessAttr;
		}

		public String getAttribute() {
			return eSessAttr;
		}

		public Object getAttribute(HttpServletRequest request) {
			return request.getAttribute(eSessAttr);
		}

	}

	public enum IWrap implements EnumInterface {
		AND(" AND "), OR(" OR "), ST_BRACE1(" ( "), ED_BRACE1(" ) "), ST_BRACE2(
				" (( "), ED_BRACE2(" )) "), ST_BRACE3(" ((( "), ED_BRACE3(
				" ))) "), ST_BRACE4(" (((( "), ED_BRACE4(" )))) "), ST_BRACE5(
				" ((((( "), ED_BRACE5(" ))))) ");

		private String append;

		IWrap(String append) {
			this.append = append;
		}

		public String get() {
			return append;
		}

	}

	public EnumInterface get_AddEntityBean();

	public String get_ProcedureName();

	public void getParamMapFromRequest(HttpServletRequest request);

	public HttpServletRequest getRequest();

	public LinkedHashMap<String, Object> getRequestParamMap();

	public LinkedHashMap<String, Object> getSearchCondtionMap();

	public LinkedHashMap<String, Object> getSearchValueMap();

	public LinkedHashMap<String, Object> getSessionFilterValueMap();

	public void set_AddEntityBean(EnumInterface _AddEntityBean);

	public void set_ProcedureName(String _ProcedureName);

	public void setRequest(HttpServletRequest request);

	public void setRequestParamMap(LinkedHashMap<String, Object> requestParamMap);

	public void setSearchCondtionMap(
			LinkedHashMap<String, Object> searchCondtionMap);

	public void setSearchValueMap(LinkedHashMap<String, Object> searchValueMap);

	public void setSessionFilterValueMap(
			LinkedHashMap<String, Object> sessionFilterValueMap);

}
