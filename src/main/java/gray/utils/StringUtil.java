package gray.utils;

import java.text.MessageFormat;

/**
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
}
