package com.demo.controller;

import com.demo.model.Order;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    // Tiêm ServletContext để thao tác với Application Scope
    @Autowired
    private ServletContext application;

    @GetMapping("/orders")
    public String showOrders(HttpSession session, Model model) {
        // KIỂM TRA BẪY: Chưa đăng nhập -> đá về trang login
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        // TẠO DỮ LIỆU ĐƠN HÀNG (Giả lập DB)
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("ORD-001", "Laptop Dell XPS", 35000000, new Date()));
        orders.add(new Order("ORD-002", "Chuột Logitech G102", 450000, new Date()));
        orders.add(new Order("ORD-003", "Bàn phím cơ Akko", 1200000, new Date()));

        // Đẩy danh sách vào Request Scope để JSP vẽ ra
        model.addAttribute("orderList", orders);

        // GIẢI QUYẾT BẪY RACE CONDITION: Dùng synchronized khóa object application
        synchronized (application) {
            Integer count = (Integer) application.getAttribute("totalViewCount");
            if (count == null) {
                count = 0;
            }
            count++; // Tăng biến đếm
            // Cập nhật lại vào Application Scope
            application.setAttribute("totalViewCount", count);
        }

        return "orders";
    }
}