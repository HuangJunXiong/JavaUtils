package gray.utils;

import java.io.*;

/**
 * 文件工具类
 * Created by Gray
 */
public class FileUtil {
    /**
     * 读文件
     * @param fileName 文件全名 格式为 D:\\a\\b\\c.txt
     * @return 文件内容
     */
    public static String readFile(String fileName) {
        String s = "";
        StringBuffer sb = new StringBuffer();
        File inFile = new File(fileName);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), "EUC_CN"));
            while ((s = in.readLine()) != null) {
                sb.append(s + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 写文件
     * @param fileName 文件全名 格式为 D:\\a\\b\\c.txt
     * @param str 文件的内容
     */
    public static void writeFile(String fileName, String str) {
        PrintStream out = null;
        try {
            File outFile = new File(fileName);
            out = new PrintStream(new BufferedOutputStream(new FileOutputStream(outFile)));
            out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
