package utils;

import entity.Kategorie;

import java.util.List;

public interface kategorieDao {

    public String addKategorie(Kategorie kategorie);

    public Kategorie getByName(String name);

    public List<Kategorie> getAllNames();

    public void deleteKategorieByName(String name);

    public void deleteAllKategorie();
}
