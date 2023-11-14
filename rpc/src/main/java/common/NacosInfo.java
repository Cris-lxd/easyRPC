package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * date： 2023/11/14
 * time： 10:43
 * author： cris
 * description：
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NacosInfo{
    private String serverAddr;

    private String username;

    private String password;


}
