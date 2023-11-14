package registry;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * date： 2023/11/14
 * time： 06:48
 * author： cris
 * description：
 **/

public class LocalRegistry {

    private static Map<String, Class> map = new HashMap<>();

    public static void register(String interfaceName, Class implClass){
        map.put(interfaceName,implClass);
    }

    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
