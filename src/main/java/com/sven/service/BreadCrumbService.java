package com.sven.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sven.annotation.BreadCrumb;
import com.sven.model.BreadCrumbModel;

@Service
public class BreadCrumbService
{

    private List<BreadCrumb> registeredBreadCrumbs = new ArrayList<>();
    private Map<String, BreadCrumbModel> breadCrumbModels = new HashMap<>();
    
    public void addRegisteredBreadCrumb(final BreadCrumb breadCrumb) {
        this.registeredBreadCrumbs.add(breadCrumb);
    }
    
    public BreadCrumb getParentRegisteredBreadCrumb(final String parentName) {
        
        return this.registeredBreadCrumbs.stream().filter(s -> s.name().equalsIgnoreCase(parentName)).findAny().orElse(null);
        
    }
    
    public void addBreadCrumbModels(final BreadCrumbModel breadCrumb) {
        this.breadCrumbModels.put(breadCrumb.getUid(), breadCrumb);
    }
    
    public BreadCrumbModel getBreadCrumbModels(final String uid) {
        return this.breadCrumbModels.get(uid);
    }
}
