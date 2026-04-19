1. Tại sao thông báo lỗi đăng nhập phải lưu vào Request Scope mà không phải Session Scope?

Lý do: Request Scope chỉ tồn tại trong một chu kỳ (vòng đời) duy nhất từ lúc Client gửi yêu cầu đến lúc Server trả về kết quả. Thông báo lỗi (như "Sai mật khẩu") là tin nhắn mang tính thời vụ, chỉ cần hiển thị một lần.

Hậu quả nếu dùng sai: Nếu lưu vào Session Scope, thông báo lỗi sẽ bám theo người dùng suốt cả phiên làm việc. Trừ khi bạn viết code để xóa nó đi (xóa thủ công), nếu không, khi người dùng tải lại trang (F5) hoặc vô tình quay lại trang đăng nhập, dòng chữ "Sai mật khẩu" vẫn chễm chệ ở đó dù họ chưa hề gõ sai lần hai.

2. Tại sao totalViewCount phải lưu vào Application Scope?

Lý do: Application Scope (đại diện bởi ServletContext) là không gian bộ nhớ dùng chung cho toàn bộ ứng dụng và tất cả người dùng. Để đếm tổng số lượt xem của cả hệ thống, biến đếm này phải được đặt ở nơi mọi nhân viên đều có thể truy cập và cộng dồn.

Hậu quả nếu dùng Session Scope: Session Scope được cấp phát riêng biệt cho từng trình duyệt/người dùng. Nếu lưu bộ đếm vào Session, Nhân viên A sẽ thấy lượt xem của riêng họ (ví dụ: 5), Nhân viên B vào máy khác sẽ thấy lượt xem bắt đầu lại từ đầu (1, 2, 3...). Hệ thống sẽ không thể có con số tổng hợp.

3. Race Condition là gì và cách khắc phục?

Phân tích đoạn code lỗi:

Race Condition (Điều kiện tương tranh) xảy ra khi có từ 2 luồng (Thread - tương ứng với 2 nhân viên) cùng truy cập và thay đổi một biến chia sẻ (Application scope) cùng một lúc.

Giả sử totalViewCount đang là 10. Nhân viên A và B truy cập trang /orders vào cùng một phần nghìn giây. Luồng A đọc biến là 10. Luồng B cũng đọc biến là 10. Luồng A cộng 1 thành 11 và lưu lại. Luồng B cũng cộng 1 thành 11 và lưu lại (đè lên số của A). Kết quả là 11, trong khi thực tế phải là 12. Dữ liệu đã bị thất thoát.

Đề xuất phòng tránh: Bọc đoạn code xử lý bộ đếm vào một khối đồng bộ hóa synchronized hoặc sử dụng các biến an toàn luồng (Thread-safe) như AtomicInteger.