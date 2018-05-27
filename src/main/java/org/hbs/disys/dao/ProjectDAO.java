package org.hbs.disys.dao;

import java.util.List;

import org.hbs.disys.controller.IProject.EGenerate;
import org.hbs.disys.controller.ProjectModel;
import org.hbs.disys.model.Competency;
import org.hbs.disys.model.CompetencyMapping;
import org.hbs.disys.model.JobTitles;
import org.hbs.disys.model.Project;
import org.hbs.disys.model.ProjectParam;
import org.hbs.disys.model.ShiftTiming;
import org.hbs.disys.model.Skills;
import org.hbs.disys.util.DataTableParam;

public interface ProjectDAO
{
	public List<Competency> getCompetencyList(ProjectParam projectParam);

	public List<Project> getProjectList(ProjectParam projectParam);

	public List<JobTitles> getJobTitlesList(ProjectParam projectParam);

	public List<ShiftTiming> getShiftTimingList(ProjectParam projectParam);

	public List<CompetencyMapping> getCompetencyMappingList(ProjectParam projectParam);

	public String getLastProjectId(EGenerate eGenerate);

	public boolean saveProjectDetails(Project project);

	public boolean saveAssignJobTitle(ProjectModel projectModel);

	public DataTableParam getCompentencyList(DataTableParam dtParam, boolean isCount);

	public String getTypeOfCompetencyById(String competencyName, String competencyType);

	public String getSkillById(String CompetencyId, String jobTitleId);

	public boolean updateCompetencySkills(Skills skills);

	public DataTableParam getSkillList(DataTableParam dtParam, boolean isCount);

	public boolean checkExistingProjectName(DataTableParam dtParam);

	public DataTableParam getUserFileList(DataTableParam dtParam, boolean isCount);

}
