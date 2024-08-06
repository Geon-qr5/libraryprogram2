package old03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import old03.common.ConnectionUtil;
import old03.dto.BookDTO;

public class BookDAO {

    /**
     * 도서 대여
     */
    public int rentBook(BookDTO book) {
        int res = 0;

        String sql = "UPDATE TB_BOOK SET RENTYN = 'Y' WHERE TITLE = 'B00001'";

        try (
            Connection con = ConnectionUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
                res = pstmt.executeUpdate();

                System.out.println("업데이트 완료!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 도서목록 조회
     */
    public List<BookDTO> getBookList() {
        List<BookDTO> list = null;

        String sql = "select * from tb_book";

        try (
                Connection con = ConnectionUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {
            list = new ArrayList<BookDTO>();

            while (rs.next()) {
                String book_no = rs.getString("book_no");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String pub_no = rs.getString("pub_no");
                int price = rs.getInt("price");

                BookDTO bookDTO = new BookDTO(book_no, title, author, pub_no, price);
                list.add(bookDTO);
            }
        } catch (SQLException e) {
            System.out.println("SQL 에러");
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 도서입력
     */
    public int insertBook(BookDTO book) {
        int res = 0;

        String sql = "insert into tb_book (book_no, title, author, pub_no, price)"
        + " values ('B' || LPAD (SEQ_TB_BOOK.NEXTVAL, 5, 0) ,? ,? ,?, ?)";
        try (
            Connection con = ConnectionUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
        ){
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPub_no());
            pstmt.setInt(4, book.getPrice());

            res = pstmt.executeUpdate();

            System.out.printf("%s : 도서가 등록되었습니다.\n", book.getTitle());
        } catch (SQLException e) {
            System.out.println("SQL 오류");
            e.printStackTrace();
        }


        return res;
    }
}
