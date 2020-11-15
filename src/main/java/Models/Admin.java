package Models;

import java.util.HashMap;

public class Admin extends User{

    private HashMap<Club, String> allClubs;

    public Admin(int id, String first_name, String last_name, String email, Role role) {
        super(id,first_name, last_name, email, role);
    }

    public HashMap<Club, String> getAllClubs() {
        return allClubs;
    }

    public void setAllClubs(HashMap<Club, String> allClubs) {
        this.allClubs = allClubs;
    }
}
