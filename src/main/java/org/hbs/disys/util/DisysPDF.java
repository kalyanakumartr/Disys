package org.hbs.disys.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hbs.disys.bo.UserBo;
import org.hbs.disys.controller.JobModel;
import org.jsoup.Jsoup;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class DisysPDF
{

	UserBo	userBo;

	public static void main(String[] args) throws MalformedURLException, IOException
	{
		DisysPDF disyspdf = new DisysPDF();
		JobModel jobModel = disyspdf.getDefaultJobModel();
		disyspdf.generatePDF(null, jobModel);
	}

	public JobModel generatePDF(HttpServletRequest request, JobModel jobModel) throws MalformedURLException, IOException
	{

		String pdfFilePath = "";
		Document document = new Document();
		Font wingding = FontFactory.getFont(FontFactory.ZAPFDINGBATS, 10);
		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new CMYKColor(255, 0, 0, 0));
		Font blackBoldFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new CMYKColor(255, 255, 255, 255));
		Font blackFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new CMYKColor(255, 255, 255, 255));
		try
		{
			pdfFilePath = request.getServletContext().getRealPath("/output");
			File file = new File(pdfFilePath);
			if (file.exists() == false)
				file.mkdirs();

			pdfFilePath = pdfFilePath + "/DISYS-" + jobModel.getDivDepartment().replaceAll(",", "") + "-" + jobModel.getJobTitle().replaceAll(",", "") + ".pdf";
			FileOutputStream fileOutStream = new FileOutputStream(pdfFilePath);
			PdfWriter writer = PdfWriter.getInstance(document, fileOutStream);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			PdfWriter bosWriter = PdfWriter.getInstance(document, bos);
			document.open();

			// Set attributes here
			document.addAuthor("Thavaa Hariharan");
			document.addCreationDate();
			document.addCreator("HBS Product");
			document.addTitle("DISYS Profile Template");
			document.addSubject("DISYS Profile Template");

			// Set Disys logo here
			PdfPTable table = new PdfPTable(3); // 3 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 1f, 1f, 1f };
			table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(new Paragraph(""));
			cell1.setBorderColor(BaseColor.WHITE);
			cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph(""));
			cell2.setBorderColor(BaseColor.WHITE);
			cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// String url = request.getServletContext().getRealPath("/WEB-INF/classes/") +
			// "static/assets/img/logo.png";

			Paragraph paragraphUserName = new Paragraph("Name : " + jobModel.getUsers().getUsUserName());
			document.add(paragraphUserName);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();

			Paragraph paragraphDateLabel = new Paragraph("Date : " + dateFormat.format(date));
			document.add(paragraphDateLabel);

			String url = "target/HBS_DISYS-1/WEB-INF/classes/static/assets/img/logo.png";

			Image image = Image.getInstance(url);
			image.scalePercent(45f);
			image.setAlignment(Image.RIGHT);
			document.add(image);

			table.addCell(cell1);
			table.addCell(cell2);
			// table.addCell(cell3);

			document.add(table);

			// DISYS India
			Paragraph paragraphOne = new Paragraph("DISYS India", blackBoldFont);
			document.add(paragraphOne);

			// document.add(dateFormat.format(date).toString());

			// Job Description Form: -
			Paragraph paragraphTwo = new Paragraph("Job Description Form: - ", blackBoldFont);
			document.add(paragraphTwo);

			// add prime table
			PdfPTable primeTable = new PdfPTable(1);
			primeTable.setWidthPercentage(100); // Width 100%
			primeTable.setSpacingBefore(10f); // Space before table
			primeTable.setSpacingAfter(10f); // Space after table

			// Department
			Phrase depPhrase = new Phrase("Division / Department - ", blackBoldFont);
			depPhrase.add(new Chunk(jobModel.getDivDepartment(), blackFont));

			PdfPCell primeCell1 = new PdfPCell(depPhrase);
			primeCell1.setBorderColor(BaseColor.BLACK);
			primeCell1.setPaddingLeft(10);
			primeCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			primeCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// Location
			Phrase locPhrase = new Phrase("Location - ", blackBoldFont);
			locPhrase.add(new Chunk(jobModel.getLocation(), blackFont));

			PdfPCell primeCell2 = new PdfPCell(locPhrase);
			primeCell2.setBorderColor(BaseColor.BLACK);
			primeCell2.setPaddingLeft(10);
			primeCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			primeCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// Title
			Phrase titlePhrase = new Phrase("Job Title - ", blackBoldFont);
			titlePhrase.add(new Chunk(jobModel.getJobTitle(), blackFont));

			PdfPCell primeCell3 = new PdfPCell(titlePhrase);
			primeCell3.setBorderColor(BaseColor.BLACK);
			primeCell3.setPaddingLeft(10);
			primeCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			primeCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// Report To
			Phrase repToPhrase = new Phrase("Reports to - ", blackBoldFont);
			repToPhrase.add(new Chunk(jobModel.getReportsTo(), blackFont));

			PdfPCell primeCell4 = new PdfPCell(repToPhrase);
			primeCell4.setBorderColor(BaseColor.BLACK);
			primeCell4.setPaddingLeft(10);
			primeCell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			primeCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

			primeTable.addCell(primeCell1);
			primeTable.addCell(primeCell2);
			primeTable.addCell(primeCell3);
			primeTable.addCell(primeCell4);

			document.add(primeTable);

			// Job Summary Table
			PdfPTable jsTable = new PdfPTable(4); // 3 columns.
			jsTable.setWidthPercentage(100); // Width 100%
			// jsTable.setSpacingBefore(10f); // Space before table
			// jsTable.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] jsColumnWidths = { 1f, 1f, 1f, 1f };
			jsTable.setWidths(jsColumnWidths);

			// Grade
			Phrase gradePhrase = new Phrase("Level / Grade :  ", blackBoldFont);
			gradePhrase.add(new Chunk(jobModel.getGrade(), blackFont));

			PdfPCell jscell1 = new PdfPCell(gradePhrase);
			jscell1.setBorderColor(BaseColor.BLACK);
			jscell1.setPaddingLeft(10);
			jscell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			jscell1.setVerticalAlignment(Element.ALIGN_TOP);

			char checked = '6';
			char unchecked = 'q';

			// Type of Position
			Phrase topPhrase = new Phrase("Type of Position : ", blackBoldFont);
			topPhrase.add(new Chunk(jobModel.getPosition(), blackFont));
			// checked
			// topPhrase.add(new Chunk(String.valueOf(checked), wingding));
			// topPhrase.add(new Chunk(" Full-time ", blackBoldFont));
			// topPhrase.add(new Chunk("\n", blackBoldFont));
			// //unchecked
			// topPhrase.add(new Chunk(String.valueOf(unchecked), wingding));
			// topPhrase.add(new Chunk(" Part-time ", blackBoldFont));
			// topPhrase.add(new Chunk("\n", blackBoldFont));
			// //unchecked
			// topPhrase.add(new Chunk(String.valueOf(unchecked), wingding));
			// topPhrase.add(new Chunk(" Contractor ", blackBoldFont));

			PdfPCell jscell2 = new PdfPCell(topPhrase);
			jscell2.setBorderColor(BaseColor.BLACK);
			jscell2.setPaddingLeft(10);
			jscell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			jscell2.setVerticalAlignment(Element.ALIGN_TOP);

			// Hours Per Week
			Phrase hpwPhrase = new Phrase("Hours : ", blackBoldFont);
			// hpwPhrase.add(new Chunk(jobModel.getHours(), blackBoldFont));
			hpwPhrase.add(new Chunk(jobModel.getHours(), blackFont));

			PdfPCell jscell3 = new PdfPCell(hpwPhrase);
			jscell3.setBorderColor(BaseColor.BLACK);
			jscell3.setPaddingLeft(10);
			jscell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			jscell3.setVerticalAlignment(Element.ALIGN_TOP);
			// Shift timings
			Phrase stPhrase = new Phrase("ShiftTiming : ", blackBoldFont);
			stPhrase.add(new Chunk(jobModel.getShiftTimings(), blackFont));

			PdfPCell jscell4 = new PdfPCell(stPhrase);
			jscell4.setBorderColor(BaseColor.BLACK);
			jscell4.setPaddingLeft(10);
			jscell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			jscell4.setVerticalAlignment(Element.ALIGN_TOP);

			jsTable.addCell(jscell1);
			jsTable.addCell(jscell2);
			jsTable.addCell(jscell3);
			jsTable.addCell(jscell4);

			document.add(jsTable);

			// Details Table
			PdfPTable detailsTable = new PdfPTable(1);
			detailsTable.setWidthPercentage(100); // Width 100%
			detailsTable.setSpacingBefore(10f); // Space before table
			detailsTable.setSpacingAfter(10f); // Space after table
			detailsTable.setSplitLate(false);

			// GENERAL DESCRIPTION: -
			PdfPCell gdCell1 = new PdfPCell(new Paragraph("GENERAL DESCRIPTION: - ", blackBoldFont));
			gdCell1.setBorderColor(BaseColor.BLACK);
			gdCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			gdCell1.setPaddingLeft(10);
			gdCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			gdCell1.setVerticalAlignment(Element.ALIGN_TOP);
			gdCell1.setColspan(2);
			detailsTable.addCell(gdCell1);

			Phrase dgdPhrase = new Phrase();

			for (Map.Entry<String, StringBuffer> entry : jobModel.getGeneralDescription().entrySet())
			{
				dgdPhrase.add(new Chunk("\n" + entry.getKey(), blackBoldFont));
				dgdPhrase.add(new Chunk("\n" + entry.getValue(), blackFont));
				dgdPhrase.add(new Chunk("\n", blackFont));
			}

			PdfPCell gdCell2 = new PdfPCell(dgdPhrase);
			gdCell2.setBorderColor(BaseColor.BLACK);
			gdCell2.setPaddingLeft(10);
			gdCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			gdCell2.setVerticalAlignment(Element.ALIGN_TOP);
			detailsTable.addCell(gdCell2);

			// WORK EXPERIENCE: -
			PdfPCell gdCell3 = new PdfPCell(new Paragraph("WORK EXPERIENCE: - ", blackBoldFont));
			gdCell3.setBorderColor(BaseColor.BLACK);
			gdCell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			gdCell3.setPaddingLeft(10);
			gdCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			gdCell3.setVerticalAlignment(Element.ALIGN_TOP);
			detailsTable.addCell(gdCell3);

			// DETAIL WORK EXPERIENCE
			Phrase dwePhrase = new Phrase();

			for (Map.Entry<String, StringBuffer> entry : jobModel.getWorkExperience().entrySet())
			{

				dwePhrase.add(new Chunk("\n" + entry.getKey(), blackBoldFont));

				StringBuffer sb = new StringBuffer(entry.getValue());
				String str = sb.toString();

				org.jsoup.nodes.Document doc = Jsoup.parse(str);
				String body = doc.body().text();
				body = body.replaceAll("\\\\n", "\n");
				body = body.replaceAll("\\\\", "");

				dwePhrase.add(new Chunk("\n" + body, blackFont));
				dwePhrase.add(new Chunk("\n", blackFont));
			}

			PdfPCell gdCell4 = new PdfPCell(dwePhrase);
			gdCell4.setBorderColor(BaseColor.BLACK);
			gdCell4.setPaddingLeft(10);
			gdCell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			gdCell4.setVerticalAlignment(Element.ALIGN_TOP);

			detailsTable.addCell(gdCell4);

			// EDUCATIONAL / CERTIFICATION: -
			PdfPCell gdCell5 = new PdfPCell(new Paragraph("EDUCATIONAL / CERTIFICATION: - ", blackBoldFont));
			gdCell5.setBorderColor(BaseColor.BLACK);
			gdCell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			gdCell5.setPaddingLeft(10);
			gdCell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			gdCell5.setVerticalAlignment(Element.ALIGN_TOP);
			detailsTable.addCell(gdCell5);

			// Details EDUCATIONAL / CERTIFICATION: -
			PdfPCell gdCell6 = new PdfPCell(new Paragraph("Any Bachelor's Degree or equivalent educational experience ", blackFont));
			gdCell6.setBorderColor(BaseColor.BLACK);
			gdCell6.setPaddingLeft(10);
			gdCell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			gdCell6.setVerticalAlignment(Element.ALIGN_TOP);
			detailsTable.addCell(gdCell6);

			document.add(detailsTable);

			// Review Table
			PdfPTable revTable = new PdfPTable(1);
			revTable.setWidthPercentage(100); // Width 100%
			revTable.setSpacingBefore(10f); // Space before table
			revTable.setSpacingAfter(10f); // Space after table

			// REVIEWED BY: -
			Phrase reviewedByPhrase = new Phrase("REVIEWED BY :-    ", blackBoldFont);
			reviewedByPhrase.add(new Chunk(jobModel.getReviewedBy(), blackFont));

			PdfPCell primeCellReview = new PdfPCell(reviewedByPhrase);
			primeCellReview.setBorderColor(BaseColor.BLACK);
			primeCellReview.setBackgroundColor(BaseColor.LIGHT_GRAY);
			primeCellReview.setPaddingLeft(10);
			primeCellReview.setHorizontalAlignment(Element.ALIGN_LEFT);
			primeCellReview.setVerticalAlignment(Element.ALIGN_MIDDLE);

			revTable.addCell(primeCellReview);

			// APPROVED BY: -

			Phrase approvedByPhrase = new Phrase("APPROVED BY :-   ", blackBoldFont);
			approvedByPhrase.add(new Chunk(jobModel.getApprovedBy(), blackFont));

			PdfPCell primeCellApproved = new PdfPCell(approvedByPhrase);
			primeCellApproved.setBorderColor(BaseColor.BLACK);
			primeCellApproved.setBackgroundColor(BaseColor.LIGHT_GRAY);
			primeCellApproved.setPaddingLeft(10);
			primeCellApproved.setHorizontalAlignment(Element.ALIGN_LEFT);
			primeCellApproved.setVerticalAlignment(Element.ALIGN_MIDDLE);

			revTable.addCell(primeCellApproved);

			document.add(revTable);

			document.close();
			writer.close();
			bosWriter.close();
			jobModel.setPdfFilePath(pdfFilePath);
			jobModel.setByteArray(bos.toByteArray());

		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return jobModel;

	}

	public JobModel getDefaultJobModel()
	{

		JobModel jobModel = new JobModel();

		jobModel.setDivDepartment("XOM UC4");
		jobModel.setJobTitle("Project Lead");
		jobModel.setGrade("G4");
		jobModel.setExpYears("8 -10 Years");
		jobModel.setHours("40");

		Map<String, StringBuffer> generalDescription = new HashMap<String, StringBuffer>();
		StringBuffer gdAS = new StringBuffer(
				"1. Understand the system context and can derive high-level architecture and solution from the RFP and/or functional specification. Has ability to model the architecture in terms of layers, modules, services and components.2. Require help to choose the right technologies, products and framework. 3. Require guidelines and review for non-functional requirements and their parameters. 4. Prove the feasibility of a design (for example, by using POC, pilots, or prototypes)");
		generalDescription.put("Architecture and Solutioning", gdAS);
		StringBuffer gdTL = new StringBuffer(
				"1. Exhibits great skills and thorough understanding of a given technology space. He/she will know how to independently install, configure, use and deploy the products/framework in the given technology space 2. Demonstrates data modeling and design skills  3. Aware of user experience needs, including web and smart clients 4. Demonstrates knowledge of various middleware framework and products");
		generalDescription.put("Technology Landscape (breadth+depth)", gdTL);
		jobModel.setGeneralDescription(generalDescription);

		Map<String, StringBuffer> workExperience = new HashMap<String, StringBuffer>();
		StringBuffer weExp = new StringBuffer("8 -10 Years");
		workExperience.put("Experience :", weExp);
		StringBuffer weDes = new StringBuffer(
				"1. Exhibits great skills and thorough understanding of a given technology space. He/she will know how to independently install, configure, use and deploy the products/framework in the given technology space 2. Demonstrates data modeling and design skills  3. Aware of user experience needs, including web and smart clients 4. Demonstrates knowledge of various middleware framework and products");
		workExperience.put(" ", weDes);
		StringBuffer weDS = new StringBuffer("UC4 Scheduling, UC4 Monitoring, UC4 Troubleshooting, Unix commands");
		workExperience.put("Desired Skills: ", weDS);
		StringBuffer wePS = new StringBuffer("Control-M, Autosys, Atomic");
		workExperience.put("Preferred Skills: ", wePS);
		jobModel.setWorkExperience(workExperience);
		Map<String, StringBuffer> educationCert = new HashMap<String, StringBuffer>();
		StringBuffer education = new StringBuffer("Any Bachelor's Degree or equivalent educational experience");
		educationCert.put("Preferred Skills: ", education);
		jobModel.setEducationCert(educationCert);

		return jobModel;

	}

}
