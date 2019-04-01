package impl;

import entity.Kommentar;
import entity.hatKommentar;
import utils.DBUtil;
import utils.KommentarDao;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KommentarImpl implements Closeable, KommentarDao {

    private Connection connection;
    private boolean voll;

    public KommentarImpl() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection("INSDB");
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (voll) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }

    @Override
    public String addKommentar(Kommentar kommentar, String benutzerName, int anzeigeId) {
        String res = "";
        int status = 0;
        int statusInsertHazKomment = 0;
        int id = 0;
        String loginBenutzer = "k.ralf";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into dbp04.Kommentar (text) VALUES(?)" // toDo
            );
            preparedStatement.setString(1, kommentar.getText());
            status = preparedStatement.executeUpdate();

            PreparedStatement selectIdKommentar = connection.prepareStatement("select id from dbp04.Kommentar where erstellungsdatum = (select max(erstellungsdatum) from dbp04.Kommentar)");
            ResultSet re = selectIdKommentar.executeQuery();
            if(re.next()){
                id = re.getInt(1);
            }
            PreparedStatement insertIntoHatKommentar = connection.prepareStatement("insert  into dbp04.HatKommentar (kommentarID,benutzername,anzeigeID) values (?,?,?)");
            insertIntoHatKommentar.setInt(1,id);
            insertIntoHatKommentar.setString(2,loginBenutzer);
            insertIntoHatKommentar.setInt(3,anzeigeId);

            statusInsertHazKomment = insertIntoHatKommentar.executeUpdate();

            if (statusInsertHazKomment == 1){
                res = "was add";
                connection.commit();

            }else{
                res = "was not add";

            }

        }catch (Exception e){
            throw new StoreException(e);
        }
        if(status == 1){
            res = "OK";
        } else {
            res = "NOT OK";
        }
        return res;
    }

    @Override
    public List<Kommentar> getAllKommentar() {
        List<Kommentar> resul = new ArrayList<>();
        ResultSet rs;
        Kommentar kommentar = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from US" //name muss hinzugefügt werden toDo
            );
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                kommentar = new Kommentar(rs.getString(2),rs.getDate(3));
                resul.add(kommentar);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  resul;
    }

    @Override
    public List<Kommentar> getByKommentarId(int id) {

        List<hatKommentar> hetKommentarWithThisId = new ArrayList<>();
        List<Kommentar> myResult = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select kommentarID,benutzername from dbp04.HatKommentar where anzeigeID = ? ");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                hatKommentar hatKommentar = new hatKommentar(rs.getInt(1),rs.getString(2));
                hetKommentarWithThisId.add(hatKommentar);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select text from dbp04.Kommentar where id = ?");

            for( int i = 0 ; i <= hetKommentarWithThisId.size(); i++){

                preparedStatement.setInt(1,hetKommentarWithThisId.get(i).getKommentarId());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    Kommentar kommentar = new Kommentar(hetKommentarWithThisId.get(i).getBenutzerName(), rs.getString(1));
                    myResult.add(kommentar);
                }
            }
           //while (hetKommentarWithThisId.iterator().hasNext()){

            //}

        }catch (Exception e){
            e.printStackTrace();
        }


        return myResult;
    }

    @Override
    public void deleteByKommentarId(int id) {

    }

    @Override
    public void deleteAllKommentar() {

    }
}
