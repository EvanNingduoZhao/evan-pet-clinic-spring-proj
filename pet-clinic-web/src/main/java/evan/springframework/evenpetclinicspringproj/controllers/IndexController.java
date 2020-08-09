package evan.springframework.evenpetclinicspringproj.controllers;

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
}
