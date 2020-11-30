package com.ronge.demo.service.impl;

import com.ronge.demo.dao.LabelDao;
import com.ronge.demo.model.Label;
import com.ronge.demo.service.LabelService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LabelServiceImpl implements LabelService {

    private static final Logger logger = LoggerFactory.getLogger(LabelServiceImpl.class);

    @Autowired
    private LabelDao labelDao;

    @Override
    public List<Label> getAllLabel() {
        return labelDao.getAllLabel();
    }

    @Override
    public Object getAllLabelAndSubLabel() {
        List<Map<String, String>> data = labelDao.getAllLabelAndSubLabel();
        Map<String,List<Map<String,String>>> result= data.stream().collect(
                Collectors.groupingBy(item->String.valueOf(item.get("id"))+"_"+item.get("name")+"_"+item.get("value")));
        return result;
    }
}
