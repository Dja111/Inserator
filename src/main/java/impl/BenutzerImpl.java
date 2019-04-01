package impl;

import entity.Benutzer;
import utils.BenutzerDao;
import utils.DBUtil;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class BenutzerImpl implements BenutzerDao,Closeable {

    private Connection connection;
    private boolean voll;

    public BenutzerImpl() throws StoreException {
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
    public List<Benutzer> getAllBenutzer() {
        Benutzer benutzer;
        String name;
        String benutzerName;
        Date eintrittDatum;
        ResultSet rs;

        List<Benutzer> benutzers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from dbp04.Benutzer"
            );
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                name = rs.getString(1);
                benutzerName = rs.getString(2);
                eintrittDatum = rs.getDate(3);
                benutzer = new Benutzer(name,benutzerName);
                benutzers.add(benutzer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  benutzers;
    }

    @Override
    public Benutzer getBenutzerByBenutzerName(String benutzerName) {
       Benutzer benutzer = null;
        String name;
        Date eintrittDatum;
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String datum = "";
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM dbp04.Benutzer WHERE benutzerName = ?"
            );
            preparedStatement.setString(1, benutzerName);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                name = rs.getString(1);
                benutzerName = rs.getString(2);
                eintrittDatum = rs.getDate(3);
                datum = df.format(eintrittDatum);
                benutzer = new Benutzer(name,benutzerName,datum);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return benutzer;

    }

    @Override
    public void addBenutzer(Benutzer benutzer) {

    }

    @Override
    public void deleteBenutzer(Benutzer benutzer) {

    }

    @Override
    public void deleteBenutzerByBenutzerName(String benutzerName) {

    }
    //post
}
