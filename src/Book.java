// defines a class that represent a book
public class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    // constructor to the book class to create the books and give them their value
    public Book(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    // get method to get the ID of the books and return the result
    public int getId() {
        return id;
    }

    // get method to get the title of the books and return the result
    public String getTitle() {
        return title;
    }

    // get method to get the author of the books and return the result
    public String getAuthor() {
        return author;
    }

    // get method with a boolean to control if the book's available or not and return the result
    public boolean isAvailable() {
        return available;
    }

    // override of the toString method to provide a string representation of the book for better usability
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}