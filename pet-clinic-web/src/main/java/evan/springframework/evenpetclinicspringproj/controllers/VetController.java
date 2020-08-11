package evan.springframework.evenpetclinicspringproj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {
    @RequestMapping({"/vets","/vets/index", "vets/index.html"})
    public String listVets(){
        System.out.println("somethings");
        return "vets/index";
    }
}
