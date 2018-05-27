package org.hbs.disys.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hbs.disys.util.model.ICommonLayout;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataTableParam extends Param implements Serializable
{
	private static final long	serialVersionUID	= 763667035218149349L;

	public static DataTableParam getDataTableParamsFromRequest(HttpServletRequest request)
	{
		DataTableParam dtParam = new DataTableParam(request);
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("draw")))
			dtParam.draw = Integer.parseInt(request.getParameter("draw"));

		if (CommonValidator.isNotNullNotEmpty(request.getParameter("search[value]")))
		{
			dtParam.sSearch = request.getParameter("search[value]");
		}

		if (CommonValidator.isNotNullNotEmpty(request.getParameter("searchCriteria")))
		{
			String param = request.getParameter("searchCriteria");
			param = new String(Base64.decodeBase64(param));
			dtParam.getMapFromJSONSearchCriteria(param);
		}

		else if (CommonValidator.isNotNullNotEmpty(request.getParameter("columns[0][search][value]")))
			dtParam.sSearch = request.getParameter("columns[0][search][value]");
		dtParam.sColumns = request.getParameter("sColumns");

		if (CommonValidator.isNotNullNotEmpty(request.getParameter("start")))
			dtParam.iDisplayStart = Integer.parseInt(request.getParameter("start"));

		if (CommonValidator.isNotNullNotEmpty(request.getParameter("length")))
			dtParam.iDisplayLength = Integer.parseInt(request.getParameter("length"));

		if (CommonValidator.isNotNullNotEmpty(request.getParameter("iColumns")))
			dtParam.iColumns = Integer.parseInt(request.getParameter("iColumns"));

		if (CommonValidator.isNotNullNotEmpty(request.getParameter("iSortingCols")))
			dtParam.iSortingCols = Integer.parseInt(request.getParameter("iSortingCols"));

		if (CommonValidator.isNotNullNotEmpty(request.getParameter("order[0][column]")))
			dtParam.iSortColumnIndex = Integer.parseInt(request.getParameter("order[0][column]"));

		if (CommonValidator.isNotNullNotEmpty(request.getParameter("order[0][dir]")))
			dtParam.sSortDirection = request.getParameter("order[0][dir]");
		return dtParam;

	}

	public String							sEcho;
	public String							sSearch;
	public String							sColumns;
	public int								iDisplayStart		= 0;
	public int								iDisplayLength		= 10;
	public int								iColumns;
	public int								iSortingCols		= 0;
	public int								iSortColumnIndex	= 0;
	public String							sSortDirection		= "asc";
	public int								draw;

	public List<? extends ICommonLayout>	layoutList;

	public DataTableParam()
	{

	}

	public DataTableParam(HttpServletRequest request)
	{
		this.request = request;
	}

	public DataTableParam(int iDisplayLength)
	{
		this.iDisplayLength = iDisplayLength;
	}

	public List<?> getDataList()
	{
		return dataList;
	}

	public long getDataListCount()
	{
		return dataListCount;
	}

	public int getDraw()
	{
		return draw;
	}

	public int getiColumns()
	{
		return iColumns;
	}

	public int getiDisplayLength()
	{
		return iDisplayLength;
	}

	public int getiDisplayStart()
	{
		return iDisplayStart;
	}

	public int getiSortColumnIndex()
	{
		return iSortColumnIndex;
	}

	public int getiSortingCols()
	{
		return iSortingCols;
	}

	public List<? extends ICommonLayout> getLayoutList()
	{
		return layoutList;
	}

	private void getMapFromJSONSearchCriteria(String searchParam)
	{
		searchValueMap = new LinkedHashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			searchValueMap = mapper.readValue(searchParam, new TypeReference<Map<String, Object>>() {
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

	@Override
	public HttpServletRequest getRequest()
	{
		return request;
	}

	public String getsColumns()
	{
		return sColumns;
	}

	public String getsEcho()
	{
		return sEcho;
	}

	public String getsSearch()
	{
		return sSearch;
	}

	public String getsSortDirection()
	{
		return sSortDirection;
	}

	public void setDataList(List<?> dataList)
	{
		this.dataList = dataList;
	}

	public void setDataListCount(long dataListCount)
	{
		this.dataListCount = dataListCount;
	}

	public void setDraw(int draw)
	{
		this.draw = draw;
	}

	public void setiColumns(int iColumns)
	{
		this.iColumns = iColumns;
	}

	public void setiDisplayLength(int iDisplayLength)
	{
		this.iDisplayLength = iDisplayLength;
	}

	public void setiDisplayStart(int iDisplayStart)
	{
		this.iDisplayStart = iDisplayStart;
	}

	public void setiSortColumnIndex(int iSortColumnIndex)
	{
		this.iSortColumnIndex = iSortColumnIndex;
	}

	public void setiSortingCols(int iSortingCols)
	{
		this.iSortingCols = iSortingCols;
	}

	public void setLayoutList(List<? extends ICommonLayout> layoutList)
	{
		this.layoutList = layoutList;
	}

	@Override
	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void setsColumns(String sColumns)
	{
		this.sColumns = sColumns;
	}

	public void setsEcho(String sEcho)
	{
		this.sEcho = sEcho;
	}

	public void setsSearch(String sSearch)
	{
		this.sSearch = sSearch;
	}

	public void setsSortDirection(String sSortDirection)
	{
		this.sSortDirection = sSortDirection;
	}
}
