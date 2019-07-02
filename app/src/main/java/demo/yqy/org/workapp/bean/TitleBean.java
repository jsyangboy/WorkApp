package demo.yqy.org.workapp.bean;

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
}
