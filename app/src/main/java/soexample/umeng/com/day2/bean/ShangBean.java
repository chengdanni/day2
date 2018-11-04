package soexample.umeng.com.day2.bean;

import java.util.List;

public class ShangBean {

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : []
         * sellerName :
         * sellerid : 0
         */

        private String sellerName;
        private String sellerid;
        public List<DataList> getList() {
            return list;
        }

        public void setList(List<DataList> list) {
            this.list = list;
        }

        private List<DataList> list;

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public class DataList{
            private String images;
            private double price;
            private String title;

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }
        }
    }
}
