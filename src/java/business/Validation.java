package business;

import data.MusicDB;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Validation {
    private static final Logger LOG = Logger.getLogger(MusicDB.class.getName());

    public static String isValidUsername (String username, String label, HashMap<String, String> errors) {
        // username must be between 4-30 characters inclusive and must be unique for all users
        
        boolean result = false;
        try {
            result = MusicDB.validateUsername(username);
        } catch (SQLException e) {
            errors.put(label, "SQL error while checking username.");
        }
        
        if ("".equals(username) || username.length() < 4 || username.length() > 30) {
            errors.put(label, Validation.capFirstLetter(label) + " must be between 4-30 characters long.");
            username = null;
        } else if (result) { // this is where it will check to see if the username is already in database.
            errors.put(label, Validation.capFirstLetter(label) + " is already taken. Please select another username.");
            username = null;
        }
        return username;
    }
    
    public static String isValidPassword (String password, String label, HashMap<String, String> errors) {
        // password must be more than 10 characters long
        
        if (password.length() <= 10) {
            errors.put(label, Validation.capFirstLetter(label) + " must be longer than 10 characters.");
            password = null;
        }
        
        return password;
    }
    
    private static String capFirstLetter(String input) {
        if (input == null) {
            return null;
        } else if (input.length() == 1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, 1).toUpperCase() + input.substring(1);
        }
    }
}
