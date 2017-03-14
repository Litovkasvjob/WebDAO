package dao.imp;

import model.Adress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SergLion on 11.03.2017.
 */
public class AdressDAOImp extends CrudDAO<Adress> {

    private static final String INSERT_ADRESS = "INSERT INTO adress (town, street, house, flat) VALUES (town = ?, street = ?, house = ?, flat = ?);";
    private static final String UPDATE_ADRESS = "UPDATE adress SET town = ?, street = ?, house = ?, flat = ? WHERE adress_id = ?;";

    private static AdressDAOImp adressDAOImp;

    private AdressDAOImp(Class<Adress> type) {
        super(type);
    }

    public static synchronized AdressDAOImp getInstance() {
        if (adressDAOImp == null) {
            adressDAOImp = new AdressDAOImp(Adress.class);
        }
        return adressDAOImp;
    }


    @Override
    protected List<Adress> readAll(ResultSet resultSet) throws SQLException {
        List<Adress> result = new ArrayList<>();
        Adress adress = null;
        while (resultSet.next()) {
            adress = new Adress();
            adress.setId(resultSet.getInt("adress_id"));
            adress.setTown(resultSet.getString("town"));
            adress.setStreet(resultSet.getString("street"));
            adress.setHouse(resultSet.getString("house"));
            adress.setFlat(Integer.parseInt(resultSet.getString("flat")));
            result.add(adress);
        }
        return result;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Adress entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADRESS, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getTown());
        preparedStatement.setString(2, entity.getStreet());
        preparedStatement.setString(3, entity.getHouse());
        preparedStatement.setString(4, String.valueOf(entity.getFlat()));
        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Adress entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADRESS);
        preparedStatement.setString(1, entity.getTown());
        preparedStatement.setString(2, entity.getStreet());
        preparedStatement.setString(3, entity.getHouse());
        preparedStatement.setString(4, String.valueOf(entity.getFlat()));
        preparedStatement.setInt(5, entity.getId());
        return preparedStatement;
    }
}

