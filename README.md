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

## Functional Description
---

- 3 Role: client, admin, shipper
- Vài trò client:
    + Đăng nhập/đăng ký
    + Thay đổi thông tin cá nhân
    + Xem tất cả sản phẩm(theo từng thể loại, hãng)
    + Search sản phẩm theo tên và mô tả sản phẩm
    + 3 thể loại: laptop, smartphone, accessory
    + Xem chi tiết sản phẩm(mô tả cấu hình)
    + Thêm comment
    + Mua sản phẩm
    + Thêm vào giỏ hàng(checkout 1 hoặc nhiều sản phẩm)
    + Xem thông tin trạng thái đơn hàng
    + Khi đơn hàng đã giao có thể gửi đánh giá
- Vài trò admin:
    + Dashboard(xem được quantity đã bán, amount, view sl theo từng trạng thái đơn hàng và top những sản phẩm bán chạy
      nhất)
    + CRUD thể loại
    + CRUD hãng
    + CRUD product
    + Xem được tất cả order(khi client đặt hàng admin sẽ xác nhận đơn hàng phân công cho shipper và chuyển đến cho role shipper)
    + Quản lý bình luận, đánh giá
    + Quản lý account
    + Xử dụng websocket làm real time cho thông báo khi người dùng mua hàng
- Vài trò shipper:
    + Tiếp nhận các đơn hàng mà admin đã nhận đơn(sau đó tiến hành giao hàng..)
