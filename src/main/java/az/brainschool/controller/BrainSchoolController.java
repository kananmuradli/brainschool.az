package az.brainschool.controller;

import az.brainschool.mail.SendEmail;
import az.brainschool.model.User;
import az.brainschool.service.abstracts.BrainSchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BrainSchoolController {

    private final BrainSchoolService brainSchoolService;

    private SendEmail sendEmail;

    public BrainSchoolController(BrainSchoolService brainSchoolService, SendEmail sendEmail) {
        this.brainSchoolService = brainSchoolService;
        this.sendEmail = sendEmail;
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    //burada biz elaqe sehifesinde gonder knobkasina basdiqda yonlendirilen controller metodudur
    @PostMapping("/save-answer")
    public String saveAnswer(@ModelAttribute(name = "user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/contact";
        }
        sendEmail.sendEmail(user.getEmail(), user.getQuestion());
        return "redirect:/contact";
    }

}
