import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static int bookCount;
    static int libCount;
    static int dayCount;

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Library> libs = new ArrayList<>();

    static ArrayList<Library> libsOriginal;

    public static void main( String args[]){

        prepareData();

//        System.out.println( bookCount);
//        System.out.println( libCount);
//        System.out.println( dayCount);
//        System.out.println( books.size());
//        System.out.println( libs.size());

        //Step 1
        populateBooks();
        orderLibInBooksBySignUpTime();
        cleanUpLibraries();

        //Step 2
        fitInTimeLine();

        //Step 3
        outputData();

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

            int libCount = 0;
            while (input.hasNextLine()) {
                line = input.nextLine();
                i = line.split(" ");
                libs.add( new Library( libCount, Integer.parseInt( i[0]), Integer.parseInt( i[1]), Integer.parseInt( i[2])));

                line = input.nextLine();
                i = line.split(" ");
                for( int a = 0; a < i.length; a++){
                    libs.get( libs.size() - 1).books.add( books.get( Integer.parseInt( i[a])));
                }

                libCount++;
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
            Collections.sort(library.books, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    Integer a = new Integer(o1.score);
                    Integer b = new Integer(o2.score);
                    return b.compareTo(a);
                }
            });
        }
    }

    //Step 1.2
    public static void orderLibInBooksBySignUpTime(){
        for (int i = 0; i < bookCount; i++) {
            Collections.sort(books.get(i).libs, new Comparator<Library>(){
                @Override
                public int compare(Library o1, Library o2) {
                    Integer a = new Integer(o1.signUpTime);
                    Integer b = new Integer(o2.signUpTime);
                    return a.compareTo(b);
                }
            });
        }
    }

    //Step 1.3
    public static void cleanUpLibraries(){
        for (int i = 0; i < bookCount; i++) {
            ArrayList<Library> updatedLibs = new ArrayList<Library>(books.get(i).libs.subList(0, 1));
            books.get(i).libs = updatedLibs;
        }
    }

    //Step 2.1
    public static void fitInTimeLine(){
        Collections.sort(libs, new Comparator<Library>() {
            @Override
            public int compare(Library o1, Library o2) {
                Integer a = new Integer(o1.signUpTime);
                Integer b = new Integer(o2.signUpTime);
                return a.compareTo(b);
            }
        });
        int splitIndex = 0;
        int count = 0;
        while( count < dayCount && splitIndex < libs.size()){
            count += libs.get( splitIndex).signUpTime;
            splitIndex++;
        }

        libsOriginal = libs;
        libs = new ArrayList<>(libs.subList( 0, splitIndex)); //TODO this

        Collections.sort(libs, new Comparator<Library>() {
            @Override
            public int compare(Library o1, Library o2) {
                Integer a = new Integer(o1.sortProduct);
                Integer b = new Integer(o2.sortProduct);
                return a.compareTo(b);
            }
        });
    }


    public static void outputData(){
        try{
            PrintWriter writer = new PrintWriter("src/output/output_a.txt", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        } catch( Exception e){

        }
    }

}
