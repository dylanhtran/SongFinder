package data;

import business.Genre;
import business.Playlist;
import business.Song;
import business.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicDB {

    private static final Logger LOG = Logger.getLogger(MusicDB.class.getName());

    public static int insertUser(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO users (username, password, userType) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, (user.getUserType()));
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }
    

    public static int deleteUser(int userID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "DELETE "
                + "FROM users "
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete movie null pointer?", e);
                throw e;
            }
        }
    }
    
    public static int updateUserType(int userID, String userType) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE users "
                + "SET usertype = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userType);
            ps.setInt(2, userID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }

    public static boolean validateUsername(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT username FROM users "
                + "WHERE username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** validate username sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** validate username null pointer?", e);
                throw e;
            }
        }
    }

    public static User getUserInfo(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM users WHERE username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                int userid = rs.getInt("userID");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String userType = rs.getString("userType");
                user = new User(userid, userName, password, userType);
            }
            return user;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }

    public static int selectUserID(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT userID "
                + "FROM users "
                + "WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            int userID = 0;
            if (rs.next()) {
                userID = rs.getInt("userID");
            }
            return userID;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select userID", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select userID null pointer?", e);
            }
        }
    }

    public static String getPasswordForUsername(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT password "
                + "FROM users "
                + "WHERE username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            String password = "";
            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
            return password;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }

    public static LinkedHashMap<String, User> selectAllUsers() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM users";
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            LinkedHashMap<String, User> users = new LinkedHashMap();

            while (rs.next()) {
                int userID = rs.getInt("userID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String userType = rs.getString("userType");
                User user = new User(userID, username, password, userType);
                users.put(user.getUsername(), user);
            }
            return users;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static int insertGenre(String genre) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO genre (genreName) "
                + "VALUES (?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, genre);
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<Genre> selectAllGenres() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM genre";
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            ArrayList<Genre> genres = new ArrayList();

            while (rs.next()) {
                int genreID = rs.getInt("genreID");
                String genreName = rs.getString("genreName");
                Genre genre = new Genre(genreID, genreName);
                genres.add(genre);
            }
            return genres;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static int deleteGenre(int genreID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "DELETE "
                + "FROM genre "
                + "WHERE genreID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, genreID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete movie null pointer?", e);
                throw e;
            }
        }
    }

    public static Boolean checkGenreName(String genreName) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM genre "
                + "WHERE genreName = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, genreName);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static int insertSong(String title, String artist) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO song (title, artist) "
                + "VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, artist);
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }
    
    public static int updateSong(int songID, String title, String artist) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE song "
                + "SET title = ?, artist = ? "
                + "WHERE songID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, artist);
            ps.setInt(3, songID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }

    public static int insertSongGenre(int songID, int genreID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO genresong (songID, genreID) "
                + "VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, songID);
            ps.setInt(2, genreID);
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }

    public static int getGenreIDForName(String genreName) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM genre "
                + "WHERE genreName = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, genreName);
            rs = ps.executeQuery();
            int genreID = 0;
            if (rs.next()) {
                genreID = rs.getInt("genreID");
            }
            return genreID;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static int getSongID(String title, String artist) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT songID FROM song "
                + "WHERE title = ? AND artist = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, artist);
            rs = ps.executeQuery();
            int songID = 0;
            if (rs.next()) {
                songID = rs.getInt("songID");
            }
            return songID;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<Song> selectAllSongs() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM song";
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            ArrayList<Song> songs = new ArrayList();

            while (rs.next()) {
                int songID = rs.getInt("songID");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                Song song = new Song(songID, title, artist);
                songs.add(song);
            }
            return songs;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static int deleteSong(int songID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "DELETE "
                + "FROM song "
                + "WHERE songID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, songID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete movie null pointer?", e);
                throw e;
            }
        }
    }

    public static Boolean userHasPlaylist(int userID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM playlist "
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static int insertPlaylist(String playlistName, int userID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO playlist (playlistName, userID) "
                + "VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, playlistName);
            ps.setInt(2, userID);
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }
    
    public static void updatePlaylistName(int playlistID, String playlistName) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE playlist "
                + "SET playlistName = ? "
                + "WHERE playlistID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, playlistName);
            ps.setInt(2, playlistID);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<Playlist> selectAllPlaylistsForUser(int userID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM playlist "
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            ArrayList<Playlist> playlists = new ArrayList();

            while (rs.next()) {
                int playlistID = rs.getInt("playlistID");
                String playlistName = rs.getString("playlistName");
                Playlist playlist = new Playlist(playlistID, userID, playlistName);
                playlists.add(playlist);
            }
            return playlists;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static int deletePlaylist(int playlistID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "DELETE "
                + "FROM playlist "
                + "WHERE playlistID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, playlistID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete movie null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<Song> selectPlaylistSongs(int playlistID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT song.songID, title, artist FROM playlistSong "
                + "INNER JOIN song on playlistsong.songID = song.songID "
                + "WHERE playlistID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, playlistID);
            rs = ps.executeQuery();
            ArrayList<Song> playlist = new ArrayList();

            while (rs.next()) {
                int songID = rs.getInt("songID");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                Song song = new Song(songID, title, artist);
                playlist.add(song);
            }
            return playlist;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static int deleteSongFromPlaylist(int playlistID, int songID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "DELETE "
                + "FROM playlistsong "
                + "WHERE playlistID = ? AND songID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, playlistID);
            ps.setInt(2, songID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete movie null pointer?", e);
                throw e;
            }
        }
    }

    public static Song getRandomSong(int genreID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT song.songID, title, artist FROM genresong "
                + "INNER JOIN song on genresong.songID = song.songID "
                + "WHERE genreID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, genreID);
            rs = ps.executeQuery();
            ArrayList<Song> songs = new ArrayList();

            while (rs.next()) {
                int songID = rs.getInt("songID");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                Song song = new Song(songID, title, artist);
                songs.add(song);
            }
            int max = songs.size() - 1;
            int min = 0;
            int range = max - min + 1;
            int rand = (int) (Math.random() * range) + min;
            Song song = songs.get(rand);
            return song;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static String getGenreNameForID(int genreID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM genre "
                + "WHERE genreID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, genreID);
            rs = ps.executeQuery();
            String genreName = "";
            if (rs.next()) {
                genreName = rs.getString("genreName");
            }
            return genreName;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }
    
    public static int insertPlaylistSong(int playlistID, int songID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO playlistsong (playlistID, songID) "
                + "VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, playlistID);
            ps.setInt(2, songID);
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }
    
    public static Boolean songExistsInPlaylist(int playlistID, int songID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM playlistsong "
                + "WHERE playlistID = ? AND songID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, playlistID);
            ps.setInt(2, songID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }
    
    
}
