package org.hbs.disys.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbs.disys.util.model.ICommonLayout;
import org.hbs.disys.util.model.ILayoutElements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataTableDynamicColumnDefs implements Serializable
{
	private static final long	serialVersionUID	= -2547250787482770702L;

	public static String getDynamicColumnDefs(List<? extends ICommonLayout> queueLayoutList)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, String> renderMap = new HashMap<String, String>(0);
		List<DataTableDynamicColumnDefs> columnsDefsDT = new ArrayList<DataTableDynamicColumnDefs>();
		int i = 1;
		for (ICommonLayout iCL : queueLayoutList)
		{
			iCL.setDisplayOrder(i++);
			String renderField = iCL.getRender();
			// Get Layout Element Render, If Specific Render Not Mention as
			// EMPTY
			if (renderField == null)
			{
				renderField = iCL.getLayoutElements().getIeRender();
			}

			// Not Null AND Not Empty Render Alone Add
			if (CommonValidator.isNotNullNotEmpty(renderField))
			{
				ILayoutElements iLE = iCL.getLayoutElements();
				renderMap.put(iLE.getIeElementId(), renderField);
				DataTableDynamicColumnDefs dtDCDefs = new DataTableDynamicColumnDefs();
				dtDCDefs.setTargets(iCL.getDisplayOrder() - 1);
				dtDCDefs.setRender(iLE.getIeElementId());
				dtDCDefs.setData("null");
				dtDCDefs.setOrderable(iCL.isOrderable());
				dtDCDefs.setVisible(iCL.isVisible());
				dtDCDefs.setSearchable(iCL.isSearchable());
				columnsDefsDT.add(dtDCDefs);
			}
		}
		String colDefs = gson.toJson(columnsDefsDT);
		if (CommonValidator.isNotNullNotEmpty(colDefs))
		{
			colDefs = colDefs.replaceAll("\"null\"", "null");
			for (String renderKey : renderMap.keySet())
			{
				colDefs = colDefs.replaceAll("\"" + renderKey + "\"", renderMap.get(renderKey));
			}
		}
		return colDefs;

	}

	protected String	data		= null;
	protected int		targets;
	protected boolean	orderable	= true;
	protected boolean	visible		= true;
	protected boolean	searchable	= true;

	protected String	render		= null;

	public String getData()
	{
		return data;
	}

	public String getRender()
	{
		return render;
	}

	public int getTargets()
	{
		return targets;
	}

	public boolean isOrderable()
	{
		return orderable;
	}

	public boolean isSearchable()
	{
		return searchable;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public void setOrderable(boolean orderable)
	{
		this.orderable = orderable;
	}

	public void setRender(String render)
	{
		this.render = render;
	}

	public void setSearchable(boolean searchable)
	{
		this.searchable = searchable;
	}

	public void setTargets(int targets)
	{
		this.targets = targets;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

}
