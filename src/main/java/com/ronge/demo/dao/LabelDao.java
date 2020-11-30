package com.ronge.demo.dao;

import com.ronge.demo.model.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface LabelDao {

    @Select("<script>" +
            " select id,name,value,order_id as orderId from label order by order_id"
            +"</script>")
    List<Label> getAllLabel();

    @Select("<script>"
            + "select a.id, a.name, a.value,b.name subName,b.value subValue ,b.order_id subOrderId "
            + "from  label a JOIN sub_label b on a.id=b.label_id  ORDER BY  a.order_id ,b.order_id "
            + "</script>")
    List<Map<String,String>> getAllLabelAndSubLabel();
}
