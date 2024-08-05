package old02;

public class RentBook {
    private String rent_no;
    private String mem_no;
    private String book_no;
    private String rent_date;
    private String return_date;

    public RentBook(String rent_no, String mem_no, String book_no) {
        this.rent_no = rent_no;
        this.mem_no = mem_no;
        this.book_no = book_no;
    }

    @Override
    public String toString() {

        String str1 = "대여번호 : " + rent_no;
        String str2 = "사용자번호 : " + mem_no;
        String str3 = "도서번호 : " + book_no;

        return str1 + " /" + str2+ " /" + str3;
    }

    public String getBook_no() {
        return book_no;
    }

    public String getMem_no() {
        return mem_no;
    }

    public String getRent_date() {
        return rent_date;
    }

    public String getRent_no() {
        return rent_no;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setBook_no(String book_no) {
        this.book_no = book_no;
    }

    public void setMem_no(String mem_no) {
        this.mem_no = mem_no;
    }

    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }

    public void setRent_no(String rent_no) {
        this.rent_no = rent_no;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}
