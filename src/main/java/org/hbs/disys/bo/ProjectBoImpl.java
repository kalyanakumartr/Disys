package org.hbs.disys.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hbs.disys.controller.IProject.EGenerate;
import org.hbs.disys.controller.JobModel;
import org.hbs.disys.controller.ProjectModel;
import org.hbs.disys.controller.ReadModel;
import org.hbs.disys.dao.ProjectDAO;
import org.hbs.disys.model.Competency;
import org.hbs.disys.model.CompetencyMapping;
import org.hbs.disys.model.JobTitles;
import org.hbs.disys.model.Project;
import org.hbs.disys.model.ProjectParam;
import org.hbs.disys.model.ShiftTiming;
import org.hbs.disys.model.Skills;
import org.hbs.disys.util.CommonValidator;
import org.hbs.disys.util.CompetencyType;
import org.hbs.disys.util.DataTableParam;
import org.hbs.disys.util.DisysPDF;
import org.hbs.disys.util.IParam.ENamed;
import org.hbs.disys.util.IParam.IWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ProjectBoImpl implements ProjectBo
{
	@Autowired
	ProjectDAO	projectDAO;

	@Override
	public List<Competency> getCompetencyListByType(ProjectParam projectParam)
	{
		ENamed.EqualTo.param_AND(projectParam, "competencyType", projectParam.competencyType);
		ENamed.EqualTo.param_AND(projectParam, "status", true);
		return projectDAO.getCompetencyList(projectParam);
	}

	@Override
	public Competency getCompetencyByName(ProjectParam projectParam)
	{
		ENamed.EqualTo.param_AND(projectParam, "competencyName", projectParam.competencyName);
		ENamed.EqualTo.param_AND(projectParam, "competencyType", projectParam.competencyType);
		ENamed.EqualTo.param_AND(projectParam, "status", true);
		List<Competency> competencyList = projectDAO.getCompetencyList(projectParam);

		if (CommonValidator.isListFirstNotEmpty(competencyList))
			return competencyList.iterator().next();
		return null;

	}

	@Override
	public List<Project> getProjectList(ProjectParam projectParam)
	{
		ENamed.EqualTo.param_AND(projectParam, "status", true);
		return projectDAO.getProjectList(projectParam);
	}

	@Override
	public Project getProjectById(ProjectParam projectParam)
	{
		ENamed.EqualTo.param_AND(projectParam, "projectId", projectParam.projectId);
		ENamed.EqualTo.param_AND(projectParam, "status", true);
		List<Project> projectList = projectDAO.getProjectList(projectParam);

		if (CommonValidator.isListFirstNotEmpty(projectList))
			return projectList.iterator().next();
		return null;

	}

	@Override
	public List<JobTitles> getJobTitlesList(ProjectParam projectParam)
	{
		ENamed.EqualTo.param_AND(projectParam, "status", true);
		return projectDAO.getJobTitlesList(projectParam);
	}

	@Override
	public JobTitles getJobTitlesById(ProjectParam projectParam)
	{
		ENamed.EqualTo.param_AND(projectParam, "jobTitleId", projectParam.jobTitleId);
		ENamed.EqualTo.param_AND(projectParam, "status", true);
		List<JobTitles> jobTitleList = projectDAO.getJobTitlesList(projectParam);

		if (CommonValidator.isListFirstNotEmpty(jobTitleList))
			return jobTitleList.iterator().next();
		return null;

	}

	@Override
	public List<ShiftTiming> getShiftTimingList(ProjectParam projectParam)
	{
		ENamed.EqualTo.param_AND(projectParam, "status", true);
		return projectDAO.getShiftTimingList(projectParam);
	}

	public StringBuffer getSkillDescription(ProjectParam projectParam)
	{
		ENamed.EqualTo.param_AND(projectParam, "competency.competencyId", projectParam.competencyId);
		ENamed.EqualTo.param_AND(projectParam, "jobTitles.jobTitleId", projectParam.jobTitleId);
		// if (projectParam.skillType != null)
		// ENamed.EqualTo.param_AND(projectParam, "skills.skillType",
		// projectParam.skillType);
		List<CompetencyMapping> compMapList = projectDAO.getCompetencyMappingList(projectParam);

		if (CommonValidator.isListFirstNotEmpty(compMapList))
		{
			return new StringBuffer(((CompetencyMapping) compMapList.iterator().next()).getSkills().getSkillDesc());
		}
		return new StringBuffer();
	}

	public ProjectDAO getProjectDAO()
	{
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO)
	{
		this.projectDAO = projectDAO;
	}

	@Override
	public List<String> getGradeLeaderList(ProjectParam projectParam)
	{
		List<String> gradeLeaderList = new ArrayList<String>();
		gradeLeaderList.add("G6");
		gradeLeaderList.add("G7");
		gradeLeaderList.add("G8");
		gradeLeaderList.add("G9 & G10");
		return gradeLeaderList;
	}

	@Override
	public JobModel getDisysPDF(HttpServletRequest request, ReadModel readModel)
	{
		DisysPDF pdfGeneration = new DisysPDF();
		JobModel jobModel = constructJobModel(readModel);
		try
		{
			return pdfGeneration.generatePDF(request, jobModel);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;

	}

	private JobModel constructJobModel(ReadModel readModel)
	{
		JobModel jobModel = new JobModel();
		ProjectParam projectParam = new ProjectParam();
		projectParam.projectId = readModel.getProject();

		Project project = getProjectById(projectParam);
		if (project != null)
		{
			jobModel.setDivDepartment(project.getProjectName());
			projectParam = new ProjectParam();
			projectParam.jobTitleId = readModel.getJobTitle();
			JobTitles jobTitles = getJobTitlesById(projectParam);
			readModel.setGrade(jobTitles.getGrade());
			readModel.setExperience(jobTitles.getExperience());
			jobModel.setJobTitle(jobTitles.getJobTitleName());
			jobModel.setGrade(jobTitles.getGrade());
			jobModel.setExpYears(jobTitles.getExperience());
			jobModel.setShiftTimings(readModel.getShiftTiming());
			jobModel.setLocation(readModel.getLocation());
			jobModel.setReportsTo(readModel.getReportsTo());
			jobModel.setPosition(readModel.getPosition());
			jobModel.setHours(readModel.getHours());
			jobModel.setReviewedBy(readModel.getReviewedBy());
			jobModel.setApprovedBy(readModel.getApprovedBy());
			// Work
			jobModel.setWorkExperience(getWorkExperienceDB(readModel));
			// LEad or Tech
			jobModel.setGeneralDescription(getGeneralExperienceDB(readModel));

			jobModel.setUsers(readModel.getUsers());
		}
		return jobModel;
	}

	public Map<String, StringBuffer> getWorkExperienceDB(ReadModel readModel)
	{

		Map<String, StringBuffer> workExperience = new LinkedHashMap<String, StringBuffer>();
		StringBuffer weExp = new StringBuffer(readModel.getExperience());
		workExperience.put("Experience :", weExp);
		ProjectParam projectParam = new ProjectParam();
		projectParam.projectId = readModel.getProject();
		Project project = getProjectById(projectParam);
		if (project != null)
		{
			projectParam = new ProjectParam();
			projectParam.competencyName = project.getProjectName();
			projectParam.competencyType = "Work";
			Competency competency = getCompetencyByName(projectParam);

			if (competency != null && !competency.toString().trim().equals(""))
			{
				projectParam = new ProjectParam();
				projectParam.competencyId = competency.getCompetencyId();
				projectParam.jobTitleId = readModel.getJobTitle();
				workExperience.put(" ", getSkillDescription(projectParam));
			}

			projectParam = new ProjectParam();
			projectParam.competencyName = project.getProjectName();
			projectParam.competencyType = "DS";
			Competency competencyDS = getCompetencyByName(projectParam);

			if (competencyDS != null && !competencyDS.toString().trim().equals(""))
			{
				projectParam = new ProjectParam();
				projectParam.competencyId = competencyDS.getCompetencyId();
				projectParam.jobTitleId = readModel.getJobTitle();
				StringBuffer dSkills = getSkillDescription(projectParam);
				if (dSkills != null && !dSkills.toString().trim().equals(""))
					workExperience.put("Desired Skills: ", dSkills);
			}
			projectParam = new ProjectParam();
			projectParam.competencyName = project.getProjectName();
			projectParam.competencyType = "PS";
			Competency competencyPS = getCompetencyByName(projectParam);
			if (competencyPS != null && !competencyPS.toString().trim().equals(""))
			{
				projectParam = new ProjectParam();
				projectParam.competencyId = competencyPS.getCompetencyId();
				projectParam.jobTitleId = readModel.getJobTitle();
				StringBuffer pSkills = getSkillDescription(projectParam);
				if (pSkills != null && !pSkills.toString().trim().equals(""))
					workExperience.put("Prefered Skills: ", pSkills);
			}
		}
		return workExperience;
	}

	public Map<String, StringBuffer> getGeneralExperienceDB(ReadModel readModel)
	{

		Map<String, StringBuffer> generalDescription = new LinkedHashMap<String, StringBuffer>();
		ProjectParam projectParam = new ProjectParam();
		List<String> gradeLeaderList = getGradeLeaderList(projectParam);

		if (gradeLeaderList.contains(readModel.getGrade()))
		{

			projectParam.competencyType = CompetencyType.LEAD.getValue();
			List<Competency> leadCompetency = getCompetencyListByType(projectParam);
			for (Competency competencyLead : leadCompetency)
			{
				projectParam = new ProjectParam();
				projectParam.jobTitleId = readModel.getJobTitle();
				projectParam.competencyId = competencyLead.getCompetencyId();
				StringBuffer skillValue = getSkillDescription(projectParam);
				if (skillValue != null && !skillValue.toString().trim().equals(""))
					generalDescription.put(competencyLead.getCompetencyName(), skillValue);
			}
		}
		else
		{
			projectParam.competencyType = CompetencyType.TECH.getValue();
			List<Competency> techCompetency = getCompetencyListByType(projectParam);
			for (Competency competencyTech : techCompetency)
			{
				projectParam = new ProjectParam();
				projectParam.jobTitleId = readModel.getJobTitle();
				projectParam.competencyId = competencyTech.getCompetencyId();
				StringBuffer skillValue = getSkillDescription(projectParam);
				if (skillValue != null && !skillValue.toString().trim().equals(""))
					generalDescription.put(competencyTech.getCompetencyName(), skillValue);
			}
		}
		return generalDescription;
	}

	public String getLastProjectId(EGenerate eGenerate)
	{
		return projectDAO.getLastProjectId(eGenerate);
	}

	public boolean saveProjectDetails(Project project)
	{
		return projectDAO.saveProjectDetails(project);
	}

	@Override
	public List<Competency> getCompetencyList(ProjectParam projectParam)
	{
		return projectDAO.getCompetencyList(projectParam);
	}

	@Override
	public List<CompetencyMapping> getCompetencyMappingList(ProjectParam projectParam)
	{
		return projectDAO.getCompetencyMappingList(projectParam);
	}

	@Override
	public boolean saveAssignJobTitle(ProjectModel projectModel)
	{
		ProjectParam projectParam = new ProjectParam();
		// Project
		projectParam.projectId = projectModel.getProjectId();
		Project project = getProjectById(projectParam);

		// JobTitle
		projectParam = new ProjectParam();
		projectParam.jobTitleId = projectModel.getJobTitleId();
		projectModel.setJobTitles(getJobTitlesById(projectParam));

		// Work Competency
		if (!StringUtils.isEmpty(projectModel.getWorkExperiencec()))
		{
			projectParam = new ProjectParam();
			projectParam.competencyName = project.getProjectName();
			projectParam.competencyType = "Work";
			projectModel.setCompetencyWork(getCompetencyByName(projectParam));
		}

		// DS Competency
		if (!StringUtils.isEmpty(projectModel.getDesiredSkill()))
		{
			projectParam = new ProjectParam();
			projectParam.competencyName = project.getProjectName();
			projectParam.competencyType = "DS";
			projectModel.setCompetencyDS(getCompetencyByName(projectParam));
		}
		// PS Competency
		if (!StringUtils.isEmpty(projectModel.getPreferredSkill()))
		{
			projectParam = new ProjectParam();
			projectParam.competencyName = project.getProjectName();
			projectParam.competencyType = "PS";
			projectModel.setCompetencyPS(getCompetencyByName(projectParam));
		}
		return projectDAO.saveAssignJobTitle(projectModel);
	}

	@Override
	public DataTableParam getCompentencyList(DataTableParam dtParam, boolean isCount)
	{

		return projectDAO.getCompentencyList(dtParam, isCount);
	}

	public String getTypeOfCompetencyById(String competencyName, String competencyType)

	{
		return projectDAO.getTypeOfCompetencyById(competencyName, competencyType);
	}

	@Override
	public boolean updateCompetencySkills(Skills skills)
	{
		return projectDAO.updateCompetencySkills(skills);
	}

	@Override
	public DataTableParam getSkillList(DataTableParam dtParam, boolean isCount)
	{
		return projectDAO.getSkillList(dtParam, isCount);
	}

	@Override
	public boolean checkExistingProjectName(DataTableParam dtParam)
	{
		return projectDAO.checkExistingProjectName(dtParam);
	}

	@Override
	public DataTableParam getUserFileList(DataTableParam dtParam, boolean isCount)
	{

		ENamed.EqualTo.param_AND(dtParam, "status", true);
		if (CommonValidator.isNotNullNotEmpty(dtParam.sSearch) && isCount == false)
		{
			ENamed.Like.param_AND(dtParam, "usUserId", dtParam.sSearch, IWrap.ST_BRACE1);
			ENamed.Like.param_OR(dtParam, "userName", dtParam.sSearch);
			ENamed.Like.param_OR(dtParam, "pdfFileName", dtParam.sSearch, IWrap.ED_BRACE1);

		}
		if (CommonValidator.isNotNullNotEmpty(dtParam.sSortDirection))
		{
			dtParam._OrderBy = ENamed.OrderBy.param("createdDate") + " " + dtParam.sSortDirection;
		}
		else
		{
			dtParam._OrderBy = ENamed.OrderBy.param("createdDate") + " DESC ";
		}

		return projectDAO.getUserFileList(dtParam, isCount);
	}

}
