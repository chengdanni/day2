package soexample.umeng.com.day2.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Car {
    @Id(autoincrement = true)
    Long id;
    String data;

    @Generated(hash = 177527266)
    public Car(Long id, String data) {
        this.id = id;
        this.data = data;
    }
    @Generated(hash = 625572433)
    public Car() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }

}
