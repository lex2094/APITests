package api;

//json в pojo class переводим респонс

public class SucessRegister  {
    private Integer id;
    private String token;

    //создаем геттеры
    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
