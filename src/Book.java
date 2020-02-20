import java.util.ArrayList;

public class Book {
    int id;
    int score;
    ArrayList<Library> libs;

    public Book(int id, int score){
        this.id = id;
        this.score = score;
        libs = new ArrayList<>();
    }
}
