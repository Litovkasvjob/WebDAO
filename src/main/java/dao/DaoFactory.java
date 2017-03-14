package dao;

import dao.api.Dao;
import dao.imp.AdressDAOImp;
import dao.imp.MusicDAOImp;
import dao.imp.RoleDAOImp;
import dao.imp.UserDAOImp;
import holder.PropertyHolder;
import model.Adress;
import model.MusicType;
import model.Role;
import model.User;

/**
 * Created by SergLion on 11.03.2017.
 */
public class DaoFactory {

    private static DaoFactory daoFactory = null;

    private Dao<Integer, User> userDao;
    private Dao<Integer, Role> roleDao;
    private Dao<Integer, MusicType> musicTypeDao;
    private Dao<Integer, Adress> adressDao;

    private DaoFactory() {
        loadDAOs();
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    private void loadDAOs() {
        if (PropertyHolder.getInstanse().isInMemoryDB()) {

        } else {
            userDao = UserDAOImp.getInstanse();
            roleDao = RoleDAOImp.getInstance();
            musicTypeDao = MusicDAOImp.getInstance();
            adressDao = AdressDAOImp.getInstance();
        }
    }

    public Dao<Integer, User> getUserDao() {
        return userDao;
    }

    public void setUserDao(Dao<Integer, User> userDao) {
        this.userDao = userDao;
    }

    public Dao<Integer, Role> getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(Dao<Integer, Role> roleDao) {
        this.roleDao = roleDao;
    }

    public Dao<Integer, MusicType> getMusicTypeDao() {
        return musicTypeDao;
    }

    public void setMusicTypeDao(Dao<Integer, MusicType> musicTypeDao) {
        this.musicTypeDao = musicTypeDao;
    }

    public Dao<Integer, Adress> getAdressDao() {
        return adressDao;
    }

    public void setAdressDao(Dao<Integer, Adress> adressDao) {
        this.adressDao = adressDao;
    }
}
