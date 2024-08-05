package old01;

public class Book {

    private String title;
    private String author;
    private boolean rent;

    public Book() {

    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        rent = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isRent() {
        return rent;
    }

}
