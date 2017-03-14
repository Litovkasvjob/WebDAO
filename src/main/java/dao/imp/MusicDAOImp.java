package dao.imp;

import model.MusicType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SergLion on 11.03.2017.
 */
public class MusicDAOImp extends CrudDAO<MusicType> {

    private static final String INSERT_MUSIC = "INSERT INTO music_type (type) VALUES (?);";
    private static final String UPDATE_MUSIC = "UPDATE music_type SET type = ? WHERE music_id = ?;";


    private static MusicDAOImp musicDAOImp;

    private MusicDAOImp(Class<MusicType> type) {
        super(type);
    }

    public static MusicDAOImp getInstance() {
        if (musicDAOImp == null) {
            musicDAOImp = new MusicDAOImp(MusicType.class);
        }
        return musicDAOImp;
    }

    @Override
    protected List<MusicType> readAll(ResultSet resultSet) throws SQLException {

        List<MusicType> result = new ArrayList<>();
        MusicType musicType = null;
        while (resultSet.next()) {
            musicType = new MusicType();
            musicType.setId(resultSet.getInt("music_id"));
            musicType.setMusicType(resultSet.getString("type"));
            result.add(musicType);
        }
        return result;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, MusicType entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MUSIC, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getMusicType());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, MusicType entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MUSIC);
        preparedStatement.setString(1, entity.getMusicType());
        preparedStatement.setInt(2, entity.getId());
        return preparedStatement;
    }
}
