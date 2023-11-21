package lk.ijse.dto;


public class UserDto {



    private String user_id;
    private String user_name;
    private String sha1_hash;
    private String profilePictureId;

    public UserDto() {}

    public UserDto(String user_id, String user_name, String sha1_hash) {

        this.user_id = user_id;
        this.user_name = user_name;
        this.sha1_hash = sha1_hash;

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSha1_hash() {
        return sha1_hash;
    }

    public void setSha1_hash(String sha1_hash) {
        this.sha1_hash = sha1_hash;
    }

    public String getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId(String profilePictureId) {
        this.profilePictureId = profilePictureId;
    }



}
