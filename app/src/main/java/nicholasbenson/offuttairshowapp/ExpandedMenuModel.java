package nicholasbenson.offuttairshowapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

public class ExpandedMenuModel {

    private String iconName = "";
    private int iconImg = -1;
    private int indicatorImg = -1;
    private int HeaderTint = Color.GREEN;
    private int indicatorTint = Color.TRANSPARENT;
    private Context ctx;

    public ExpandedMenuModel (Context context)
    {
        this.ctx = context;
        HeaderTint = ContextCompat.getColor(ctx, R.color.colorPrimaryDark);

    }

    public int getIndicatorTint() {
        return indicatorTint;
    }
    public void setIndicatorTint(int indicatorTint) {
        this.indicatorTint = indicatorTint;
    }
    public String getIconName() {
        return iconName;
    }
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
    public int getIconImg() {
        return iconImg;
    }
    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }
    public int getIndicatorImg()
    {
        return indicatorImg;
    }
    public void setIndicatorImg(int indicatorImg)
    {
        this.indicatorImg = indicatorImg;
    }
    public int getHeaderTint()
    {
        return HeaderTint;
    }
    public void setHeaderTint(int Tint)
    {
        this.HeaderTint = Tint;
    }
}
