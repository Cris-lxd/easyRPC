import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import common.Invocation;
import common.NacosInfo;
import common.Url;
import protocol.HttpService;
import registry.LocalRegistry;
import registry.NocosRegistry;
import service.CallService;
import service.CallServiceImpl;

/**
 * date： 2023/11/14
 * time： 06:08
 * author： cris
 * description：
 **/
public class Provider {
    public static void main(String[] args) {
        try {
            // 本地服务注册，集群服务可使用 nacos
            LocalRegistry.register(CallService.class.getName(), CallServiceImpl.class);

            // 构建 nacos 连接
            NacosInfo nacosInfo = new NacosInfo();
            nacosInfo.setServerAddr("localhost:8848");
            nacosInfo.setUsername("nacos");
            nacosInfo.setPassword("nacos");

            // 构建服务实例
            Instance instance = new Instance();
            instance.setIp("localhost");
            instance.setPort(8080);
            instance.setServiceName("my_server8080");
            NocosRegistry.registry(nacosInfo,instance);

            // 发送请求
            Url url = new Url("localhost",8080);
            new HttpService().start(url);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }


    }

    // 快速排序



}