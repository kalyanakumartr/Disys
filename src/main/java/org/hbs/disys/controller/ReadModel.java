package org.hbs.disys.controller;

import java.io.Serializable;

import org.hbs.disys.model.IUsers;
import org.hbs.disys.model.JobTitles;
import org.hbs.disys.model.Project;
import org.springframework.beans.factory.annotation.Autowired;

public class ReadModel implements Serializable
{

	private static final long	serialVersionUID	= 5046452571435979395L;

	private String				divisionDepartment	= "";
	private String				experience			= "4 - 6 Years";
	private String				grade				= "G7";
	private String				hours				= "";
	private String				jobTitle			= "JOB0002";
	private String				location			= "";
	private String				position			= "";
	private String				project				= "P0001";
	private String				reportsTo			= "";
	private String				shiftTiming			= "";
	private IUsers				users;
	private String				reviewedBy			= "";
	private String				approvedBy			= "";

	@Autowired
	private Project				projectObject;
	private JobTitles			jobTitleObject;

	public ReadModel()
	{

	}

	public ReadModel(String jobTitle, String project, String experience, String grade, String divisionDepartment, String shiftTiming, String reportsTo, String location, String hours, String position,
			String reviewedBy, String approvedBy, IUsers users)
	{
		super();
		this.jobTitle = jobTitle;
		this.project = project;
		this.experience = experience;
		this.grade = grade;
		this.divisionDepartment = divisionDepartment;
		this.shiftTiming = shiftTiming;
		this.reportsTo = reportsTo;
		this.location = location;
		this.hours = hours;
		this.position = position;
		this.reviewedBy = reviewedBy;
		this.approvedBy = approvedBy;

	}

	public String getDivisionDepartment()
	{
		return divisionDepartment;
	}

	public String getExperience()
	{
		return experience;
	}

	public String getGrade()
	{
		return grade;
	}

	public String getHours()
	{
		return hours;
	}

	public String getJobTitle()
	{
		return jobTitle;
	}

	public String getLocation()
	{
		return location;
	}

	public String getPosition()
	{
		return position;
	}

	public String getProject()
	{
		return project;
	}

	public String getReportsTo()
	{
		return reportsTo;
	}

	public String getShiftTiming()
	{
		return shiftTiming;
	}

	public void setDivisionDepartment(String divisionDepartment)
	{
		this.divisionDepartment = divisionDepartment;
	}

	public void setExperience(String experience)
	{
		this.experience = experience;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public void setHours(String hours)
	{
		this.hours = hours;
	}

	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public void setProject(String project)
	{
		this.project = project;
	}

	public void setReportsTo(String reportsTo)
	{
		this.reportsTo = reportsTo;
	}

	public void setShiftTiming(String shiftTiming)
	{
		this.shiftTiming = shiftTiming;
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

	public IUsers getUsers()
	{
		return users;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

	public Project getProjectObject()
	{
		return projectObject;
	}

	public void setProjectObject(Project projectObject)
	{
		this.projectObject = projectObject;
	}

	public JobTitles getJobTitleObject()
	{
		return jobTitleObject;
	}

	public void setJobTitleObject(JobTitles jobTitleObject)
	{
		this.jobTitleObject = jobTitleObject;
	}
}
