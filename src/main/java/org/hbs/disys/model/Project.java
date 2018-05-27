package org.hbs.disys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project extends CommonBeanFields
{

	private static final long	serialVersionUID	= 8248066653635561134L;

	// @Id
	// @GeneratedValue
	// @Column(name="autoId")
	// private int autoId;
	private String				projectId;

	private String				projectName;

	private String				projectDesc;

	public Project()
	{
		super();
	}

	public Project(String projectId, String projectName, String projectDec)
	{
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDec;
	}

	@Column(name = "projectDesc")
	public String getProjectDesc()
	{
		return projectDesc;
	}

	@Id
	@Column(name = "projectId", nullable = false)
	public String getProjectId()
	{
		return projectId;
	}

	@Column(name = "projectName")
	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectDesc(String projectDesc)
	{
		this.projectDesc = projectDesc;
	}

	public void setProjectId(String projectId)
	{
		this.projectId = projectId;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

}
