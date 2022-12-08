package api;

//json в pojo class переводим реквест
public class Register {
    private String email;
    private String password;

    public Register(String email, String password) {
        this.email = email; //вызываем метод для ссылки на этот обьект
        this.password = password;
    }
}
