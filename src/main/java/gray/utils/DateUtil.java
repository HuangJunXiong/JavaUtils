package gray.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
