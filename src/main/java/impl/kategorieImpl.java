package impl;

import entity.Kategorie;
import utils.DBUtil;
import utils.StoreException;
import utils.kategorieDao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class kategorieImpl implements Closeable, kategorieDao {

    private Connection connection;
    private boolean voll;

    public kategorieImpl() throws StoreException {
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
    public String addKategorie(Kategorie kategorie) {
        String res ="";
        int status =0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert  into dbp04.Kategorie values (?)");
            preparedStatement.setString(1,kategorie.getName());
            status = preparedStatement.executeUpdate();

            if(status == 1){
                res ="was add";
            }else {
                res ="was not add";
            }
        }catch (Exception e){
            throw  new StoreException(e);
        }
        return res;
    }

    @Override
    public Kategorie getByName(String name) {
        Kategorie kategorie = null;
        ResultSet rs;
        String nameR = "";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM INSDBBENUTZERTABLE WHERE benutzerName = ?"
            );
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                nameR = rs.getString(1);
                kategorie = new Kategorie(nameR);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return kategorie;

    }

    @Override
    public List<Kategorie> getAllNames() {
        List<Kategorie> ListKa = new ArrayList<>();
        Kategorie kategorie = null;
        ResultSet rs;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from US" //name muss hinzugefügt werden toDo
            );
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                kategorie = new Kategorie(rs.getString(2));
                ListKa.add(kategorie);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListKa;

    }

    @Override
    public void deleteKategorieByName(String name) {

    }

    @Override
    public void deleteAllKategorie() {

    }
}
