package utils;

import entity.Benutzer;

import java.util.List;

public interface BenutzerDao {

    public List<Benutzer> getAllBenutzer();

    public  Benutzer getBenutzerByBenutzerName(String benutzerName);

    public  void addBenutzer(Benutzer benutzer);

    public void deleteBenutzer(Benutzer benutzer);

    public void deleteBenutzerByBenutzerName(String benutzerName);
}
