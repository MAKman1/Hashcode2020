import java.util.ArrayList;

public class Library {
    int id;
    int bookCount;
    int signUpTime;
    int processLimit;

    int sortProduct;

    ArrayList<Book> books;

    public Library( int id, int bookCount, int signUpTime, int processLimit){
        this.id = id;
        this.bookCount = bookCount;
        this.signUpTime = signUpTime;
        this.processLimit = processLimit;

        this.sortProduct = bookCount / processLimit;
        this.books = new ArrayList<>();
    }
}
