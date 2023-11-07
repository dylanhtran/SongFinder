package business;

public class GenreSong {
    private int genreID, songID;

    public GenreSong() {
    }

    public GenreSong(int genreID, int songID) {
        this.genreID = genreID;
        this.songID = songID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }
}
