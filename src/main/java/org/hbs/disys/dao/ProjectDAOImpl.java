package org.hbs.disys.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.hbs.disys.model.UserFile;
import org.hbs.disys.util.CommonHibernateSessionFactorySupport;
import org.hbs.disys.util.CommonValidator;
import org.hbs.disys.util.CustomLogger;
import org.hbs.disys.util.DataTableParam;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ProjectDAOImpl extends CommonHibernateSessionFactorySupport implements ProjectDAO
{
	private static final long	serialVersionUID	= 6642035851229058176L;
	private final CustomLogger	logger				= new CustomLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public List<Competency> getCompetencyList(ProjectParam projectParam)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			sbSelectQry.append(FROM + Competency.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : projectParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(projectParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry.append(projectParam._OrderBy);

			Query<Competency> query = session.createQuery((sbSelectQry.toString()));

			_SetNamedParameterValueFromSearchValueMap(projectParam, query);

			return query.list();
		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<Competency>(0);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompetencyMapping> getCompetencyMappingList(ProjectParam projectParam)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			sbSelectQry.append(FROM + CompetencyMapping.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : projectParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(projectParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry.append(projectParam._OrderBy);

			Query<CompetencyMapping> query = session.createQuery((sbSelectQry.toString())).setMaxResults(5).setFirstResult(0);

			_SetNamedParameterValueFromSearchValueMap(projectParam, query);

			return query.list();
		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<CompetencyMapping>(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobTitles> getJobTitlesList(ProjectParam projectParam)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			sbSelectQry.append(FROM + JobTitles.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : projectParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(projectParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry.append(projectParam._OrderBy);

			Query<JobTitles> query = session.createQuery((sbSelectQry.toString()));

			_SetNamedParameterValueFromSearchValueMap(projectParam, query);

			return query.list();
		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<JobTitles>(0);
	}

	@SuppressWarnings("unchecked")
	public List<Project> getProjectList(ProjectParam projectParam)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			sbSelectQry.append(FROM + Project.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : projectParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(projectParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry.append(projectParam._OrderBy);

			Query<Project> query = session.createQuery((sbSelectQry.toString()));

			_SetNamedParameterValueFromSearchValueMap(projectParam, query);

			return query.list();
		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<Project>(0);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShiftTiming> getShiftTimingList(ProjectParam projectParam)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			sbSelectQry.append(FROM + ShiftTiming.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : projectParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(projectParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry.append(projectParam._OrderBy);

			Query<ShiftTiming> query = session.createQuery((sbSelectQry.toString()));

			_SetNamedParameterValueFromSearchValueMap(projectParam, query);

			return query.list();
		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<ShiftTiming>(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLastProjectId(EGenerate eGenerate)
	{

		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append("select projectId from " + eGenerate.getClassName());
			sbQuery.append(" Order By projectId Desc");
			List<String> projectList = session.createQuery(sbQuery.toString()).setMaxResults(1).list();

			if (CommonValidator.isListFirstNotEmpty(projectList))
				return projectList.iterator().next();

		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}

		return "";
	}

	@Override
	public boolean saveProjectDetails(Project project)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			String compIdWork = primaryKey(null);
			String compIdDs = primaryKey(compIdWork);
			String compIdPs = primaryKey(compIdDs);
			Competency workComp = new Competency(compIdWork, project.getProjectName(), "Work");
			workComp.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			workComp.setCreatedUser(project.getCreatedUser());

			Competency dsComp = new Competency(compIdDs, project.getProjectName(), "DS");
			dsComp.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			dsComp.setCreatedUser(project.getCreatedUser());

			Competency psComp = new Competency(compIdPs, project.getProjectName(), "PS");
			psComp.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			psComp.setCreatedUser(project.getCreatedUser());

			session = getSessionFactory().openSession();
			_Txn = session.beginTransaction();

			// Save or Update Customer

			project.setStatus(true);
			project.setModifiedUser(null);
			project.setModifiedDate(null);

			session.save(project);

			session.save(workComp);
			session.save(dsComp);
			session.save(psComp);

			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					hibExcep.printStackTrace();
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public String getLastSkillId()
	{

		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append("select skillId from " + Skills.class.getCanonicalName());
			sbQuery.append(" where skillId like 'SKCM%' Order By skillId Desc");

			List<String> compList = session.createQuery(sbQuery.toString()).setMaxResults(1).list();

			if (CommonValidator.isListFirstNotEmpty(compList))
				return compList.iterator().next();
			else
				return "SKCM0000001";

		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}

		return "";
	}

	private String primaryKeySkills(String skillId)
	{
		String code = "SKCM";
		String lastCompetencyId = skillId == null ? getLastSkillId() : skillId;

		int runningKeyDigit = Integer.parseInt(lastCompetencyId.subSequence(code.length(), lastCompetencyId.length()).toString()) + 1;
		String strKeyDigit = String.valueOf(runningKeyDigit);
		int keyDigitLen = strKeyDigit.length();
		int keyLength = lastCompetencyId.length() - code.length();
		for (int i = 0; i < keyLength - keyDigitLen; i++)
		{
			strKeyDigit = "0" + strKeyDigit;
		}

		return code.trim() + strKeyDigit;
	}

	@SuppressWarnings("unchecked")
	public String getLastCompetencyId()
	{

		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append("select competencyId from " + Competency.class.getCanonicalName());
			sbQuery.append(" Order By competencyId Desc");

			List<String> compList = session.createQuery(sbQuery.toString()).setMaxResults(1).list();

			if (CommonValidator.isListFirstNotEmpty(compList))
				return compList.iterator().next();

		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}

		return "";
	}

	private String primaryKey(String compId)
	{
		String code = "COM";
		String lastCompetencyId = compId == null ? getLastCompetencyId() : compId;

		int runningKeyDigit = Integer.parseInt(lastCompetencyId.subSequence(code.length(), lastCompetencyId.length()).toString()) + 1;
		String strKeyDigit = String.valueOf(runningKeyDigit);
		int keyDigitLen = strKeyDigit.length();
		int keyLength = lastCompetencyId.length() - code.length();
		for (int i = 0; i < keyLength - keyDigitLen; i++)
		{
			strKeyDigit = "0" + strKeyDigit;
		}

		return code.trim() + strKeyDigit;
	}

	@Override
	public boolean saveAssignJobTitle(ProjectModel projectModel)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			String skillsId = null;
			Skills workSkills = null;
			Skills dsSkills = null;
			Skills psSkills = null;
			CompetencyMapping workCompMapping = null;
			CompetencyMapping dsCompMapping = null;
			CompetencyMapping psCompMapping = null;

			if (null != projectModel.getCompetencyWork())
			{
				skillsId = primaryKeySkills(skillsId);
				workSkills = new Skills(skillsId, projectModel.getWorkExperiencec(), "Work");
				workSkills.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				workSkills.setCreatedUser(projectModel.getCreatedUser());
				workCompMapping = new CompetencyMapping(projectModel.getCompetencyWork(), projectModel.getJobTitles(), workSkills);

			}

			if (null != projectModel.getCompetencyDS())
			{
				skillsId = primaryKeySkills(skillsId);
				dsSkills = new Skills(skillsId, projectModel.getDesiredSkill(), "DS");
				dsSkills.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				dsSkills.setCreatedUser(projectModel.getCreatedUser());

				dsCompMapping = new CompetencyMapping(projectModel.getCompetencyDS(), projectModel.getJobTitles(), dsSkills);
			}

			if (null != projectModel.getCompetencyPS())
			{
				skillsId = primaryKeySkills(skillsId);
				psSkills = new Skills(skillsId, projectModel.getPreferredSkill(), "PS");
				psSkills.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				psSkills.setCreatedUser(projectModel.getCreatedUser());

				psCompMapping = new CompetencyMapping(projectModel.getCompetencyPS(), projectModel.getJobTitles(), psSkills);
			}
			session = getSessionFactory().openSession();
			_Txn = session.beginTransaction();

			// Save or Update

			if (null != workSkills)
			{
				session.save(workSkills);
				session.save(workCompMapping);
			}
			if (null != dsSkills)
			{
				session.save(dsSkills);
				session.save(dsCompMapping);
			}
			if (null != psSkills)
			{
				session.save(psSkills);
				session.save(psCompMapping);
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					hibExcep.printStackTrace();
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public DataTableParam getCompentencyList(DataTableParam dtParam, boolean isCount)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			Query query = null;
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(isCount ? "Select Count(*)" : "");
			sbSelectQry.append(FROM + CompetencyMapping.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : dtParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(dtParam.searchCondtionMap.get(condKey));
			}
			if (isCount)
			{
				query = session.createQuery((sbSelectQry.toString()));
			}
			else
			{
				sbSelectQry.append(dtParam._OrderBy);
				if (dtParam.iDisplayLength != 0)
				{
					query = session.createQuery((sbSelectQry.toString())).setMaxResults(dtParam.iDisplayLength).setFirstResult(dtParam.iDisplayStart);
				}
				else
				{
					query = session.createQuery((sbSelectQry.toString()));
				}
			}

			_SetNamedParameterValueFromSearchValueMap(dtParam, query);

			if (isCount)
				dtParam.dataListCount = (long) query.uniqueResult();
			else
				dtParam.dataList = query.list();
		}
		catch (Exception excep)
		{
			dtParam.dataList.clear();
			dtParam.dataListCount = 0;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return dtParam;
	}

	@Override
	public String getTypeOfCompetencyById(String competencyName, String competencyType)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			if (CommonValidator.isNotNullNotEmpty(competencyName) && CommonValidator.isNotNullNotEmpty(competencyType))
			{
				sbSelectQry.append("select competencyId from " + Competency.class.getCanonicalName() + " where competencyName ='" + competencyName + "' and competencyType='" + competencyType);

				return session.createQuery(sbSelectQry.toString()).uniqueResult().toString();
			}

		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}

		return "";
	}

	@Override
	public String getSkillById(String CompetencyId, String jobTitleId)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			if (CommonValidator.isNotNullNotEmpty(CompetencyId) && CommonValidator.isNotNullNotEmpty(jobTitleId))
			{
				sbSelectQry.append("select skillId from " + CompetencyMapping.class.getCanonicalName() + " where competencyId ='" + CompetencyId + "' and jobTitleId='" + jobTitleId);

				return session.createQuery(sbSelectQry.toString()).uniqueResult().toString();
			}

		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}

		return "";
	}

	@Override
	public boolean updateCompetencySkills(Skills skills)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			session = getSessionFactory().openSession();
			_Txn = session.beginTransaction();

			// Save or Update

			if (null != skills)
				session.update(skills);

			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					hibExcep.printStackTrace();
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public DataTableParam getSkillList(DataTableParam dtParam, boolean isCount)
	{
		Session session = getSessionFactory().openSession();
		try
		{

			Query query = null;
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(isCount ? "Select Count(*)" : "");

			sbSelectQry.append(FROM + Skills.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : dtParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(dtParam.searchCondtionMap.get(condKey));
			}
			if (isCount)
			{
				query = session.createQuery((sbSelectQry.toString()));
			}
			else
			{
				sbSelectQry.append(dtParam._OrderBy);
				if (dtParam.iDisplayLength != 0)
				{
					query = session.createQuery((sbSelectQry.toString())).setMaxResults(dtParam.iDisplayLength).setFirstResult(dtParam.iDisplayStart);
				}
				else
				{
					query = session.createQuery((sbSelectQry.toString()));
				}
			}

			_SetNamedParameterValueFromSearchValueMap(dtParam, query);

			if (isCount)
				dtParam.dataListCount = (long) query.uniqueResult();
			else
				dtParam.dataList = query.list();
		}
		catch (Exception excep)
		{
			excep.printStackTrace();
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return dtParam;
	}

	@Override
	public boolean checkExistingProjectName(DataTableParam dtParam)
	{

		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append("Select PT.projectName From Project PT" + WHERE_1_1);

			for (String condKey : dtParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(dtParam.searchCondtionMap.get(condKey));
			}

			String jobTitle = (String) dtParam.getSearchValueMap().get("projectName");
			boolean status = (boolean) dtParam.getSearchValueMap().get("status");

			dtParam.dataListCount = session.createQuery((sbSelectQry.toString())).setParameter("projectName", jobTitle + "%").setParameter("status", status).list().size();
			if (dtParam.dataListCount == 0)
			{
				return true;

			}
			else
			{
				return false;

			}

		}
		catch (Exception excep)
		{
			dtParam.dataList.clear();
			excep.printStackTrace();
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return false;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public DataTableParam getUserFileList(DataTableParam dtParam, boolean isCount)
	{
		Session session = getSessionFactory().openSession();
		try
		{

			Query query = null;
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(isCount ? "Select Count(*)" : "");
			sbSelectQry.append(FROM + UserFile.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : dtParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(dtParam.searchCondtionMap.get(condKey));
			}
			if (isCount)
			{
				query = session.createQuery((sbSelectQry.toString()));
			}
			else
			{
				sbSelectQry.append(dtParam._OrderBy);
				if (dtParam.iDisplayLength != 0)
				{
					query = session.createQuery((sbSelectQry.toString())).setMaxResults(dtParam.iDisplayLength).setFirstResult(dtParam.iDisplayStart);
				}
				else
				{
					query = session.createQuery((sbSelectQry.toString()));
				}
			}

			_SetNamedParameterValueFromSearchValueMap(dtParam, query);

			if (isCount)
				dtParam.dataListCount = (long) query.uniqueResult();
			else
				dtParam.dataList = query.list();
		}
		catch (Exception excep)
		{
			dtParam.dataList.clear();
			dtParam.dataListCount = 0;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return dtParam;

	}
}
