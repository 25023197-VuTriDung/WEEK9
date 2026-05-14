package com.lab;

public class ShippingCalculator {
    public double calculate(double weight, String type) {
        // Thêm kiểm tra lỗi để nộp bài
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }

        if ("STANDARD".equalsIgnoreCase(type)) {
            return weight * 3000;
        } else if ("EXPRESS".equalsIgnoreCase(type)) {
            return weight * 5000 + 20000;
        }
        return 0;
    }
}