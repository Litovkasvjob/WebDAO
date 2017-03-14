import dao.DaoFactory;
import dao.imp.UserDAOImp;
import model.Role;
import model.User;

/**
 * Created by SergLion on 12.03.2017.
 */
public class Main {
    public static void main(String[] args) {




        System.out.println(DaoFactory.getInstance().getUserDao().getAll());
        UserDAOImp userDAOImp = (UserDAOImp) DaoFactory.getInstance().getUserDao();

        userDAOImp.readUserInfo(3);
    }
}
