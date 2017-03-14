package dao.imp;

import model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SergLion on 11.03.2017.
 */
public class RoleDAOImp extends CrudDAO<Role> {

    private static final String INSERT_ROLE = "INSERT INTO role (name) VALUES (?);";
    private static final String UPDATE_ROLE = "UPDATE role SET name = ? WHERE role_id = ?;";

    private static RoleDAOImp roleDAOImp;

    private RoleDAOImp(Class<Role> type) {
        super(type);
    }

    public static synchronized RoleDAOImp getInstance() {
        if (roleDAOImp == null) {
            roleDAOImp = new RoleDAOImp(Role.class);
        }
        return roleDAOImp;
    }


    @Override
    protected List<Role> readAll(ResultSet resultSet) throws SQLException {

        List<Role> result = new ArrayList<>();
        Role role = null;
        while (resultSet.next()) {
            role = new Role();
            role.setId(resultSet.getInt("role_id"));
            role.setRole(resultSet.getString("name"));
            result.add(role);
        }
        return result;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Role entity) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getRole());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Role entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE);
        preparedStatement.setString(1, entity.getRole());
        preparedStatement.setInt(2, entity.getId());
        return preparedStatement;
    }
}
