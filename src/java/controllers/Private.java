package controllers;

import business.Genre;
import business.Song;
import business.Playlist;
import business.User;
import business.Validation;
import data.MusicDB;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class Private extends HttpServlet {

    String url = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("Public");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }

        switch (action) {
            case "goToHomePage": {
                if (loggedInUser == null) {
                    url = "/index.jsp";
                } else if (loggedInUser.getUserType().equalsIgnoreCase("admin")) {
                    url = "/adminHome.jsp";
                } else if (loggedInUser.getUserType().equalsIgnoreCase("user")) {
                    url = "/userHome.jsp";
                }
                break;
            }
            case "logout": {
                logout(request);

                break;
            }
            case "admin": {
                url = "/adminHome.jsp";
                break;
            }
            case "user": {
                url = "/userHome.jsp";
                break;
            }
            case "allUserPage": {
                url = "/allUsers.jsp";
                LinkedHashMap<String, User> allUsers = new LinkedHashMap();

                try {
                    allUsers = MusicDB.selectAllUsers();
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }

                request.setAttribute("allUsers", allUsers);
                break;
            }
            case "deleteUser": {
                int userID = 0;
                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }

                try {
                    MusicDB.deleteUser(userID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                url = "/Private?action=allUserPage";
                break;
            }
            case "updateUserPage": {
                url = "/updateUser.jsp";
                int userID = 0;
                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("userID", userID);
                break;
            }
            case "updateUserType": {
                url = "/Private?action=allUserPage";
                int userID = 0;
                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                String usertype = request.getParameter("usertype");
                try {
                    MusicDB.updateUserType(userID, usertype);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "addGenrePage": {
                url = "/addGenrePage.jsp";
                break;
            }
            case "addGenre": {
                String genre = request.getParameter("genre");
                url = "/addGenrePage.jsp";

                try {
                    if (!MusicDB.checkGenreName(genre)) {
                        MusicDB.insertGenre(genre);
                    }
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            }
            case "deleteGenrePage": {
                url = "/deleteGenres.jsp";
                ArrayList<Genre> genres = new ArrayList();
                try {
                    genres = MusicDB.selectAllGenres();
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("allGenres", genres);

                break;
            }
            case "deleteGenre": {
                int genreID = 0;
                try {
                    genreID = Integer.parseInt(request.getParameter("genreID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    MusicDB.deleteGenre(genreID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                url = "/Private?action=deleteGenrePage";
                break;
            }
            case "addSongPage": {
                url = "/addSong.jsp";
                ArrayList<Genre> genres = new ArrayList();
                try {
                    genres = MusicDB.selectAllGenres();
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("allGenres", genres);

                break;
            }
            case "addSong": {
                url = "/Private?action=addSongPage";
                String title = request.getParameter("song");
                String artist = request.getParameter("artist");
                try {
                    MusicDB.insertSong(title, artist);
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }

                ArrayList<String> genres = new ArrayList();
                String genreNames[] = request.getParameterValues("genre");
                genres.addAll(Arrays.asList(genreNames));
                int songID = 0;
                try {
                    songID = MusicDB.getSongID(title, artist);
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }

                for (String g : genres) {
                    try {
                        int genreID = MusicDB.getGenreIDForName(g);
                        MusicDB.insertSongGenre(songID, genreID);
                    } catch (SQLException e) {
                        Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                break;
            }
            case "allSongPage": {
                url = "/allSongs.jsp";
                ArrayList<Song> songs = new ArrayList();
                try {
                    songs = MusicDB.selectAllSongs();
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("allSongs", songs);
                break;
            }
            case "updateSongPage": {
                url = "/updateSong.jsp";
                String title = request.getParameter("title");
                String artist = request.getParameter("artist");
                int songID = Integer.parseInt(request.getParameter("songID"));
                request.setAttribute("songID", songID);
                request.setAttribute("title", title);
                request.setAttribute("artist", artist);
                break;
            }
            case "updateSong": {
                url = "/Private?action=allSongPage";
                int songID = Integer.parseInt(request.getParameter("songID"));
                String title = request.getParameter("title");
                String artist = request.getParameter("artist");
                try {
                    MusicDB.updateSong(songID, title, artist);
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            }
            case "deleteSong": {
                url = "/Private?action=allSongPage";
                int songID = 0;
                try {
                    songID = Integer.parseInt(request.getParameter("songID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    MusicDB.deleteSong(songID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "playlistCheck": {
                try {
                    if (MusicDB.userHasPlaylist(loggedInUser.getUserID())) {
                        url = "/Private?action=discoverPage";
                    } else {
                        url = "/Private?action=playlistPage";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "playlistPage": {
                url = "/myPlaylists.jsp";
                ArrayList<Playlist> playlists = new ArrayList();
                try {
                    playlists = MusicDB.selectAllPlaylistsForUser(loggedInUser.getUserID());
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("allPlaylists", playlists);
                break;
            }
            case "addPlaylistPage": {
                url = "/addPlaylist.jsp";
                break;
            }
            case "addPlaylist": {
                url = "/Private?action=playlistPage";
                int userID = loggedInUser.getUserID();
                String playlistName = request.getParameter("playlist");
                try {
                    MusicDB.insertPlaylist(playlistName, userID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "deletePlaylist": {
                url = "/Private?action=playlistPage";
                int playlistID = 0;
                try {
                    playlistID = Integer.parseInt(request.getParameter("playlistID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    MusicDB.deletePlaylist(playlistID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "viewPlaylist": {
                url = "/viewPlaylist.jsp";
                int playlistID = 0;
                String playlistName;
                playlistName = request.getParameter("playlistName");
                try {
                    playlistID = Integer.parseInt(request.getParameter("playlistID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                ArrayList<Song> playlist = new ArrayList();
                try {
                    playlist = MusicDB.selectPlaylistSongs(playlistID);
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("playlist", playlist);
                request.setAttribute("playlistID", playlistID);
                request.setAttribute("playlistName", playlistName);
                break;
            }
            case "removeSongFromPlaylist": {
                url = "/Private?action=viewPlaylist";
                int playlistID = 0;
                try {
                    playlistID = Integer.parseInt(request.getParameter("playlistID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                int songID = 0;
                try {
                    songID = Integer.parseInt(request.getParameter("songID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    MusicDB.deleteSongFromPlaylist(playlistID, songID);
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            }
            case "updatePlaylistPage": {
                url = "/updatePlaylist.jsp";
                int playlistID = 0;
                try {
                    playlistID = Integer.parseInt(request.getParameter("playlistID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("playlistID", playlistID);
                break;
            }
            case "updatePlaylist": {
                url = "/Private?action=playlistPage";
                int playlistID = 0;
                try {
                    playlistID = Integer.parseInt(request.getParameter("playlistID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                String playlistName = request.getParameter("playlistName");
                
                try {
                    MusicDB.updatePlaylistName(playlistID, playlistName);
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            }
            case "discoverPage": {
                url = "/discover.jsp";
                ArrayList<Genre> genres = new ArrayList();
                try {
                    genres = MusicDB.selectAllGenres();
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("allGenres", genres);
                break;
            }
            case "discoverSongPage": {
                url = "/discoverSong.jsp";
                int genreID = 0;
                try {
                    genreID = Integer.parseInt(request.getParameter("genreID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                
                try {
                    String genreName = MusicDB.getGenreNameForID(genreID);
                    Song song = MusicDB.getRandomSong(genreID);
                    request.setAttribute("song", song);
                    String title = song.getTitle();
                    String filePath = "Songs/" + title + " - " + song.getArtist() + ".mp3";
                    request.setAttribute("filepath", filePath);
                    request.setAttribute("genreName", genreName);
                    request.setAttribute("allPlaylists", MusicDB.selectAllPlaylistsForUser(loggedInUser.getUserID()));
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("genreID", genreID);
                break;
            }
            case "addSongToPlaylist": {
                url = "/discoverSong.jsp";
                int songID = Integer.parseInt(request.getParameter("songID"));
                int playlistID = Integer.parseInt(request.getParameter("playlistID"));
                try {
                    if(!MusicDB.songExistsInPlaylist(playlistID, songID)) {
                        MusicDB.insertPlaylistSong(playlistID, songID);
                    }
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                Song song = new Song(Integer.parseInt(request.getParameter("songID")), request.getParameter("title"), request.getParameter("artist"));
                int genreID = Integer.parseInt(request.getParameter("genreID"));
                try {
                    String genreName = MusicDB.getGenreNameForID(genreID);
                    request.setAttribute("song", song);
                    String title = song.getTitle();
                    String filePath = "Songs/" + title + " - " + song.getArtist() + ".mp3";
                    request.setAttribute("filepath", filePath);
                    request.setAttribute("genreName", genreName);
                    request.setAttribute("allPlaylists", MusicDB.selectAllPlaylistsForUser(loggedInUser.getUserID()));
                } catch (SQLException e) {
                    Logger.getLogger(MusicDB.class.getName()).log(Level.SEVERE, null, e);
                }
                request.setAttribute("genreID", genreID);
                break;
            }
            case "skipSong": {
                url = "/Private?action=discoverSongPage";
                break;
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        url = "/Public?action=gotoIndex";
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
