package utils;

import entity.Nachricht;

import java.util.List;

public interface NachrichtDao {

    public List<Nachricht> getAllNachrichts();


    public  Nachricht getNachrichtById();

    public String addNachricht(Nachricht nachricht);

    public String deleteNachricht(Nachricht nachricht);

}
