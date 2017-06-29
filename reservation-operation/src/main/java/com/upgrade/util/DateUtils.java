package com.upgrade.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DateUtils {
	public static List<Date> getDaysBetweenDates(Date startDate, Date endDate)
	{
		endDate = new Date(endDate.getTime() + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);

		while (calendar.getTime().before(endDate))
		{

			Date result = new Date(calendar.getTime().getTime());
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}
}
