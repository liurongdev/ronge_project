package com.ronge.demo.dao;

import com.ronge.demo.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liurong
 * @date 2020/10/4 20:14
 */
public interface CommentDao {


    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("<script>"
            + "insert into comment_info(articleId,authorId,content,createTime)"
            + " values(#{articleId},#{authorId},#{content},#{createTime,jdbcType=TIMESTAMP})"
            +"</script>")
    int addComment(Comment comment);

    @Delete("delete from comment_info where commentId = #{commentId}")
    void deleteByCommentId(@Param("commentId") int commentId);

    @Select("<script>"
            +" select id,articleId,authorId,content,like_counts as likeCounts,dislike_counts as dislikeCounts,createTime"
            + " from comment_info where articleId = #{articleId}"
            +"</script>")
    List<Comment> getItemsByArticleId(@Param("articleId") long articleId);


    @Update("<script>"
            + "update comment_info set "
            +"<choose>"
                + "<when  test=\"operateType == 'do_like'\">"
                + " like_counts  = like_counts+1"
                +"</when>"
                + "<when  test=\"operateType == 'undo_like'\">"
                + " like_counts  = like_counts-1"
                +"</when>"
                + "<when  test=\"operateType == 'do_dislike'\">"
                + " dislike_counts  = dislike_counts+1"
                +"</when>"
                + "<when  test=\"operateType == 'undo_dislike'\">"
                + " dislike_counts  = dislike_counts-1"
                +"</when>"
                + "<otherwise>"
                +"</otherwise>"
            +"</choose>"
            + " where id = #{id}"
            +"</script>")
    int doAction(@Param("id") int commentId,@Param("operateType") String operateType);

}
