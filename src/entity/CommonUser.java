package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommonUser implements User{

    private final String name;
    private final String password;

    CommonUser(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName() { return name;}

    public String getPassword() { return password; }
}
