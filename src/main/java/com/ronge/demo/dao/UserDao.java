package com.ronge.demo.dao;

import com.ronge.demo.model.Article;
import com.ronge.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select id,name as userName,password as passWord,create_date as createDate,update_date as updateDate,head_image_url as headImageUrl"
            + " from user where name = #{name} ")
    User findUserByName(@Param("name") String name);

    @Select("<script>"
            + "select id,name as userName,password as passWord,"
            + "create_date as createDate,update_date as updateDate,"
            + "head_image_url as headImageUrl"
            + " from user where id in "
            + "(select a.id from "
            + "(select authorId as id,"
            + " sum(0.6*like_counts + 0.4*comment_counts) as hotIndex"
            + " from article_info where 1=1 "
            + "<if test='label != null and label != \"\"'>"
            + " and label = #{label}"
            + "</if>"
            + " group by authorId"
            + " order by hotIndex desc ,updateTime desc"
            + " limit #{size}"
            + ") as a)"
            + "</script>")
    List<User> getHotUser(@Param("label") String label, @Param("size") int size);


    @Select("<script>"
            + "select id,title,content,createTime,updateTime,"
            + " label,sub_label as subLabel,authorId,like_counts as likeCounts ,comment_counts as commentCounts"
            + " from article_info "
            + " where 1=1 "
            + "<if test = \"userId != '' and userId !=null\">"
            + " and authorId = #{userId}"
            + "</if>"
            + "<if test=\"orderKey != '' and orderKey != null \">"
            + " order by ${orderKey}"
            + "</if>"
            + " limit ${start},${count} "
            + "</script>")
    List<Article> getArticles(@Param("userId") String userId,
                              @Param("start") int start,
                              @Param("count") int count,
                              @Param("orderKey") String orderKey);
}
