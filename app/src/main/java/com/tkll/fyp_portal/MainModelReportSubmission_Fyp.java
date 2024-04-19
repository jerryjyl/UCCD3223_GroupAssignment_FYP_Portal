package com.tkll.fyp_portal;

public class MainModelReportSubmission_Fyp {
    public String name,url;

    public MainModelReportSubmission_Fyp(){

    }

    public MainModelReportSubmission_Fyp(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
