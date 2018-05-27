package org.hbs.disys.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataTableObject implements Serializable
{
	private static final long		serialVersionUID	= -3217131233777574761L;

	public int						iTotalRecords;

	public int						iTotalDisplayRecords;

	public String					sEcho;

	public String					aoColumns;

	public int						draw;

	public List<List<String>>		aaData;

	public List<ArrayList<String>>	aaData1;

	public List<?>					mData;

	public List<List<String>> getAaData()
	{
		return aaData;
	}

	public String getAoColumns()
	{
		return aoColumns;
	}

	public int getDraw()
	{
		return draw;
	}

	public int getiTotalDisplayRecords()
	{
		return iTotalDisplayRecords;
	}

	public int getiTotalRecords()
	{
		return iTotalRecords;
	}

	public List<?> getmData()
	{
		return mData;
	}

	public String getsEcho()
	{
		return sEcho;
	}

	public void setAaData(List<List<String>> aaData)
	{
		this.aaData = aaData;
	}

	public void setAoColumns(String aoColumns)
	{
		this.aoColumns = aoColumns;
	}

	public void setDraw(int draw)
	{
		this.draw = draw;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords)
	{
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public void setiTotalRecords(int iTotalRecords)
	{
		this.iTotalRecords = iTotalRecords;
	}

	public void setmData(List<?> mData)
	{
		this.mData = mData;
	}

	public void setsEcho(String sEcho)
	{
		this.sEcho = sEcho;
	}

	public List<ArrayList<String>> getAaData1()
	{
		return aaData1;
	}

	public void setAaData1(List<ArrayList<String>> aaData1)
	{
		this.aaData1 = aaData1;
	}

}