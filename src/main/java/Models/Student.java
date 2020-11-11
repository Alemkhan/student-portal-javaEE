package Models;

import java.util.HashMap;

public class Student extends User {

    private Major major;
    private HashMap<String, String> clubs; //Map of Clubs with Club - Club Role Match

    public Student(String first_name, String last_name, String email, int role_id, Major major, HashMap<String, String> clubs) {
        super(first_name, last_name, email, role_id);
        this.major = major;
        this.clubs = clubs;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public HashMap<String, String> getClubs() {
        return clubs;
    }

    public void setClubs(HashMap<String, String> clubs) {
        this.clubs = clubs;
    }
}
