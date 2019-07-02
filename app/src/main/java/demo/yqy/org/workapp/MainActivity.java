package demo.yqy.org.workapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import demo.yqy.org.workapp.bean.ItemBean;
import demo.yqy.org.workapp.bean.ShowBean;
import demo.yqy.org.workapp.bean.TitleBean;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycleView;

    MyAdapter adapter;
    List<ShowBean> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (recycleView == null) {
            recycleView = findViewById(R.id.recycleview);
        }
        adapter = new MyAdapter(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(adapter);

        Resources res = this.getResources(); //这句放在onCreate中

        String[] titleArr = res.getStringArray(R.array.title);
        for (int i = 0; i < titleArr.length; i++) {
            final String str = titleArr[i];
            if (str.startsWith("#")) {
                stringList.add(new TitleBean(str.replace("#", "")));
            } else {
                String str[] = titleArr[i].split("#");
                stringList.add(new ItemBean(str, contentArr[i]));
            }
        }
        adapter.notifyDataSetChanged();
    }


    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context mContext;

        public MyAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == ShowBean.Type_Item) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_content, parent, false);
                ContentViewHolder viewHolder = new ContentViewHolder(view);
                return viewHolder;
            } else {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_title, parent, false);
                TitleViewHolder viewHolder = new TitleViewHolder(view);
                return viewHolder;
            }
        }

        @Override
        public int getItemViewType(int position) {
            return stringList.get(position).getShowType();
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == ShowBean.Type_Title) {
                TitleViewHolder viewHolder = ((TitleViewHolder) holder);
                final TitleBean itemBean = (TitleBean) stringList.get(position);
                final String content = itemBean.getTitle();
                if (!TextUtils.isEmpty(content)) {
                    viewHolder.title.setText(content);
                }
            } else {
                ContentViewHolder viewHolder = ((ContentViewHolder) holder);
                final ItemBean itemBean = (ItemBean) stringList.get(position);
                final String content = itemBean.getTitle();
                if (!TextUtils.isEmpty(content)) {
                    viewHolder.content.setText(content);
                }

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ContentActivity.class);
                        intent.putExtra("bean", itemBean);
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return stringList.size();
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView content;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.tv_content);

            content.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            content.getPaint().setAntiAlias(true);//抗锯齿
        }
    }


}
