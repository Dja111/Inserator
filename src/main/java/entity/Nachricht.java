package entity;

public class Nachricht {
    private int id;
    private String text;

    Nachricht(int id, String text){
        this.id = id;
        this.text = text;
    }
    public Nachricht(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }
}
