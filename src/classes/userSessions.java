/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author garmin
 */
public class userSessions {
    
    public static int insertUserSession(int userPK){
        Connection connection = database.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        int thisSessionPK = 0;
        
        try{
            ps = connection.prepareStatement("INSERT INTO usersessions(user_fk) VALUES (?)");
            ps.setString(1,Integer.toString(userPK));
            ps.executeUpdate();
            ps = connection.prepareStatement("SELECT MAX(usersession_pk) sessionPK FROM usersessions WHERE user_fk = ?");
            ps.setString(1, Integer.toString(userPK));
            rs = ps.executeQuery();
            rs.next();
            thisSessionPK = rs.getInt("sessionPK");
        }
        catch(SQLException ex){
            Logger.getLogger(userSessions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thisSessionPK;
    }
    
    public static boolean isSessionValid(int thisSessionPK){
        Connection connection = database.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        int timeDiff = 0;
        boolean valid = false;
        
        try{
            ps = connection.prepareStatement("SELECT TIMESTAMPDIFF(minute, datelastactive, sysdate()) diff FROM usersessions WHERE usersession_pk = ?");
            ps.setString(1, Integer.toString(thisSessionPK));
            rs = ps.executeQuery();
            rs.next();
            timeDiff = rs.getInt("diff");
            if (timeDiff > 0){
                valid = false;
                ps = connection.prepareStatement("UPDATE posplex.usersessions SET activeflag = false WHERE usersession_pk = ?");
                ps.setString(1, Integer.toString(thisSessionPK));
                ps.executeUpdate();
            }
            else{
                valid = true;
                ps = connection.prepareStatement("UPDATE posplex.usersessions SET activeflag = true, datelastactive = sysdate() WHERE usersession_pk = ?");
                ps.setString(1, Integer.toString(thisSessionPK));
                ps.executeUpdate();
            }
        }
        catch(SQLException ex){
        Logger.getLogger(userSessions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }
}
