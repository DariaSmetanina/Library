package model;

public class Book {
    int id;
    String title;
    String autor;
    String genre;
    String place;
    int taken;

    public Book(int i, String tit, String au, String gnr, String pl, int tkn){
        id=i;
        title=tit;
        autor=au;
        genre=gnr;
        place=pl;
        taken = tkn;
    }

    @Override
    public String toString() {

        return title+" "+autor+"\n";
    }

    public int getId(){
        return id;
    }

    public String getGenre(){
        String g=genre;
        return g;
    }
}
