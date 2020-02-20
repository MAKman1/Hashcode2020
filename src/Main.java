import java.util.ArrayList;

public class Main {

    static int bookCount;
    static int libCount;
    static int dayCount;

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Library> libs = new ArrayList<>();

    public static void main( String args[]){
        System.out.println( "Hi");
        
        prepareData();

        //Step 1
        populateBooks();
        orderLibInBooksBySignUpTime();
        cleanUpLibraries();
    }

    //Parsing input
    public static void prepareData(){

    }

    //Step 1.1
    public static void populateBooks(){

    }

    //Step 1.2
    public static void orderLibInBooksBySignUpTime(){

    }

    //Step 1.3
    public static void cleanUpLibraries(){

    }
}
