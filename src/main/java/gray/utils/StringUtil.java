package gray.utils;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Created by Gray
 */
public class StringUtil {

    /**
     * 字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

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
        if (StringUtil.isEmpty(value)) return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(value).matches();
    }

    /**
     * 判断当前字符串是否为email
     * @param value
     * @return
     */
    public static boolean isEmail(String value) {
        if (StringUtil.isEmpty(value)) return false;
        String style = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return value.matches(style);
    }

    /**
     * 判断当前字符串是否为邮政编码
     */
    public static boolean isZip(String value) {
        if (StringUtil.isEmpty(value)) return false;
        String partten = "^[1-9]\\d{5}$";
        return value.matches(partten);
    }

    /**
     * 判断当前字符串是否为手机号
     * @param value
     * @return
     */
    public static boolean isMobile(String value) {
        if (StringUtil.isEmpty(value) || value.trim().length() < 11) return false;
//		String partten = "^(13[0-9]|15[0|3|6|7|8|9]|18[7|8|9])\\d{8}$";
        String partten = "^0?1[3|4|5|7|8][0-9]\\d{8}$";
        return value.matches(partten);
    }

    /**
     * 判断当前字符串是否为电话号码
     */
    public static boolean isPhone(String value) {
        if (StringUtil.isEmpty(value) || value.trim().length() < 6) return false;
//		String partten = "^([-0|1|2|3|4|5|6|7|8|9|+|.|#|*|(|)]){7,}$";
        String partten = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,5})?$";
        return value.matches(partten);
    }

    /**
     * 将字符串数组用指定的连接符连接
     * @return
     */
    public static String concatStr(List<String> stringList, String delimit){
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<stringList.size(); i++){
            sb.append(stringList.get(i) + delimit);
        }
        if (!StringUtil.isEmpty(sb.toString()))
            return sb.substring(0, sb.length() - delimit.length());
        return sb.toString();
    }

    /**
     * 将字符串数组拼接成字符串
     * @author Steven, 2011-07-28
     * @since  V1.5.4
     */
    public static String concatStr(String[] stringArray, String delimit){
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<stringArray.length; i++){
            if (stringArray[i]!=null){
                sb.append(stringArray[i] + delimit);
            }
        }
        if (!StringUtil.isEmpty(sb.toString()))
            return sb.substring(0, sb.length() - delimit.length());
        return sb.toString();
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtil.isNotEmpty(keyword)) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
