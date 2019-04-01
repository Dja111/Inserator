package impl;

import entity.hatKategorie;
import utils.DBUtil;
import utils.StoreException;
import utils.hatKategorieDao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HatKategorieImpl implements hatKategorieDao,Closeable {

    private Connection connection;
    private boolean voll;

    public HatKategorieImpl()throws StoreException {
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
    public String addHatKategorie(hatKategorie hatKategorie) {
        String res ="";
        int status = 0;

        try {
            this.connection.setAutoCommit(false);


            PreparedStatement preparedStatement = connection.prepareStatement("insert into dbp04.HatKategorie values (?,?)");
            status = preparedStatement.executeUpdate();
            if(status == 1){
                res ="was add";
            }else {
                res ="was not add";
            }
        }catch (Exception e){
            throw  new StoreException(e);
        }
        return  res;
    }
}
