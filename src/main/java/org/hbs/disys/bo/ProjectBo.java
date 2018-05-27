package org.hbs.disys.bo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hbs.disys.controller.IProject.EGenerate;
import org.hbs.disys.controller.JobModel;
import org.hbs.disys.controller.ProjectModel;
import org.hbs.disys.controller.ReadModel;
import org.hbs.disys.model.Competency;
import org.hbs.disys.model.CompetencyMapping;
import org.hbs.disys.model.JobTitles;
import org.hbs.disys.model.Project;
import org.hbs.disys.model.ProjectParam;
import org.hbs.disys.model.ShiftTiming;
import org.hbs.disys.model.Skills;
import org.hbs.disys.util.DataTableParam;

public interface ProjectBo
{
	public List<Competency> getCompetencyListByType(ProjectParam projectParam);

	public Competency getCompetencyByName(ProjectParam projectParam);

	public List<Project> getProjectList(ProjectParam projectParam);

	public Project getProjectById(ProjectParam projectParam);

	public List<JobTitles> getJobTitlesList(ProjectParam projectParam);

	public JobTitles getJobTitlesById(ProjectParam projectParam);

	public List<ShiftTiming> getShiftTimingList(ProjectParam projectParam);

	public List<String> getGradeLeaderList(ProjectParam projectParam);

	public JobModel getDisysPDF(HttpServletRequest request, ReadModel readModel);

	public String getLastProjectId(EGenerate eGenerate);

	public boolean saveProjectDetails(Project project);

	public List<Competency> getCompetencyList(ProjectParam projectParam);

	public List<CompetencyMapping> getCompetencyMappingList(ProjectParam projectParam);

	public boolean saveAssignJobTitle(ProjectModel projectModel);

	public Map<String, StringBuffer> getGeneralExperienceDB(ReadModel readModel);

	public DataTableParam getCompentencyList(DataTableParam dtParam, boolean isCount);

	public boolean updateCompetencySkills(Skills skills);

	public DataTableParam getSkillList(DataTableParam dtParam, boolean isCount);

	public boolean checkExistingProjectName(DataTableParam dtParam);

	public DataTableParam getUserFileList(DataTableParam dtParam, boolean isCount);
}
