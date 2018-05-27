package org.hbs.disys.controller;

import java.util.HashMap;
import java.util.Map;

import org.hbs.disys.model.IUsers;

public class JobModel
{

	private String						logo;
	private String						company				= "DISYS India";
	private String						divDepartment		= "";
	private String						location			= "Chennai";
	private String						jobTitle;
	private String						reportsTo			= "Project Manager";
	private String						grade				= "G1";
	private String						position			= "Full-Time";
	private String						hours				= "40 / Week";
	private String						shiftTimings		= "US Shift";
	private String						expYears;
	private String						pdfFilePath			= "";
	private String						reviewedBy			= "";
	private String						approvedBy			= "";

	private Map<String, StringBuffer>	generalDescription	= new HashMap<String, StringBuffer>();
	private Map<String, StringBuffer>	workExperience		= new HashMap<String, StringBuffer>();
	private Map<String, StringBuffer>	educationCert		= new HashMap<String, StringBuffer>();

	private byte[]						byteArray;

	private IUsers						users;

	public String getLogo()
	{
		return logo;
	}

	public void setLogo(String logo)
	{
		this.logo = logo;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getDivDepartment()
	{
		return divDepartment;
	}

	public void setDivDepartment(String divDepartment)
	{
		this.divDepartment = divDepartment;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getJobTitle()
	{
		return jobTitle;
	}

	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	public String getReportsTo()
	{
		return reportsTo;
	}

	public void setReportsTo(String reportsTo)
	{
		this.reportsTo = reportsTo;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public String getHours()
	{
		return hours;
	}

	public void setHours(String hours)
	{
		this.hours = hours;
	}

	public String getShiftTimings()
	{
		return shiftTimings;
	}

	public void setShiftTimings(String shiftTimings)
	{
		this.shiftTimings = shiftTimings;
	}

	public String getExpYears()
	{
		return expYears;
	}

	public void setExpYears(String expYears)
	{
		this.expYears = expYears;
	}

	public String getPdfFilePath()
	{
		return pdfFilePath;
	}

	public void setPdfFilePath(String pdfFilePath)
	{
		this.pdfFilePath = pdfFilePath;
	}

	public Map<String, StringBuffer> getGeneralDescription()
	{
		return generalDescription;
	}

	public void setGeneralDescription(Map<String, StringBuffer> generalDescription)
	{
		this.generalDescription = generalDescription;
	}

	public Map<String, StringBuffer> getWorkExperience()
	{
		return workExperience;
	}

	public void setWorkExperience(Map<String, StringBuffer> workExperience)
	{
		this.workExperience = workExperience;
	}

	public Map<String, StringBuffer> getEducationCert()
	{
		return educationCert;
	}

	public void setEducationCert(Map<String, StringBuffer> educationCert)
	{
		this.educationCert = educationCert;
	}

	public byte[] getByteArray()
	{
		return byteArray;
	}

	public void setByteArray(byte[] byteArray)
	{
		this.byteArray = byteArray;
	}

	public IUsers getUsers()
	{
		return users;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

	public String getReviewedBy()
	{
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy)
	{
		this.reviewedBy = reviewedBy;
	}

	public String getApprovedBy()
	{
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy)
	{
		this.approvedBy = approvedBy;
	}

}
