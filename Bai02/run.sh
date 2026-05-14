#!/bin/bash

# Dừng script nếu có lỗi
set -e

echo "Cleaning and compiling project..."
# Dùng mvn trực tiếp vì máy bạn đã nhận lệnh mvn rồi
mvn clean compile

echo "Running application..."
# Đổi class chính thành bank_system.Main
mvn exec:java -Dexec.mainClass="bank_system.Main"