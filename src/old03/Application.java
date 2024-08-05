package old03;

import java.util.List;

import old01.common.ScannerUtil;
import old03.dao.BookDAO;
import old03.dto.BookDTO;

public class Application {
    ScannerUtil scan = new ScannerUtil();
    BookDAO dao = new BookDAO();

    public static void main(String[] args) {
        Application app = new Application();
        app.bookList();
        app.insertBook();
    }

    public void bookList() {
        List<BookDTO> list = dao.getBookList();
        for (BookDTO book : list) {
            System.out.println(book);
        }
    }

    public void insertBook() {
        String title = scan.getString("도서 제목을 입력하세요.");
        String author = scan.getString("도서 작가를 입력하세요.");
        String pub_no = scan.getString("출판사 번호를 입력하세요.");
        BookDTO book = new BookDTO("", title, author, pub_no);
        dao.insertBook(book);

    }
}
