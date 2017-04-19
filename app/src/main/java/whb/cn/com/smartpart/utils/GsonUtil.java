package whb.cn.com.smartpart.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Json转换工具类.
 */
public class GsonUtil {

    /**
     * 对象转Json 字符串
     * @param obj
     * @return json string
     */
    public static String toJson(Object obj) {
        Gson mapper = new Gson();
        String jsonInString = null;
        try {
            jsonInString = mapper.toJson(obj);
        } catch (Exception e) {
        }
        return jsonInString;
    }

    /**
     * Json 字符串转对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson mapper = new Gson();
        T t = null;
        try {
            t = mapper.fromJson(json, clazz);
        } catch (Exception e) {
        }
        return t;
    }


    public static <T> T fromJsonT(String json, Class<T> clazz) {
        Gson mapper = new Gson();
        T t = null;
        try {
            t = mapper.fromJson(json,  new TypeToken<T>() {
            }.getType());
        } catch (Exception e) {
        }
        return t;
    }

}
