package utils;

import entity.KleinAnzeige;

import java.util.List;

public interface KleinanzeigeDao {

    public String addKleinanzeige(String title, String beschreibung, double preis, String loginBenutzerName, String status, String kategorieName);

    public List<KleinAnzeige> getAllKleinanzeige();

    public List<KleinAnzeige> getAllKleinanzeigeByUserNameAktiv(String benutzerName);

    public List<KleinAnzeige> getAllKleinanzeigeByUserNameVerkauft(String benutzerName);

    public KleinAnzeige getByPreis(double preis);

    public KleinAnzeige getByTitle(String title);

    public KleinAnzeige getById (int id);

    public String getByVerkaufer(String verkaufer);

    public String updateKleinanzeige(KleinAnzeige kleinanzeige);

    public  void deleteByVerkaufer(java.lang.String verkaufer);

    public  String deleteKleinAnzeige(int id);

    public void deleteAllanzeige();

    public void deleteKleinanzeigeByPreis(double preis);

    public void deleteKleinanzeigeByTitle(java.lang.String title);

    public  void deleteKleinanzeigeById(int id);
}
