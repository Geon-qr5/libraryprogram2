package old01;
import java.util.ArrayList;

import old01.common.ScannerUtil;


public class Library {
    ScannerUtil scan = new ScannerUtil();

    ArrayList<Book> bookList = new ArrayList<Book>();

    public Library() {
        bookList.add(new Book("제목1", "작가1"));
        bookList.add(new Book("제목2", "작가2"));
        bookList.add(new Book("제목3", "작가3"));
        bookList.add(new Book("제목4", "작가4"));
        bookList.add(new Book("제목5", "작가5"));
    }

    public void menu() {

        while (true) {
            System.out.println("===========================");
            System.out.println("OO도서관에 오신것을 환영합니다.");
            System.out.println("===========================");
            System.out.println("----------도서목록----------");
            System.out.println(toString());
            System.out.println("-----------MAIN-----------");
            System.out.println("1. 도서 대여");
            System.out.println("2. 도서 반납");
            System.out.println("3. 도서 등록");
            System.out.println("4. 도서 삭제");
            System.out.println("9. 프로그램 종료");
            System.out.println("--------------------------");
            int menuNum = scan.getInt("번호를 입력하세요.");

            switch (menuNum) {
                case 1:
                    bookRent();
                    break;
                case 2:
                    bookReturn();
                    break;
                case 3:
                    bookAdd();
                    break;
                case 4:
                    bookDelete();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.out.println("이용해주셔서 감사합니다.");
                    System.exit(-1);
                    break;
                default:
                    System.out.println("잘못입력하였습니다.");
                    break;
            }

        }

    }

    private void bookRent() {

        while (true) {
            System.out.println("도서를 대여합니다.");
            int bookNo = scan.getInt("종료 : 0 / 도서 번호를 입력하세요.") - 1;

            if (bookNo >= bookList.size()) {
                System.out.println("없는 도서 번호입니다.");
                continue;
            } else if (bookNo == -1) {
                System.out.println("도서 대여를 종료합니다.");
                return;
            }

            if (bookList.get(bookNo).isRent() == false) {
                bookList.get(bookNo).setRent(true);
                System.out.printf("'%s'를 대여하였습니다.\n", bookList.get(bookNo).getTitle());
            } else if (bookList.get(bookNo).isRent() == true) {
                System.out.println("이미 대여중인 도서입니다.");
                continue;
            }

            String repeat = scan.getString("도서 대여를 계속 진행하시겠습니까? (Y/N)");
            if (repeat.equalsIgnoreCase("Y")) {
                continue;
            } else if (repeat.equalsIgnoreCase("N")) {
                System.out.println("도서 대여를 종료합니다.");
                return;
            } else {
                System.out.println("잘못입력하셨습니다.");
                continue;
            }
        }
    }

    private void bookReturn() {
        while (true) {
            System.out.println("도서를 반납합니다.");
            int bookNo = scan.getInt("종료 : 0 / 도서 번호를 입력하세요.") - 1;

            if (bookNo >= bookList.size()) {
                System.out.println("없는 번호입니다.");
                continue;
            } else if (bookNo == -1) {
                System.out.println("도서 반납을 종료합니다.");
                return;
            }

            if (bookList.get(bookNo).isRent() == true) {
                bookList.get(bookNo).setRent(false);
                System.out.printf("'%s'를 반납하였습니다.\n", bookList.get(bookNo).getTitle());
            } else if (bookList.get(bookNo).isRent() == false) {
                System.out.println("반납이 불가능한 도서입니다.");
                continue;
            }

            String repeat = scan.getString("도서 반납을 계속 진행하시겠습니까? (Y/N)");
            if (repeat.equalsIgnoreCase("Y")) {
                continue;
            } else if (repeat.equalsIgnoreCase("N")) {
                System.out.println("도서 반납을 종료합니다.");
                return;
            } else {
                System.out.println("잘못입력하셨습니다.");
                continue;
            }
        }
    }

    private void bookAdd() {
        while (true) {
            String repeat = scan.getString("도서를 추가하시겠습니까? (Y/N)");
            if (repeat.equalsIgnoreCase("Y")) {
                System.out.println("도서를 추가합니다.");
                String title = scan.getString("도서 제목을 입력하세요.");
                String author = scan.getString("도서 작가를 입력하세요.");

                Book book = new Book(title, author);
                bookList.add(book);

            } else if (repeat.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("잘못입력하셨습니다.");
                continue;
            }

        }
        
    }
    
    private void bookDelete() {
        while (true) {
            String repeat = scan.getString("도서를 삭제하시겠습니까? (Y/N)");
            if (repeat.equalsIgnoreCase("Y")) {
                System.out.println("도서를 삭제합니다.");
                int bookNo = scan.getInt("도서 번호를 입력하세요.") - 1 ;
                Book book = bookList.remove(bookNo);
                System.out.printf("%s는 삭제되었습니다.\n",book.getTitle());
    
            } else if (repeat.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("잘못입력하셨습니다.");
                continue;
            }
    
        }
    }

    @Override
    public String toString() {
        String str = "", rent = null;
        int bookNo = 1;
        for (Book i : bookList) {
            String title = i.getTitle();
            String author = i.getAuthor();
            if (!i.isRent()) {
                rent = "가능";
            } else {
                rent = "대여중";
            }

            str += "No." + bookNo + " / 제목 : " + title + " / 작가 : " + author + " / 대여 :" + rent + "\n";

            bookNo++;
        }
        return str;
    }

}
