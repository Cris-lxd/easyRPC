package proxy;


import com.alibaba.nacos.api.naming.pojo.Instance;
import common.Invocation;
import common.NacosInfo;
import common.Url;
import protocol.HttpClient;
import registry.LocalRegistry;
import registry.NocosRegistry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

/**
 * date： 2023/11/14
 * time： 10:05
 * author： cris
 * description：
 **/
public class ProxyFactory {

    /**
     *
     * @param interfaceClass   接口名称
     * @param nacosInfo     nacos 连接信息
     * @param serviceName      服务名称
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class interfaceClass, NacosInfo nacosInfo, String serviceName){
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient = new HttpClient();
                List<Instance> instance = NocosRegistry.getInstance(nacosInfo, serviceName);
                for(Instance o:instance){
                    Url url = new Url(o.getIp(), o.getPort());
                    // 构建接口，参数等
                    Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                    // 发送请求
                    String res = httpClient.send(url, invocation);
                    if(Optional.of(res).isPresent()){
                        return res;
                    }
                }
                return "no service available";
            }
        });
        return (T) proxyInstance;
    }
}
