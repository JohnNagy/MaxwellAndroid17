package nicholasbenson.offuttairshowapp;

public class Parent {
    private String mTitle;
    private String ChildBody;
    private String ChildLink;
    private Integer color;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getChildBody() {
        return ChildBody;
    }

    public void setChildBody(String ChildBody) {
        this.ChildBody = ChildBody;
    }

    public String getChildLink() {
        return ChildLink;
    }

    public void setChildLink(String ChildLink) {
        this.ChildLink = ChildLink;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
