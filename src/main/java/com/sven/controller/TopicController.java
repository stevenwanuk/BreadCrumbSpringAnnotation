package com.sven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sven.annotation.BreadCrumb;

@Controller
public class TopicController
{

    @BreadCrumb(name = "topicBC", key = "topicId", label = "topicTitle")
    @RequestMapping("/topic")
    public ModelAndView topic()
    {
        ModelAndView mav = new ModelAndView("topic");
        mav.addObject("topicId", "topicId-1");
        mav.addObject("topicTitle", "topicTitle-1");
        return mav;
    }

    @BreadCrumb(name = "mainSectionBC", key = "mainSectionId", label = "mainSectionTitle",
            parent = "topicBC",
            parentKey = "topicId")
    @RequestMapping("/mainSection")
    public ModelAndView mainSection()
    {

        ModelAndView mav = new ModelAndView("mainSection");
        mav.addObject("mainSectionId", "mainSectionId-1-2");
        mav.addObject("mainSectionTitle", "mainSectionTitle-1-2");
        mav.addObject("topicId", "topicId-1");
        mav.addObject("topicTitle", "topicTitle-1");
        return mav;
    }
    
    @BreadCrumb(name = "subSectionBC", key = "subSectionId", label = "subSectionTitle",
            parent = "mainSectionBC",
            parentKey = "mainSectionId")
    @RequestMapping("/subSection")
    public ModelAndView subSection()
    {

        ModelAndView mav = new ModelAndView("subSection");
        mav.addObject("subSectionId", "subSectionId-1-2-3");
        mav.addObject("subSectionTitle", "subSectionTitle-1-2-3");
        
        mav.addObject("mainSectionId", "mainSectionId-1-2");
        mav.addObject("mainSectionTitle", "mainSectionTitle-1-2");
        mav.addObject("topicId", "topicId-1");
        mav.addObject("topicTitle", "topicTitle-1");
        return mav;
    }
}
