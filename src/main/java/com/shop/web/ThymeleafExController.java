package com.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RequestMapping(value = "/thymeleaf")
@Controller
public class ThymeleafExController {
	@GetMapping(value = "/ex")
	@ResponseBody
    public String thymeleafExample() {
        return "thymeleafEx";
	}
}
