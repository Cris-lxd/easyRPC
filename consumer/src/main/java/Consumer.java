import common.Invocation;
import common.NacosInfo;
import common.Url;
import protocol.HttpClient;
import proxy.ProxyFactory;
import service.CallService;
import service.CallServiceImpl;

import java.util.Date;

/**
 * date： 2023/11/14
 * time： 06:00
 * author： cris
 * description：
 **/
public class Consumer {
    public static void main(String[] args) {
        NacosInfo nacosInfo = new NacosInfo("localhost:8848","nacos","nacos");
        String serviceName = "my_server8080";
        // 获取代理对象
        CallService callService = ProxyFactory.getProxy(CallService.class, nacosInfo,serviceName);
        String res = callService.call(new Date());
        System.out.println(res);
    }
}
