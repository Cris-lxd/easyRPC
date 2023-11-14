package registry;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import common.NacosInfo;

import java.util.List;
import java.util.Properties;

/**
 * date： 2023/11/14
 * time： 10:23
 * author： cris
 * description：
 **/

public class NocosRegistry {

    private static NamingService naming;

    /**
     * 用于将服务注册到 nacos
     * @param nacosInfo     nacos 连接信息
     * @param instance      要注册的实例
     */
    public static void registry(NacosInfo nacosInfo, Instance instance) throws NacosException {
        // 获取注册中心
        naming = get(nacosInfo);
        // 注册实例
        naming.registerInstance(instance.getServiceName(),instance);
    }


    public static List<Instance> getInstance(NacosInfo nacosInfo,String serviceName) throws NacosException {
        // 获取注册中心
        naming = get(nacosInfo);
        // 获取实例
        return naming.getAllInstances(serviceName);
    }

    public static NamingService get(NacosInfo nacosInfo) throws NacosException {
        Properties properties = new Properties();
        BeanUtil.copyProperties(nacosInfo,properties);
        return NamingFactory.createNamingService(properties);
    }

}
