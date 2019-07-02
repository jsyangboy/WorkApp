package demo.yqy.org.workapp.bean;

/**
 * @author yqy
 * @create 19-6-24
 * @Describe
 */
public class ShowBean {
    public final static int Type_Title = 0;
    public final static int Type_Item = 1;
    private int showType;

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }
}
