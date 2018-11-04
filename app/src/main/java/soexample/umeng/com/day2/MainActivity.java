package soexample.umeng.com.day2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day2.Adatpter.ShangAdapter;
import soexample.umeng.com.day2.SqliteUtits.SqliteUtils;
import soexample.umeng.com.day2.bean.Car;
import soexample.umeng.com.day2.bean.LunBean;
import soexample.umeng.com.day2.bean.ShangBean;
import soexample.umeng.com.day2.net.HttpHelper;

public class MainActivity extends AppCompatActivity {
    private List<ShangBean.DataBean.DataList> list1=new ArrayList<>();
    private ViewPager view;
    private RecyclerView recyclerView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                int i = view.getCurrentItem();
                if (i < list.size() - 1) {
                    i++;
                } else {
                    i = 0;
                }
                view.setCurrentItem(i);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }




    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (ViewPager) findViewById(R.id.pager);
        recyclerView = (RecyclerView) findViewById(R.id.view);


        dohttp();
        if (isNetworkAvalible(MainActivity.this)){
            okhttp();
        }else{
            Car car = SqliteUtils.getSqliteUtils().queryAll().get(0);
            String data = car.getData();

            ShangBean tiao = new Gson().fromJson(data, ShangBean.class);
            List<ShangBean.DataBean> da = tiao.getData();
            for (int i = 0; i < da.size(); i++) {
                if (da.get(i).getList() == null || da.get(i).getList().size() == 0) {

                } else {
                    list1.addAll(da.get(i).getList());
                }
            }
            ShangAdapter shangAdapter = new ShangAdapter(MainActivity.this, list1);
            StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(s);
            recyclerView.setAdapter(shangAdapter);
        }
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    private void okhttp() {
        String shang = "http://www.zhaoapi.cn/product/getCarts?uid=71";
        new HttpHelper().get(shang).result(new HttpHelper.HttpListener() {
           // private JiuAdapter myAdapler;
            @Override
            public void success(String data) {
                SqliteUtils.getSqliteUtils().deleteAll();
                Car car = new Car();
                car.setData(data);
                SqliteUtils.getSqliteUtils().insert(car);

                ShangBean tiao = new Gson().fromJson(data, ShangBean.class);
                List<ShangBean.DataBean> da = tiao.getData();
                for (int i = 0; i < da.size(); i++) {
                    if (da.get(i).getList() == null || da.get(i).getList().size() == 0) {

                    } else {
                        list1.addAll(da.get(i).getList());
                    }
                }
                ShangAdapter shangAdapter = new ShangAdapter(MainActivity.this, list1);
                StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(s);
                recyclerView.setAdapter(shangAdapter);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private MyAdate myAdate;
    private List<LunBean.DataBean> list = new ArrayList<>();

    class MyAdate extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            String icon = list.get(position).getIcon();
            String replace = icon.replace("https", "http");
            Picasso.with(MainActivity.this).load(replace).fit().into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }


    private void dohttp() {
        String url = "http://www.zhaoapi.cn/ad/getAd";

        new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                LunBean bean = new Gson().fromJson(data, LunBean.class);
                list = bean.getData();
                myAdate = new MyAdate();
                view.setAdapter(myAdate);
            }
            @Override
            public void fail(String error) {

            }
        });
    }



    /**
     * 判断网络情况
     *
     * @param context 上下文
     * @return false 表示没有网络 true 表示有网络
     */
    public boolean isNetworkAvalible(Context context) {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
