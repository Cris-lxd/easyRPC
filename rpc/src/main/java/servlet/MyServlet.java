package servlet;


import servlet.MyServletHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * date： 2023/11/14
 * time： 06:30
 * author： cris
 * description：
 **/
public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        try {
            new MyServletHandler().service(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
