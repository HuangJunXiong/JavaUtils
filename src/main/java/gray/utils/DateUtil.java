package gray.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gray
 */
public class DateUtil {
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat DEFAULT_DATE_TIME_FROMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期字符串（格式为yyyy-MM-dd）
     * @return
     */
    public static String formatTodayDate() {
        String date = DEFAULT_DATE_FORMAT.format(new Date());
        return date;
    }

    /**
     * 获取当前日期字符串（格式为yyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String formatTodayDateTime() {
        String date = DEFAULT_DATE_TIME_FROMAT.format(new Date());
        return date;
    }

    /**
     * 按指定格式转换时间字符串（格式为yyyy-MM-dd）
     * @param myDate
     * @return
     */
    public static String formatDate(Date myDate) {
        String date = DEFAULT_DATE_FORMAT.format(myDate);
        return date;
    }

    /**
     * 按指定格式转换时间字符串（格式为yyy-MM-dd HH:mm:ss）
     * @param myDate
     * @return
     */
    public static String formatDateTime(Date myDate) {
        String date = DEFAULT_DATE_TIME_FROMAT.format(myDate);
        return date;
    }

    /**
     * 按指定格式转换时间字符串
     * @param strDate
     * @param strFormat
     * @return
     */
    public static String formatDateToS(String strDate, String strFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        String date = null;
        try {
            date = sdf.format(sdf.parse(strDate));
        } catch (ParseException e) {
            return null; // 异常处理，返回空
        }
        return date;
    }

    /**
     * 按指定格式转换时间字符串
     * @param myDate
     * @param strFormat
     * @return
     */
    public static String formatDateToS(Date myDate, String strFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        String date = sdf.format(myDate);
        return date;
    }

    /**
     * 按指定格式将dateStr转化为Date；出现异常返回null，不报错
     * @param dateStr
     * @param strFormat
     * @return
     */
    public static Date parseDate(String dateStr, String strFormat) {
        if(null == dateStr || dateStr == "") return null;

        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            return null; // 异常处理，返回空
        }
        return date;
    }

    /**
     * 将yyyy/MM/dd或yyyy-MM-dd HH:MM:SS日期字符串转换为Calendar类型
     * @param baseDate
     * @return
     */
    public static Calendar parseDateTime(String baseDate) {
        Calendar cal = new GregorianCalendar();
        int yy = Integer.parseInt(baseDate.substring(0, 4));
        int mm = Integer.parseInt(baseDate.substring(5, 7)) - 1;
        int dd = Integer.parseInt(baseDate.substring(8, 10));
        int hh = 0;
        int mi = 0;
        int ss = 0;
        if (baseDate.length() > 12) {
            hh = Integer.parseInt(baseDate.substring(11, 13));
        }
        if (baseDate.length() > 15) {
            mi = Integer.parseInt(baseDate.substring(14, 16));
        }
        if (baseDate.length() > 18) {
            ss = Integer.parseInt(baseDate.substring(17, 19));
        }
        cal.set(yy, mm, dd, hh, mi, ss);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 将Date型变量去掉时分秒，仅留年月日
     * @param date
     * @return
     */
    public static Date convDateTimeToDate(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 将Date型变量转换成yyyyMM格式
     * @param date
     * @return
     */
    public static String convDateToYM(Date date) {
        return formatDateToS(date, "yyyyMM");
    }

    /**
     * 获得yyyy/MM/dd或yyyy-MM-dd HH:MM:SS中的DAY_OF_WEEK
     * 1="日", 2="一", 3="二", 4="三", 5="四", 6="五", 7="六"
     * @param strDate
     * @return
     */
    public static int getWeekDay(String strDate) {
        Calendar cal = parseDateTime(strDate);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得yyyy/MM/dd或yyyy-MM-dd HH:MM:SS中的DAY_OF_WEEK
     * 1="日", 2="一", 3="二", 4="三", 5="四", 6="五", 7="六"
     * @param date
     * @return int
     */
    public static int getWeekDay(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得yyyy/MM/dd或yyyy-MM-dd HH:MM:SS中的中文星期显示
     * @param strDate
     * @return
     */
    public static String getWeekDayName(String strDate) {
        String mName[] = { "日", "一", "二", "三", "四", "五", "六" };
        int iWeek = getWeekDay(strDate);
        iWeek = iWeek - 1;
        return "星期" + mName[iWeek];
    }

    /**
     * @see 获得yyyy/MM/dd或yyyy-MM-dd HH:MM:SS中的中文星期显示
     * @param date
     * @return int
     */
    public static String getWeekDayName(Date date) {
        String mName[] = { "日", "一", "二", "三", "四", "五", "六" };
        int iWeek = getWeekDay(date);
        iWeek = iWeek - 1;
        return "星期" + mName[iWeek];
    }

    /**
     * 日期相加：起始日期+iCount（iType）
     * @param strDate
     * @param iCount
     * @param iType Calendar.YEAR,MONTH,DATE;HOUR_OF_DAY,MINUTE,SECOND,MILLISECOND
     * @return
     */
    public static String dateTimeAdd(String strDate, int iCount, int iType) {
        Calendar cal = parseDateTime(strDate);
        cal.add(iType, iCount);
        SimpleDateFormat sdf = null;
        if ((iType == Calendar.YEAR) || (iType == Calendar.MONTH) || (iType == Calendar.DATE))
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        else
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = sdf.format(cal.getTime());
        return sDate;
    }

    /**
     *
     * @param date
     * @param iCount
     * @param iType Calendar.YEAR,MONTH,DATE;HOUR_OF_DAY,MINUTE,SECOND,MILLISECOND
     * @return
     */
    public static Date dateTimeAdd(Date date, int iCount, int iType) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(iType, iCount);
        return cal.getTime();
    }

    /**
     * 日期相加：起始日期+iCount（Calendar.DATE）
     * @param date
     * @param iCount
     * @return
     */
    public static Date dateAdd(Date date, int iCount) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, iCount);
        return cal.getTime();
    }

    /**
     * 时间差异：结束日期 - 起始日期
     * @param strDateBegin
     * @param strDateEnd
     * @param iType Calendar.SECOND=秒 MINUTE=分钟 HOUR=小时 DATE=天
     * @return
     */
    public static int dateDiff(String strDateBegin, String strDateEnd, int iType) {
        Calendar calBegin = parseDateTime(strDateBegin);
        Calendar calEnd = parseDateTime(strDateEnd);
        long lBegin = calBegin.getTimeInMillis();
        long lEnd = calEnd.getTimeInMillis();
        if (iType == Calendar.SECOND) // 秒
            return (int) ((lEnd - lBegin) / 1000L);
        if (iType == Calendar.MINUTE) // 分钟
            return (int) ((lEnd - lBegin) / 60000L);
        if (iType == Calendar.HOUR) // 时
            return (int) ((lEnd - lBegin) / 3600000L);
        if (iType == Calendar.DATE) // 天
            return (int) ((lEnd - lBegin) / 86400000L);
        else
            return -1;
    }

    /**
     * 时间差异：结束日期 - 起始日期
     * @param dateBegin
     * @param dateEnd
     * @param iType
     * @return
     */
    public static int dateDiff(Date dateBegin, Date dateEnd, int iType) {
        Calendar calBegin = new GregorianCalendar();
        calBegin.setTime(dateBegin);
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(dateEnd);
        long lBegin = calBegin.getTimeInMillis();
        long lEnd = calEnd.getTimeInMillis();
        if (iType == Calendar.SECOND) // 秒
            return (int) ((lEnd - lBegin) / 1000L);
        if (iType == Calendar.MINUTE) // 分钟
            return (int) ((lEnd - lBegin) / 60000L);
        if (iType == Calendar.HOUR) // 时
            return (int) ((lEnd - lBegin) / 3600000L);
        if (iType == Calendar.DATE) // 天
            return (int) ((lEnd - lBegin) / 86400000L);
        if(iType == Calendar.MONTH){ // 月
            int beginYear = calBegin.get(Calendar.YEAR);
            int endYear = calEnd.get(Calendar.YEAR);
            int year = endYear - beginYear;
            int beginDay = calBegin.get(Calendar.MONTH);
            int endDay = calEnd.get(Calendar.MONTH);
            int month = endDay - beginDay;
            return year*12+month;
        }
        if(iType == Calendar.YEAR){ // 年
            int beginYear = calBegin.get(Calendar.YEAR);
            int endYear = calEnd.get(Calendar.YEAR);
            int year = endYear-beginYear;
            return year;
        }
        return -1;
    }
}
