package com.itheima.mapper;

import com.itheima.domain.Book;
import com.itheima.pojo.ResultData;
import org.apache.ibatis.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper
public interface BookMapper {

//    @Select("select * from tb_book where id=#{id}")
//    public Book findBookById(Integer id);

    //查看新书
    @Select("select * from book where book_status=0 order by book_uploadtime desc;")
//    @Results(id = "bookMap",value = {
//            @Result(id = true,column = "boo")
//    })
    public List<Book> selectNewBooks();

    //

    @Update(" <script>  " +
            "  update book" +
            "  <trim prefix=\"set\" suffixOverrides=\",\">  " +
            "    <if test = \"book_author != null\">  " +
            "      book_author = #{book_author},  " +
            "    </if>  " +
            "      <if test = \"book_borrower != null\">  " +
            "      book_borrower = #{book_borrower} ,  " +
            "    </if>  " +
            "      <if test = \"book_borrowtime != null\">  " +
            "      book_borrowtime = #{book_borrowtime} ,  " +
            "    </if>  " +
            "      <if test = \"book_id != null\">  " +
            "      book_id = #{book_id} ,  " +
            "    </if>  " +
            "      <if test = \"book_isbn != null\">  " +
            "      book_isbn = #{book_isbn} ,  " +
            "    </if>  " +
            "      <if test = \"book_name != null\">  " +
            "      book_name = #{book_name} ,  " +
            "    </if>  " +
            "      <if test = \"book_pagination != null\">  " +
            "      book_pagination = #{book_pagination} ,  " +
            "    </if>  " +
            "      <if test = \"book_press != null\">  " +
            "      book_press = #{book_press} ,  " +
            "    </if>  " +
            "      <if test = \"book_price != null\">  " +
            "      book_price = #{book_price} ,  " +
            "    </if>  " +
            "      <if test = \"book_returntime != null\">  " +
            "      book_returntime = #{book_returntime} ,  " +
            "    </if>  " +
            "      <if test = \"book_status != null\">  " +
            "      book_status = #{book_status} ,  " +
            "    </if>  " +
            "      <if test = \"book_uploadtime != null\">  " +
            "      book_uploadtime = #{book_uploadtime} ,  " +
            "    </if>  " +
            "  </trim>  " +
            "    where book_id =  #{book_id}  " +
            "</script>  ")
    Integer updateBook(Book book);

    @Insert("<script>  " +
            "insert into book(book_name,book_isbn,book_press,book_author,book_pagination,book_price,book_uploadtime,book_status,book_borrower,book_borrowtime,book_returntime)   " +
            "  values <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">  " +
            "  #{book_name},#{book_isbn},#{book_press},#{book_author},#{book_pagination},#{book_price},#{book_uploadtime},#{book_status},#{book_borrower},#{book_borrowtime},#{book_returntime}, "+
            "  </trim>"+
            "</script>  " +
            "")
    Integer insertBook(Book book);


}
