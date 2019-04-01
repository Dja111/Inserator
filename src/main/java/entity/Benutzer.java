package entity;

import java.util.Date;

public class Benutzer {
    private String name;
    private String benutzerName;
    private Date eintrittDatum;
    private  String datum;

    public Benutzer(String name, String benutzerName, Date eintrittDatum){
        this.name = name;
        this.benutzerName = benutzerName;
        this.eintrittDatum = eintrittDatum;
    }

    public Benutzer(String name, String benutzerName, String datum){
        this.name = name;
        this.benutzerName = benutzerName;
        this.datum = datum;
    }

    public  Benutzer(String name, String benutzerName){
        this.name = name;
        this.benutzerName = benutzerName;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getName() {
        return name;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public Date getEintrittDatum() {
        return eintrittDatum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBenutzerName(String benutzerName) {
        this.benutzerName = benutzerName;
    }

    public void setEintrittDatum(Date eintrittDatum) {
        this.eintrittDatum = eintrittDatum;
    }
}
