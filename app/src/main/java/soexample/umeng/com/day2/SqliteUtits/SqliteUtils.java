package soexample.umeng.com.day2.SqliteUtits;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import soexample.umeng.com.day2.bean.Car;
import soexample.umeng.com.day2.greendao.CarDao;
import soexample.umeng.com.day2.greendao.DaoMaster;

public class SqliteUtils {
    private CarDao carDao;

    private SqliteUtils(){}

    private static  SqliteUtils mSqliteUtils;

    public static SqliteUtils getSqliteUtils(){
        if(mSqliteUtils==null){
            mSqliteUtils=new SqliteUtils();

        }
        return mSqliteUtils;
    }

    public void init(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "car");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster mDaoMaster=new DaoMaster(db);
        carDao=mDaoMaster.newSession().getCarDao();
    }

    public void insert(Car cacheCar){
        carDao.insert(cacheCar);
    }

    public void deleteAll(){
        carDao.deleteAll();
    }

    public List<Car> queryAll(){
        return carDao.loadAll();
    }

    public Car query(String key){
        return carDao.load(Long.parseLong(key));
    }

    public void update(Car cacheCar){
        carDao.update(cacheCar);
    }
}


