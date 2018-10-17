package utils;

import com.google.gson.Gson;
import groovy.lang.Singleton;

/**
 * create by Mofang_ysc on 2018/9/15 0015
 */

public class JsonUtil {
    private final static Gson INSTANCE = new Gson();

    private JsonUtil(){}

    public static Gson getInstance(){
        return INSTANCE;
    }
}
