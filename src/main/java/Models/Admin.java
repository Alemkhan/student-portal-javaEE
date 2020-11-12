package Models;

public class Admin extends User{

    public Admin(int id, String first_name, String last_name, String email, Role role) {
        super(id,first_name, last_name, email, role);
    }

}
