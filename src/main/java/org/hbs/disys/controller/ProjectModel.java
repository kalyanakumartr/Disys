package org.hbs.disys.controller;

import org.hbs.disys.model.CommonBeanFields;
import org.hbs.disys.model.Competency;
import org.hbs.disys.model.JobTitles;
import org.hbs.disys.model.Skills;

public class ProjectModel extends CommonBeanFields
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8619772919698518469L;
	private String				projectId			= "";
	private String				jobTitleId			= "";
	private String				workExperiencec		= "";
	private String				desiredSkill		= "";
	private String				preferredSkill		= "";
	private String				competenceId		= "";
	private String				competenceValue		= "";					// Skill

	private Competency			competency;
	private Competency			competencyWork;
	private Competency			competencyDS;
	private Competency			competencyPS;
	private JobTitles			jobTitles;
	private Skills				skills;

	public ProjectModel()
	{

	}

	public String getProjectId()
	{
		return projectId;
	}

	public void setProjectId(String projectId)
	{
		this.projectId = projectId;
	}

	public String getJobTitleId()
	{
		return jobTitleId;
	}

	public void setJobTitleId(String jobTitleId)
	{
		this.jobTitleId = jobTitleId;
	}

	public String getWorkExperiencec()
	{
		return workExperiencec;
	}

	public void setWorkExperiencec(String workExperiencec)
	{
		this.workExperiencec = workExperiencec;
	}

	public String getDesiredSkill()
	{
		return desiredSkill;
	}

	public void setDesiredSkill(String desiredSkill)
	{
		this.desiredSkill = desiredSkill;
	}

	public String getPreferredSkill()
	{
		return preferredSkill;
	}

	public void setPreferredSkill(String preferredSkill)
	{
		this.preferredSkill = preferredSkill;
	}

	public String getCompetenceId()
	{
		return competenceId;
	}

	public void setCompetenceId(String competenceId)
	{
		this.competenceId = competenceId;
	}

	public String getCompetenceValue()
	{
		return competenceValue;
	}

	public void setCompetenceValue(String competenceValue)
	{
		this.competenceValue = competenceValue;
	}

	public Competency getCompetency()
	{
		return competency;
	}

	public void setCompetency(Competency competency)
	{
		this.competency = competency;
	}

	public JobTitles getJobTitles()
	{
		return jobTitles;
	}

	public void setJobTitles(JobTitles jobTitles)
	{
		this.jobTitles = jobTitles;
	}

	public Skills getSkills()
	{
		return skills;
	}

	public void setSkills(Skills skills)
	{
		this.skills = skills;
	}

	public Competency getCompetencyWork()
	{
		return competencyWork;
	}

	public void setCompetencyWork(Competency competencyWork)
	{
		this.competencyWork = competencyWork;
	}

	public Competency getCompetencyDS()
	{
		return competencyDS;
	}

	public void setCompetencyDS(Competency competencyDS)
	{
		this.competencyDS = competencyDS;
	}

	public Competency getCompetencyPS()
	{
		return competencyPS;
	}

	public void setCompetencyPS(Competency competencyPS)
	{
		this.competencyPS = competencyPS;
	}

}
