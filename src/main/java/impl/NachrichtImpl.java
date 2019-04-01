package impl;

import entity.Nachricht;
import utils.DBUtil;
import utils.NachrichtDao;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NachrichtImpl implements Closeable, NachrichtDao {

    private Connection connection;
    private boolean voll;

    public NachrichtImpl() throws StoreException {
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
    public List<Nachricht> getAllNachrichts() {
        return null;
    }

    @Override
    public Nachricht getNachrichtById() {
        return null;
    }

    @Override
    public String addNachricht(Nachricht nachricht) {
        int status = 0;
        String res ="";
        try {

                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into blocks(blocker, blockee) VALUES(?, ?)" //toDo
                );
                preparedStatement.setString(1, nachricht.getText());
                status = preparedStatement.executeUpdate() ;

        }catch (Exception e){
            throw  new StoreException(e);
        }

        if(status == 1){
            res = "OK";
        } else {
            res = "NOT OK";
        }
        return res;

    }

    @Override
    public String deleteNachricht(Nachricht nachricht) {
        return null;
    }
}
