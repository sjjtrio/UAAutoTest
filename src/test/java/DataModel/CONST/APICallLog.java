package DataModel.CONST;


public class APICallLog {

    private String method;
    private String message;
    private String name;
    private String status;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public APICallLog(String method, String message, String name,int status) {
        this.method = method;
        this.message = message;
        this.name = name;
        this.status = Integer.toString(status);
    }
}
