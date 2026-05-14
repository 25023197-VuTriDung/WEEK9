#!/bin/bash

# Dừng script nếu có lỗi
set -e

echo "--- Đang kiểm tra Bài 06 ---"

# 1. Biên dịch dự án để đảm bảo file pom.xml và cấu trúc src không lỗi
echo "Đang biên dịch..."
mvn compile

# 2. Thông báo hoàn tất
echo "------------------------------"
echo "Bài 06 đã được biên dịch thành công."