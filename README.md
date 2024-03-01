# Framework
---

- Java 11+
- Spring Boot 2.7.x
- Spring Data JPA
- Spring Web
- Spring redis
- Spring cache
- spring websocket
- Spring Security by jwt
- Send mail
- MySql
- Paypal

## Functional Description
---

- Có sử dụng redis làm thư viện, để cho người dùng có thể truy cập web nhanh hơn thay vì phải truy vấn thẳng từ trong
  sql
- 3 Role: client, admin, shipper
- Vài trò client:
    + Đăng nhập/đăng ký
    + Thay đổi thông tin cá nhân
    + Xem tất cả sản phẩm(theo từng thể loại, hãng)
    + Search sản phẩm theo tên và mô tả sản phẩm
    + 3 thể loại: laptop, smartphone, accessory
    + Xem chi tiết sản phẩm(mô tả cấu hình)
    + Thêm comment
    + Mua sản phẩm(Tích hợp thanh toán online bằng Paypal)
    + Thêm vào giỏ hàng
    + Xem thông tin trạng thái đơn hàng
    + Khi đơn hàng đã giao có thể gửi đánh giá
- Vài trò admin:
    + Dashboard(xem được quantity đã bán, amount, view sl theo từng trạng thái đơn hàng và top những sản phẩm bán chạy
      nhất)
    + CRUD thể loại
    + CRUD hãng
    + CRUD product
    + Xem được tất cả order(khi client đặt hàng admin sẽ xác nhận đơn hàng và chuyển đến cho role shipper)
    + Quản lý bình luận, đánh giá
    + Quản lý account
    + Xử dụng websocket làm real time cho thông báo khi người dùng mua hàng
- Vài trò shipper:
    + Tiếp nhận các đơn hàng mà admin đã xác nhận(sau đó tiến hành giao hàng..)
  
