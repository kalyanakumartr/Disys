package org.hbs.disys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "competency")
public class Competency extends CommonBeanFields
{

	private static final long	serialVersionUID	= 921305976385402045L;
	private String				competencyId;
	private String				competencyName;
	private String				competencyType;

	public Competency()
	{
	}

	public Competency(String competencyId)
	{
		this.competencyId = competencyId;
	}

	public Competency(String competencyId, String competencyName, String competencyType)
	{
		super();
		this.competencyId = competencyId;
		this.competencyName = competencyName;
		this.competencyType = competencyType;
	}

	public Competency(String competencyName, String competencyType)
	{
		super();
		this.competencyName = competencyName;
		this.competencyType = competencyType;
	}

	@Id
	@Column(name = "competencyId", nullable = false)
	public String getCompetencyId()
	{
		return competencyId;
	}

	@Column(name = "competencyName")
	public String getCompetencyName()
	{
		return competencyName;
	}

	@Column(name = "competencyType")
	public String getCompetencyType()
	{
		return competencyType;
	}

	public void setCompetencyId(String competencyId)
	{
		this.competencyId = competencyId;
	}

	public void setCompetencyName(String competencyName)
	{
		this.competencyName = competencyName;
	}

	public void setCompetencyType(String competencyType)
	{
		this.competencyType = competencyType;
	}

}
