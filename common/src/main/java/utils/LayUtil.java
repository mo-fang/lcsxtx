package utils;

import com.google.gson.Gson;

import java.util.List;

public class LayUtil {
    public static String getLayJson(List objList,long total ){
        String result = "";
        Gson gson = new Gson();
        if (objList==null){
            result =  "{\"code\":0,\"msg\":\"获取数据列表为空\",\"count\":"+total+",\"data\":\"[{}]\"}";
        }else {
            result =  "{\"code\":0,\"msg\":\"\",\"count\":"+total+",\"data\":"+gson.toJson(objList)+"}";
        }
        return result;
    }
    public static String getLayJsonWeChat(List objList,long total,Integer page ){
        String result = "";
        Gson gson = new Gson();
        if (objList==null){
            result =  "{\"code\":0,\"msg\":\"获取数据列表为空\",\"count\":"+total+",\"data\":\"[{}]\"}";
        }else {
            result =  "{\"code\":0,\"msg\":\"\",\"count\":"+total+",\"page\":"+page+",\"data\":"+gson.toJson(objList)+"}";
        }
        return result;
    }

}
