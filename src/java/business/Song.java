package business;

public class Song {
    private int songID;
    private String title, artist;

    public Song() {
    }

    public Song(int songID, String title, String artist) {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
