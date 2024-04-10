package com.tkll.fyp_portal;
public class MainModel {

    String ImagePath;
    String Supervisor;
    String ProjectTitle;
    String Description;
    String ExpectedOutcome;
    String AreaOfStudy;
    String SkillsRequired;
    String NoStudents;
    String Equipment;
    String SuitableFor;


    MainModel(){

    }

    public MainModel(String imagePath,String supervisor, String projectTitle, String description, String expectedOutcome, String areaOfStudy, String skillsRequired, String noStudents, String equipment, String suitableFor) {
        ImagePath=imagePath;
        Supervisor = supervisor;
        ProjectTitle = projectTitle;
        Description = description;
        ExpectedOutcome = expectedOutcome;
        AreaOfStudy = areaOfStudy;
        SkillsRequired = skillsRequired;
        NoStudents = noStudents;
        Equipment = equipment;
        SuitableFor = suitableFor;
    }
    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
    public String getSupervisor() {
        return Supervisor;
    }

    public void setSupervisor(String supervisor) {
        Supervisor = supervisor;
    }

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        ProjectTitle = projectTitle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getExpectedOutcome() {
        return ExpectedOutcome;
    }

    public void setExpectedOutcome(String expectedOutcome) {
        ExpectedOutcome = expectedOutcome;
    }

    public String getAreaOfStudy() {
        return AreaOfStudy;
    }

    public void setAreaOfStudy(String areaOfStudy) {
        AreaOfStudy = areaOfStudy;
    }

    public String getSkillsRequired() {
        return SkillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        SkillsRequired = skillsRequired;
    }

    public String getNoStudents() {
        return NoStudents;
    }

    public void setNoStudents(String noStudents) {
        NoStudents = noStudents;
    }

    public String getEquipment() {
        return Equipment;
    }

    public void setEquipment(String equipment) {
        Equipment = equipment;
    }

    public String getSuitableFor() {
        return SuitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        SuitableFor = suitableFor;
    }

}

