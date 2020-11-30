package com.ronge.demo.dao;

import com.ronge.demo.model.Comment;
import com.ronge.demo.model.Reply;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liurong
 * @date 2020/10/4 20:55
 */
public interface ReplyDao {

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("<script>"
            + "insert into reply_info(commentId,articleId,origin_authorId,reply_authorId,content,createTime)"
            + " values(#{commentId},#{articleId},#{originAuthorId},#{replyAuthorId},#{content},#{createTime,jdbcType=TIMESTAMP})"
            +"</script>")
    int addReply(Reply comment);


    @Delete("delete from reply_info where id = #{id}")
    int deleteById(@Param("id") int id);


    @Delete("delete from reply_info where commentId = #{commentId}")
    void deleteByCommentId(@Param("commentId") int commentId);



    @Select("<script>"
            +" select id,commentId,articleId,origin_authorId as originAuthorId,reply_authorId as replyAuthorId,"
            +" content,like_counts as likeCounts,dislike_counts as dislikeCounts,createTime"
            +" from reply_info"
            +" where commentId = #{commentId}"
            +"</script>")
    List<Reply> getItemsByCommentId(@Param("commentId") int commentId);


    @Update("<script>"
            + "update reply_info set "
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
    int doAction(@Param("id") int replyId,@Param("operateType") String operateType);



}
