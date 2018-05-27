package org.hbs.disys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobtitles")
public class JobTitles extends CommonBeanFields
{

	private static final long	serialVersionUID	= 5082420731093355876L;
	private String				jobTitleId;
	private String				jobTitleName;
	private String				jobTitleDesc;
	private String				experience;
	private String				grade;

	public JobTitles()
	{
	}

	public JobTitles(String jobTitleId, String jobTitleName, String jobTitleDesc, String experience, String grade)
	{
		super();
		this.jobTitleId = jobTitleId;
		this.jobTitleName = jobTitleName;
		this.jobTitleDesc = jobTitleDesc;
		this.experience = experience;
		this.grade = grade;
	}

	@Column(name = "experience")
	public String getExperience()
	{
		return experience;
	}

	@Column(name = "grade")
	public String getGrade()
	{
		return grade;
	}

	@Column(name = "jobTitleDesc")
	public String getJobTitleDesc()
	{
		return jobTitleDesc;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jobTitleId")
	public String getJobTitleId()
	{
		return jobTitleId;
	}

	@Column(name = "jobTitleName")
	public String getJobTitleName()
	{
		return jobTitleName;
	}

	public void setExperience(String experience)
	{
		this.experience = experience;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public void setJobTitleDesc(String jobTitleDesc)
	{
		this.jobTitleDesc = jobTitleDesc;
	}

	public void setJobTitleId(String jobTitleId)
	{
		this.jobTitleId = jobTitleId;
	}

	public void setJobTitleName(String jobTitleName)
	{
		this.jobTitleName = jobTitleName;
	}

	/*
	 * public String getSelectValue() { return this.jobTitleId + "," + this.grade + "," +
	 * this.experience; }
	 */

}
