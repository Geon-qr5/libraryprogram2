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
                int price = rs.getInt("price");
                String pub_no = rs.getString("pub_no");

                BookDTO bookDTO = new BookDTO(book_no, title, author, price, pub_no);
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

        String sql = "insert into tb_book (book_no, title, author, price, pub_no) "
        + "values ('B' || lpad(seq_tb_book.nextval, 5, 0), ?, ?, ?, ?)";
        try (
            Connection con = ConnectionUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ){
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPrice());
            pstmt.setString(4, book.getPub_no());
            
            System.out.println(sql);

        } catch (SQLException e) {
            System.out.println("SQL 오류");
            e.printStackTrace();
        }

        System.out.printf("%s : 도서가 등록되었습니다.", book.getTitle());

        return res;
    }
}
