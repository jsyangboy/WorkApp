package demo.yqy.org.workapp.bean;

import android.os.Parcel;

/**
 * @author yqy
 * @create 19-6-24
 * @Describe
 */
public class TitleBean extends ShowBean {
    private String title;

    public TitleBean(String title) {
        this.title = title;
        setShowType(ShowBean.Type_Title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.title);
    }

    protected TitleBean(Parcel in) {
        super(in);
        this.title = in.readString();
    }

    public static final Creator<TitleBean> CREATOR = new Creator<TitleBean>() {
        @Override
        public TitleBean createFromParcel(Parcel source) {
            return new TitleBean(source);
        }

        @Override
        public TitleBean[] newArray(int size) {
            return new TitleBean[size];
        }
    };
}
