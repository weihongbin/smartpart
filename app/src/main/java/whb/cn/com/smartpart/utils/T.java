package whb.cn.com.smartpart.utils;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/*****************************************
 * @author cxy
 *         created at  2016/10/18 11:15
 ****************************************/
public class T {
    public static <T> void show(Context context, T msg) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e("TAG", "当前线程不为主线程，[" + String.valueOf(msg) + " ]无法Toast");
            return;
        }
        Toast.makeText(context, String.valueOf(msg), Toast.LENGTH_SHORT).show();

    }
}
