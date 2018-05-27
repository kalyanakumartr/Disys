package org.hbs.disys.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hbs.disys.util.PropertyEnumUtil.EGeneral;

public class CommonUtil implements IConstProperty
{

	public static final long	serialVersionUID	= 5975112215738796905L;

	public static Timestamp convertTimeToDisplayTimeZone(Timestamp timeStamp)
	{
		try
		{
			Date date = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_24).parse(timeStamp.toString());
			DateFormat gmtFormat = new SimpleDateFormat();
			gmtFormat.setTimeZone(TimeZone.getTimeZone(PropFactory.getInstance().getProperty(EGeneral.Display_Time_Zone)));
			return new Timestamp(new SimpleDateFormat().parse(gmtFormat.format(date)).getTime());
		}
		catch (ParseException e)
		{
			return timeStamp;
		}
	}

	public static String convertTimeToIST(String cstTimeStamp)
	{
		try
		{

			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_24);
			sdf.setTimeZone(TimeZone.getTimeZone(PropFactory.getInstance().getProperty(EGeneral.Display_Time_Zone)));
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_24);
			return sdfCurrent.format(sdf.parse(cstTimeStamp));
		}
		catch (ParseException e)
		{
			return cstTimeStamp;
		}
	}

	public static boolean deleteFiles(String delFileDir)
	{
		try
		{
			File file2Delete = new File(delFileDir);

			if (file2Delete.exists())
			{
				File[] imageFileList = file2Delete.listFiles();
				for (int idx = 0; idx < imageFileList.length; idx++)
				{
					imageFileList[idx].delete();
				}
				file2Delete.delete();
				return true;
			}

		}
		catch (Exception excep)
		{
			excep.printStackTrace();
		}
		return false;
	}

	public static Object getDataObject(Object object, Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if (object != null)
		{
			Class<?> dataClass = object.getClass();
			Field[] fields = clazz.getDeclaredFields();

			if (CommonValidator.isNotNullNotEmpty(fields) == false)
			{
				fields = clazz.getSuperclass().getDeclaredFields();
			}

			Object returnObj = clazz.newInstance();
			for (Field field : fields)
			{
				String mtdGetterName1 = GET + field.getName().toUpperCase();
				String mtdGetterName2 = IS + field.getName().toUpperCase();
				String mtdSetterName = SET + field.getName().toUpperCase();

				for (Method method : dataClass.getMethods())
				{
					if (CommonValidator.isEqual(method.getName(), mtdGetterName1) || CommonValidator.isEqual(method.getName(), mtdGetterName2))
					{
						Object value = method.invoke(object, new Object[] {});
						for (Method methodSetter : clazz.getMethods())
						{
							if (CommonValidator.isEqual(methodSetter.getName(), mtdSetterName))
							{
								methodSetter.invoke(returnObj, new Object[] { value });
								break;
							}
						}
						break;
					}
				}
			}
			return returnObj;
		}
		else
		{
			return null;
		}
	}

	public static long getDateDifference(long startDate, long endDate, String format)
	{
		long diffFormat = 0;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(startDate) && CommonValidator.isNotNullNotEmpty(endDate))
			{
				long diff = endDate - startDate;
				if (CommonValidator.isEqual(format, SECONDS))
				{
					diffFormat = diff / 1000 % 60;
				}
				else if (CommonValidator.isEqual(format, MINUTES))
				{
					diffFormat = diff / (60 * 1000) % 60;
				}
				else if (CommonValidator.isEqual(format, HOURS))
				{
					diffFormat = diff / (60 * 60 * 1000) % 24;
				}
				else if (CommonValidator.isEqual(format, DAYS))
				{
					diffFormat = diff / (24 * 60 * 60 * 1000);
				}
			}
			return diffFormat;
		}
		catch (Exception e)
		{
			return diffFormat;
		}
	}

	public static String getDateInFormat(Date date, String dateFormat)
	{
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);
		return dateFormatType != null ? dateFormatType.format(date) : "";
	}

	public static Date getDateInFormat(String dateStr, String dateFormat)
	{
		try
		{
			return new SimpleDateFormat(dateFormat).parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public static String getDateStringInFormat(String dateStr, String dateFormat)
	{
		try
		{
			Date parseDate = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_24).parse(dateStr);
			return new SimpleDateFormat(dateFormat).format(parseDate);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public static String getDateTimeZoneDateInFormat(Date date, String dateFormat, String timeZone)
	{
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);
		if (CommonValidator.isNotNullNotEmpty(timeZone))
			dateFormatType.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
		return (dateFormatType != null) ? dateFormatType.format(date) : "";
	}

	public static String getDecimalValueofAmt(Double amount)
	{
		if (CommonValidator.isNotNullNotEmpty(amount))
		{
			return new DecimalFormat("#0.00").format(amount);
		}
		return "";
	}

	public static String getDecodeString(String str)
	{
		return new String(Base64.decodeBase64(str.getBytes()));
	}

	public static String getEncodeString(String str)
	{
		return new String(Base64.encodeBase64(str.getBytes()));
	}

	public static String getInvoiceAmountInUSD(Double amount, Double exChangeRate)
	{
		if (CommonValidator.isNotNullNotEmpty(amount) && CommonValidator.isNotNullNotEmpty(exChangeRate))
		{
			amount = amount * exChangeRate;
			return getDecimalValueofAmt(amount);
		}
		return "";
	}

	public static Timestamp getTimeZoneDateInFormat(Date date, String dateFormat, EnumInterface timeZone)
	{
		return getTimeZoneDateInFormat(date, dateFormat, timeZone.name());
	}

	public static Timestamp getTimeZoneDateInFormat(Date date, String dateFormat, String timeZone)
	{
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);
		if (CommonValidator.isNotNullNotEmpty(timeZone))
		{
			dateFormatType.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
		}
		return Timestamp.valueOf(dateFormatType != null ? dateFormatType.format(date) : "");
	}

	public static Timestamp getTimeZoneDateInFormat(int daysCount, Date date, String dateFormat, String timeZone)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, daysCount); // add daysCount of days
		date = cal.getTime();
		return getTimeZoneDateInFormat(date, dateFormat, timeZone);
	}

	public static Timestamp getTimeZoneDateInFormat(String date, String dateFormat, String timeZone)
	{
		Date newDate = getDateInFormat(date, DATE_FORMAT_MM_DD_YYYY);
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);
		if (CommonValidator.isNotNullNotEmpty(timeZone))
		{
			dateFormatType.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
		}
		return Timestamp.valueOf(dateFormatType != null ? dateFormatType.format(newDate) : "");
	}

	public static Timestamp getTimeZoneDateInFormat(String date, String fromFormat, String toFormat, String timeZone)
	{
		Date newDate = getDateInFormat(date, fromFormat);
		DateFormat toFormatType = new SimpleDateFormat(toFormat);
		if (CommonValidator.isNotNullNotEmpty(timeZone))
			toFormatType.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
		return Timestamp.valueOf(toFormatType.format(newDate));
	}

	public static String getTimeZoneDateInFormatStr(Date date, String dateFormat, String timeZone)
	{
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);
		if (CommonValidator.isNotNullNotEmpty(timeZone))
		{
			dateFormatType.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
		}
		return (dateFormatType != null ? dateFormatType.format(date) : "");
	}

	public static String getTimeZoneDateInFormatStr(int daysCount, Date date, String dateFormat, String timeZone)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, daysCount); // add daysCount of days
		date = cal.getTime();
		return getTimeZoneDateInFormatStr(date, dateFormat, timeZone);
	}

	public static String initCapsName(String name)
	{
		char[] nameArr = name.toLowerCase().toCharArray();
		int i = 0;
		boolean capFlag = false;
		StringBuffer sbName = new StringBuffer();
		char appendChar = ' ';
		while ( i < nameArr.length )
		{
			if (i == 0)
			{
				sbName.append(String.valueOf(nameArr[i]).toUpperCase());
			}
			else if (nameArr[i] == ' ' || nameArr[i] == ',' || nameArr[i] == '-' || nameArr[i] == '_' || nameArr[i] == '.' || nameArr[i] == '#' || nameArr[i] == '(' || nameArr[i] == '$')
			{
				appendChar = nameArr[i];
				capFlag = true;
			}
			else if (capFlag)
			{
				sbName.append(appendChar + String.valueOf(nameArr[i]).toUpperCase());
				appendChar = ' ';
				capFlag = false;
			}
			else
			{
				sbName.append(nameArr[i]);
			}
			i++;
		}
		return sbName.toString();
	}

	public static String replaceSingleQuotes(String fieldValue)
	{

		if (fieldValue != null)
		{
			fieldValue = fieldValue.replace("'", "''");
		}

		return fieldValue;

	}
}
