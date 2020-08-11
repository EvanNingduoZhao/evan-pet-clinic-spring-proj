package evan.springframework.evenpetclinicspringproj.controllers;

import evan.springframework.evanpetclinicspringproj.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 这里用的是另一种RequestMapping的方法
// 写一个class level的@RequestMapping的annotation就表示下面所以的RequestMapping
// 里的内容都是带着/owner是这个prefix的
@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //所以这里其实相当于@ResquestMapping({"/owners","/owners/index", "/owners/index.html"})
    @RequestMapping({"","/","/index", "/index.html"})
    public String listOwners(Model model){
        //在这个listOwners method被execute时
        // spring会自动create一个Model object作为这个method的parameter
        // 这个method会给model addAttribute, 这个被加上去的attribute的名字叫owners，
        // attribute的内容是这个OwnerController的那个class attribute, ownerService的findAll的结果
        // ownerService实际上是OwnerServiceMap这个class的一个instance，关于ownerService
        // 在DataLoader那个file的comment里已经讲的很细了
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
