package impl;

import entity.hatKommentar;
import utils.DBUtil;
import utils.HatKommentarDao;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HatKommentarImpl implements HatKommentarDao,Closeable {

    private Connection connection;
    private boolean voll;

    public HatKommentarImpl()throws StoreException {
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
    public String addNewHatKommentar(hatKommentar hatKommentar) {

        String res ="";
        int status = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into dbp04.HatKommentar values (?,?,?)"
            );
            preparedStatement.setInt(1,hatKommentar.getKommentarId());
            preparedStatement.setString(2,hatKommentar.getBenutzer());
            preparedStatement.setInt(3,hatKommentar.getKleinAnzeigeId());

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
}
