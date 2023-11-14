package service;


import org.apache.http.client.utils.DateUtils;

import java.util.Date;

/**
 * date： 2023/11/14
 * time： 05:55
 * author： cris
 * description：
 **/
public class CallServiceImpl implements CallService{
    @Override
    public String call(Date date) {
        return "call success, this time is " + DateUtils.formatDate(date,"yyyy-MM-dd HH:mm:ss");
    }

}
