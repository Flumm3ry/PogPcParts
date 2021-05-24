package src;

import java.io.Serializable;

/**
 *
 * @author jerem
 */
public class User implements Serializable {
    
    private final String name;
    private final String phone;
    private final String address;
    private final String email;
    private final String password;
    private final String appGroup;
    private final boolean active; 

    public User(String name, String phone, String address, 
            String email, String password, String appGroup, boolean active) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.appGroup = appGroup;
        this.active = active;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getAppGroup() {
        return appGroup;
    }

    public boolean isActive() {
        return active;
    }

}
