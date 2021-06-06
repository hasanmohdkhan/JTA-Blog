package com.jta.in.blog.utils.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TimeStampUtils {

    public Timestamp convertTimestampFromMillis(Long millis) {
        return new Timestamp(millis);
    }
    public String getCurrentdateInStringFormat(String format) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    	return dateFormat.format(new Date());
    }
    public Long getTodayTimestampWithZeroHourAndMinute() {
    	Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTimeInMillis();
    }
    
    public Long getCurrentTimestamp() {
    	Calendar cal = Calendar.getInstance();
    	return cal.getTimeInMillis();
    }
    
    /**
     * method to get the current epoch time in UTC
     * @return epcohTime
     */
    public static Long getCurrentTimestampInUTC() {
    	Calendar calendar = Calendar.getInstance();
    	return calendar.getTimeInMillis()-calendar.get(Calendar.ZONE_OFFSET);
    }
    
    
    /**
	 * This method is use to get the date in string from epoch time
	 * @param epochTime epoch time
	 * @param pattern format tobe use
	 * @return String dateStr
	 */
	public static String getDateStrFromEpochTime(long epochTime ,String pattern) {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		epochTime = epochTime + cal.get(Calendar.ZONE_OFFSET);
		date.setTime(epochTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
}
