# README.md cho Auction System — Hệ Thống Đấu Giá Trực Tuyến

Dựa trên codebase được phân tích, dưới đây là file README.md có cấu trúc chi tiết:

```markdown
# Auction System — Hệ Thống Đấu Giá Trực Tuyến

Bài tập lớn môn **Lập Trình Nâng Cao** — Mô hình Client–Server, giao tiếp TCP Socket, giao diện JavaFX.

## 📋 Mô Tả Bài Toán

Hệ thống cho phép người dùng:
- **Đăng ký tài khoản** với vai trò Bidder (người mua) hoặc Seller (người bán)
- **Đăng sản phẩm lên đấu giá** (Electronics, Art, Vehicle)
- **Đặt giá thầu** theo thời gian thực với hỗ trợ auto-bidding
- **Thanh toán** sau khi phiên kết thúc
- **Admin duyệt** phiên đấu giá và quản lý người dùng

### Giao Tiếp Mạng
- **TCP Socket** (port 1234): Truyền dữ liệu chính và realtime
- **UDP Broadcast** (port 8888): Client tự phát hiện Server trong mạng LAN *(nếu triển khai)*

---

## 🛠️ Công Nghệ & Môi Trường

| Thành phần | Chi tiết |
|-----------|---------|
| **Ngôn ngữ** | Java 21 |
| **Giao diện** | JavaFX 21.0.6 + FXML |
| **Build tool** | Apache Maven |
| **Cơ sở dữ liệu** | MySQL (local hoặc TiDB Cloud) |
| **Connection pool** | HikariCP 5.1.0 |
| **Mã hóa mật khẩu** | PBKDF2 (PasswordHasher) |
| **Kiểm thử** | JUnit 5.10 + Mockito 5.x |

### Yêu Cầu Cài Đặt
- JDK 21+
- Apache Maven 3.8+
- MySQL 5.7+ hoặc TiDB Cloud
- Kết nối Internet (nếu dùng TiDB Cloud)

---

## 📁 Cấu Trúc Thư Mục

```
Baitaplon_LTNC/
├── pom.xml                          ← Parent POM (single module)
│
├── src/main/java/com/biddingapp/
│   ├── client/
│   │   ├── view/
│   │   │   ├── Launcher.java        ← Entry point (khởi tạo SocketClient)
│   │   │   └── HelloApplication.java ← JavaFX Application
│   │   ├── controller/
│   │   │   ├── LoginController.java
│   │   │   ├── RegisterController.java
│   │   │   ├── AdminController.java
│   │   │   ├── SellerController.java
│   │   │   ├── BidderController.java
│   │   │   └── HelloController.java  ← Controller cũ (demo)
│   │   ├── helper/
│   │   │   └── ModernUI.java        ← UI utilities
│   │   └── SocketClient.java        ← TCP Socket client
│   │
│   ├── sever/                       ← ⚠️ Tên folder bị sai chính tả (server)
│   │   ├── AuctionSever.java        ← TCP Socket server
│   │   └── service/
│   │       ├── BiddingService.java
│   │       ├── AutoBiddingEngine.java
│   │       └── AuctionExpiryTask.java
│   │
│   ├── service/
│   │   ├── DemoDataStore.java       ← In-memory data store
│   │   ├── AuctionService.java      ← Business logic
│   │   ├── WalletService.java       ← Quản lý ví
│   │   ├── OrderService.java        ← Quản lý đơn hàng
│   │   ├── NotificationService.java ← Thông báo
│   │   └── DomainMapper.java        ← Mapping utilities
│   │
│   ├── database/
│   │   ├── DatabaseConnection.java
│   │   ├── DatabaseSettings.java
│   │   ├── DatabaseStore.java
│   │   └── dao/
│   │       ├── UserDao.java
│   │       ├── ProductDao.java
│   │       ├── AuctionModelDao.java
│   │       ├── BidDao.java
│   │       ├── WalletDao.java
│   │       ├── OrderDao.java
│   │       └── ...
│   │
│   ├── model/
│   │   ├── User.java
│   │   ├── Product.java
│   │   ├── Auction.java
│   │   ├── Bid.java
│   │   ├── Wallet.java
│   │   ├── Order.java
│   │   ├── Notification.java
│   │   ├── DomainEnums.java
│   │   └── ...
│   │
│   └── common/
│       ├── NetworkConfig.java
│       └── security/
│           ├── AccountValidation.java
│           └── PasswordHasher.java
│
├── src/main/resources/com/biddingapp/client/view/
│   ├── Login-View.fxml
│   ├── Register-View.fxml
│   ├── Admin-View.fxml
│   ├── Seller-View.fxml
│   ├── Bidder-View.fxml
│   ├── Hello-View.fxml
│   ├── Dashboard.fxml
│   └── auction-pro.css
│
├── database/
│   ├── schema.sql               ← Script tạo CSDL
│   └── README.md                ← Hướng dẫn database
│
├── docs/
│   ├── codebase-walkthrough.md  ← Hướng dẫn đọc code
│   └── three-machine-runbook.md ← Chạy trên 3 máy
│
└── README.md                    ← File này



```

---

## 🚀 Hướng Dẫn Chạy

### Bước 1: Build Dự Án

Từ thư mục gốc, chạy:

```bash
mvn clean package -DskipTests
```

Lệnh này biên dịch source code và sinh ra file `.jar` trong thư mục `target/`.

**Nếu chưa có Maven:**
- **macOS**: `brew install maven`
- **Ubuntu/Debian**: `sudo apt install maven`
- **Windows**: Tải tại [maven.apache.org](https://maven.apache.org), giải nén và thêm vào PATH

### Bước 2: Tạo & Cấu Hình Database

#### 2a. Tạo Database

Chạy script SQL trong MySQL:

```sql
SOURCE database/schema.sql;
```

Hoặc nếu database đã tồn tại, xóa và tạo lại:

```sql
DROP DATABASE IF EXISTS biddingapp;
SOURCE database/schema.sql;
```

#### 2b. Cấu Hình Kết Nối (Tùy Chọn)

Mặc định kết nối:
```
jdbc:mysql://localhost:3306/biddingapp
user: root
password: rong
```

Để thay đổi, dùng environment variables:

```powershell
# Windows PowerShell
$env:BIDDINGAPP_DB_URL="jdbc:mysql://localhost:3306/biddingapp?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Bangkok"
$env:BIDDINGAPP_DB_USER="root"
$env:BIDDINGAPP_DB_PASSWORD="your_password"
```

Hoặc JVM properties:

```bash
-Dbiddingapp.db.url="..." -Dbiddingapp.db.user="..." -Dbiddingapp.db.password="..."
```

### Bước 3: Chạy Server

Từ thư mục gốc, chạy class `AuctionSever`:

```bash
java -cp target/classes com.biddingapp.sever.AuctionSever
```

Hoặc chạy từ IDE bằng cách chọn class `AuctionSever.java` và Run.

**Khi thành công, console hiển thị:**
```
=== AUCTION SERVER RUNNING ON PORT 1234 ===
[SERVER] Waiting for clients...
```

### Bước 4: Chạy Client (JavaFX App)

Mở terminal khác, từ thư mục gốc:

```bash
mvn javafx:run
```

Hoặc chạy từ IDE bằng cách chọn class `Launcher.java` → Run.

**Khi thành công, cửa sổ Login hiển thị.**

---

## 📝 Tài Khoản Demo

Sau khi chạy `schema.sql`, có 3 tài khoản seed:

| Username | Password | Role | Vai Trò |
|----------|----------|------|---------|
| `admin` | `123` | ADMIN | Quản trị viên |
| `seller` | `123` | SELLER | Người bán |
| `bidder` | `123` | BIDDER | Người mua |

> **Lưu ý**: Mật khẩu `123` được tự động nâng cấp sang PBKDF2 sau lần đăng nhập thành công đầu tiên.

---

## ✨ Danh Sách Chức Năng Đã Hoàn Thành

### 👤 Người Dùng
- ✅ Đăng ký / Đăng nhập / Đăng xuất
- ✅ Xem & chỉnh sửa thông tin cá nhân
- ✅ Cập nhật thông tin ngân hàng (số tài khoản, tên ngân hàng)
- ✅ Nạp số dư tài khoản

### 🛍️ Sản Phẩm & Đấu Giá
- ✅ Đăng sản phẩm lên đấu giá (3 loại: Electronics, Art, Vehicle)
- ✅ Xem danh sách phiên đấu giá đang mở / đang diễn ra
- ✅ Xem chi tiết phiên đấu giá (giá hiện tại, thời gian còn lại, lịch sử đặt giá)
- ✅ Đặt giá thầu thủ công (manual bid)
- ✅ Đặt giá thầu tự động (auto bid — tự động tăng đến mức tối đa)
- ✅ Theo dõi phiên đấu giá (watch/unwatch)
- ✅ Xem danh sách sản phẩm đã đăng & giá thầu của tôi

### 💳 Thanh Toán & Thông Báo
- ✅ Xử lý thanh toán sau khi phiên đấu giá kết thúc
- ✅ Hệ thống thông báo trong app (realtime)

### 🔐 Quản Trị (Admin)
- ✅ Dashboard thống kê (người dùng, phiên đấu giá, doanh thu)
- ✅ Duyệt / Từ chối phiên đấu giá chờ duyệt
- ✅ Quản lý toàn bộ phiên đấu giá
- ✅ Quản lý người dùng (khóa / mở tài khoản)

### 🚀 Tính Năng Nâng Cao
- ✅ **Auto-bidding**: Đặt giá tự động với maxBid + stepIncrement (Strategy pattern)
- ✅ **Anti-sniping**: Tự động gia hạn 60 giây khi có bid vào cuối phiên
- ✅ **Biểu đồ lịch sử giá realtime**: JavaFX LineChart + Observer pattern
- ✅ **Concurrency an toàn**: ReentrantLock theo từng phiên, tránh race condition
- ✅ **Socket realtime**: Broadcast message đến tất cả client kết nối

---

## 🏗️ Kiến Trúc Hệ Thống

### Luồng Chạy Tổng Quát

```
Người dùng thao tác trên JavaFX
        ↓
Controller xử lý sự kiện
        ↓
Service xử lý nghiệp vụ
        ↓
DemoDataStore / DAO / MySQL
        ↓
SocketClient gửi lệnh realtime
        ↓
AuctionSever nhận lệnh và broadcast
        ↓
Các màn hình Admin / Seller / Bidder cập nhật UI
```

### Design Pattern Sử Dụng
- **Singleton**: `DemoDataStore`, `DatabaseStore`, `SocketClient`
- **Factory Method**: `ItemFactory` (Art, Electronics, Vehicle)
- **Observer**: Notification system, realtime updates
- **Strategy**: Auto-bidding engine
- **DAO**: Data Access Object pattern cho database

### Giao Tiếp Socket

#### Các Lệnh Client → Server
```
LOGIN;username;password
REGISTER;username;password;fullName;email;role
POST_PRODUCT;name;price;step;minutes;image;[buyNowPrice]
BID_REQUEST;productName;amount
AUTO_BID;productName;maxAmount
APPROVE;productName
REJECT;productName
RELOAD
DEPOSIT_REQUEST;amount
ORDER_UPDATE;productName;paymentStatus;shippingStatus;orderStatus
```

#### Các Message Server → Client
```
LOGIN_SUCCESS;userId;username;role
REGISTER_SUCCESS;username;role
ADD;name;price;remainingSeconds;image;approved;startTime;endTime;bidStep;seller;status
PRICE_UPDATE;productName;amount;bidder;bidCount
APPROVED;productName
REJECT;productName
TIME;productName;remainingSeconds
BALANCE_UPDATE;username;balance
AUCTION_FINISH;productName;winner;phone;address;price;image
ERROR;message
```

---

## 📊 Cơ Sở Dữ Liệu

### Các Bảng Chính

| Bảng | Mục Đích |
|------|----------|
| `users` | Lưu thông tin người dùng (Admin, Seller, Bidder) |
| `categories` | Danh mục sản phẩm |
| `products` | Thông tin sản phẩm |
| `auctions` | Phiên đấu giá |
| `bids` | Lịch sử đặt giá |
| `wallets` | Ví tiền người dùng |
| `wallet_transactions` | Lịch sử giao dịch ví |
| `orders` | Đơn hàng sau đấu giá |
| `notifications` | Thông báo hệ thống |
| `admin_logs` | Nhật ký hành động admin |

### Xem Sơ Đồ Database

Chạy lệnh SQL để xem cấu trúc:

```sql
USE biddingapp;
DESCRIBE users;
DESCRIBE auctions;
DESCRIBE bids;
-- ... etc
```

---

## 🔧 Cấu Hình & Tùy Chỉnh

### Tắt/Bật Database

Mặc định app dùng MySQL. Để chạy demo data trong RAM:

```powershell
$env:BIDDINGAPP_USE_DATABASE="false"
```

Hoặc JVM option:

```bash
-Dbiddingapp.useDatabase=false
```

### Thay Đổi Port Server

Mặc định port là `1234`. Để thay đổi:

```powershell
$env:BIDDINGAPP_SERVER_PORT="5000"
```

Hoặc chỉnh sửa `NetworkConfig.java`:

```java
public static int serverPort() {
    return Integer.parseInt(System.getProperty("biddingapp.server.port", "1234"));
}
```

---

## 🐛 Troubleshooting

### Lỗi: "Không kết nối được Server"

**Nguyên nhân**: Server chưa chạy hoặc port bị chiếm.

**Giải pháp**:
1. Kiểm tra Server đang chạy: `AuctionSever.java`
2. Kiểm tra port 1234 có bị chiếm: `netstat -an | grep 1234` (macOS/Linux) hoặc `netstat -ano | findstr :1234` (Windows)
3. Thay port khác nếu cần

### Lỗi: "Không kết nối được MySQL"

**Nguyên nhân**: MySQL chưa chạy hoặc cấu hình sai.

**Giải pháp**:
1. Kiểm tra MySQL đang chạy
2. Kiểm tra credentials (user, password) trong `DatabaseSettings.java`
3. Kiểm tra database `biddingapp` đã được tạo: `SHOW DATABASES;`

### Lỗi: "FXML file not found"

**Nguyên nhân**: File FXML không ở đúng thư mục resources.

**Giải pháp**:
1. Kiểm tra file `.fxml` nằm trong `src/main/resources/com/biddingapp/client/view/`
2. Rebuild project: `mvn clean package`

---

## 📚 Tài Liệu Thêm

- **Hướng Dẫn Đọc Code**: Xem `docs/codebase-walkthrough.md`
- **Chạy Trên 3 Máy**: Xem `docs/three-machine-runbook.md`
- **Database Setup**: Xem `database/README.md`

---

## 🎯 Lộ Trình Phát Triển Tiếp Theo

Những phần vẫn cần cải thiện:

1. **Chuyển AuctionSever từ RAM sang MySQL**
   - Hiện tại inventory lưu trong RAM, mất dữ liệu khi restart
   - Cần đọc/ghi products, auctions, bids từ database

2. **Xóa DemoDataStore**
   - Controller vẫn phụ thuộc trực tiếp vào `DemoDataStore`
   - Cần refactor để dùng Service/DAO

3. **Sửa tên package `sever` → `server`**
   - Tên folder bị sai chính tả
   - Cần cập nhật tất cả import statements

4. **Hỗ trợ Upload Ảnh Lên Server**
   - Hiện tại ảnh lưu dạng Base64 trong database
   - Nên upload lên Cloudinary hoặc file server

5. **Nâng Cấp Protocol Socket sang JSON**
   - Hiện tại dùng delimiter `;` (dễ bị lỗi)
   - Nên dùng JSON để an toàn hơn

---

## 📄 License

Bài tập lớn môn Lập Trình Nâng Cao - Đại học [Tên Trường]

---

## 👥 Tác Giả

**Nhóm 9** - Lớp [Tên Lớp]

---

## 📞 Hỗ Trợ

Nếu gặp vấn đề, vui lòng:
1. Kiểm tra `docs/codebase-walkthrough.md`
2. Xem console log của Server và Client
3. Kiểm tra database connection settings
4. Liên hệ nhóm phát triển

---

**Cập nhật lần cuối**: 2024
```

---

## 📌 Ghi Chú Quan Trọng

### 1. **Cấu Trúc Thư Mục Thực Tế**

Dựa trên codebase, dự án sử dụng **single module Maven** (không phải multi-module như trong yêu cầu gốc). Tất cả code nằm trong:
```
src/main/java/com/biddingapp/
```

### 2. **Tên Package Bị Sai Chính Tả**

Package server được đặt tên là `sever` (thiếu 'r'). Điều này xuất hiện trong:
- `src/main/java/com/biddingapp/sever/AuctionSever.java`
- `src/main/java/com/biddingapp/sever/service/`

### 3. **Giao Tiếp Socket**

Dự án sử dụng **TCP Socket trên port 1234** (không phải 8080 như trong yêu cầu gốc). Delimiter là dấu `;` để phân tách các trường trong message.

### 4. **Database Connection**

Mặc định kết nối MySQL local:
```
jdbc:mysql://localhost:3306/biddingapp
user: root
password: rong
```

### 5. **Chế Độ Demo**

Có thể tắt database và chạy demo data trong RAM bằng environment variable:
```
BIDDINGAPP_USE_DATABASE=false
```

---

File README.md trên đã được tạo dựa trên **phân tích chi tiết codebase** và sẵn sàng để sử dụng trong dự án.
