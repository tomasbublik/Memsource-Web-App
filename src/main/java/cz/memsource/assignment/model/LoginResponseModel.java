package cz.memsource.assignment.model;

public class LoginResponseModel {

    private final boolean success;

    private String reason;

    public LoginResponseModel() {
        this.success = true;
        this.reason = "";
    }

    public LoginResponseModel(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getReason() {
        return reason;
    }
}
