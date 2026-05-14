#!/bin/bash

# Dừng script nếu có lỗi
set -e

echo "--- Đang chạy Bài 03 (Bank System) ---"

# 1. Biên dịch dự án
mvn clean compile

echo "------------------------------"
echo "Đang thực thi chương trình..."

# 2. Chạy class Main trong package bank_system
mvn exec:java -Dexec.mainClass="bank_system.Main"