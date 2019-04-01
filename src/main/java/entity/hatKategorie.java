package entity;

public class hatKategorie {

    private int  kleinAnzeigeId;
    private String kategorieName;

    public hatKategorie(int kleinAnzeigeId, String hategorieName){

        this.kleinAnzeigeId = kleinAnzeigeId;
        this.kategorieName = hategorieName;
    }

    public int getKleinAnzeigeId() {
        return kleinAnzeigeId;
    }

    public void setKleinAnzeigeId(int kleinAnzeigeId) {
        this.kleinAnzeigeId = kleinAnzeigeId;
    }

    public String getKategorieName() {
        return kategorieName;
    }

    public void setKategorieName(String kategorieName) {
        this.kategorieName = kategorieName;
    }
}
