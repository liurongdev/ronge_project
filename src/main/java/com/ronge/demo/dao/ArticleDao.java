package com.ronge.demo.dao;

import com.ronge.demo.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    @Insert({"insert into article_info(id,title,content,createTime,updateTime,label,sub_label,authorId) "
            + "values(#{id},#{title},#{content},#{createTime},#{updateTime,jdbcType=TIMESTAMP}"
            +",#{label},#{subLabel},#{authorId})"})
    boolean addArticle(Article article);


    @Select("select * from  article_info where id = #{articleId}")
    Article getItemById(long articleId);


    @Select("<script>"
            +"select * from article_info where authorId = #{userId}"
            +"</script>")
    List<Article> getItemsByUserId(long userId);


    @Select("<script>"
            + "select id,title,content,createTime,updateTime,"
            +" label,sub_label as subLabel,authorId,like_counts as likeCounts ,comment_counts as commentCounts"
            +" from article_info "
            + " where 1=1 "
            + "<if test = \"label != '' and label !=null\">"
            + " and label = #{label}"
            + "</if>"
            + "<if test = \"subLabel != '' and subLabel != null\"> "
            + " and sub_label = #{subLabel}"
            + "</if>"
            + " order by createTime desc "
            + "</script>")
    List<Article> getItemsByCategory(@Param("label") String label,
                                     @Param("subLabel") String subLabel,
                                     @Param("orderKey") String orderKey);


    @Update("<script>"
            +" update article_info set like_counts = like_counts+1 where id = #{id}"
            +"</script>")
    void doLike(@Param("id") long articleId);

    @Update("<script>"
            +" update article_info set like_counts = like_counts-1 where id = #{id}"
            +"</script>")
    void undoLike(@Param("id") long articleId);


    @Select("<script>"
            +"select id,title,content,createTime,updateTime,label,sub_label,authorId,like_counts,comment_counts, "
            + "(60*like_counts + 40*comment_counts)/100 as heatIndex "
            + " from article_info where id != #{id}  "
            + " order by heatIndex desc ,updateTime desc"
            + " limit #{size}"
            +"</script>")
    List<Article> getRelateArticleList(@Param("id") long articleId,@Param("size") int size);
}
