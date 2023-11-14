package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * date： 2023/11/14
 * time： 06:17
 * author： cris
 * description：
 **/

@Data
@AllArgsConstructor
public class Url {

    private String hostname;

    private Integer port;
}
