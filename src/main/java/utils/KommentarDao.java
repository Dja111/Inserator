package utils;

import entity.Kommentar;
import entity.hatKommentar;

import java.util.List;

public interface KommentarDao {

        public  String addKommentar(Kommentar kommentar, String benutzerName, int anzeigeID);

        public List<Kommentar> getAllKommentar();

        public List<Kommentar> getByKommentarId(int id);

        public  void deleteByKommentarId(int id);

        public  void deleteAllKommentar();
}
