import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        for (int i = 0; i < bookCount; i++) {
            Collections.sort(books.get(bookCount).libs, new CustomComparator());
        }
    }

    // Custom Comparator
    public static class CustomComparator implements Comparator<Library> {
        @Override
        public int compare(Library o1, Library o2) {
            Integer a = new Integer(o1.signUpTime);
            Integer b = new Integer(o2.signUpTime);
            return a.compareTo(b);
        }
    }

    //Step 1.3
    public static void cleanUpLibraries(){

    }
}
