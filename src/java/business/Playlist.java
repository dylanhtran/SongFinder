package business;

public class Playlist {
    private int playlistID, userID;
    private String playlistName;

    public Playlist() {
    }

    public Playlist(int playlistID, int userID, String playlistName) {
        this.playlistID = playlistID;
        this.userID = userID;
        this.playlistName = playlistName;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
