package org.hbs.disys.model;

import java.util.ArrayList;
import java.util.List;

import org.hbs.disys.util.model.CommonLayout;
import org.hbs.disys.util.model.LayoutElements;

public class LayoutUserFile extends CommonLayout
{

	private static final long	serialVersionUID	= 1L;

	public static List<LayoutUserFile> getLayoutUserFile()
	{

		List<LayoutUserFile> layoutUserFileList = new ArrayList<LayoutUserFile>();
		LayoutUserFile layoutUserFile = new LayoutUserFile();
		layoutUserFile.setDisplayName("Created Date");
		layoutUserFile.setDisplayWidth(20);

		LayoutElements layoutElement = new LayoutElements();
		layoutElement.setIeElementId("UF0001");
		layoutElement.setIeBeanName("userfile");
		layoutElement.setIeDisplayProperty("createdDate");
		layoutElement.setIeDisplayPropertyGetter("getCreatedDate()");

		layoutUserFile.setLayoutElements(layoutElement);

		layoutUserFileList.add(layoutUserFile);

		layoutUserFile = new LayoutUserFile();
		layoutUserFile.setDisplayName("User Id");
		layoutUserFile.setDisplayWidth(20);

		layoutElement = new LayoutElements();
		layoutElement.setIeElementId("UF0002");
		layoutElement.setIeBeanName("userfile");
		layoutElement.setIeDisplayProperty("usUserId");
		layoutElement.setIeDisplayPropertyGetter("getUsUserId()");

		layoutUserFile.setLayoutElements(layoutElement);
		layoutUserFileList.add(layoutUserFile);

		layoutUserFile = new LayoutUserFile();
		layoutUserFile.setDisplayName("User Name");
		layoutUserFile.setDisplayWidth(20);

		layoutElement = new LayoutElements();
		layoutElement.setIeElementId("UF0003");
		layoutElement.setIeBeanName("userfile");
		layoutElement.setIeDisplayProperty("userName");
		layoutElement.setIeDisplayPropertyGetter("getUserName()");

		layoutUserFile.setLayoutElements(layoutElement);
		layoutUserFileList.add(layoutUserFile);

		layoutUserFile = new LayoutUserFile();
		layoutUserFile.setDisplayName("PdfFile Name");
		layoutUserFile.setDisplayWidth(40);

		layoutElement = new LayoutElements();
		layoutElement.setIeElementId("UF0004");
		layoutElement.setIeBeanName("userfile");
		layoutElement.setIeDisplayProperty("pdfFileName");
		layoutElement.setIeDisplayPropertyGetter("getPdfFileName()");

		layoutUserFile.setLayoutElements(layoutElement);

		layoutUserFileList.add(layoutUserFile);

		return layoutUserFileList;
	}
}
