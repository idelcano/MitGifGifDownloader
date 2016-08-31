/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import apicalls.Feelings.Feel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import language.Language;
import language.Translation;
import pojos.GifMetadata;

/**
 *
 * @author ina
 */
public class SQLiteJDBC {

    public final static String dbName = "MitGit.db";
    public static String dbPath = "";

    public static void createDB(String DBpath) throws SQLException, ClassNotFoundException {
        dbPath = DBpath + dbName;
        Connection c = connectDB();
        Statement stmt = null;
        try {
            System.out.println("Opened database successfully");

            String sql[] = {"CREATE TABLE IF NOT EXISTS  `feelings` ("
                + "	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	`feel`	TEXT UNIQUE"
                + ")",
                "CREATE TABLE IF NOT EXISTS  `gifs` ("
                + "	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	`content`	TEXT UNIQUE,"
                + "	`cID`	TEXT,"
                + "	`embebedLink`	TEXT,"
                + "	`still_image`	TEXT,"
                + "	`added_by_admin`	BLOB,"
                + "	`index_mit`	NUMERIC,"
                + "	`content_type`	TEXT"
                + ")", "CREATE TABLE IF NOT EXISTS  `giffeelings` ("
                + "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "`id_gif`	INTEGER,"
                + "`id_feeling`	INTEGER,"
                + "`rank`	INTEGER,"
                + "`distance`	REAL,"
                + "`sigma`	REAL,"
                + "`mu`	REAL)",
                "CREATE TABLE IF NOT EXISTS  `giftags`("
                + "	`id_gif`	INTEGER,"
                + "	`id_tag`	INTEGER,"
                + " PRIMARY KEY(id_gif,id_tag))",
                "CREATE TABLE IF NOT EXISTS `tags`("
                + "	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	`tag`	TEXT UNIQUE"
                + ")"
            };
            for (int i = 0; i < sql.length; i++) {
                stmt = c.createStatement();
                stmt.executeUpdate(sql[i]);
                stmt.close();
            }
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection c = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        return c;
    }

    public static void insertFeel(Connection c, Feel feel) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = c.prepareStatement("insert into feelings (feel) values(?)");
        stmt.setString(1, Translation.getStringFromFeelings(Language.ENG, feel));
        try {
            stmt.executeUpdate();
        } catch (SQLException e) { 
            if (!e.getMessage().contains("CONSTRAINT")) {
                e.printStackTrace();
            }
        }
    }

    static void convertGifMetadata(Connection c, GifMetadata gif) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = c.prepareStatement("insert into gifs (content,cID, embebedLink,still_image,added_by_admin,index_mit,content_type) values(?,?,?,?,?,?,?)");
        stmt.setString(1, gif.getContent());
        stmt.setString(2, gif.getcID());
        stmt.setString(3, gif.getContent_data().getEmbedLink());
        stmt.setString(4, gif.getContent_data().getStill_image());
        stmt.setBoolean(5, gif.getContent_data().isAdded_with_admin());
        stmt.setInt(6, gif.getIndex());
        stmt.setString(7, gif.getContent_type());
        try {
            stmt.executeUpdate();
        } catch (SQLException e) { 
            if (!e.getMessage().contains("CONSTRAINT")) {
                e.printStackTrace();
            }
        }
        int gifId = getIdFromTableAndFieldAndValue(c, "gifs", "content", gif.getContent());
        for (String tag : gif.getContent_data().getTags()) {
            stmt = c.prepareStatement("insert into tags (tag) values (?)");
            stmt.setString(1, tag);
            try {
                stmt.executeUpdate();
            } catch (SQLException e) { 
                if (!e.getMessage().contains("CONSTRAINT")) {
                    e.printStackTrace();
                } 
            }
            int tagId = getIdFromTableAndFieldAndValue(c, "tags", "tag", tag);

            stmt = c.prepareStatement("insert into giftags (id_gif,id_tag) values(?,?)");
            stmt.setInt(1, gifId);
            stmt.setInt(2, tagId);
            try {
                stmt.executeUpdate();
            } catch (SQLException e) { 
                if (!e.getMessage().contains("CONSTRAINT")) {
                    e.printStackTrace();
                }
            }
        }

        int feelId = getIdFromTableAndFieldAndValue(c, "feelings", "feel", Translation.getStringFromFeelings(Language.ENG, gif.getFeel()));
        stmt = c.prepareStatement("insert into giffeelings (id_gif,id_feeling,rank,distance,mu,sigma) values(?,?,?,?,?,?)");
        stmt.setInt(1, gifId);
        stmt.setInt(2, feelId);
        stmt.setInt(3, gif.getRank());
        stmt.setDouble(4, gif.getParameters().getDistance());
        if (gif.getParameters().getMu() == null) {
            stmt.setNull(5, java.sql.Types.DOUBLE);
        } else {
            stmt.setDouble(5, gif.getParameters().getMu());
        }

        if (gif.getParameters().getSigma() == null) {
            stmt.setNull(6, java.sql.Types.DOUBLE);
        } else {
            stmt.setDouble(6, gif.getParameters().getSigma());
        }
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            
        }
    }

    public static int getIdFromTableAndFieldAndValue(Connection c, String table, String field, String value) {
        String sql="";
        try{ 
            sql = "SELECT id  FROM " + table + " where " + field + " like " + "'" + value.replaceAll("'","''") + "'";

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(sql);
            Logger.getLogger(SQLiteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
