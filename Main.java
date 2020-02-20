
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.min;
import java.util.*;

//Yeet
public class Main {
    
    public static int calcPrio(int daysLeft, Library lib){
        int n = (daysLeft - lib.getSignUp())*lib.getBooksPerDay();
        int sum = 0;
        for( int i = 0; i < min(n, lib.getNumBooks()); i++ ){
            sum += lib.getBookList().get(i).getScore();
        }
        return sum;
    }
    
    public static void main(String [] args) throws IOException{
        Scanner inputFile = new Scanner( new File("d_tough_choices.txt"));
        
        int numBooks = inputFile.nextInt();
        int numLibs = inputFile.nextInt();
        int numDays = inputFile.nextInt();
        //set book scores to all books in array
        int scores[] = new int[numBooks];
        for( int i = 0; i < numBooks; i++){
            scores[i] = inputFile.nextInt();
        }
        
        ArrayList<Library> allLibs = new ArrayList<Library>();
        //initialize all the libraries
        for(int i = 0; i < numLibs; i++){
            Library l = new Library();
            l.setID(i);
            l.setNumBooks(inputFile.nextInt());
            l.setSignUp(inputFile.nextInt());
            l.setBooksPerDay(inputFile.nextInt());
            allLibs.add(l);
            
            for(int j = 0; j < l.getNumBooks(); j++ ){
                int index = inputFile.nextInt();
                l.addBook(new Book(index, scores[index]));
            }      
        }
        
        //printing out allLibs
       
        for( int i = 0; i < numLibs; i++ ){
            Collections.sort( allLibs.get(i).getBookList(), new SortByScore() );
        }
                
        ArrayList<Library> usedLibs = new ArrayList<Library>();
        int minSignUp = Integer.MAX_VALUE;
        
        do{
            //(re)calc priorities
            for( int i = 0; i < numLibs; i++ ){
                allLibs.get(i).setPriority( calcPrio(numDays, allLibs.get(i)) );
            }

            int max = -1;
            int maxIndex = -1;
            for( int i = 0; i < numLibs; i++ ){
                if( allLibs.get(i).getPriority() >  max ){
                    maxIndex = i;
                    max = allLibs.get(i).getPriority();
                }
            }
            for( int i = 0; i < numLibs; i++ ){
                if( allLibs.get(i) != allLibs.get(maxIndex) ){
                    for( int j = 0; j < allLibs.get(i).getNumBooks(); j++ ){
                        
                            if( j < maxIndex )
                                maxIndex--;
                        for( int k = 0; k < allLibs.get(maxIndex).getNumBooks(); k++ ){
                            //System.out.println(j + " " + allLibs.size());
                            if((j > allLibs.size()))
                                break;
                            
                            if( allLibs.get(i).getBookList().get(j).getID() == allLibs.get(maxIndex).getBookList().get(k).getID()){
                                allLibs.get(i).getBookList().remove(j);
                                allLibs.get(i).setNumBooks(allLibs.get(i).getNumBooks()-1);
                                maxIndex--;
                            }
                        }
                    }
                }
            }
            for( int i = 0; i < numLibs; i++ ){
                Collections.sort( allLibs.get(i).getBookList(), new SortByScore() );
            }        

            usedLibs.add(allLibs.get(maxIndex));
            numDays-=allLibs.get(maxIndex).getSignUp();
            allLibs.remove(maxIndex);
            numLibs--;
            
            for( int i = 0; i < allLibs.size(); i++ ){
                if( allLibs.get(i).getSignUp() < minSignUp ){
                    minSignUp = allLibs.get(i).getSignUp();
                }
            }
        } while( numDays > 0 && numDays > minSignUp );
        
        PrintWriter output = new PrintWriter( new File( "results4.txt" ));
        
        output.println( usedLibs.size() );
        
        for( int i = 0; i < usedLibs.size(); i++ ){
            output.println(usedLibs.get(i).getID() + " " + usedLibs.get(i).getNumBooks());
            for( int j = 0; j < usedLibs.get(i).getNumBooks(); j++ ){
                output.print( usedLibs.get(i).getBookList().get(j).getID() + " ");
            }
            output.println("");
        }
     
        output.close();
    }
}
