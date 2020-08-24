package evan.springframework.evanpetclinicspringproj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    // 这里的意思就是，当试图访问url或者url/或者url/index或者url/index.html
    // 时Spring就会自动执行index这个method，之后这个method是return一个string，叫index
    // 但是spring会自动到templates这个folder里找一个叫index.html的file，之后chrome里呈现的
    // 就是这个html的内容
    @RequestMapping({"","/","index","index.html"})
    public String index(){
        return "index";
    }
    //在要访问http://localhost:8080/owners/find时
    // 执行findOwners这个method，return我们写好的notimplemented这个html
    @RequestMapping("/oups")
    public String oupsHandler(){
        return "notimplemented";
    }
}
