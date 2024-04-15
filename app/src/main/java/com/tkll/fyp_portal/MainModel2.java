package com.tkll.fyp_portal;
public class MainModel2 {

    String AreaOfStudy;
    String ModeratorName;
    String Programme;
    String ProjectTitle;
    String ProjectType;
    String StudentName;
    String SupervisorName;


    MainModel2(){

    }

    public MainModel2(String areaOfStudy, String moderatorName, String programme, String projectTitle, String projectType, String studentName, String supervisorName) {
        AreaOfStudy = areaOfStudy;
        ModeratorName = moderatorName;
        Programme = programme;
        ProjectTitle = projectTitle;
        ProjectType = projectType;
        StudentName = studentName;
        SupervisorName = supervisorName;
    }

    public String getAreaOfStudy() {
        return AreaOfStudy;
    }

    public void setAreaOfStudy(String areaOfStudy) {
        AreaOfStudy = areaOfStudy;
    }

    public String getModeratorName() {
        return ModeratorName;
    }

    public void setModeratorName(String moderatorName) {
        ModeratorName = moderatorName;
    }

    public String getProgramme() {
        return Programme;
    }

    public void setProgramme(String programme) {
        Programme = programme;
    }

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        ProjectTitle = projectTitle;
    }

    public String getProjectType() {
        return ProjectType;
    }

    public void setProjectType(String projectType) {
        ProjectType = projectType;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getSupervisorName() {
        return SupervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        SupervisorName = supervisorName;
    }
}

