package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection conn = Util.getInstance().getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try(Statement stmt = conn.createStatement()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS `katappfirst`.`user` (
                      `id` BIGINT NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(45) NULL,
                      `lastname` VARCHAR(45) NULL,
                      `age` TINYINT NULL,
                      PRIMARY KEY (`id`));""";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement stmt = conn.createStatement()) {
            String sql = "DROP TABLE IF EXISTS `katappfirst`.`user`;";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement stmt = conn.prepareStatement("""
            INSERT `katappfirst`.`user`(name,lastname,age)
            VALUES (?, ?, ?)""")) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement stmt = conn.prepareStatement("""
            DELETE FROM `katappfirst`.`user`
            WHERE `id` = ?""")) {

            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try(PreparedStatement statement = conn.prepareStatement("""
            SELECT * FROM `katappfirst`.`user`""")) {
            List<User> users = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {
            String sql = """
                    DELETE FROM `katappfirst`.`user`""";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
