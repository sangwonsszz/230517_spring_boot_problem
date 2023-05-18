package io.playdata.spring_boot_test.controller;

import io.playdata.spring_boot_test.model.Account;
import io.playdata.spring_boot_test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AuthController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/fail")
    public String showLoginFail(Model model) {
        model.addAttribute("msg", "잘못된 로그인");
        return "error";
    }

    @GetMapping("/denied")
    public String showAccessDenied(Model model) {
        model.addAttribute("msg", "접근이 거부되었습니다.");
        return "error";
    }

    @Autowired
    private AccountService accountService;

    @GetMapping("/join")
    public String showJoinForm(Model model) {
        model.addAttribute("account", new Account());
        return "join";
    }

    @PostMapping("/join")
    public String saveAccount(@ModelAttribute("account") Account account) throws Exception {
        accountService.saveAccount(account);
        return "redirect:/login";
    }

    @ExceptionHandler
    public String error(Exception ex, Model model) {
        model.addAttribute("msg", ex.getMessage());
        return "error";
    }
}