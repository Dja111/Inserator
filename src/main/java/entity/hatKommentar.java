package entity;

public class hatKommentar {

    private int kleinAnzeigeId;
    private String benutzerName ;
    private  int  kommentarId;


    public hatKommentar(int kleinAnzeigeId, String benutzerName, int kommentarId){
        this.benutzerName = benutzerName;
        this.kleinAnzeigeId = kleinAnzeigeId;
        this.kommentarId = kommentarId;
    }

    public hatKommentar(int kommentarId, String benutzerName){
        this.benutzerName = benutzerName;
        this.kommentarId = kommentarId;
    }

    public String getBenutzerName(){return  benutzerName;}

    public void setKleinAnzeigeId(int kleinAnzeigeId) {
        this.kleinAnzeigeId = kleinAnzeigeId;
    }

    public int getKleinAnzeigeId() {
        return kleinAnzeigeId;
    }

    public int getKommentarId() {
        return kommentarId;
    }

    public void setKommentarId(int kommentarId) {
        this.kommentarId = kommentarId;
    }

    public String getBenutzer() {
        return benutzerName;
    }

    public void setBenutzer(String benutzer) {
        this.benutzerName = benutzer;
    }
}
