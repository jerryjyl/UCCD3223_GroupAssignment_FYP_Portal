package com.tkll.fyp_portal.fragment;

public class studlist {


    public class studlist {
        String AreaOfStudy;
        String Programme;
        String ProjectType;
        String StudentName;
        String StudentNo;
        String SupervisorName;

        public studlist(String areaOfStudy, String programme, String projectType, String studentName, String studentNo, String supervisorName) {
            this.AreaOfStudy = areaOfStudy;
            this.Programme = programme;
            this.ProjectType = projectType;
            this.StudentName = studentName;
            this.StudentNo = studentNo;
            this.SupervisorName = supervisorName;
        }

        public String getAreaOfStudy() {
            return AreaOfStudy;
        }

        public void setAreaOfStudy(String areaOfStudy) {
            AreaOfStudy = areaOfStudy;
        }

        public String getProgramme() {
            return Programme;
        }

        public void setProgramme(String programme) {
            Programme = programme;
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

        public String getStudentNo() {
            return StudentNo;
        }

        public void setStudentNo(String studentNo) {
            StudentNo = studentNo;
        }

        public String getSupervisorName() {
            return SupervisorName;
        }

        public void setSupervisorName(String supervisorName) {
            SupervisorName = supervisorName;
        }
    }

}
