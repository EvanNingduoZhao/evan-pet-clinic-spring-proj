package evan.springframework.evenpetclinicspringproj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 这里用的是另一种RequestMapping的方法
// 写一个class level的@RequestMapping的annotation就表示下面所以的RequestMapping
// 里的内容都是带着/owner是这个prefix的
@RequestMapping("/owners")
@Controller
public class OwnerController {
    //所以这里其实相当于@ResquestMapping({"/owners","/owners/index", "/owners/index.html"})
    @RequestMapping({"","/","/index", "/index.html"})
    public String listOwners(){
        return "owners/index";
    }
}
