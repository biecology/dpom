package com.server.service;

import com.dao.entity.ComConfigViewpager;

import java.util.List;

public interface IComConfigViewpaperService {

    List<ComConfigViewpager> getAllBySort(Integer type);
}
