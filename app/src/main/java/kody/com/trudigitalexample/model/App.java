package kody.com.trudigitalexample.model;

import android.graphics.drawable.Drawable;

/**
 * Created by kody on 2/1/16.
 * can be used by kody and people in [kody}]
 */
public class App {

    private String name;
    private String shortDescription;
    private String longDescription;
    private Drawable icon;
    private int iconId, splashId, apkId;

    public App(String name, String shortDescription, String longDescription, Drawable icon,
               int iconId, int splashId, int apkID) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.icon = icon;
        this.iconId = iconId;
        this.splashId = splashId;
        this.apkId = apkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getApkId() {
        return apkId;
    }

    public int getSplashId() {
        return splashId;
    }
}
