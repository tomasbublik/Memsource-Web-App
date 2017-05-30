package cz.memsource.assignment.api;

public class MemsourceLogin {

    private String userName;

    private String password;

    private String code;

    public MemsourceLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.code = "0";
    }

    public MemsourceLogin(String userName, String password, String code) {
        this.userName = userName;
        this.password = password;
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
