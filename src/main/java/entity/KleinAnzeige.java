package entity;


import java.sql.Date;

public class KleinAnzeige {
    private  int id;
    private String title;
    private String beschreibungsText;
    private  double preis;
    private Date erstellungsDatum;
    private String status;
    private String verkaufer;
    private String datum;

    public KleinAnzeige(int id, String title, String beschreibungsText, double preis, Date erstellungsDatum, String verkaufer, String status){
        this.id = id;
        this.title = title;
        this.beschreibungsText = beschreibungsText;
        this.preis = preis;
        this.erstellungsDatum = erstellungsDatum;
        this.status = status;
        this.verkaufer = verkaufer;
    }
    public KleinAnzeige(java.lang.String title, java.lang.String beschreibungsText, double preis, Date erstellungsDatum,String verkaufer ,String status ){
        this.title = title;
        this.beschreibungsText = beschreibungsText;
        this.preis = preis;
        this.erstellungsDatum = erstellungsDatum;
        this.status = status;
        this.verkaufer = verkaufer;
    }

    public KleinAnzeige(int id,java.lang.String title, java.lang.String beschreibungsText, double preis, String datum,String verkaufer ,String status ){
        this.id = id;
        this.title = title;
        this.beschreibungsText = beschreibungsText;
        this.preis = preis;
        this.datum = datum;
        this.status = status;
        this.verkaufer = verkaufer;
    }

    public KleinAnzeige(java.lang.String title, java.lang.String beschreibungsText, double preis, String datum,String verkaufer ,String status ){

        this.title = title;
        this.beschreibungsText = beschreibungsText;
        this.preis = preis;
        this.datum = datum;
        this.status = status;
        this.verkaufer = verkaufer;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBeschreibungsText(java.lang.String beschreibungsText) {
        this.beschreibungsText = beschreibungsText;
    }

    public void setVerkaufer(java.lang.String verkaufer) {
        this.verkaufer = verkaufer;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void setErstellungsDatum(Date erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

    public int getId() {
        return id;
    }

    public java.lang.String getStatus() {
        return status;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public Date getErstellungsDatum() {
        return erstellungsDatum;
    }

    public double getPreis() {
        return preis;
    }

    public java.lang.String getBeschreibungsText() {
        return beschreibungsText;
    }

    public java.lang.String getVerkaufer() {
        return verkaufer;
    }
}
