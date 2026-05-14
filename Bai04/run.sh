#!/bin/bash

# Dừng script nếu có lỗi
set -e

echo "--- Đang chạy Unit Test cho Bài 04 (PathTest) ---"

# 1. Dùng Maven để chạy các bài kiểm thử
# Maven sẽ tự động tìm các file có đuôi 'Test' trong thư mục src/test/java
mvn test