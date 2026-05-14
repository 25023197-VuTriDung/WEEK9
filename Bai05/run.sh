#!/bin/bash

# Dừng script nếu có lỗi
set -e

echo "--- Đang chạy Bài 05: Calculator & Tests ---"

# 1. Biên dịch và chạy toàn bộ unit test
# Maven sẽ tự động kiểm tra logic của Calculator thông qua CalculatorTest
mvn test