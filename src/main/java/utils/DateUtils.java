package utils;

import com.example.halagodainv.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static final String DATE_TIME_STRING = "yyyy-MM-dd HH:mm";

    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_STRING);
        String date = simpleDateFormat.format(new Date());
        return date;
    }


    public static Date parseStringToDate(String StringDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT);
        Date date = null;
        try {
            date = formatter.parse(StringDate);
        } catch (ParseException e) {
            logger.info(e.getMessage(),e);
        }
        return date;
    }

    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        return date;
    }

    public static String getCalendar() {
        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        String calendar = sdf.format(cal.getTime());
        return calendar;
    }

    public static Long getTimestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_STRING);
        String timestamp = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
        if (timestamp == null) return null;
        try {
            Date dt = simpleDateFormat.parse(timestamp);
            long epoch = dt.getTime();
            return (epoch / 1000);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String parseDate(int time) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(time * 1000L));
        return String.valueOf(date);
    }

    public static String parseTimestampToDate(int time) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(time * 1000L));
        return String.valueOf(date);
    }

    public static Integer parrtTimeStamp(String time) {
        try {
            DateFormat f;
            f = new SimpleDateFormat("YYYY-MM-DD");
            Date date = f.parse(time);
            Timestamp timeStampDate = new Timestamp(date.getTime());
            int tim = Integer.parseInt(String.valueOf(timeStampDate));
            return tim;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer parseTimeStamp(String timestamp) {
        if (timestamp == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dt = sdf.parse(timestamp);
            long epoch = dt.getTime()/1000L;
            return (int) (epoch);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Integer parseDateToTimeStamp(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(date);
            long epoch = dt.getTime()/1000L;
            return (int) (epoch);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Integer parse(String date) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            //convert string to date
            d = inputFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Date Format Not Supported");
            e.printStackTrace();
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        return Integer.valueOf(outputFormat.format(d).toString());
    }
}
