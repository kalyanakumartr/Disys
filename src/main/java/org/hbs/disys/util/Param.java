package org.hbs.disys.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hbs.disys.util.PropertyEnumUtil.EEltType;
import org.hbs.disys.util.PropertyEnumUtil.EWrap;
import org.hbs.disys.util.model.ICommonLayout;
import org.hbs.disys.util.model.ILayoutElements;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Param implements IParam, IConstProperty
{
	private static final long				serialVersionUID		= -7820466140069794475L;
	public String							multiBeanId				= "PRD00001";
	public HttpServletRequest				request;
	public HttpServletResponse				response;
	public String							_ProcedureName			= "";
	public String							_OrderBy				= "";
	public EnumInterface					_AddEntityBean;
	public long								dataListCount			= 0L;
	public List<?>							dataList				= new ArrayList<>(0);
	public LinkedHashMap<String, Object>	requestParamMap			= new LinkedHashMap<String, Object>(0);
	public LinkedHashMap<String, Object>	sessionFilterValueMap	= new LinkedHashMap<String, Object>(0);
	public LinkedHashMap<String, Object>	searchValueMap			= new LinkedHashMap<String, Object>(0);
	public LinkedHashMap<String, Object>	searchCondtionMap		= new LinkedHashMap<String, Object>(0);

	public EnumInterface get_AddEntityBean()
	{
		return _AddEntityBean;
	}

	public String get_OrderBy()
	{
		return _OrderBy;
	}

	public String get_ProcedureName()
	{
		return _ProcedureName;
	}

	public List<?> getDataList()
	{
		return dataList;
	}

	public long getDataListCount()
	{
		return dataListCount;
	}

	public List<ILayoutElements> getLayoutElements(List<? extends ICommonLayout> iCLList)
	{
		List<ILayoutElements> eltList = new ArrayList<ILayoutElements>(iCLList.size());
		for (ICommonLayout iCL : iCLList)
		{
			eltList.add(iCL.getLayoutElements());
		}
		return eltList;
	}

	public void getMapOnConditionParams(List<? extends ILayoutElements> ieList) throws ClassNotFoundException
	{
		searchCondtionMap = new LinkedHashMap<String, Object>();
		for (ILayoutElements elt : ieList)
		{

			if (CommonValidator.isEqual(elt.getIeColumnType().toUpperCase(), EEltType.SESSION_USER.name()))
			{
				Object sessionUserEmployeeId = request.getAttribute(ESession.EmployeeId.getAttribute());
				if (CommonValidator.isNotNullNotEmpty(sessionUserEmployeeId))
				{
					ENamed.EqualTo.param_AND(this, elt.getIeDisplayProperty(), sessionUserEmployeeId);
				}
				continue;
			}
			String propertyKey = elt.getIeComboSearchProperty();
			if (CommonValidator.isNotNullNotEmpty(propertyKey) == false)
			{
				propertyKey = elt.getIeDisplayProperty();
			}
			Object object = searchValueMap.get(propertyKey);

			if (CommonValidator.isNotNullNotEmpty(object) && (object instanceof String || object instanceof ArrayList))
			{
				switch ( elt.getIeDataType() )
				{
					case "String" :
					{
						object = (String) object;
						break;
					}
					case "Integer" :
					{
						object = Integer.parseInt((String) object);
						break;
					}
					case "Long" :
					{
						object = Long.parseLong((String) object);
						break;
					}
					case "Float" :
					{
						object = Float.parseFloat((String) object);
						break;
					}
					case "Double" :
					{
						object = Double.parseDouble((String) object);
						break;
					}
					case "Boolean" :
					{
						object = (boolean) object;
						break;
					}
					default :
						break;
				}

				switch ( EEltType.valueOf(elt.getIeColumnType().toUpperCase()) )
				{
					case TEXT :
					{
						if (object instanceof String)
							ENamed.Like.param_AND(this, propertyKey, EWrap.Percent.enclose(object));
						else
							ENamed.Like.param_AND(this, propertyKey, object);
						break;
					}
					case EMAIL :
					{
						ENamed.Like.param_AND(this, propertyKey, EWrap.Percent.enclose(object));
						break;
					}
					case COMBO :
					case NUMBER :
					case CURRENCY :
					case DATE :
					case DATETIME :
					case BOOLEAN :
					{
						ENamed.EqualTo.param_AND(this, propertyKey, object);
						break;
					}
					case PERIOD :
					{
						if (CommonValidator.isListFirstNotEmpty((List<?>) object))
						{
							List<Date> dateList = new ArrayList<Date>(2);
							for (Object ob : (List<?>) object)
							{
								dateList.add(CommonUtil.getDateInFormat((String) ob, DATE_FORMAT_MM_DD_YYYY));
							}
							ENamed.Between.param_AND(this, propertyKey, dateList);
						}
						break;
					}

					default :
						break;
				}
			}
			else
			{
				searchValueMap.remove(propertyKey);
			}

		}
	}

	public void getParamMapFromRequest(HttpServletRequest request)
	{
		this.request = request;

		String param = request.getParameter("_p");

		if (CommonValidator.isNotNullNotEmpty(param))
		{
			String paramJson = new String(Base64.decodeBase64(param));

			ObjectMapper mapper = new ObjectMapper();
			try
			{
				this.requestParamMap = mapper.readValue(paramJson, new TypeReference<Map<String, Object>>() {
				});
			}
			catch (JsonGenerationException e)
			{
				e.printStackTrace();
			}
			catch (JsonMappingException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public LinkedHashMap<String, Object> getRequestParamMap()
	{
		return requestParamMap;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public LinkedHashMap<String, Object> getSearchCondtionMap()
	{
		return searchCondtionMap;
	}

	public LinkedHashMap<String, Object> getSearchValueMap()
	{
		return searchValueMap;
	}

	public LinkedHashMap<String, Object> getSessionFilterValueMap()
	{
		return sessionFilterValueMap;
	}

	public void set_AddEntityBean(EnumInterface _AddEntityBean)
	{
		this._AddEntityBean = _AddEntityBean;
	}

	public void set_OrderBy(String _OrderBy)
	{
		this._OrderBy = _OrderBy;
	}

	public void set_ProcedureName(String _ProcedureName)
	{
		this._ProcedureName = _ProcedureName;
	}

	public void setConditionAndValueParamsForAutoComplete(List<? extends ILayoutElements> ieList, String autoCompleteCriteriaValue)
	{
		searchCondtionMap = new LinkedHashMap<String, Object>();
		searchValueMap = new LinkedHashMap<String, Object>();
		int i = 0;
		for (ILayoutElements iLE : ieList)
		{
			if (ieList.size() == 1)
			{
				ENamed.Like.param_AND(this, iLE.getIeDisplayProperty(), autoCompleteCriteriaValue);
			}
			else
			{
				if (i++ == 0)
				{
					ENamed.Like.param_AND(this, iLE.getIeDisplayProperty(), autoCompleteCriteriaValue, IWrap.ST_BRACE1);
				}
				else
				{
					if (i == ieList.size())
						ENamed.Like.param_OR(this, iLE.getIeDisplayProperty(), autoCompleteCriteriaValue, IWrap.ED_BRACE1);
					else
						ENamed.Like.param_OR(this, iLE.getIeDisplayProperty(), autoCompleteCriteriaValue);
				}
			}

		}
	}

	public void setDataList(List<?> dataList)
	{
		this.dataList = dataList;
	}

	public void setDataListCount(long dataListCount)
	{
		this.dataListCount = dataListCount;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void setRequestParamMap(LinkedHashMap<String, Object> requestParamMap)
	{
		this.requestParamMap = requestParamMap;
	}

	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	public void setSearchCondtionMap(LinkedHashMap<String, Object> searchCondtionMap)
	{
		this.searchCondtionMap = searchCondtionMap;
	}

	public void setSearchValueMap(LinkedHashMap<String, Object> searchValueMap)
	{
		this.searchValueMap = searchValueMap;
	}

	public void setSessionFilterValueMap(LinkedHashMap<String, Object> sessionFilterValueMap)
	{
		this.sessionFilterValueMap = sessionFilterValueMap;
	}

}
