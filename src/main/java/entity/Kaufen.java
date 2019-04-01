package entity;

public class Kaufen {

    private int KleinAnzeigeId;
    private String benutzerName;


    public Kaufen(int KleinAnzeige, String benutzerName){
        this.benutzerName = benutzerName;
        this.KleinAnzeigeId = KleinAnzeige;
    }

    Kaufen(String benutzerName){
        this.benutzerName = benutzerName;
    }

    public void setBenutzerName(String benutzerName) {
        this.benutzerName = benutzerName;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public int getKleinAnzeigeId() {
        return KleinAnzeigeId;
    }

    public void setKleinAnzeigeId(int kleinAnzeigeId) {
        KleinAnzeigeId = kleinAnzeigeId;
    }
}
