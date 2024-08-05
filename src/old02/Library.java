package old02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import old02.common.ConnectionUtil;
import old02.common.ScannerUtil;

public class Library {
    ScannerUtil scan = new ScannerUtil();
    List<Book> bookList = new ArrayList<Book>();
    List<Pub> pubList = new ArrayList<Pub>();
    List<RentBook> rentBookList = new ArrayList<RentBook>();

    public Library() {

    }

    /**
     * 메인 메뉴
     */
    public void mainMenu() {
        while (true) {
            System.out.println("===========================");
            System.out.println("OO도서관에 오신것을 환영합니다.");
            System.out.println("===========================");
            System.out.println("┌────────MAIN───────────┐");
            System.out.println("│ 1. 도서 관리          │");
            System.out.println("│ 2. 사용자 관리        │");
            System.out.println("│ 9. 프로그램 종료      │");
            System.out.println("└───────────────────────┘");
            int menuNum = scan.getInt("번호를 입력하세요.");

            switch (menuNum) {
                case 1:
                    bookMenu();
                    break;
                case 2: // 미완성 - 멤버메뉴 구성
                    memberMenu();
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

    /**
     * 도서 관리 메뉴
     */
    public void bookMenu() {
        while (true) {
            System.out.println("┌────────BOOK───────────┐");
            System.out.println("│ 1. 도서 대여          │");
            System.out.println("│ 2. 도서 반납          │");
            System.out.println("│ 3. 도서 등록          │");
            System.out.println("│ 4. 도서 삭제          │");
            System.out.println("│ 5. 도서 조회          │");
            System.out.println("│ 6. 출판사 조회        │");
            System.out.println("│ 7. 메인 메뉴          │");
            System.out.println("│ 9. 프로그램 종료      │");
            System.out.println("└───────────────────────┘");
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
                case 5:
                    bookCheck();
                    break;
                case 6:
                    pubCheck();
                    break;
                case 7:
                    mainMenu();
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

    /**
     * 사용자 관리 메뉴
     */
    public void memberMenu() {

    }

    /**
     * 도서대여
     * 
     * 추후 등록된 사용자가 아닌 경우 오류반환
     * 등록된 도서가 아닌 경우 오류반환
     * 작업예정
     */
    private void bookRent() {
        try {
            Connection con = ConnectionUtil.getConnection();
            Statement st = con.createStatement();

            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");
            String user = scan.getString("사용자 번호를 입력하세요.");
            String sql = "SELECT * FROM TB_RENT WHERE RETURN_DATE IS NULL AND MEM_NO = '" + user + "'";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.printf("현재 %s 사용자가 대여중인 책 목록입니다.\n", user);
            while (rs.next()) {
                String rent_no = rs.getString("RENT_NO");
                String mem_no = rs.getString("MEM_NO");
                String book_no = rs.getString("BOOK_NO");
                RentBook book = new RentBook(rent_no, mem_no, book_no);

                rentBookList.add(book);
            }

            for (RentBook rent : rentBookList) {
                System.out.println(rent.toString());
            }

            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");

            rentBookList.clear();

            bookCheck();
            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");

            String rentBookNo = scan.getString("대여를 원하는 도서번호를 입력하세요.");

            sql = "UPDATE TB_BOOK SET RENTYN = 'Y' WHERE BOOK_NO = '" + rentBookNo + "'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            String rentNo = "'R' || LPAD ( SEQ_TB_RENT.NEXTVAL, 5, 0)";
            sql = "INSERT INTO TB_RENT (RENT_NO, MEM_NO, BOOK_NO) VALUES (" + rentNo + ",'" + user + "','" + rentBookNo
                    + "')";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.printf("%s 도서대여가 완료되었습니다.\n", rentBookNo);

            sql = "SELECT * FROM TB_RENT WHERE RETURN_DATE IS NULL AND MEM_NO = '" + user + "'";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.printf("현재 %s 사용자가 대여중인 책 목록입니다.\n", user);
            while (rs.next()) {
                String rent_no = rs.getString("RENT_NO");
                String mem_no = rs.getString("MEM_NO");
                String book_no = rs.getString("BOOK_NO");
                RentBook book = new RentBook(rent_no, mem_no, book_no);

                rentBookList.add(book);
            }

            for (RentBook rent : rentBookList) {
                System.out.println(rent.toString());
            }

            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");

            rentBookList.clear();

            bookCheck();
            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");

            ConnectionUtil.closeConnection(rs, pstmt, con, st);
        } catch (SQLException e) {
            System.out.println("도서 대여 중 오류발생");
            e.printStackTrace();
        }

    }

    /**
     * 도서반납
     */
    private void bookReturn() {
        try {
            Connection con = ConnectionUtil.getConnection();
            Statement st = con.createStatement();

            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");
            String user = scan.getString("사용자 번호를 입력하세요.");
            String sql = "SELECT * FROM TB_RENT WHERE RETURN_DATE IS NULL AND MEM_NO = '" + user + "'";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.printf("현재 %s 사용자가 대여중인 책 목록입니다.\n", user);
            while (rs.next()) {
                String rent_no = rs.getString("RENT_NO");
                String mem_no = rs.getString("MEM_NO");
                String book_no = rs.getString("BOOK_NO");
                RentBook book = new RentBook(rent_no, mem_no, book_no);

                rentBookList.add(book);
            }

            for (RentBook rent : rentBookList) {
                System.out.println(rent.toString());
            }

            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");

            rentBookList.clear();

            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");

            String returnBookNo = scan.getString("반납을 원하는 도서번호를 입력하세요.");

            sql = "UPDATE TB_BOOK SET RENTYN = 'N' WHERE BOOK_NO = '" + returnBookNo + "'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            sql = "UPDATE TB_RENT SET RETURN_DATE = SYSDATE WHERE RETURN_DATE IS NULL AND BOOK_NO = '" + returnBookNo + "'";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.printf("%s 도서반납이 완료되었습니다.\n", returnBookNo);

            sql = "SELECT * FROM TB_RENT WHERE RETURN_DATE IS NULL AND MEM_NO = '" + user + "'";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.printf("현재 %s 사용자가 대여중인 책 목록입니다.\n", user);
            while (rs.next()) {
                String rent_no = rs.getString("RENT_NO");
                String mem_no = rs.getString("MEM_NO");
                String book_no = rs.getString("BOOK_NO");
                RentBook book = new RentBook(rent_no, mem_no, book_no);

                rentBookList.add(book);
            }

            for (RentBook rent : rentBookList) {
                System.out.println(rent.toString());
            }

            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");

            rentBookList.clear();

            bookCheck();
            System.out.println("~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~- ~-");

            ConnectionUtil.closeConnection(rs, pstmt, con, st);
        } catch (SQLException e) {
            System.out.println("도서 반납 중 오류발생");
            e.printStackTrace();
        }

    }

    /**
     * 도서추가
     * 추후 작업으로 실행여부를 묻는 작업 추가예정
     */
    private void bookAdd() {
        System.out.println("도서추가를 진행합니다.");
        String book_no = "'B' || LPAD ( SEQ_TB_BOOK.NEXTVAL, 5, 0)";
        String title = scan.getString("도서 타이틀");
        String author = scan.getString("도서 작가명");
        String price = scan.getString("도서 가격");
        pubCheck();
        String pubNo = scan.getString("출판사번호");
        String quarry = "BOOK_NO, TITLE, AUTHOR, PRICE, PUB_NO";
        String blues = book_no + ",'" + title + "','" + author + "'," + price + ",'" + pubNo + "'";
        String sql = "INSERT INTO TB_BOOK (" + quarry + ") VALUES (" + blues + ")";

        try {
            Connection con = ConnectionUtil.getConnection();
            Statement st = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ConnectionUtil.closeConnection(rs, pstmt, con, st);
        } catch (SQLException e) {
            System.out.println("도서 추가 중 오류발생");
            e.printStackTrace();
        }

        System.out.printf("%s도서가 추가 되었습니다.\n", title);
    }

    /**
     * 도서삭제
     * 추후 작업으로 실행여부를 묻는 작업 추가예정
     */
    private void bookDelete() {
        System.out.println("도서삭제를 진행합니다.");
        bookCheck();
        String bookNo = scan.getString("도서 번호를 입력하세요.");
        String sql = "DELETE TB_BOOK WHERE BOOK_NO = '" + bookNo + "'";
        try {
            Connection con = ConnectionUtil.getConnection();
            Statement st = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ConnectionUtil.closeConnection(rs, pstmt, con, st);
            System.out.println("도서가 삭제되었습니다.");
        } catch (SQLException e) {
            System.out.println("도서추가 중 오류발생");
            e.printStackTrace();
        }

    }

    /**
     * 도서조회
     */
    private void bookCheck() {

        System.out.println("도서를 조회합니다.");

        String sql = "SELECT * FROM TB_BOOK";

        try {
            Connection con = ConnectionUtil.getConnection();
            Statement st = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String book_no = rs.getString("BOOK_NO");
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                String rentyn = rs.getString("RENTYN");
                Book book = new Book(book_no, title, author, rentyn);

                bookList.add(book);
            }

            for (Book bookFor : bookList) {
                System.out.println(bookFor.toString());
            }

            bookList.clear();

            ConnectionUtil.closeConnection(rs, pstmt, con, st);
        } catch (SQLException e) {
            System.out.println("도서 조회 중 오류발생");
            e.printStackTrace();
        }

    }

    /**
     * 출판사조회
     */
    private void pubCheck() {

        System.out.println("출판사를 조회합니다.");

        String sql = "SELECT * FROM TB_PUB";

        try {
            Connection con = ConnectionUtil.getConnection();
            Statement st = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String pub_no = rs.getString("PUB_NO");
                String pubName = rs.getString("PUB_NAME");
                String phone = rs.getString("PHONE");

                Pub pub = new Pub(pub_no, pubName, phone);
                pubList.add(pub);
            }

            for (Pub pub : pubList) {
                System.out.println(pub.toString());
            }

            pubList.clear();

            ConnectionUtil.closeConnection(rs, pstmt, con, st);
        } catch (SQLException e) {
            System.out.println("출판사 조회 중 오류발생");
            e.printStackTrace();
        }

    }

}
