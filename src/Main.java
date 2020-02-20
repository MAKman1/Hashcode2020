import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int bookCount;
    static int libCount;
    static int dayCount;

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Library> libs = new ArrayList<>();

    public static void main( String args[]){
        prepareData();

//        System.out.println( bookCount);
//        System.out.println( libCount);
//        System.out.println( dayCount);
//        System.out.println( books.size());
//        System.out.println( libs.size());

        //Step 1
        populateBooks();
        for (Book book: books) {
            System.out.println(book.libs.get(0).bookCount);
        }
        orderLibInBooksBySignUpTime();
        cleanUpLibraries();
    }

    //Parsing input
    public static void prepareData(){
        try {

            File file = new File( "src/inputs/a_example.txt");
//            File file = new File( "src/inputs/b_read_on.txt");
//            File file = new File( "src/inputs/c_incunabula.txt");

            Scanner input = new Scanner(file);

            String line = input.nextLine();
            String[] i = line.split(" ");
            bookCount = Integer.parseInt( i[0]);
            libCount = Integer.parseInt( i[1]);
            dayCount = Integer.parseInt( i[2]);

            line = input.nextLine();
            i = line.split(" ");
            for( int a = 0; a < bookCount; a++){
                books.add( new Book( a, Integer.parseInt( i[a])));
            }

            while (input.hasNextLine()) {
                line = input.nextLine();
                i = line.split(" ");
                libs.add( new Library( Integer.parseInt( i[0]), Integer.parseInt( i[1]), Integer.parseInt( i[2])));

                line = input.nextLine();
                i = line.split(" ");
                for( int a = 0; a < i.length; a++){
                    libs.get( libs.size() - 1).books.add( books.get( Integer.parseInt( i[a])));
                }
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Step 1.1
    public static void populateBooks(){
        for (Library library : libs) {
            for (Book book: library.books) {
                book.libs.add(library);
            }
        }

    }

    //Step 1.2
    public static void orderLibInBooksBySignUpTime(){

    }

    //Step 1.3
    public static void cleanUpLibraries(){

    }
}
