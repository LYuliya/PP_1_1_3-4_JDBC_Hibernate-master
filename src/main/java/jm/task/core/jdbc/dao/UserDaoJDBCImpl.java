package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao {
    public UserDaoJDBCImpl() {
    }

   public void createUsersTable() throws SQLException {
        try ( Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (Id BIGINT PRIMARY KEY AUTO_INCREMENT , name VARCHAR(20), lastName VARCHAR(20), age TINYINT)");
            System.out.println("Table has been created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() throws SQLException {
       try ( Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE if EXISTS users;");
            System.out.println("Table has been deleted!");
       } catch (SQLException e) {
            e.printStackTrace();

       }



    }
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try ( Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES ( ?, ?, ?);")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User with name - " +name+ " added to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }


   }

    public void removeUserById(long id) throws SQLException {
       try ( Connection connection = Util.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("delete from users  where id=?;")) {
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
            System.out.println("User deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> listUsers = new ArrayList<>();

        try ( Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from users;")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }
    public void cleanUsersTable() throws SQLException {
       try (Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement("truncate table users;")) {
            statement.executeUpdate();
            System.out.println("Data removed from table");
       } catch (SQLException e) {
            e.printStackTrace();
       }
   }
}
