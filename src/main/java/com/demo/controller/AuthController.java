package com.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        // Nếu đã đăng nhập thì không cho vào trang login nữa
        if (session.getAttribute("loggedUser") != null) {
            return "redirect:/orders";
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {
        // Hardcode kiểm tra tài khoản
        if (("admin".equals(username) && "admin123".equals(password)) ||
                ("staff".equals(username) && "staff123".equals(password))) {

            // LƯU VÀO SESSION SCOPE (Tồn tại xuyên suốt phiên làm việc)
            session.setAttribute("loggedUser", username);
            session.setAttribute("role", username.equals("admin") ? "Quản lý" : "Nhân viên");

            return "redirect:/orders";
        } else {
            // LƯU VÀO REQUEST SCOPE (Thông qua Model - chỉ tồn tại 1 lần)
            model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa toàn bộ Session
        session.invalidate();
        return "redirect:/login";
    }
}