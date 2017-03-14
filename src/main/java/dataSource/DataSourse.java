package dataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import holder.PropertyHolder;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by SergLion on 06.03.2017.
 */
public class DataSourse {

    private static ComboPooledDataSource poolConnections;
    private static DataSourse dataSourse;

    public DataSourse() {
        initPollConnections();
    }

    public static synchronized DataSourse getInstance() {
        if (dataSourse == null) {
            dataSourse = new DataSourse();
        }
        return dataSourse;
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = poolConnections.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void initPollConnections() {
        poolConnections = new ComboPooledDataSource();

        PropertyHolder propertyHolder = PropertyHolder.getInstanse();

        try {
            poolConnections.setJdbcUrl(propertyHolder.getJdbcUrl());
            poolConnections.setUser(propertyHolder.getDbUserLogin());
            poolConnections.setPassword(propertyHolder.getDbUserPassword());
            poolConnections.setDriverClass(propertyHolder.getDbDriver());

            poolConnections.setMinPoolSize(5);
            poolConnections.setAcquireIncrement(5);
            poolConnections.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }
}
