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
        app.mainMenu();
    }

    /**
     * 메인메뉴 선택창
     */
    public void mainMenu() {
        while (true) {
            int menuSelect = -1;

            System.out.println("================================");
            System.out.println("00도서관에 오신것을 환영합니다.");
            System.out.println("================================");
            System.out.println("┌────────── MAIN ──────────┐");
            System.out.println("│ 1. 도서 조회             │");
            System.out.println("│ 2. 도서 대여             │");
            System.out.println("│ 3. 도서 반납             │");
            System.out.println("│ 4. 도서 등록             │");
            System.out.println("│ 5. 도서 삭제             │");
            System.out.println("│ 0. 프로그램종료          │");
            System.out.println("└──────────────────────────┘");
            menuSelect = scan.getInt("번호를 입력하세요.");

            switch (menuSelect) {
                case 1:
                    // 도서 조회
                    System.out.println("~/~/~/~/~/~/~/~/~/~/~/~/~/~/~");
                    System.out.println("도서목록을 조회합니다.");
                    System.out.println("- - - - - - - - - - - - - - -");
                    bookList();
                    System.out.println("~/~/~/~/~/~/~/~/~/~/~/~/~/~/~");
                    break;
                case 2:
                    // 도서 대여
                    rentBook();

                    break;
                case 3:
                    // 도서 반납
                    break;
                case 4:
                    // 도서 추가
                    System.out.println("~/~/~/~/~/~/~/~/~/~/~/~/~/~/~");
                    System.out.println("도서를 추가합니다.");
                    System.out.println("- - - - - - - - - - - - - - -");
                    insertBook();
                    System.out.println("~/~/~/~/~/~/~/~/~/~/~/~/~/~/~");
                    break;
                case 5:
                    // 도서 삭제

                    break;
                case 0:
                    // 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.out.println("이용해주셔서 감사합니다.");
                    System.exit(-1);
                    break;
                default:
                    System.out.println("잘못된 값입니다.");
                    break;
            }
        }
    }

    /**
     * 도서목록조회 - Application
     */
    public void bookList() {
        List<BookDTO> list = dao.getBookList();
        for (BookDTO book : list) {
            System.out.println(book);
        }
    }

    /**
     * 도서정보등록 - Application
     */
    public void insertBook() {
        String title = scan.getString("도서 제목을 입력하세요.");
        String author = scan.getString("도서 작가를 입력하세요.");
        String pub_no = scan.getString("출판사 번호를 입력하세요.");
        int price = scan.getInt("도서 금액을 입력하세요.");
        BookDTO book = new BookDTO("", title, author, pub_no, price);
        dao.insertBook(book);

    }

    public void rentBook() {
        dao.rentBook(null);
    }
}
