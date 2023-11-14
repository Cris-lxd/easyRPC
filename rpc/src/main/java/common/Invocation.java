package common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * date： 2023/11/14
 * time： 06:36
 * author： cris
 * description：
 **/

@Data
@AllArgsConstructor
public class Invocation implements Serializable {
    private String interfaceName;

    private String methodName;

    private Class[] parameterTypes;

    private Object[] parameters;
}
