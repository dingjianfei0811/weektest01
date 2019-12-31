package com.bawei.dingjianfei20191231.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dingjianfei20191231.R;
import com.bawei.dingjianfei20191231.model.bean.Bean;
import com.bawei.dingjianfei20191231.netutil.Netutil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：丁建飞
 * 时间：2019/12/31  9:31
 * 类名：com.bawei.dingjianfei20191231.view
 */
public class RViewAdapTer extends RecyclerView.Adapter<RViewAdapTer.MyViewHode> {
    private List<Bean.RankingBean> ranking;

    public RViewAdapTer(List<Bean.RankingBean> ranking) {

        this.ranking = ranking;
    }

    @NonNull
    @Override
    public MyViewHode onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.view, null);

        return new MyViewHode(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHode holder, int position) {
        String name = ranking.get(position).getName();
        String avatar = ranking.get(position).getAvatar();
        String rank = ranking.get(position).getRank();

        holder.name.setText(name);
        holder.pai.setText(rank);
        Netutil.getInstance().getGlide(avatar,holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickItem.onclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    public class MyViewHode extends RecyclerView.ViewHolder {
        @BindView(R.id.pai)
        TextView pai;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.name)
        TextView name;
        public MyViewHode(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnclickItem onclickItem;

    public void setOnclickItem(OnclickItem onclickItem) {
        this.onclickItem = onclickItem;
    }

    public  interface   OnclickItem{
        void  onclick(int p);
    }

}
