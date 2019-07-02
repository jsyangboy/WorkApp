package demo.yqy.org.workapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import demo.yqy.org.workapp.bean.ItemBean;

/**
 * @author yqy
 * @create 19-7-2
 * @Describe
 */
public class ContentActivity extends AppCompatActivity {

    ItemBean itemBean;

    TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        tvContent = findViewById(R.id.tv_content);

        try {
            itemBean = getIntent().getParcelableExtra("bean");
            if (itemBean != null) {
                tvContent.setText(itemBean.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
