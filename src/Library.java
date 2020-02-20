import java.util.ArrayList;

public class Library {
    int bookCount;
    int signUpTime;
    int processLimit;

    int sortProduct;

    ArrayList<Book> books;

    public Library( int bookCount, int signUpTime, int processLimit){
        this.bookCount = bookCount;
        this.signUpTime = signUpTime;
        this.processLimit = processLimit;

        this.sortProduct = bookCount / processLimit;
        this.books = new ArrayList<>();
    }
}
