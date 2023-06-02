package com.ywy.bootadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Controller
 *
 * @author ywy
 * @date 2020/3/19
 */
@Controller
@ApiIgnore
public class IndexController {
    /**
     * 页面路由
     * @param modelAndView
     * @param pageName
     * @return
     */
    @GetMapping("/router")
    public ModelAndView router(ModelAndView modelAndView, String pageName) {
        modelAndView.setViewName(pageName);
        return modelAndView;
    }

    /**
     * 访问页面
     * 登录页面、403页面……
     * @return
     */
    @GetMapping(value = "/{page}.html")
    public String login(@PathVariable("page") String page) {
        return page;
    }
}
