package old03.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String book_no;
    private String title;
    private String author;
    private int price;
    private String pub_no;
    private String rentyn;
    private String delyn;
    private String regdate;
    /**
     * 도서정보
     */
    public BookDTO (String book_no, String title, String author, String pub_no, int price){
        this.book_no = book_no;
        this.title = title;
        this.author = author;
        this.pub_no = pub_no;
        this.price = price;
    }
    
    @Override
    public String toString() {

        String str1 = "도서번호 : " + book_no;
        String str2 = "도서명 : " + title;
        String str3 = "도서작가 : " + author;
        String str4 = "대여여부 : " + rentyn;

        return str1 + " /" + str2+ " /" + str3+ " /" + str4;
    }
}
