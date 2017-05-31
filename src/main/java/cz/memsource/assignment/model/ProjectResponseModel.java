package cz.memsource.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectResponseModel {

    private List<ProjectModel> projectModels;

    private String reason;

    private final boolean success;

    public ProjectResponseModel() {
        this.success = true;
    }

    public ProjectResponseModel(String reason) {
        this.reason = reason;
        this.success = false;
    }

    public void addProject(ProjectModel projectModel) {
        if (this.projectModels == null) {
            this.projectModels = new ArrayList<>();
        }
        this.projectModels.add(projectModel);
    }

    public static class ProjectModel {

        private final String name;

        private final String sourceLanguage;

        private final String targetLanguage;

        private final String status;

        public ProjectModel(String name, String sourceLanguage, String targetLanguage, String status) {
            this.name = name;
            this.sourceLanguage = sourceLanguage;
            this.targetLanguage = targetLanguage;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public String getSourceLanguage() {
            return sourceLanguage;
        }

        public String getTargetLanguage() {
            return targetLanguage;
        }

        public String getStatus() {
            return status;
        }
    }

    public String getReason() {
        return reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<ProjectModel> getProjectModels() {
        return projectModels;
    }

    public void setProjectModels(List<ProjectModel> projectModels) {
        this.projectModels = projectModels;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
