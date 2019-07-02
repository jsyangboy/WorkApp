package demo.yqy.org.workapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author yqy
 * @create 19-6-24
 * @Describe
 */
public class ItemBean extends ShowBean implements Parcelable {

    private String content;//对应的标题
    private String title;
    public ItemBean(String title,String content) {
        this.title = title;
        this.content = content;
        setShowType(ShowBean.Type_Item);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        dest.writeString(this.content);
        dest.writeString(this.title);
    }

    protected ItemBean(Parcel in) {
        super(in);
        this.content = in.readString();
        this.title = in.readString();
    }

    public static final Creator<ItemBean> CREATOR = new Creator<ItemBean>() {
        @Override
        public ItemBean createFromParcel(Parcel source) {
            return new ItemBean(source);
        }

        @Override
        public ItemBean[] newArray(int size) {
            return new ItemBean[size];
        }
    };
}
