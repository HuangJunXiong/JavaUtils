package gray.utils;

import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Created by Gray
 */
public class StringUtil {
    /**
     * 返回消息字符串，自动替换{}中的变量
     * @param message
     * @param variables
     * @return
     */
    public static String message(String message, Object... variables) {
        String output = MessageFormat.format(message, variables);
        return output;
    }

    /**
     * 判断当前字符串是否为数字
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        if (StringUtils.isEmpty(value)) return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(value).matches();
    }
}
