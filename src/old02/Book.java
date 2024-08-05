package old02;

public class Book {

    private String book_no;
    private String title;
    private String author;
    private String rentyn;

    public Book(String book_no, String title, String author, String rentyn) {
        this.book_no = book_no;
        this.title = title;
        this.author = author;
        this.rentyn = rentyn;
    }

    @Override
    public String toString() {

        String str1 = "도서번호 : " + book_no;
        String str2 = "도서명 : " + title;
        String str3 = "도서작가 : " + author;
        String str4 = "대여여부 : " + rentyn;

        return str1 + " /" + str2+ " /" + str3+ " /" + str4;
    }

    public String getAuthor() {
        return author;
    }

    public String getBook_no() {
        return book_no;
    }

    public String getRentyn() {
        return rentyn;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBook_no(String book_no) {
        this.book_no = book_no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRentyn(String rentyn) {
        this.rentyn = rentyn;
    }
}
