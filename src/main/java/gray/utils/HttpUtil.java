package gray.utils;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * http工具类
 * Created by gray
 */
public class HttpUtil {
    /**
     * 组装的json对象传到服务器
     * @param connectURL
     * @param obj
     * @return
     */
    public static Object doPost(String connectURL, Object obj) {
        HttpURLConnection conn = null;
        OutputStream os = null; // conn的输出流
        ObjectOutputStream oos = null; // 对象输出流
        InputStream is = null; // 得到HttpURLConnection的输入流

        try {
            conn = setURLConnection(connectURL); // 设置HttpURLConnection参数
            os = conn.getOutputStream(); // 得到HttpURLConnection的输出流
            oos = new ObjectOutputStream(os); // 对象输出流：使用JSON传值
            setObjOutStream(oos, obj); // 得到对象输出流

            is = conn.getInputStream();
            Object result = readObjectFromServer(is);// 从服务器读取对象
            conn.disconnect();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (os != null) os.close();
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 设置HttpURLConnection参数
     * @param url 请求处理的地址
     * @return 后台与服务器之间的通信连接
     * @throws IOException
     * @throws ProtocolException
     */
    private static HttpURLConnection setURLConnection(String connectURL) throws IOException, ProtocolException {
        URL url = new URL(connectURL); // 请求处理的Servlet
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 转化为HttpURLConnection类型对象，以便用到HttpURLConnection更多API
        conn.setDoOutput(true); // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false
        conn.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true
        conn.setUseCaches(false); // post请求不能使用缓存
        // conn.setRequestProperty("Content-type", "application/x-java-serialized-object"); // 设定传送的内容类型是可序列化的java对象
        conn.setRequestProperty("Content-type", "application/json"); // 设定传送的内容类型是json对象
        conn.setRequestMethod("POST"); // 设定请求的方法为"POST"，默认是GET

        try {
            conn.connect(); // 连接，从上述至此的配置必须要在connect之前完成
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
        } catch (ConnectException e1) {
            if (e1.getMessage().equals("Connection refused: connect")) {
                System.out.println("连接超时");
            }
        }
        return conn;
    }

    /**
     * 写入对象输出流
     * @param oos 对象输出流
     * @return 对象输出流
     * @throws IOException
     */
    private static void setObjOutStream(ObjectOutputStream oos, Object obj) throws IOException {
        oos.writeObject(obj); // 向对象输出流写出数据，这些数据将存到内存缓冲区中
        oos.flush(); // 刷新对象输出流，将任何字节都写入潜在的流中
    }

    /**
     * 从服务器读取对象
     * @param inStrm 输入流
     * @return 从服务器返回的对象
     * @throws IOException
     */
    private static Object readObjectFromServer(InputStream inStrm) throws IOException {
        ObjectInputStream objInStream; // 输入流从服务器读取JSON对象
        objInStream = new ObjectInputStream(inStrm); // 输入流从服务器读取JSON对象
        Object inObj = null;
        try {
            inObj = objInStream.readObject(); // 读取对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return inObj;
    }
}
