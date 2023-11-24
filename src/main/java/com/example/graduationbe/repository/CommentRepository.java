package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select com_id,content, product_p_id, title, images,full_name,date " +
            " from (select com_id,content, product_p_id, title, images,u.userid,full_name,date\n" +
            "from comment a inner join product p on a.product_p_id = p.p_id " +
            "inner join user u on a.user_userid = u.userid group by product_p_id) as comments", nativeQuery = true)
    List<Map<Object, String>> getComment();

    @Modifying
    @Query(value = "delete from Comment l where l.comId=:comId")
    void deleteCommentByComId(Long comId);
}
