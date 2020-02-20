/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author neha9
 */
public class Book {
    private int ID;
    private int score;
    
    public Book(){ ID = 0; score =0;}
    public Book( int i, int s ){ ID = i; score =s;}
    public int getID(){return ID;}
    public int getScore(){return score;}
    public void setID(int i){ID = i;}
    public void setScore(int s){score = s;}
    
    
}
