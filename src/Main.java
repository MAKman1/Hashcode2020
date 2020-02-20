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

    static int outputNo = 0;

    public static void main( String args[]){

        outputNo = 0;
        while( outputNo < 6){
            books = new ArrayList<>();
            libs = new ArrayList<>();
            libsOriginal = new ArrayList<>();

            System.out.println( "Output no: " + outputNo);
            prepareData();

            //Step 1
            populateBooks();
            orderLibInBooksBySignUpTime();
            cleanUpLibraries();

            //Step 2
            fitInTimeLine();

            //Step 3
            outputData();

            outputNo++;
        }
    }

    //Parsing input
    public static void prepareData(){
        int lineno = 0;
        try {
            File file = null;
            switch ( outputNo){
                case 0:
                    file = new File( "src/inputs/a_example.txt");
                    break;
                case 1:
                    file = new File( "src/inputs/b_read_on.txt");
                    break;
                case 2:
                    file = new File( "src/inputs/c_incunabula.txt");
                    break;
                case 3:
                    file = new File( "src/inputs/d_tough_choices.txt");
                    break;
                case 4:
                    file = new File( "src/inputs/e_so_many_books.txt");
                    break;
                case 5:
                    file = new File( "src/inputs/f_libraries_of_the_world.txt");
                    break;
            }

            Scanner input = new Scanner(file);

            String line = input.nextLine();
            ++lineno;
            String[] i = line.split(" ");
            bookCount = Integer.parseInt( i[0]);
            libCount = Integer.parseInt( i[1]);
            dayCount = Integer.parseInt( i[2]);

            line = input.nextLine();
            ++lineno;
            i = line.split(" ");
            for( int a = 0; a < bookCount; a++){
                books.add( new Book( a, Integer.parseInt( i[a])));
            }

            int libCountCurrent = 0;
            while (input.hasNextLine()) {
                line = input.nextLine();
                if( line == null || line.equals("")){
                    break;
                }

                ++lineno;
                i = line.split(" ");
                libs.add( new Library( libCountCurrent, Integer.parseInt( i[0]), Integer.parseInt( i[1]), Integer.parseInt( i[2])));

                line = input.nextLine();
                ++lineno;
                i = line.split(" ");
                for( int a = 0; a < i.length; a++){
                    libs.get( libs.size() - 1).books.add( books.get( Integer.parseInt( i[a])));
                }

                libCountCurrent++;
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println( "line: " + lineno);
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
            Book current = books.get( i);
            for( int j = 1; j < current.libs.size(); j++){
                Library currentLib = current.libs.get( j);
                for( int k = 0; k < currentLib.books.size(); k++){
                    if( currentLib.books.get( k).id == current.id){
                        currentLib.books.remove( k);
                        k--;
                    }
                }
            }
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
        libs = new ArrayList<>(libsOriginal.subList( 0, splitIndex)); //TODO this

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
            PrintWriter writer = null;
            switch (outputNo){
                case 0:
                    writer = new PrintWriter("src/output/output_a.txt");
                    break;
                case 1:
                    writer = new PrintWriter("src/output/output_b.txt");
                    break;
                case 2:
                    writer = new PrintWriter("src/output/output_c.txt");
                    break;
                case 3:
                    writer = new PrintWriter("src/output/output_d.txt");
                    break;
                case 4:
                    writer = new PrintWriter("src/output/output_e.txt");
                    break;
                case 5:
                    writer = new PrintWriter("src/output/output_f.txt");
                    break;

            }

            writer.print("");

            int libS = libs.size();
            for( int i = 0; i < libs.size(); i++){
                if( libs.get( i).books.size() < 1){
                    libS--;
                }
            }
            writer.println( "" + libS);

            for( int i = 0; i < libs.size(); i++){
                if(libs.get( i).books.size() > 0){
                    writer.println( "" + libs.get( i).id + " " + libs.get( i).books.size()); //TODO this
                    String out = "";
                    for( int j = 0; j < libs.get( i).books.size(); j++){
                        out += libs.get( i).books.get(j).id;
                        if( j < libs.get( i).books.size() - 1){
                            out += " ";
                        }
                    }
                    writer.println( out);
                }
            }

            writer.close();
        } catch( Exception e){
            System.out.println( "Exception: " + e.toString());
        }
    }

}
