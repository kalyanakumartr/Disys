package org.hbs.disys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.disys.util.CommonValidator;

@Entity
@Table(name = "userfile")
public class UserFile extends CommonBeanFields implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	private String				ufAutoId;
	private String				usEmployeeId;
	private String				usUserId;
	private String				userName;
	private String				pdfFileName;
	// private Timestamp usCreatedDate;

	private String				createdDateDisplay;

	public UserFile()
	{

	}

	public UserFile(String ufAutoId, String usEmployeeId, String usUserId, String userName, String pdfFileName, String createdDateDisplay)
	{
		super();
		this.ufAutoId = ufAutoId;
		this.usEmployeeId = usEmployeeId;
		this.usUserId = usUserId;
		this.userName = userName;
		this.pdfFileName = pdfFileName;
		this.createdDateDisplay = createdDateDisplay;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ufAutoId")
	public String getUfAutoId()
	{
		return ufAutoId;
	}

	public void setUfAutoId(String ufAutoId)
	{
		this.ufAutoId = ufAutoId;
	}

	@Column(name = "usEmployeeId")
	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	@Column(name = "userName")
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name = "pdfFileName")
	public String getPdfFileName()
	{
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName)
	{
		this.pdfFileName = pdfFileName;
	}

	@Column(name = "usUserId")
	public String getUsUserId()
	{
		return usUserId;
	}

	public void setUsUserId(String usUserId)
	{
		this.usUserId = usUserId;
	}

	/*
	 * @Transient public Timestamp getUsCreatedDate() { return usCreatedDate; } public void
	 * setUsCreatedDate(Timestamp usCreatedDate) { if
	 * (CommonValidator.isNotNullNotEmpty(getCreatedDate())) { this.usCreatedDate =
	 * getCreatedDate(); } }
	 */

	@Transient
	public String getCreatedDateDisplay()
	{
		if (CommonValidator.isNotNullNotEmpty(getCreatedDate()))
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
			createdDateDisplay = simpleDateFormat.format(getCreatedDate());
		}

		return createdDateDisplay;
	}

	public void setCreatedDateDisplay(String createdDateDisplay)
	{
		this.createdDateDisplay = createdDateDisplay;
	}

}
