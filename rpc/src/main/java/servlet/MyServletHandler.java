package servlet;

import common.Invocation;import org.apache.commons.io.IOUtils;
import registry.LocalRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 * date： 2023/11/14
 * time： 06:32
 * author： cris
 * description：
 **/
public class MyServletHandler {
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        try{
            // Create a new ObjectInputStream to read the request
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            Class implClass = LocalRegistry.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(),invocation.getParameterTypes());     // add types for overload
            String res = (String) method.invoke(implClass.newInstance(), invocation.getParameters());
            IOUtils.write(res,resp.getOutputStream());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
