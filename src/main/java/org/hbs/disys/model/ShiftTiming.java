package org.hbs.disys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shifttiming")
public class ShiftTiming implements Serializable
{

	private static final long	serialVersionUID	= -8843497769142303675L;
	private String				shiftId;
	private String				shiftType;

	public ShiftTiming()
	{
	}

	public ShiftTiming(String shiftId)
	{
		this.shiftId = shiftId;
	}

	public ShiftTiming(String shiftId, String shiftType)
	{
		super();
		this.shiftId = shiftId;
		this.shiftType = shiftType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shiftId")
	public String getShiftId()
	{
		return this.shiftId;
	}

	@Column(name = "shiftType")
	public String getShiftType()
	{
		return this.shiftType;
	}

	public void setShiftId(String shiftId)
	{
		this.shiftId = shiftId;
	}

	public void setShiftType(String shiftType)
	{
		this.shiftType = shiftType;
	}

}
