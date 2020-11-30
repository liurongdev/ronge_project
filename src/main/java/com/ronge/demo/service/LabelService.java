package com.ronge.demo.service;

import com.ronge.demo.model.Label;

import java.util.List;

public interface LabelService {

    List<Label> getAllLabel();

    Object getAllLabelAndSubLabel();
}
