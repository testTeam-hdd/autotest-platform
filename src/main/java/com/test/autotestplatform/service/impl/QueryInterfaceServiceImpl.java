package com.test.autotestplatform.service.impl;

import com.test.autotestplatform.service.QueryInterfaceService;
import com.test.autotestplatform.utils.GetClassesUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * dongdong Created by 6:17 PM  2020/9/4
 */
@Service
public class QueryInterfaceServiceImpl implements QueryInterfaceService {
    @Override
    public Set<String> queryAllInterface() {
        GetClassesUtil.init();
        Map<String, List<Class>> map = GetClassesUtil.getMap();
        Set<String> projectName = map.keySet();
        return projectName;
    }



}
