package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: RedirectionController
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/31 19:14
 */

@Controller
@RequestMapping("/r")
public class RedirectionController {

    @PostMapping("toHome")
    public String toHome() {
        return "redirect:home.html";
    }

    @PostMapping("toFail")
    public String toFail() {
        return "redirect:fail.html";
    }
}
