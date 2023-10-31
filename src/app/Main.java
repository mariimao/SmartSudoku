package app;

import data_access.UserDAO;
import entity.EasyBoard;
import entity.HardBoard;
import entity.CommonUserFactory;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        EasyBoard easyTester = new EasyBoard();
        System.out.println(easyTester);
        
        HardBoard hardTester = new HardBoard();
        System.out.println(hardTester);


        // testing userDAO
        UserDAO userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        userDataAccessObject.deleteAll(); //for testing
        userDataAccessObject.addUser("usertest1", "supersecurepassword");
        userDataAccessObject.addUser("usertest2", "notsosecurepassword");
        System.out.println(userDataAccessObject.toString());

        userDataAccessObject.delete("usertest1");
        System.out.println(userDataAccessObject.toString());


    }
}
