package Models;

public class Club {

    private int club_id;
    private String club_name;
    private String description;
    private String avatar;
    private int owner_id;

    public Club() {
    }

    public Club(int club_id, String club_name, String description, String avatar, int owner_id) {
        this.club_id = club_id;
        this.club_name = club_name;
        this.description = description;
        this.avatar = avatar;
        this.owner_id = owner_id;
    }

    public Club(String club_name, String description, String avatar) {
        this.club_name = club_name;
        this.description = description;
        this.avatar = avatar;
    }

    public Club(int club_id) {
        this.club_id = club_id;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
