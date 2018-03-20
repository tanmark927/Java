/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs.pkg323.java.project;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author marktan
 */
public class JDBCproj_v2 {
    static String USER;
    static String PASS;
    static String DBNAME;
    static final String displayGroupsFormat = "%-30s%-30s%-15s%-30s\n";
    static final String displayBooksFormat  = "%-30s%-40s%-30s%-15s%-15s\n";
    static final String displayPubFormat    = "%-40s%-45s%-20s%-30s\n";
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
    static Connection conn;
    static Statement stmt;
    
    /**
    * Takes the input string and outputs "N/A" if the string is empty or null.
    * @param input The string to be mapped.
    * @return  Either the input string or "N/A" as appropriate.
    */
    public static String dispNull (String input)
    {
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
    
    /**
     * Displays the menu for the user
     * @param in the Scanner object used for user input
     * @return the user-specified option
     */
    public static int askUser(Scanner in)
    {
        System.out.println("Menu");
        System.out.println("1. List all writing groups");
        System.out.println("2. List all data for a writing group");
        System.out.println("3. List all publishers");
        System.out.println("4. List all data for a publisher");
        System.out.println("5. List all books");
        System.out.println("6. List all data for a book");
        System.out.println("7. Insert a new book");
        System.out.println("8. Delete book");
        System.out.println("9. Insert publisher and update books from old publisher");
        System.out.println("0. Exit");
        System.out.print("User Input : ");
        return in.nextInt();
    }
    
    /**
     * Displays all writing groups
     */
    public static void listAllWritingGroups()
    {
        try
        {
            //stmt = conn.createStatement();
            ResultSet rSet = stmt.executeQuery("select * from writinggroups order by groupname");
            System.out.printf(displayGroupsFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
            while (rSet.next())
            {
                String groupName = rSet.getString("groupname");
                String headWriter = rSet.getString("headwriter");
                int yrForm = rSet.getInt("yearformed");
                String subj = rSet.getString("subject");
                System.out.printf(displayGroupsFormat, dispNull(groupName), dispNull(headWriter), dispNull(Integer.toString(yrForm)), dispNull(subj));
            }
            System.out.println();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Displays one writing group based on user-requested group name
     * @param in Scanner object that takes user input
     */
    public static void listOneWritingGroup()
    {
        System.out.println("Type in group name: ");
        String gName = CheckInput.getString();
        try
        {
            PreparedStatement pstmt = null;            
            String str = "select * from writinggroups where groupname = ?";
            pstmt = conn.prepareStatement(str);
            pstmt.setString(1,gName);
            ResultSet rSet = pstmt.executeQuery();
            System.out.printf(displayGroupsFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
            while (rSet.next())
            {
                String groupName = rSet.getString("groupname");
                String headWriter = rSet.getString("headwriter");
                int yrForm = rSet.getInt("yearformed");
                String subj = rSet.getString("subject");
                System.out.printf(displayGroupsFormat, dispNull(groupName), dispNull(headWriter), dispNull(Integer.toString(yrForm)), dispNull(subj));
            }
            System.out.println();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Displays all publishers
     */
    public static void listAllPublishers()
    {
        try
        {
            //stmt = conn.createStatement();
            ResultSet rSet = stmt.executeQuery("select * from publishers order by publishername");
            System.out.printf(displayPubFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
            while (rSet.next())
            {
                String pubName = rSet.getString("publishername");
                String pubAdd = rSet.getString("publisheraddress");
                String pubPhone = rSet.getString("publisherphone");
                String pubEmail = rSet.getString("publisheremail");
                System.out.printf(displayPubFormat, dispNull(pubName), dispNull(pubAdd), dispNull(pubPhone), dispNull(pubEmail));
            }
            System.out.println();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Displays one writing group based on user-requested publisher name
     */
    public static void listOnePublisher()
    {
        System.out.println("Type in publisher name: ");
        String pubName = CheckInput.getString();
        try
        {
            PreparedStatement pstmt = null;
            String str = "select * from publishers where publishername = ?";
            pstmt = conn.prepareStatement(str);
            pstmt.setString(1,pubName);
            ResultSet rSet = pstmt.executeQuery();
            System.out.printf(displayPubFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
            while (rSet.next())
            {
                String pName = rSet.getString("publishername");
                String pubAdd = rSet.getString("publisheraddress");
                String pubPhone = rSet.getString("publisherphone");
                String pubEmail = rSet.getString("publisheremail");
                System.out.printf(displayPubFormat, dispNull(pName), dispNull(pubAdd), dispNull(pubPhone), dispNull(pubEmail));
            }
            System.out.println();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Displays all books
     */
    public static void listAllBooks()
    {
        try
        {
            //stmt = conn.createStatement();
            ResultSet rSet = stmt.executeQuery("select * from books order by groupname, booktitle");
            System.out.printf(displayBooksFormat, "Group Name", "Book Title", "Publisher Name", "Year Published", "Number of Pages");
            while (rSet.next())
            {
                String groupName = rSet.getString("groupname");
                String bookTitle = rSet.getString("booktitle");
                String publisherName = rSet.getString("publishername");
                int yearPub = rSet.getInt("yearpubilshed");
                int numPage = rSet.getInt("numberpages");
                System.out.printf(displayBooksFormat, dispNull(groupName), dispNull(bookTitle), dispNull(publisherName), dispNull(Integer.toString(yearPub)),dispNull(Integer.toString(numPage)));
            }
            System.out.println();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Displays one book based on user-requested book title
     */
    public static void listOneBook()
    {
        System.out.println("Type in title: ");
        String title = CheckInput.getString();
        
        try
        {
            PreparedStatement pstmt = null;
            String str = "select * from books where booktitle = ?";
            pstmt = conn.prepareStatement(str);
            pstmt.setString(1,title);
            ResultSet rSet = pstmt.executeQuery();
            System.out.printf(displayBooksFormat, "Group Name", "Book Title", "Publisher Name", "Year Published", "Number of Pages");
            while (rSet.next())
            {
                String groupName = rSet.getString("groupname");
                String bookTitle = rSet.getString("booktitle");
                String publisherName = rSet.getString("publishername");
                String yearPub = rSet.getString("yearpubilshed");
                String numPage = rSet.getString("numberpages");
                System.out.printf(displayBooksFormat, dispNull(groupName), dispNull(bookTitle), dispNull(publisherName), dispNull(yearPub),dispNull(numPage));
            }
            System.out.println();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Inserts a book and its information into the database
     */
    public static void insertBook()
    {
        System.out.println("Type in group name: ");
        String groupName = CheckInput.getString();
        System.out.println("Type in title: ");
        String title = CheckInput.getString();
        System.out.println("Type in publisher name: ");
        String pubName = CheckInput.getString();
        System.out.println("Type in year published: ");
        int yrPub = CheckInput.checkInt();
        System.out.println("Type in number of pages: ");
        int pageNum = CheckInput.checkInt();
        try
        {
            PreparedStatement pstmt = null;
            String str = "insert into books (groupname, publishername, booktitle, yearpubilshed, numberpages) values (?,?,?,?,?)";
            pstmt = conn.prepareStatement(str);
            pstmt.setString(1,groupName);
            pstmt.setString(2,pubName);
            pstmt.setString(3,title);
            pstmt.setInt(4,yrPub);
            pstmt.setInt(5,pageNum);
            pstmt.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Removes a book from the database based on user-requested book title
     */
    public static void deleteBook()
    {
        System.out.println("Type in title to delete: ");
        String title = CheckInput.getString();
        try
        {
            PreparedStatement pstmt = null;
            String str = "delete from books where booktitle = ?";
            pstmt = conn.prepareStatement(str);
            pstmt.setString(1,title);
            pstmt.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Inserts a publisher and its information into the database
     * and updates books from the old publisher to the new publisher
     */
    public static void insertPub()
    {
        System.out.println("Type in publisher name: ");
        String pubName = CheckInput.getString();
        System.out.println("Type in publisher address: ");
        String pubAddress = CheckInput.getString();
        System.out.println("Type in publisher phone: ");
        String pubPhone = CheckInput.getString();
        System.out.println("Type in publisher email: ");
        String pubEmail = CheckInput.getString();
                    
        System.out.println("What's the updated book publisher name?");
        String updateStr = CheckInput.getString();
        System.out.println("What's the name of the books' publisher being updated?");
        String bookPub = CheckInput.getString();
        try
        {
            PreparedStatement stmtInsert = null;
            PreparedStatement stmtUpdate = null;
            
            String sqlInsert = "insert into publishers (publishername, publisheraddress, publisherphone, publisheremail) values (?,?,?,?)";
            stmtInsert = conn.prepareStatement(sqlInsert);
            stmtInsert.setString(1,pubName);
            stmtInsert.setString(2,pubAddress);
            stmtInsert.setString(3,pubPhone);
            stmtInsert.setString(4,pubEmail);
            stmtInsert.executeUpdate();
            
            String sqlUpdate = "UPDATE books SET publishername = ? where publishername = ?";
            stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, updateStr);
            stmtUpdate.setString(2, bookPub);
            stmtUpdate.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {    
        Scanner in = new Scanner(System.in);
        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        System.out.print("Database user name: ");
        USER = in.nextLine();
        System.out.print("Database password: ");
        PASS = in.nextLine();
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
        conn = null;
        stmt = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            int userInput = 10;
            
            do
            {
            userInput = askUser(in);
            switch (userInput)
            {
                case 1:
                    listAllWritingGroups();
                    break;
                case 2:
                    listOneWritingGroup();
                    break;
                case 3:
                    listAllPublishers();
                    break;
                case 4:
                    listOnePublisher();
                    break;
                case 5:
                    listAllBooks();
                    break;
                case 6:
                    listOneBook();
                    break;
                case 7:
                    insertBook();
                    break;
                case 8:
                    deleteBook();
                    break;
                case 9:
                    insertPub();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.print("Invalid Selection\n");    
            }
            }while(userInput != 0);
            stmt.close();
            conn.close();
        }
        catch (SQLNonTransientConnectionException ce) {
            ce = new SQLNonTransientConnectionException("\nThe database you are "
                    + "connecting to does not exist.\nPlease try again.");
            System.err.println(ce);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main
}
