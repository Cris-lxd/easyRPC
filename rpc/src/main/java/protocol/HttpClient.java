package protocol;

import common.Invocation;
import common.Url;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

/**
 * date： 2023/11/14
 * time： 09:30
 * author： cris
 * description：
 **/
public class HttpClient{

    public String send(Url url, Invocation invocation){
        String result = "";
        try {
            URL httpUrl = new URL("http", url.getHostname(), url.getPort(), "/");
            URLConnection urlConnection = httpUrl.openConnection();
            urlConnection.setDoOutput(true);

            // 1. 向服务器发送消息
            OutputStream outputStream = urlConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            // 2. 获取返回结果
            InputStream inputStream = urlConnection.getInputStream();
            result = IOUtils.toString(inputStream);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
