
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author neha9
 */
public class Library {
    private int ID;
    private int signUp;
    private int booksPerDay;
    private int numBooks;
    private int priority;
    private ArrayList <Book>bookList = new ArrayList<Book>();
    
    public Library(){ID = 0; signUp = 0; booksPerDay = 0;}
    public Library(int i, int s, int b){ID = i; signUp = s; booksPerDay = b;}
    
    public int getID(){return ID;}
    public int getSignUp(){return signUp;}
    public int getBooksPerDay(){return booksPerDay;}
    public int getNumBooks(){return numBooks;}
    public ArrayList<Book> getBookList(){return bookList;}
    public int getPriority(){return priority;}
    
    public void setID(int i){ID = i;}
    public void setSignUp(int s){signUp = s;}
    public void setBooksPerDay(int b){booksPerDay = b;}
    public void setNumBooks(int n){numBooks = n;}
    public void addBook(Book b){bookList.add(b);}
    public void setPriority(int p){priority = p;}
    
    
}
    

