package org.hbs.disys.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hbs.disys.util.model.ICommonLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataTableDynamicColumns implements IConstProperty
{

	private static final long	serialVersionUID	= -5706887636272324167L;

	public static String getDynamicColumns(List<? extends ICommonLayout> queueLayoutList)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		List<DataTableDynamicColumns> columnsDT = new ArrayList<DataTableDynamicColumns>();
		for (ICommonLayout iCL : queueLayoutList)
		{
			DataTableDynamicColumns dtDC = new DataTableDynamicColumns();
			if (CommonValidator.isNotNullNotEmpty(iCL.getDisplayName()))
				dtDC.setTitle(iCL.getDisplayName());
			else
				dtDC.setTitle(iCL.getLayoutElements().getIeDisplayName());
			if (CommonValidator.isNotNullNotEmpty(iCL.getDefaultContent()))
				dtDC.setDefaultContent(iCL.getDefaultContent());
			else
				dtDC.setDefaultContent("");
			dtDC.setVisible(iCL.isVisible());
			dtDC.setSearchable(iCL.isSearchable());
			dtDC.setOrderable(iCL.isOrderable());
			dtDC.setWidth(iCL.getDisplayWidth() + "%");
			dtDC.setClassName(iCL.getCssClassName());
			columnsDT.add(dtDC);
		}
		return gson.toJson(columnsDT);
	}

	public static List<String> getJSONFromObject(int startLevel, int depthLevel, Object inObject) throws IllegalArgumentException, IllegalAccessException
	{
		List<String> dataList = new ArrayList<String>(0);
		if (inObject != null)
		{
			try
			{
				BeanInfo info = Introspector.getBeanInfo(inObject.getClass(), Object.class);
				PropertyDescriptor[] props = info.getPropertyDescriptors();
				for (PropertyDescriptor pd : props)
				{
					if ((pd.getClass().isInstance(List.class) || pd.getClass().isInstance(Set.class) || pd.getClass().isInstance(Map.class)) == false)
					{
						Method getter = pd.getReadMethod();

						Object dataObject = getter.invoke(inObject);

						if (dataObject != null)
						{
							if (dataObject instanceof String || dataObject instanceof Integer || dataObject instanceof Long || dataObject instanceof Boolean || dataObject instanceof Double
									|| dataObject instanceof Float || dataObject instanceof Character || dataObject instanceof Byte || dataObject instanceof Short)
							{
								dataList.add(pd.getDisplayName() + " : " + dataObject.toString());
							}
							else if (dataObject instanceof String[] || dataObject instanceof Integer[] || dataObject instanceof Long[] || dataObject instanceof Boolean
									|| dataObject instanceof Double[] || dataObject instanceof Float[] || dataObject instanceof Character[] || dataObject instanceof Byte[]
									|| dataObject instanceof Short)
							{
								String data = dataObject.toString();
								if (dataObject.getClass().isArray())
								{
									data = "";
									for (Object o : (Object[]) dataObject)
									{
										data += " " + o.toString();
									}
								}
								dataList.add(pd.getDisplayName() + " : " + data);
							}
							else
							{
								if (startLevel < depthLevel && depthLevel < 3)
								{
									List<String> nestedData = getJSONFromObject(++startLevel, depthLevel, dataObject);
									if (nestedData != null && nestedData.isEmpty() == false)
									{
										Gson gson = new GsonBuilder().setPrettyPrinting().create();
										StringBuilder sb = new StringBuilder(gson.toJson(nestedData));
										dataList.add(pd.getDisplayName() + " : " + sb.toString());
									}
									startLevel = 0;
								}
							}
						}
					}
				}
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
			catch (IntrospectionException e)
			{
				e.printStackTrace();
			}
		}
		return dataList;
	}

	public static List<List<String>> getJSONFromObject(List<? extends ICommonLayout> elementList, Object... masterObject)
	{
		if (CommonValidator.isNotNullNotEmpty(masterObject))
		{
			Method method = null;
			List<List<String>> mDataList = new ArrayList<List<String>>(masterObject.length);
			String[] _KeyValue = null;
			for (Object inObject : masterObject)
			{
				_KeyValue = new String[elementList.size()];

				int i = 1;
				for (ICommonLayout iCL : elementList)
				{
					iCL.setDisplayOrder(i++);
					if (CommonValidator.isNotNullNotEmpty(iCL.getLayoutElements().getIeDisplayPropertyGetter()))
					{
						String getter = iCL.getLayoutElements().getIeDisplayPropertyGetter().replaceAll("\\(\\)", "");

						Object traverseObject = inObject;
						if (traverseObject != null)
						{
							for (String mtdName : getter.split("\\."))
							{
								try
								{
									try
									{
										method = traverseObject.getClass().getDeclaredMethod(mtdName);
									}
									catch (NoSuchMethodException | SecurityException e)
									{
										method = traverseObject.getClass().getMethod(mtdName);

									}
									if (CommonValidator.isNotNullNotEmpty(method))
									{
										traverseObject = method.invoke(traverseObject, new Object[] {});
										if (traverseObject == null)
										{
											break;
										}
									}
								}
								catch (Exception e)
								{
									e.printStackTrace();
									break;
								}
							}
						}

						// Create JSON Structure Key Value Pair
						if (CommonValidator.isNotNullNotEmpty(traverseObject))
						{
							if (traverseObject instanceof Timestamp)
							{
								// traverseObject = CommonUtil.getTimeZoneDateInFormat((Timestamp)
								// traverseObject, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, null);
							}

							_KeyValue[iCL.getDisplayOrder() - 1] = traverseObject.toString();

						}
						else
						{
							_KeyValue[iCL.getDisplayOrder() - 1] = "";
						}

					}
					else
					{
						_KeyValue[iCL.getDisplayOrder() - 1] = "";
					}
				}
				mDataList.add(Arrays.asList(_KeyValue));
			}
			return mDataList;
		}
		return null;
	}

	protected String	cellType		= null;
	protected String	contentPadding	= null;
	protected String	createdCell		= null;
	protected String	data			= null;
	protected String	defaultContent	= null;
	protected String	name			= null;

	protected String	orderData		= null;
	protected String	orderDataType	= null;
	protected String	type			= null;
	// Mandatory Columns
	protected String	title			= null;
	protected String	className		= null;
	protected String	width			= null;
	protected boolean	orderable		= true;

	protected boolean	visible			= true;

	protected boolean	searchable		= true;

	protected String	render			= null;

	public String getCellType()
	{
		return cellType;
	}

	public String getClassName()
	{
		return className;
	}

	public String getContentPadding()
	{
		return contentPadding;
	}

	public String getCreatedCell()
	{
		return createdCell;
	}

	public String getData()
	{
		return data;
	}

	public String getDefaultContent()
	{
		return defaultContent;
	}

	public String getName()
	{
		return name;
	}

	public String getOrderData()
	{
		return orderData;
	}

	public String getOrderDataType()
	{
		return orderDataType;
	}

	public String getRender()
	{
		return render;
	}

	public String getTitle()
	{
		return title;
	}

	public String getType()
	{
		return type;
	}

	public String getWidth()
	{
		return width;
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

	public void setCellType(String cellType)
	{
		this.cellType = cellType;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public void setContentPadding(String contentPadding)
	{
		this.contentPadding = contentPadding;
	}

	public void setCreatedCell(String createdCell)
	{
		this.createdCell = createdCell;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public void setDefaultContent(String defaultContent)
	{
		this.defaultContent = defaultContent;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setOrderable(boolean orderable)
	{
		this.orderable = orderable;
	}

	public void setOrderData(String orderData)
	{
		this.orderData = orderData;
	}

	public void setOrderDataType(String orderDataType)
	{
		this.orderDataType = orderDataType;
	}

	public void setRender(String render)
	{
		this.render = render;
	}

	public void setSearchable(boolean searchable)
	{
		this.searchable = searchable;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

}
