package com.example.pr11_deepsea.Model;

public class DashboardModel {
    private String  activityHeading, activityInfo;
    private int activityPicture;

    public DashboardModel() {
    }

    public DashboardModel(int activityPicture, String activityHeading, String activityInfo) {
        this.activityPicture = activityPicture;
        this.activityHeading = activityHeading;
        this.activityInfo = activityInfo;
    }

    public int getActivityPicture() {
        return activityPicture;
    }

    public void setActivityPicture(int activityPicture) {
        this.activityPicture = activityPicture;
    }

    public String getActivityHeading() {
        return activityHeading;
    }

    public void setActivityHeading(String activityHeading) {
        this.activityHeading = activityHeading;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }
}
