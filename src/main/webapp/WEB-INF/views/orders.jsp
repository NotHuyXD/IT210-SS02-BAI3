<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Thêm thư viện core (c) và format (fmt) --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Danh sách đơn hàng</title>
    <style>table, th, td { border: 1px solid black; border-collapse: collapse; padding: 8px; }</style>
</head>
<body>
    <%-- Lấy dữ liệu từ Session Scope --%>
    <h2>Xin chào, ${sessionScope.loggedUser}! Vai trò: ${sessionScope.role}</h2>

    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    <hr>

    <h3>Danh sách đơn hàng mới nhất</h3>
    <table>
        <tr>
            <th>Mã đơn</th>
            <th>Tên sản phẩm</th>
            <th>Tổng tiền</th>
            <th>Ngày đặt</th>
        </tr>

        <%-- Duyệt danh sách từ Request Scope --%>
        <c:forEach items="${requestScope.orderList}" var="o">
            <tr>
                <td>${o.id}</td>
                <td>${o.productName}</td>

                <%-- Format Tiền tệ VNĐ --%>
                <td><fmt:formatNumber value="${o.totalPrice}" type="currency" currencySymbol="VNĐ" maxFractionDigits="0"/></td>

                <%-- Format Ngày tháng --%>
                <td><fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm"/></td>
            </tr>
        </c:forEach>
    </table>

    <hr>
    <%-- Lấy dữ liệu đếm toàn cục từ Application Scope --%>
    <p><i>Tổng lượt xem đơn hàng toàn hệ thống: <b>${applicationScope.totalViewCount}</b> lượt</i></p>

</body>
</html>