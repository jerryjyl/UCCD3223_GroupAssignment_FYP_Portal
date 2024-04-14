package com.tkll.fyp_portal.fragment;

public class listmodel {
    private String areaOfStudy;
    private String moderatorName;
    private String programme;
    private String projectTitle;
    private String projectType;
    private String studentName;
    private String studentNo;
    private String supervisorName;

    // Default constructor required for Firebase
    public listmodel() {
    }

    public listmodel(String areaOfStudy, String moderatorName, String programme, String projectTitle, String projectType, String studentName, String studentNo, String supervisorName) {
        this.areaOfStudy = areaOfStudy;
        this.moderatorName = moderatorName;
        this.programme = programme;
        this.projectTitle = projectTitle;
        this.projectType = projectType;
        this.studentName = studentName;
        this.studentNo = studentNo;
        this.supervisorName = supervisorName;
    }

    public String getAreaOfStudy() {
        return areaOfStudy;
    }

    public String getModeratorName() {
        return moderatorName;
    }

    public String getProgramme() {
        return programme;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getSupervisorName() {
        return supervisorName;
    }
}
