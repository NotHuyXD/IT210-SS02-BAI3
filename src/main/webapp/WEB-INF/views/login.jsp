<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Đăng nhập hệ thống</title></head>
<body>
    <h2>Đăng nhập Hệ thống Đơn hàng</h2>

    <%-- Hiển thị lỗi từ Request Scope (Nếu có) --%>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        Username: <input type="text" name="username" required><br><br>
        Password: <input type="password" name="password" required><br><br>
        <button type="submit">Đăng nhập</button>
    </form>
</body>
</html>