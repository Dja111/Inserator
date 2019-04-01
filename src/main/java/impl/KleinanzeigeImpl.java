package impl;


import entity.KleinAnzeige;
import utils.DBUtil;
import utils.KleinanzeigeDao;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KleinanzeigeImpl implements Closeable, KleinanzeigeDao {

    private Connection connection;
    private boolean voll;

    public KleinanzeigeImpl() throws StoreException {
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
    public java.lang.String addKleinanzeige(String title, String beschreibung, double preis, String loginBenutzerName, String status, String kategorieName) {

        java.lang.String res ="";

        int Status = 0;

        int StatusForUpdateKategorie = 0;

        String result ="";

        int insered2 = 0;


        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO dbp04.Anzeige (titel,text,preis,ersteller,status) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, beschreibung);
            preparedStatement.setDouble(3, preis);
            preparedStatement.setString(4, loginBenutzerName);
            preparedStatement.setString(5, status);
            Status = preparedStatement.executeUpdate();


        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("select id from dbp04.Anzeige where erstellungsdatum = (select max(erstellungsdatum) from dbp04.Anzeige)");
            ResultSet id = preparedStatement1.executeQuery();
            while (id.next()){
                int idi = id.getInt("id");
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO dbp04.HatKategorie (anzeigeID,kategorie) VALUES (?,?)");
                preparedStatement2.setInt(1, idi);
                preparedStatement2.setString(2, kategorieName);
                insered2 = preparedStatement2.executeUpdate();
                connection.commit();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(Status == 1 && insered2 == 1){
            res = "OK";
        } else {
            res = "NOT OK";

        }
        return res;


    }


    @Override
    public List<KleinAnzeige> getAllKleinanzeige() {

        List<KleinAnzeige> result  = new ArrayList<>();
        int id = 0;
        String title ="";
        String beschreibung ="";
        double preis = 0;
        String datum="";
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String verkaufer ="";
        String status ="";
        ResultSet rs ;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from dbp04.Anzeige");
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
                title= rs.getString(2);
                beschreibung = rs.getString(3);
                 preis = rs.getDouble(4);
                 Date date = rs.getDate(5);
                 verkaufer = rs.getString(6);
                 status= rs.getString(7);
                 datum = df.format(date);
                 KleinAnzeige kleinAnzeige = new KleinAnzeige(id,title,beschreibung,preis,datum,verkaufer,status);
                result.add(kleinAnzeige);
            }
        }catch (Exception e){
            throw  new StoreException(e);
        }
        return  result;
    }

    @Override
    public List<KleinAnzeige> getAllKleinanzeigeByUserNameAktiv(String benutzerName) {
        List<KleinAnzeige> result  = new ArrayList<>();
        int id = 0;
        String title ="";
        String beschreibung ="";
        double preis = 0;
        String datum="";
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String verkaufer ="";
        String status ="";
        ResultSet rs ;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from dbp04.Anzeige where ersteller = ? and status = ?");

            preparedStatement.setString(1,benutzerName);
            preparedStatement.setString(2,"aktiv");
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
                title= rs.getString(2);
                beschreibung = rs.getString(3);
                preis = rs.getDouble(4);
                Date date = rs.getDate(5);
                verkaufer = rs.getString(6);
                status= rs.getString(7);
                datum = df.format(date);
                KleinAnzeige kleinAnzeige = new KleinAnzeige(id,title,beschreibung,preis,datum,verkaufer,status);
                result.add(kleinAnzeige);
            }
        }catch (Exception e){
            throw  new StoreException(e);
        }
        return  result;
    }

    @Override
    public List<KleinAnzeige> getAllKleinanzeigeByUserNameVerkauft(String benutzerName) {
        List<KleinAnzeige> result  = new ArrayList<>();
        int id = 0;
        String title ="";
        String beschreibung ="";
        double preis = 0;
        String datum="";
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String verkaufer ="";
        String status ="";
        ResultSet rs ;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from dbp04.Anzeige where ersteller = ? and status = ?");
            preparedStatement.setString(1,benutzerName);
            preparedStatement.setString(2,"verkauft");
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
                title= rs.getString(2);
                beschreibung = rs.getString(3);
                preis = rs.getDouble(4);
                Date date = rs.getDate(5);
                verkaufer = rs.getString(6);
                status= rs.getString(7);
                datum = df.format(date);
                KleinAnzeige kleinAnzeige = new KleinAnzeige(id,title,beschreibung,preis,datum,verkaufer,status);
                result.add(kleinAnzeige);
            }
        }catch (Exception e){
            throw  new StoreException(e);
        }
        return  result;
    }

    @Override
    public KleinAnzeige getByPreis(double preis) {

        KleinAnzeige kleinanzeige = null;
        ResultSet rs;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from dbp04.Anzeige where preis = ?");
            preparedStatement.setDouble(1,preis);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                kleinanzeige = new KleinAnzeige(rs.getString(2),rs.getString(3),rs.getDouble(4),
                        rs.getDate(5),rs.getString(6),rs.getString(7));
            }
        }catch (Exception e){
            throw new  StoreException(e);
        }
        return  kleinanzeige;

    }

    @Override
    public KleinAnzeige getByTitle(String title) {
        KleinAnzeige kleinanzeige = null;
        ResultSet rs;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from dbp04.Anzeige where TITLE = ?"
            );
            preparedStatement.setString(1,title);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                kleinanzeige = new KleinAnzeige(rs.getString(2),rs.getString(3),rs.getDouble(4),
                        rs.getDate(5),rs.getString(6),rs.getString(7));
            }
        }catch (Exception e){
            throw new  StoreException(e);
        }
        return  kleinanzeige;

    }

    @Override
    public KleinAnzeige getById(int id) {
        KleinAnzeige kleinAnzeige = null;
        String title ="";
        String beschreibung ="";
        double preis = 0;
        String datum="";
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String verkaufer ="";
        String status ="";
        ResultSet rs;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from dbp04.Anzeige where id = ?");
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                title= rs.getString(2);
                beschreibung = rs.getString(3);
                preis = rs.getDouble(4);
                Date date = rs.getDate(5);
                verkaufer = rs.getString(6);
                status= rs.getString(7);
                datum = df.format(date);
                kleinAnzeige = new KleinAnzeige(title,beschreibung,preis,datum,verkaufer,status);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return kleinAnzeige;
    }

    @Override
    public String getByVerkaufer(String verkaufer) {
        return null;
    }

    @Override
    public String updateKleinanzeige(KleinAnzeige kleinanzeige) {

        String res = "";
        int status =0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update dbp04.Anzeige set title = ?,set Text =?, set preis =?" +
                    "set erstellungsdatum =? ,set ersteller =?,set status = ? where id = ?");
            preparedStatement.setString(1,kleinanzeige.getTitle());
            preparedStatement.setString(2,kleinanzeige.getBeschreibungsText());
            preparedStatement.setDouble(3,kleinanzeige.getPreis());
            preparedStatement.setDate(4,kleinanzeige.getErstellungsDatum());
            preparedStatement.setString(5,kleinanzeige.getVerkaufer());
            preparedStatement.setString(6,kleinanzeige.getStatus());
            preparedStatement.setInt(7,kleinanzeige.getId());
            status = preparedStatement.executeUpdate();
            if(status == 1){
                res ="was updated";
            }else {
                res = "was not updated";
            }
        }catch (Exception e){
            throw  new  StoreException(e);
        }
        return res;
    }

    @Override
    public void deleteByVerkaufer(java.lang.String verkaufer) {

    }

    @Override
    public String deleteKleinAnzeige(int id) {
        String res ="";
        int status =0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from dbp04.Anzeige where id =? ");
            preparedStatement.setInt(1,id);
            status = preparedStatement.executeUpdate();
            if(status == 1){
                res ="was deleted";
                connection.commit();
            }else{
                res ="was not deleted";
            }
        }catch (Exception e){
            throw new StoreException(e);
        }
        return res;
    }

    @Override
    public void deleteAllanzeige() {

    }

    @Override
    public void deleteKleinanzeigeByPreis(double preis) {

    }

    @Override
    public void deleteKleinanzeigeByTitle(java.lang.String title) {

    }

    @Override
    public void deleteKleinanzeigeById(int id) {

    }
}
