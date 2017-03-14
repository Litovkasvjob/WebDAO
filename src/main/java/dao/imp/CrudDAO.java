package dao.imp;

import dao.api.Dao;
import dataSource.DataSourse;
import model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by SergLion on 06.03.2017.
 */
public abstract class CrudDAO<T extends Entity<Integer>> implements Dao<Integer, T> {

    private Class<T> type;
    private DataSourse dataSourse;

    private static final String SELECT_ALL = "SELECT * FROM %s";
    private static final String FIND_BY_ID = "SELECT * FROM %s WHERE %s_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE %s_id = ?";

    public CrudDAO(Class<T> type) {
        this.type = type;
        this.dataSourse = DataSourse.getInstance();
    }

    public List<T> getAll() {
        String sql = String.format(SELECT_ALL, type.getSimpleName());

        List result = null;

        try(Connection connection = dataSourse.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }




    public T getById(Integer key) {

        String sql = String.format(FIND_BY_ID, type.getSimpleName(), type.getSimpleName() );

        List result = null;

        try(Connection connection = dataSourse.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (result.size() > 0) ? (T) result.get(0) : null;
    }

    public void save(T entity) {

        try(Connection connection = dataSourse.getConnection()){
            PreparedStatement preparedStatement = createInsertStatement(connection, entity);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer key) {

        String sql = String.format(DELETE_BY_ID, type.getSimpleName(), type.getSimpleName());

        try(Connection connection = dataSourse.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void update(T entity) {

        try(Connection connection = dataSourse.getConnection()){
            PreparedStatement preparedStatement = createUpdateStatement(connection, entity);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    protected abstract List<T> readAll(ResultSet resultSet) throws SQLException;

    protected abstract PreparedStatement createInsertStatement(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement createUpdateStatement(Connection connection, T entity) throws SQLException;



}
