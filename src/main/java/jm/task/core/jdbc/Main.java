package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Gordei", "Pushkin", (byte)30);
        userService.saveUser("NeGordei", "NePushkin", (byte)15);
        userService.saveUser("Fedor", "Kurakin", (byte)21);
        userService.saveUser("Alexandr", "Pushkin", (byte)46);

        System.out.println(userService.getAllUsers());

        userService.removeUserById(3);
        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());

        userService.dropUsersTable();
    }
}
