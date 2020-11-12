package Models;

import java.util.HashMap;

public class Student extends User {

    private Major major;
    private HashMap<Club, String> clubs; //Map of Clubs with Club - Club Role Match

    public Student(int id, String first_name, String last_name, String email, Role role, Major major, HashMap<Club, String> studentClubs) {
        super(id, first_name, last_name, email, role);
        this.major = major;
        this.clubs = studentClubs;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public HashMap<Club, String> getClubs() {
        return clubs;
    }

    public void setClubs(HashMap<Club, String> clubs) {
        this.clubs = clubs;
    }
}
