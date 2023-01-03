package onmera.cometa.controller;

import onmera.cometa.domain.User;
import onmera.cometa.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping("/")
    public String main(Model model,
                        @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile" , user);
        data.put("messages" , messageRepo.findAll());

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
