package demo.yqy.org.workapp.bean;

/**
 * @author yqy
 * @create 19-6-24
 * @Describe
 */
public class ItemBean extends ShowBean {

    private String content;//对应的标题


    public ItemBean(String content) {
        this.content = content;
        setShowType(ShowBean.Type_Item);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
