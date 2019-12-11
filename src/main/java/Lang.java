public class Lang {
    private String code;
    private String welcomeMessage;
    private Long id;

    public Lang(String code, String welcomeMessage, Long id) {
        this.code = code;
        this.welcomeMessage = welcomeMessage;
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public Long getId() {
        return id;
    }
}
