package demo.yqy.org.workapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author yqy
 * @create 19-6-24
 * @Describe
 */
public class ShowBean implements Parcelable {
    public final static int Type_Title = 0;
    public final static int Type_Item = 1;
    private int showType;

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.showType);
    }

    public ShowBean() {
    }

    protected ShowBean(Parcel in) {
        this.showType = in.readInt();
    }

}
