package impl;

import entity.Kaufen;
import entity.KleinAnzeige;
import utils.DBUtil;
import utils.KaufenDao;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class KaufenImpl implements KaufenDao,Closeable {

    private Connection connection;
    private boolean voll;

    public KaufenImpl()throws StoreException {
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
    public String addNewKauf(Kaufen kaufen) {
        String res ="";
        int status =0;
        KleinAnzeige kleinAnzeige = null;

        int id = 0;
        String title ="";
        String beschreibung ="";
        double preis = 0;
        String datum="";
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String verkaufer ="";
        String Status ="";
        String loginBenutzer = "k.ralf";
        int ID = kaufen.getKleinAnzeigeId();
        String compare = "aktiv   ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from dbp04.Anzeige where id = ?");
            preparedStatement.setInt(1,ID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
                title= rs.getString(2);
                beschreibung = rs.getString(3);
                preis = rs.getDouble(4);
                Date date = rs.getDate(5);
                verkaufer = rs.getString(6);
                Status= rs.getString(7);
                datum = df.format(date);
                 kleinAnzeige = new KleinAnzeige(id,title,beschreibung,preis,datum,verkaufer,Status);

            }
            if(!(compare.equals(kleinAnzeige.getStatus() ))){
                res ="was already buy";
                return  res;
            }else {

                try {
                    PreparedStatement preparedStatement1 = connection.prepareStatement("insert into dbp04.Kauft (benutzerName, anzeigeID) values (?,?) ");
                    preparedStatement1.setString(1,loginBenutzer);
                    preparedStatement1.setInt(2,kleinAnzeige.getId());
                    status = preparedStatement1.executeUpdate();
                    if(status == 1){
                        res = " article was buy";
                        connection.commit();
                    }else {
                        res = " article was not buy";
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
