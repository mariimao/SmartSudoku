package app;

import data_access.UserDAO;
import entity.CommonUser;
import entity.EasyBoard;
import entity.HardBoard;
import entity.CommonUserFactory;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        EasyBoard easyTester = new EasyBoard();
        System.out.println(easyTester);

        HardBoard hardTester = new HardBoard();
        System.out.println(hardTester);


        // testing userDAO
        Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);

        UserDAO userDataAccessObject;
        try {
            userDataAccessObject = new UserDAO("mongodb+srv://smartsudoku:smartsudoku@cluster0.hbx3f3f.mongodb.net/\n\n",
                    "smartsudoku", "user", new CommonUserFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //userDataAccessObject.deleteAll(); //for testing
        Map<LocalTime, Integer> sampleScores = new HashMap<>();
        sampleScores.put(LocalTime.now(), 4);
        sampleScores.put(LocalTime.of(12, 30, 1), 3);
        CommonUser user1 = new CommonUser("user1", "pass2", sampleScores);
        userDataAccessObject.addUser(user1);
        sampleScores.put(LocalTime.of(12, 31, 1), 4);
        CommonUser user2 = new CommonUser("user2", "pass", sampleScores);
        userDataAccessObject.addUser(user2);
        System.out.println(userDataAccessObject.toString());
        System.out.println(user1.getName());
        System.out.println(user1.getPassword());
        System.out.println(user1.getScores());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getScores());

//        userDataAccessObject.delete("user1");
//        System.out.println(userDataAccessObject.toString());


    }
}
