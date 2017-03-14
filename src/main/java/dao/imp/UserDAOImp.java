package dao.imp;

import dao.DaoFactory;
import dataSource.DataSourse;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SergLion on 11.03.2017.
 */
public class UserDAOImp extends CrudDAO<User>{


    private static UserDAOImp userDAOImp;

    private static final String INSERT_USER = "INSERT INTO user (login, password, role_id) VALUES (?, ?, ?);";
    private static final String UPDATE_USER = "UPDATE user SET login = ?, password = ?, role_id = ? WHERE user_id = ?;";


    private UserDAOImp(Class<User> type) {
        super(type);
    }

    public static synchronized UserDAOImp getInstanse() {
        if (userDAOImp == null) {
            userDAOImp = new UserDAOImp(User.class);
        }
        return userDAOImp;
    }

    @Override
    protected List<User> readAll(ResultSet resultSet) throws SQLException {

        List<User> result = new ArrayList<>();
        User user = null;
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(DaoFactory.getInstance().getRoleDao().getById(resultSet.getInt("role_id")));
            result.add(user);

        }
        return result;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setInt(3, entity.getRole().getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setInt(3, entity.getRole().getId());
        preparedStatement.setInt(4, entity.getId());
        return preparedStatement;
    }

    public void readUserInfo(Integer user_id) {

        String sql = "SELECT user.user_id, user.login, user.password, role.name FROM user LEFT JOIN role" +
                " ON user.user_id = role.role_id WHERE user.user_id = ?;";

        String sql_adress = "SELECT adress.town, adress.street, adress.house FROM adress WHERE adress.adress_id = ?;";

        String sql_music = "SELECT music_type.type FROM music_type LEFT JOIN user_music ON music_type.music_id = user_music.music_id WHERE user_music.user_id = ?";
        String result = "";


        try (Connection connection = DataSourse.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result = result + String.valueOf(resultSet.getInt("user_id")) + " " + resultSet.getString("login") + " " + resultSet.getString("password") + " " + resultSet.getString("name");
            }

            PreparedStatement preparedStatement1 = connection.prepareStatement(sql_adress);
            preparedStatement1.setInt(1, user_id);
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while (resultSet1.next()) {
                result = result + " " +  resultSet1.getString("town") + " " + resultSet1.getString("street") + " " + resultSet1.getString("house");
            }

            PreparedStatement preparedStatement2 = connection.prepareStatement(sql_music);
            preparedStatement2.setInt(1, user_id);
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            String result_music = "";

            while (resultSet2.next()) {
                result_music = result_music + " " + resultSet2.getString("type");
            }

            System.out.println(result + " " + result_music);




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
