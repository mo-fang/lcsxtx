package utils;

/**
 * create by Mofang_ysc on 2018/10/8 0008
 */

public class StringUtil {
    public static boolean isEmpty(Object o){
        if(o==null){
            return true;
        }else if(o instanceof String && "".equals(((String) o).replaceAll(" ",""))){
            return true;
        };
        return false;
    }

}
