public class Project {
    private String projectName;
    private String role;
    private String description;
    private String startDate;
    private String endDate;

    public Project(String projectName, String role, String description, String startDate, String endDate) {
        this.projectName = projectName;
        this.role = role;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getProjectName() { return projectName; }
    public void setProjectName(String name) { this.projectName = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDescription() { return description; }
    public void setDescription(String desc) { this.description = desc; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String start) { this.startDate = start; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String end) { this.endDate = end; }
}