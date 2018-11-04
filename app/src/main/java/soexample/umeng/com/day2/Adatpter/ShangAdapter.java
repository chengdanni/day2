package soexample.umeng.com.day2.Adatpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day2.R;
import soexample.umeng.com.day2.bean.ShangBean;

public class ShangAdapter extends RecyclerView.Adapter<ShangAdapter.sViewHolder> {
    private List<ShangBean.DataBean.DataList> list = new ArrayList<>();
    private Context context;


    public ShangAdapter(Context context, List<ShangBean.DataBean.DataList> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public sViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shang, null);
        sViewHolder vi = new sViewHolder(view);
        return vi;

    }

    @Override
    public void onBindViewHolder(sViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        holder.imageView.setImageURI(split[0]);
        /*Picasso.with(context).load(split[0]).fit().into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class sViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final TextView textView;

        public sViewHolder(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
