package Models;

import java.util.HashMap;

public class Student extends User {

    private Major major;
    private String group_name;
    private int year;
    private HashMap<Club, String> clubs; //Map of Clubs with Club - Club Role Match

    public Student(int id, String first_name, String last_name, String email, Role role, Major major, HashMap<Club, String> studentClubs) {
        super(id, first_name, last_name, email, role);
        this.major = major;
        this.clubs = studentClubs;
    }

    public Student() {

    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
