package demo.yqy.org.workapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

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

    TextView tvTitle;
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        tvTitle = findViewById(R.id.tv_title);
        mWebView = findViewById(R.id.web);

        try {
            itemBean = getIntent().getParcelableExtra("bean");
            if (itemBean != null) {
                tvTitle.setText(itemBean.getJsName());
            }

            final String fileName = "file:///android_asset/" + itemBean.getJsName() + ".html";
            File file = new File(fileName);
            readHtmlFormAssets(fileName);
            Log.e("yqy", "fileName = " + fileName);
            if (file.exists()) {
                Toast.makeText(this, "有文件", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "没有文件", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readHtmlFormAssets(String url) {
        mWebView.setVisibility(View.VISIBLE);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setBackgroundColor(0);
        mWebView.loadUrl(url);
    }

}
