package entity;


import java.sql.Date;

public class Kommentar {
    private int kommentarId;
    private String text;
    private Date erstellungsDate;
    private  String benutzerName;

    public Kommentar(String benutzerName, String text){
        this.benutzerName = benutzerName;
        this.text = text;
    }
    public Kommentar(int kommentarId, String text, Date erstellungsDate){
        this.kommentarId = kommentarId;
        this.text = text;
        this.erstellungsDate = erstellungsDate;
    }
    public Kommentar(String text, Date erstellungsDate){
        this.text = text;
        this.erstellungsDate = erstellungsDate;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public Kommentar(String text){
        this.text = text;
    }

    public void setErstellungsDate(Date erstellungsDate) {
        this.erstellungsDate = erstellungsDate;
    }

    public void setKommentarId(int kommentarId) {
        this.kommentarId = kommentarId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getErstellungsDate() {
        return erstellungsDate;
    }

    public int getKommentarId() {
        return kommentarId;
    }

    public String getText() {
        return text;
    }
}
