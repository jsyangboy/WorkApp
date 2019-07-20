package demo.yqy.org.workapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import demo.yqy.org.workapp.bean.ItemBean;
import demo.yqy.org.workapp.bean.ShowBean;
import demo.yqy.org.workapp.bean.TitleBean;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recycleView;

    MyAdapter adapter;
    List<ShowBean> stringList = new ArrayList<>();
    List<ShowBean> fileterStringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchView != null) {
                    searchView.performClick();
                }
            }
        });
        fab.setVisibility(View.GONE);


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
                String string[] = titleArr[i].split("#");
                if (string.length > 1) {
                    stringList.add(new ItemBean(str, string[1]));
                }
            }
        }
        adapter.setStringList(stringList);
        adapter.notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);  //menu文件

        return true;
    }

    SearchView searchView;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setQueryHint("搜索");  //设置输入前得提示文字

        //设置输入内容监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("yqy", "onQueryTextSubmit");
                //用户点击搜索
                fileterStringList.clear();
                adapter.setStringList(stringList);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    //搜索的内容改变
                    Log.e("yqy", "onQueryTextChange newText" + newText);
                    fileterStringList.clear();
                    if (!TextUtils.isEmpty(newText)) {
                        for (ShowBean showBean : stringList) {
                            if (showBean.getShowType() == ShowBean.Type_Item) {
                                ItemBean titleBean = (ItemBean) showBean;
                                if (titleBean.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                                    fileterStringList.add(showBean);
                                }
                            }
                        }
                    }

                    if (fileterStringList.size() > 0) {
                        adapter.setStringList(fileterStringList);
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter.setStringList(stringList);
                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.e("yqy", "onClose");
                fileterStringList.clear();
                adapter.setStringList(stringList);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }


    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context mContext;

        private List<ShowBean> stringList = new ArrayList<>();

        public MyAdapter(Context mContext) {
            this.mContext = mContext;
        }

        public void setStringList(List<ShowBean> stringList) {
            this.stringList = stringList;
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

                viewHolder.content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ContentActivity.class);
                        intent.putExtra("bean", itemBean);
                        startActivity(intent);
                    }
                });
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
