package api;

public class UnSuccessRegister {
    private String error;

    public UnSuccessRegister(String error){
        this.error = error;
    }
    public String getError() {
        return error;
    }
}
