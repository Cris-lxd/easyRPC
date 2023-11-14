package protocol;

import common.Url;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import servlet.MyServlet;

/**
 * date： 2023/11/14
 * time： 06:13
 * author： cris
 * description：
 **/
public class HttpService {

    public void start(Url url){
        try {
            Tomcat tomcat = new Tomcat();
            Server server = tomcat.getServer();
            Service service = server.findService("Tomcat");

            Connector connector = tomcat.getConnector();
            connector.setPort(url.getPort());

            StandardEngine engine = new StandardEngine();
            engine.setDefaultHost(url.getHostname());

            StandardHost host = new StandardHost();
            host.setName(url.getHostname());

            String contextPath = "";
            StandardContext context = new StandardContext();
            context.setPath(contextPath);
            context.addLifecycleListener(new Tomcat.FixContextListener());

            host.addChild(context);
            engine.addChild(host);

            service.setContainer(engine);
            service.addConnector(connector);

            tomcat.addServlet(contextPath,"myServlet",new MyServlet());
            context.addServletMappingDecoded("/*","myServlet");

            tomcat.start();
            server.await();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
