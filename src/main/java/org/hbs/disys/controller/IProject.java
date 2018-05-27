package org.hbs.disys.controller;

import java.io.Serializable;

import org.hbs.disys.bo.ProjectBo;
import org.hbs.disys.util.EnumInterface;

public interface IProject extends Serializable
{
	public enum EGenerate implements EnumInterface
	{
		Project("P", "org.hbs.disys.model.Project"), JobTitles("J00", "org.hbs.disys.model.JobTitles"), Skills("S00", "org.hbs.disys.model.Skills");

		String	code;
		String	className;

		EGenerate(String code, String className)
		{
			this.code = code;
			this.className = className;
		}

		public String getClassName()
		{
			return className;
		}

		public String primaryKey(ProjectBo projectBo)
		{
			String lastProjectId = projectBo.getLastProjectId(this);

			int runningKeyDigit = Integer.parseInt(lastProjectId.subSequence(code.length(), lastProjectId.length()).toString()) + 1;
			String strKeyDigit = String.valueOf(runningKeyDigit);
			int keyDigitLen = strKeyDigit.length();

			int keyLength = lastProjectId.length() - code.length();
			for (int i = 0; i < (keyLength - keyDigitLen); i++)
			{
				strKeyDigit = "0" + strKeyDigit;
			}

			return code.trim() + strKeyDigit;
		}

	}

	public String	INDEX								= "/";
	public String	LOGIN								= "/d56b699830e77ba53855679cb1d";
	public String	LOGOUT								= "/d56b67ba538599830e756791dcb";
	public String	HOME								= "/a6c241b8797f52e1e77317b96a2";

	public String	GENERATE_PDF						= "/b282328146ac6afebaa8acd80e7";
	public String	ACCESS_DENIED						= "/6ac6afebaab282328148acde780";

	public String	SKILLS								= "/a658279f9b983958149f31e4d84";
	public String	COMPSKILLS							= "/b658279f9b98366666666666666";
	public String	ASSIGN_JOBTITLE						= "/f91709c28e7e1a9127df3d234cc";
	public String	PROJECT								= "/f86faa6bbf9ac94a7e459509a20";

	public String	ASSIGN_JOBTITLE_LIST				= "/c832398520c6db9d8dd62cf17353";

	public String	HOME_PAGE							= "web/content/home";
	public String	LOGIN_PAGE							= "web/content/login";
	public String	INDEX_PAGE							= "web/content/index";
	public String	RESULT_PAGE							= "web/content/result";

	public String	SKILL_PAGE							= "web/content/skills";
	public String	COMPETENCYSKILLS_VIEW__PAGE			= "web/content/skills";
	public String	COMPSKILLS_PAGE						= "web/content/compSkills";
	public String	ASSIGN_JOB_PAGE						= "web/content/assignJobTitle";
	public String	PROJECT_PAGE						= "web/content/project";

	public String	ASSIGN_JOBTITLE_LIST_PAGE			= "web/content/assignjobtitlelist";

	public String	USER_AND_PDF_FILE_DETAILS			= "web/content/users-pdffile-details";

	public String	SAVE_SKILLS							= "/a6c241b8797f52e1eADDskills";

	public String	SAVE_PROJECT						= "/a6c241b8797f52e1eADDproject";

	public String	SAVE_ASSIGN_JOBTITLE				= "/a6c241b8797f52e1eADDJobTitle";

	public String	COMPETENCYSKILLS					= "/a6c241b8797f52e1eSEARCHCOMPETENCY";

	public String	COMPETENCYSKILLS_SEARCH				= "/a6c241b8797fCOMPETENCYSKILLS_SEARCH";

	public String	COMP_SKILLS							= "/a658279f9b9839581COMPSKILLS";

	public String	DATATABLE_COMP_SKILLS_PAGE			= "web/content/datatablecompSkills";

	public String	ASSIGN_JOBTITLE_WITH_LIST_AJAX		= "getProjectandJobTitleIdAjax";

	public String	SAVE_AND_UPDATE_COMPETENCIES		= "a5a57f2a4d53a08ba62cb21358b";

	public String	SEARCH_EXPERINCE_GRADE_BY_JOBTITLE	= "bed79dfc2dd159f4800d763e4615e982508";

	public String	SHOW_USER_FILE_DETAILS				= "/bed79dfc2dd159f4800d763e4615e9fi8le";

	public String	SHOW_USER_FILE_DETAILS_AJAX			= "getUserPDFfileAjax";

}
