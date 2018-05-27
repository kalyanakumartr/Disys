package org.hbs.disys.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.hbs.disys.bo.ProjectBo;
import org.hbs.disys.bo.UserBo;
import org.hbs.disys.model.CompetencyMapping;
import org.hbs.disys.model.IUsers;
import org.hbs.disys.model.JobTitles;
import org.hbs.disys.model.LayoutUserFile;
import org.hbs.disys.model.Project;
import org.hbs.disys.model.ProjectParam;
import org.hbs.disys.model.Skills;
import org.hbs.disys.model.UserFile;
import org.hbs.disys.model.UserParam;
import org.hbs.disys.util.CommonValidator;
import org.hbs.disys.util.DataTableDynamicColumnDefs;
import org.hbs.disys.util.DataTableDynamicColumns;
import org.hbs.disys.util.DataTableObject;
import org.hbs.disys.util.DataTableParam;
import org.hbs.disys.util.IConstProperty;
import org.hbs.disys.util.IParam.ENamed;
import org.hbs.disys.util.model.CommonLayout;
import org.hbs.disys.util.model.LayoutElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ProjectController implements IProject, IConstProperty
{
	private static final long	serialVersionUID	= -5341372055897031819L;

	@Autowired
	ProjectBo					projectBo;
	@Autowired
	UserBo						userBo;

	ReadModel					readModel			= new ReadModel();

	@RequestMapping(GENERATE_PDF)
	public @ResponseBody ResponseEntity<byte[]> generatePDF(HttpServletRequest request, @ModelAttribute("readModel") ReadModel readModel) throws InvocationTargetException
	{
		System.out.println("------- Project Generate Pdf ----------------------" + readModel.getProject());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username /user id
		UserParam userParam = new UserParam();
		userParam.userId = name;
		ENamed.EqualTo.param_AND(userParam, "usUserId", userParam.userId);
		ENamed.EqualTo.param_AND(userParam, "status", true);
		IUsers user = userBo.getUser(userParam);

		readModel.setUsers(user);

		JobModel jobModel = projectBo.getDisysPDF(request, readModel);
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.parseMediaType("application/pdf"));

		headers.add("content-disposition", "inline;filename=" + jobModel.getPdfFilePath());
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(jobModel.getByteArray(), headers, HttpStatus.OK);

		try
		{
			UserFile userFile = new UserFile();
			userFile.setUsEmployeeId(jobModel.getUsers().getUsEmployeeId());
			userFile.setUsUserId(jobModel.getUsers().getUsUserId());
			userFile.setUserName(jobModel.getUsers().getUsUserName());
			userFile.setPdfFileName("DISYS-" + jobModel.getDivDepartment() + "-" + jobModel.getJobTitle() + ".pdf");
			userFile.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			userFile.setCreatedUser(user);

			userBo.saveUserFile(userFile);

		}
		catch (Exception excep)
		{
			excep.printStackTrace();
		}

		return response;
	}

	public String getExperienceAndGrade(String jobTitleId)
	{
		ProjectParam projectParam = new ProjectParam();
		projectParam.jobTitleId = jobTitleId;
		List<JobTitles> jobTitleList = projectBo.getJobTitlesList(projectParam);
		String expGrad = "";
		for (JobTitles jobTitle : jobTitleList)
		{
			expGrad = jobTitle.getExperience() + "," + jobTitle.getGrade();
		}
		return expGrad;

	}

	@RequestMapping(HOME)
	public ModelAndView getHome(HttpServletRequest request)
	{
		ModelAndView modelView = new ModelAndView(HOME_PAGE);
		ProjectParam projectParam = new ProjectParam();

		modelView.addObject("projectList", projectBo.getProjectList(projectParam));
		modelView.addObject("jobTitleList", projectBo.getJobTitlesList(projectParam));
		modelView.addObject("shiftTimingList", projectBo.getShiftTimingList(projectParam));
		modelView.addObject("gradeList", projectBo.getGradeLeaderList(projectParam));

		return modelView;
	}

	@RequestMapping(LOGIN)
	public ModelAndView getLogin(HttpServletRequest request)
	{

		return new ModelAndView(LOGIN_PAGE);
	}

	public ProjectBo getProjectBo()
	{
		return projectBo;
	}

	@RequestMapping(SKILLS)
	public ModelAndView getSkillPage()
	{

		ModelAndView modelView = new ModelAndView(SKILL_PAGE);

		ProjectParam projectParam = new ProjectParam();
		List<Project> projectList = (List<Project>) projectBo.getProjectList(projectParam);
		List<String> strings = new ArrayList<>(projectList.size());
		for (Object object : projectList)
		{
			strings.add(Objects.toString(object, null));
		}
		modelView.addObject("projectModel", new ProjectModel());

		modelView.addObject("formResumeFilterURL", COMPSKILLS);
		modelView.addObject("projectList", projectBo.getProjectList(projectParam));
		modelView.addObject("jobTitleList", projectBo.getJobTitlesList(projectParam));

		readModel = new ReadModel();
		readModel.setGrade("G4");
		readModel.setJobTitle("JOB0002");
		modelView.addObject("techCompetencyList", projectBo.getGeneralExperienceDB(readModel));

		readModel = new ReadModel();
		readModel.setGrade("G6");
		readModel.setJobTitle("JOB0006");
		modelView.addObject("leadCompetencyList", projectBo.getGeneralExperienceDB(readModel));

		projectParam.searchCondtionMap.clear();
		projectParam.searchValueMap.clear();

		projectParam.projectId = readModel.getProject();
		projectParam.jobTitleId = readModel.getJobTitle();

		return modelView;
	}

	@RequestMapping(value = SAVE_SKILLS, method = RequestMethod.POST)
	public ModelAndView saveSkills(HttpServletRequest request, HttpServletResponse response, @Valid ProjectModel projectModel)
	{
		ModelAndView modelAndView = new ModelAndView(SKILL_PAGE);
		ProjectParam projectParam = new ProjectParam();
		if (projectBo.saveAssignJobTitle(projectModel))
		{
			modelAndView.addObject("formResumeFilterURL", COMPSKILLS);
			modelAndView.addObject("projectList", projectBo.getProjectList(projectParam));
			modelAndView.addObject("jobTitleList", projectBo.getJobTitlesList(projectParam));

			modelAndView.addObject("competencyList", projectBo.getCompetencyList(projectParam));
			modelAndView.addObject("leadCompetencyList", projectBo.getCompetencyList(projectParam));
		}
		else
		{
			modelAndView.addObject("projectModel", projectModel);
			modelAndView.addObject("message", "Competency Value to Project failed to Save, Please Try again");
		}
		modelAndView.addObject("projectList", projectBo.getProjectList(projectParam));
		modelAndView.addObject("jobTitleList", projectBo.getJobTitlesList(projectParam));
		modelAndView.setViewName(ASSIGN_JOBTITLE);
		return modelAndView;
	}

	@RequestMapping(COMPSKILLS)
	public ModelAndView getCompSkillPage(HttpServletRequest request, @ModelAttribute("readModel") ReadModel readModel)
	{
		ProjectParam projectParam = new ProjectParam();
		projectParam.projectId = readModel.getProject();
		projectParam.jobTitleId = readModel.getJobTitle();
		ModelAndView modelView = new ModelAndView(COMPSKILLS_PAGE);
		modelView.addObject("compSkillsList", projectBo.getCompetencyMappingList(projectParam));

		return modelView;
	}

	@RequestMapping(INDEX)
	public String index()
	{

		return "redirect:" + LOGIN;
	}

	@RequestMapping(value = SAVE_PROJECT, method = RequestMethod.POST)
	public String saveProject(HttpServletRequest request, HttpServletResponse response, @Valid Project project, final RedirectAttributes redirectAttributes)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username /user id
		UserParam userParam = new UserParam();
		userParam.userId = name;

		ENamed.EqualTo.param_AND(userParam, "usUserId", userParam.userId);
		ENamed.EqualTo.param_AND(userParam, "status", true);

		IUsers user = userBo.getUser(userParam);

		if (CommonValidator.isNotNullNotEmpty(project))
		{

			DataTableParam dtParam = new DataTableParam();
			ENamed.Like.param_AND(dtParam, "projectName", project.getProjectName());
			ENamed.EqualTo.param_AND(dtParam, "status", true);

			if (projectBo.checkExistingProjectName(dtParam))
			{
				project.setProjectId(EGenerate.Project.primaryKey(projectBo));
				project.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				project.setCreatedUser(user);

				if (projectBo.saveProjectDetails(project))
				{
					redirectAttributes.addFlashAttribute("message", "Project Saved Successfullly");
					redirectAttributes.addFlashAttribute("css", "success");

				}
				else
				{
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("message", "Project Saved failed, Please Try again");
				}

			}
			else
			{

				redirectAttributes.addFlashAttribute("message", "Already this Project Name is Exist, Please Try again Another Project Name");
				redirectAttributes.addFlashAttribute("css", "warning");
			}
		}

		return "redirect:" + PROJECT;
	}

	@RequestMapping(value = ASSIGN_JOBTITLE_LIST, method = RequestMethod.GET)
	public ModelAndView preAssignJobTitleListPage()
	{
		ModelAndView modelView = new ModelAndView(ASSIGN_JOBTITLE_LIST_PAGE);

		ProjectParam projectParam = new ProjectParam();
		List<Project> projectList = (List<Project>) projectBo.getProjectList(projectParam);
		List<String> strings = new ArrayList<>(projectList.size());
		for (Object object : projectList)
		{
			strings.add(Objects.toString(object, null));
		}
		modelView.addObject("projectModel", new ProjectModel());

		modelView.addObject("getProjectandJobTitleIdAjax", ASSIGN_JOBTITLE_WITH_LIST_AJAX);

		modelView.addObject("projectList", projectBo.getProjectList(projectParam));
		modelView.addObject("jobTitleList", projectBo.getJobTitlesList(projectParam));

		modelView.addObject("columnsList", DataTableDynamicColumns.getDynamicColumns(LayoutCompetency.getLayoutCompetency()));
		modelView.addObject("columnDefsList", DataTableDynamicColumnDefs.getDynamicColumnDefs(LayoutCompetency.getLayoutCompetency()));

		return modelView;
	}

	public void setProjectBo(ProjectBo projectBo)
	{
		this.projectBo = projectBo;
	}

	@RequestMapping(ASSIGN_JOBTITLE)
	public ModelAndView assignJobPage()
	{

		ModelAndView modelView = new ModelAndView(ASSIGN_JOB_PAGE);
		ProjectParam projectParam = new ProjectParam();
		modelView.addObject("projectModel", new ProjectModel());
		modelView.addObject("projectList", projectBo.getProjectList(projectParam));
		modelView.addObject("jobTitleList", projectBo.getJobTitlesList(projectParam));

		return modelView;
	}

	@RequestMapping(value = SAVE_ASSIGN_JOBTITLE, method = RequestMethod.POST)
	public String saveAssignJobTitle(HttpServletRequest request, HttpServletResponse response, @Valid ProjectModel projectModel, final RedirectAttributes redirectAttributes)
	{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username /user id
		UserParam userParam = new UserParam();
		userParam.userId = name;

		ENamed.EqualTo.param_AND(userParam, "usUserId", userParam.userId);
		ENamed.EqualTo.param_AND(userParam, "status", true);

		IUsers user = userBo.getUser(userParam);
		projectModel.setCreatedUser(user.getCreatedUser());

		if (projectBo.saveAssignJobTitle(projectModel))
		{

			redirectAttributes.addFlashAttribute("message", "Assign Jobtitle to Project Successfully");
			redirectAttributes.addFlashAttribute("css", "success");
		}
		else
		{

			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("message", "Assign Jobtitle to Project failed, Please Try again");

		}

		return "redirect:" + ASSIGN_JOBTITLE;
	}

	@RequestMapping(value = PROJECT, method = RequestMethod.GET)
	public ModelAndView preProjectPage()
	{
		ModelAndView modelView = new ModelAndView(PROJECT_PAGE);

		modelView.addObject("project", new Project());

		return modelView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = ASSIGN_JOBTITLE_WITH_LIST_AJAX, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getProjectandJobTitleIdAjax(HttpServletRequest request)
	{

		DataTableParam dtParam = DataTableParam.getDataTableParamsFromRequest(request);

		Object projectId = dtParam.searchValueMap.get("projectId");
		Object jobTitleId = dtParam.searchValueMap.get("jobTitleId");

		if (CommonValidator.isNotNullNotEmpty(projectId) && CommonValidator.isNotNullNotEmpty(jobTitleId))
		{

			ENamed.EqualTo.param_AND(dtParam, "competency.competencyName", projectId);
			ENamed.EqualTo.param_AND(dtParam, "jobTitles.jobTitleId", jobTitleId);

			List<CompetencyMapping> competencyMapList = (List<CompetencyMapping>) projectBo.getCompentencyList(dtParam, false).dataList;

			int competencyMapListCount = (int) projectBo.getCompentencyList(dtParam, true).dataListCount;

			List<List<String>> mDataList = DataTableDynamicColumns.getJSONFromObject(LayoutCompetency.getLayoutCompetency(), competencyMapList.toArray(new Object[competencyMapList.size()]));

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(mDataList);
			dataTableObject.setiTotalDisplayRecords(competencyMapListCount);
			dataTableObject.setiTotalRecords(competencyMapListCount);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			StringBuilder sb = new StringBuilder(gson.toJson(dataTableObject));

			return sb.toString();
		}
		return "";

	}

	@RequestMapping(value = SEARCH_EXPERINCE_GRADE_BY_JOBTITLE)
	public @ResponseBody String searchExperienceAndGradeUsingJobTitleByAjax(HttpServletRequest request)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		DataTableParam dtParam = DataTableParam.getDataTableParamsFromRequest(request);

		String jobTitleId = (String) dtParam.searchValueMap.get("jobTitleId");

		ProjectParam projectParam = new ProjectParam();
		ENamed.EqualTo.param_AND(projectParam, "jobTitleId", jobTitleId);
		List<JobTitles> jobTitleList = projectBo.getJobTitlesList(projectParam);
		if (CommonValidator.isListFirstNotEmpty(jobTitleList))
			return gson.toJson(jobTitleList.iterator().next());
		return "";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = SAVE_AND_UPDATE_COMPETENCIES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String saveAndUpdateCompetenciesByAjax(HttpServletRequest request)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		DataTableParam dtParam = DataTableParam.getDataTableParamsFromRequest(request);

		String action = (String) dtParam.searchValueMap.get("status");

		String projectId = (String) dtParam.searchValueMap.get("projectId");
		String jobTitleId = (String) dtParam.searchValueMap.get("jobTitleId");

		Object preferredSkillId = dtParam.searchValueMap.get("preferredSkillId");
		Object desiredSkillId = dtParam.searchValueMap.get("desiredSkillId");
		Object workSkillId = dtParam.searchValueMap.get("workSkillId");

		Object workExperience = dtParam.searchValueMap.get("workExperience");
		Object desiredSkill = dtParam.searchValueMap.get("desiredSkill");
		Object preferredSkill = dtParam.searchValueMap.get("preferredSkill");

		String workExperienceData = (String) workExperience;
		workExperienceData = new String(Base64.decodeBase64(workExperienceData));
		workExperienceData = workExperienceData.replaceAll("^\"|\"$", "");

		String desiredSkillData = (String) desiredSkill;
		desiredSkillData = new String(Base64.decodeBase64(desiredSkillData));
		desiredSkillData = desiredSkillData.replaceAll("^\"|\"$", "");

		String preferredSkillData = (String) preferredSkill;
		preferredSkillData = new String(Base64.decodeBase64(preferredSkillData));
		preferredSkillData = preferredSkillData.replaceAll("^\"|\"$", "");

		String workSkillIdValue = (String) workSkillId;

		String desiredSkillIdValue = (String) desiredSkillId;

		String preferredSkillIdValue = (String) preferredSkillId;

		if (CommonValidator.isNotNullNotEmpty(action) && CommonValidator.isEqual("save", action))
		{

			if (CommonValidator.isNullOrEmpty(workSkillId) || CommonValidator.isNullOrEmpty(preferredSkillId) || CommonValidator.isNullOrEmpty(desiredSkillId))
			{

				ProjectModel projectModel = new ProjectModel();
				if (CommonValidator.isNotNullNotEmpty(projectId) && CommonValidator.isNotNullNotEmpty(jobTitleId))
				{
					projectModel.setProjectId(projectId);
					projectModel.setJobTitleId(jobTitleId);
				}

				projectModel.setWorkExperiencec(workExperienceData);
				projectModel.setDesiredSkill((String) desiredSkillData);
				projectModel.setPreferredSkill((String) preferredSkillData);

				if (CommonValidator.isNotNullNotEmpty(projectModel))
				{
					if (projectBo.saveAssignJobTitle(projectModel))
					{
						return gson.toJson("Assign Jobtitle to Project Successfully" + HASH + "success");
					}
					else
					{
						return gson.toJson("Assign Jobtitle to Project failed, Please Try again" + HASH + "danger");

					}

				}

			}

		}
		if (CommonValidator.isNotNullNotEmpty(action) && CommonValidator.isEqual("update", action))
		{
			if (CommonValidator.isNotNullNotEmpty(workSkillIdValue))
			{
				dtParam.dataList.clear();

				ArrayList<String> skillIdList = new ArrayList<String>(0);
				skillIdList.add(workSkillIdValue);
				skillIdList.add(desiredSkillIdValue);
				skillIdList.add(preferredSkillIdValue);

				ENamed.In.param_AND(dtParam, "skillId", skillIdList);

				List<Skills> skillList = (List<Skills>) projectBo.getSkillList(dtParam, false).dataList;

				for (Skills skill : skillList)
				{
					if (CommonValidator.isNotNullNotEmpty(skill.getSkillType()))
					{
						if (skill.getSkillType().equals("Work"))
						{

							skill.setSkillDesc(workExperienceData);
							projectBo.updateCompetencySkills(skill);
						}
						else if (skill.getSkillType().equals("DS"))
						{

							skill.setSkillDesc(desiredSkillData);
							projectBo.updateCompetencySkills(skill);
						}
						else if (skill.getSkillType().equals("PS"))
						{

							skill.setSkillDesc(preferredSkillData);
							projectBo.updateCompetencySkills(skill);

							return gson.toJson("Assign Jobtitle with Project Competency Update Successfully" + HASH + "success");

						}
						else
						{
							return gson.toJson("Assign Jobtitle with Project Competency Update failed, Please Try again" + HASH + "danger");
						}

					}
				}

			}

		}

		return "";

	}

	@RequestMapping(value = SHOW_USER_FILE_DETAILS, method = RequestMethod.GET)
	public ModelAndView preShowUserFileDetailsPage()
	{
		ModelAndView modelView = new ModelAndView(USER_AND_PDF_FILE_DETAILS);

		modelView.addObject("getProjectandJobTitleIdAjax", SHOW_USER_FILE_DETAILS_AJAX);

		modelView.addObject("columnsList", DataTableDynamicColumns.getDynamicColumns(LayoutUserFile.getLayoutUserFile()));
		modelView.addObject("columnDefsList", DataTableDynamicColumnDefs.getDynamicColumnDefs(LayoutUserFile.getLayoutUserFile()));

		return modelView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = SHOW_USER_FILE_DETAILS_AJAX, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getShowUserFileDetailsByAjax(HttpServletRequest request)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username /user id
		UserParam userParam = new UserParam();
		userParam.userId = name;
		ENamed.EqualTo.param_AND(userParam, "usUserId", userParam.userId);
		ENamed.EqualTo.param_AND(userParam, "status", true);

		IUsers user = userBo.getUser(userParam);

		DataTableParam dtParam = DataTableParam.getDataTableParamsFromRequest(request);
		if (CommonValidator.isNotNullNotEmpty(user.getUsEmployeeId()))
		{
			if (CommonValidator.isNotEqual(user.getUsEmployeeId(), "SuperAdmin"))
			{
				ENamed.EqualTo.param_AND(dtParam, "usEmployeeId", user.getUsEmployeeId());

			}

			List<UserFile> userFileList = (List<UserFile>) projectBo.getUserFileList(dtParam, false).dataList;
			int userFileListCount = (int) projectBo.getUserFileList(dtParam, true).dataListCount;

			List<List<String>> mDataList = DataTableDynamicColumns.getJSONFromObject(LayoutUserFile.getLayoutUserFile(), userFileList.toArray(new Object[userFileList.size()]));

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(mDataList);
			dataTableObject.setiTotalDisplayRecords(userFileListCount);
			dataTableObject.setiTotalRecords(userFileListCount);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			StringBuilder sb = new StringBuilder(gson.toJson(dataTableObject));

			return sb.toString();
		}
		return "";
	}
}

class LayoutCompetency extends CommonLayout
{

	private static final long	serialVersionUID	= -3342738846120975819L;

	public static List<LayoutCompetency> getLayoutCompetency()
	{
		List<LayoutCompetency> layoutCompetencyList = new ArrayList<LayoutCompetency>();
		LayoutCompetency layoutCompetency = new LayoutCompetency();
		layoutCompetency.setDisplayName("Competency Type");
		layoutCompetency.setDisplayWidth(50);

		LayoutElements layoutElement = new LayoutElements();
		layoutElement.setIeElementId("COM0001");
		layoutElement.setIeBeanName("competency");
		layoutElement.setIeDisplayProperty("competency.competencyType");
		layoutElement.setIeDisplayPropertyGetter("getCompetency().getCompetencyType()");

		layoutCompetency.setLayoutElements(layoutElement);

		layoutCompetencyList.add(layoutCompetency);

		layoutCompetency = new LayoutCompetency();
		layoutCompetency.setDisplayName("Competency Value");
		layoutCompetency.setDisplayWidth(50);

		layoutElement = new LayoutElements();
		layoutElement.setIeElementId("COM0002");
		layoutElement.setIeBeanName("skills");
		layoutElement.setIeDisplayProperty("skills.skillDesc");
		layoutElement.setIeDisplayPropertyGetter("getSkills().getSkillDesc()");

		layoutCompetency.setLayoutElements(layoutElement);

		layoutCompetencyList.add(layoutCompetency);

		layoutCompetency = new LayoutCompetency();
		layoutCompetency.setDisplayName("Skill Id");
		layoutCompetency.setDisplayWidth(50);

		layoutElement = new LayoutElements();
		layoutElement.setIeElementId("COM003");
		layoutElement.setIeBeanName("skills");
		layoutElement.setIeDisplayProperty("skills.skillId");
		layoutElement.setIeDisplayPropertyGetter("getSkills().getSkillId()");

		layoutCompetency.setLayoutElements(layoutElement);

		layoutCompetencyList.add(layoutCompetency);

		return layoutCompetencyList;
	}

}
