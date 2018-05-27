package org.hbs.disys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class Skills extends CommonBeanFields
{

	private static final long	serialVersionUID	= -5720433862856964269L;
	private String				skillId;
	private String				skillType;
	private String				skillDesc;

	public Skills()
	{
	}

	public Skills(String skillId, String skillDesc, String skillType)
	{
		this.skillId = skillId;
		this.skillDesc = skillDesc;
		this.skillType = skillType;
	}

	public Skills(String skillId)
	{
		this.skillId = skillId;
	}

	@Column(name = "skillDesc")
	public String getSkillDesc()
	{
		return skillDesc;
	}

	@Id
	@Column(name = "skillId", nullable = false)
	public String getSkillId()
	{
		return skillId;
	}

	@Column(name = "skillType")
	public String getSkillType()
	{
		return skillType;
	}

	public void setSkillDesc(String skillDesc)
	{
		this.skillDesc = skillDesc;
	}

	public void setSkillId(String skillId)
	{
		this.skillId = skillId;
	}

	public void setSkillType(String skillType)
	{
		this.skillType = skillType;
	}

}
