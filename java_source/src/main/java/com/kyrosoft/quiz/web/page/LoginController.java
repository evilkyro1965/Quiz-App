package com.kyrosoft.quiz.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2/23/2016.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "login")
    public String login() {
        return "login_view";
    }

    @RequestMapping(value = "landing")
    public String landing() {
        return "index";
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

}
