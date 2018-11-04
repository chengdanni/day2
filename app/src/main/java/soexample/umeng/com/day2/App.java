package soexample.umeng.com.day2;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import soexample.umeng.com.day2.SqliteUtits.SqliteUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SqliteUtils.getSqliteUtils().init(this);
        Fresco.initialize(this);
    }


}
