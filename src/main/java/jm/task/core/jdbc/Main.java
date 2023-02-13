package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Michael", "Romanov", (byte) 25);
        userService.saveUser("Anna", "Petrova", (byte) 32);
        userService.saveUser("Olga", "Yuryeva", (byte) 20);
        userService.saveUser("Alexander", "Voronov", (byte) 33);
        System.out.println(userService.getAllUsers());
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
