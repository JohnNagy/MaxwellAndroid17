package nicholasbenson.offuttairshowapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

public class ExpandedMenuModelChild {

    private String ChildName = "";
    private int iconImg = -1;
    private int indicatorImg = -1;
    private int HeaderTint = Color.GREEN;
    private Context ctx;


    public String getChildName() {
        return ChildName;
    }
    public void setChildName(String childName) {
        this.ChildName = childName;
    }
    public int getIconImg() {
        return iconImg;
    }
    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }

}
