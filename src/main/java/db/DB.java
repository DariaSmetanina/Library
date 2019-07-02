package db;

import javafx.util.Pair;

import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import model.*;


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DB {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://127.0.0.1:3306/library19?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String user = "root";
    private static final String password = "@WSX2wsx";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public DB(){

        try {

            // opening database connection to MySQL server
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            //  getting Statement object to execute query
            stmt = con.createStatement();
            System.out.print("correct ");
        } catch (Exception e) {
            System.out.print("error ");
            System.out.print(e.toString());
        }
    }

    public Boolean login(String log, String pas){
        String query ="SELECT * FROM user WHERE (`Login` = '"+log+"');";
        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);
            if(rs.next()){
                String teorpas = rs.getString(6);
                rs.close();

                if (teorpas.equals(pas)) {

                    return TRUE;
                }
            }
        }
        catch (Exception e){
            System.out.print(e+"\n");
        }
        return FALSE;

    }

    public int addBook(String autor, String title,String genre){
        String place="";
        String place2="";

        String query = "select Place from book order by idBook";

        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                place2 = rs.getString(1);
            }
            int zal=0;
            int shkaf=0;
            int polka=0;
            int mesto=0;
            String test="";
            int i=0;
            while(place2.charAt(i)!=';'){
                test=test+place2.charAt(i);
                i++;
            }
            zal=Integer.parseInt(test);
            test="";
            i++;
            while(place2.charAt(i)!=';'){
                test=test+place2.charAt(i);
                i++;
            }
            shkaf=Integer.parseInt(test);
            test="";
            i++;
            while(place2.charAt(i)!=';'){
                test=test+place2.charAt(i);
                i++;
            }
            polka=Integer.parseInt(test);
            test="";
            i++;
            while(i<place2.length()){
                test=test+place2.charAt(i);
                i++;
            }
            mesto=Integer.parseInt(test)+1;

            if(mesto>30){
                mesto=1;
                polka++;
                if(polka>10){
                    polka=1;
                    shkaf++;
                    if(shkaf>12){
                        shkaf=1;
                        zal++;
                        if(zal>9){
                            return -1; //нет свободного места
                        }
                    }
                }

            }

            place=zal+";"+shkaf+";"+polka+";"+mesto;

            addBook2(autor,title,genre, place);

        }
        catch (Exception e) {
            System.out.print("error place");
            System.out.print(e.toString());
        }
        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }


        return 0;
    }

    private int addBook2(String autor, String title,String genre, String place){
        String query = "select * from book";
        try {
            // executing SELECT query
            int count=0;
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                count = rs.getInt(1);
            }

            query = "INSERT INTO book (idBook, Autor, Title, Genre, Place, Taken)" +
                    "VALUES(" + (count+1) +",'"+autor+"','"+title+"','"+genre+"','" + place+"',"+"NULL"+ ");";

            //System.out.print(query);
            stmt.executeUpdate(query);
        }
        catch (Exception e) {
            System.out.print(e.toString());
            System.out.print("error add");
        }
        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        return 0;
    }

    public void delBook(int id){
        String query = "DELETE FROM book WHERE (`idBook` = '"+id+"');";

        try {
            // executing SELECT query
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.print(e);
        }

    }

    public int takeBook(int idBook, int idUser){
        String query ="UPDATE book SET `Taken` = '"+idUser+"' WHERE (`idBook` = '"+idBook+"');";
        try {
            // executing SELECT query
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.print(e);
        }
        return 0;
    }

    public int returnBook(int idBook){
        String query ="UPDATE book SET `Taken` = NULL WHERE (`idBook` = '"+idBook+"');";
        try {
            // executing SELECT query
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.print(e);
        }
        return 0;
    }

    public int bookTaken(int id){
        String query ="SELECT Taken FROM book WHERE (`idBook` = "+id+");";
        int rid=0;
        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);
            if(rs.next()){
               rid=rs.getInt(1);
            }
            rs.close();
        }
        catch (Exception e){
            System.out.print(e+"\n");
        }
        return rid;
    }

    public Pair<String, Pair<Integer, Boolean>> getUserInfo(String log){
        int id=0;
        boolean st=FALSE;
        String nm="";

        String query ="SELECT * FROM user WHERE (`Login` = '"+log+"');";
        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);
            if(rs.next()){
                id = rs.getInt(1);
                st = rs.getBoolean(2);
                nm = rs.getString(3);
                rs.close();
            }
        }
        catch (Exception e){
            System.out.print(e+"\n");
        }

        Pair<Integer, Boolean> idAndStatus = new Pair<Integer, Boolean>(id,st);
        Pair<String, Pair<Integer, Boolean>> ret = new Pair<String, Pair<Integer, Boolean>>(nm,idAndStatus);
        return ret; }

    public Pair<String, String> getBookInfo(int id){

        String query ="SELECT * FROM book WHERE (`idBook` = '"+id+"');";
        String nm="";
        String au="";
        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);

            if(rs.next()){

                nm=rs.getString(3);
                au=rs.getString(2);
                rs.close();
            }
        }
        catch (Exception e){
            System.out.print(e+"\n");
        }

        Pair<String, String> p = new Pair<String,String>(nm,au);
        return p;
    }

    public Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> getBookPlace(int id){

        String query ="SELECT Place FROM book WHERE (`idBook` = '"+id+"');";
        String place2="";

        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);

            if(rs.next()){

               place2=rs.getString(1);
               rs.close();
            }
        }
        catch (Exception e){
            System.out.print(e+"\n");
        }

        int zal=0;
        int shkaf=0;
        int polka=0;
        int mesto=0;

        String test="";
        int i=0;
        while(place2.charAt(i)!=';'){
            test=test+place2.charAt(i);
            i++;
        }
        zal=Integer.parseInt(test);

        test="";
        i++;
        while(place2.charAt(i)!=';'){
            test=test+place2.charAt(i);
            i++;
        }
        shkaf=Integer.parseInt(test);

        test="";
        i++;
        while(place2.charAt(i)!=';'){
            test=test+place2.charAt(i);
            i++;
        }
        polka=Integer.parseInt(test);

        test="";
        i++;
        while(i<place2.length()){
            test=test+place2.charAt(i);
            i++;
        }
        mesto=Integer.parseInt(test);

        Pair<Integer,Integer> p1=new Pair(zal, shkaf);
        Pair<Integer,Integer> p2=new Pair(polka, mesto);
        Pair<Pair<Integer,Integer>, Pair<Integer,Integer>> p = new Pair(p1,p2);
        return p;
    }

    public ArrayList<Book> boolList(){

        String query ="SELECT * FROM book order by genre;";
        ArrayList<Book> al=new ArrayList<>();
        try {

            rs = stmt.executeQuery(query);
            while(rs.next()){
                Book bo=new Book(rs.getInt(1),rs.getString(3),rs.getString(2),rs.getString(4),rs.getString(5),rs.getInt(6));
                al.add(bo);
            }
            rs.close();
        }
        catch (Exception e){
            System.out.print(e+"\n");
        }
        return al;
    }

    /*public ArrayList<Book> myBookList(int id){
        String nm="";

        String query ="SELECT * FROM book WHERE (`Taken` = '"+id+"');";
        ArrayList<Book> al=new ArrayList<>();
        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);
            while(rs.next()){
                Book bo=new Book(rs.getInt(1),rs.getString(3),rs.getString(2),rs.getString(4),rs.getString(5),rs.getInt(6));
                al.add(bo);
            }
            rs.close();
        }
        catch (Exception e){
            System.out.print(e+"\n");
        }
        return al;
    }*/

    public void exit(){
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        try { stmt.close();  System.out.print("exit ");} catch(SQLException se) { /*can't do anything */ }
    }

}
