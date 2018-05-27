package org.hbs.disys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "competencymapping")
public class CompetencyMapping implements Serializable
{

	private static final long	serialVersionUID	= -5603882078684877322L;
	private Integer				competencyAutoId;
	private Competency			competency;
	private JobTitles			jobTitles;
	private Skills				skills;

	public CompetencyMapping()
	{
	}

	public CompetencyMapping(Integer competencyAutoId, Competency competency, JobTitles jobTitles, Skills skills)
	{
		super();
		this.competencyAutoId = competencyAutoId;
		this.competency = competency;
		this.jobTitles = jobTitles;
		this.skills = skills;
	}

	public CompetencyMapping(Competency competency, JobTitles jobTitles, Skills skills)
	{
		super();
		this.competency = competency;
		this.jobTitles = jobTitles;
		this.skills = skills;
	}

	@ManyToOne(targetEntity = Competency.class)
	@JoinColumn(name = "competencyId", nullable = false)
	public Competency getCompetency()
	{
		return competency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competencyAutoId")
	public Integer getCompetencyAutoId()
	{
		return competencyAutoId;
	}

	@ManyToOne(targetEntity = JobTitles.class)
	@JoinColumn(name = "jobTitleId", nullable = false)
	public JobTitles getJobTitles()
	{
		return jobTitles;
	}

	@ManyToOne(targetEntity = Skills.class)
	@JoinColumn(name = "skillId", nullable = false)
	public Skills getSkills()
	{
		return skills;
	}

	public void setCompetency(Competency competency)
	{
		this.competency = competency;
	}

	public void setCompetencyAutoId(Integer competencyAutoId)
	{
		this.competencyAutoId = competencyAutoId;
	}

	public void setJobTitles(JobTitles jobTitles)
	{
		this.jobTitles = jobTitles;
	}

	public void setSkills(Skills skills)
	{
		this.skills = skills;
	}

}
