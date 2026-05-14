package com.practice;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathTest {
    @Test
    public void testOSPathFixed() {
        // Paths.get giúp tự động đổi dấu / hoặc \ tùy theo Hệ điều hành
        Path safePath = Paths.get("target", "test-folder");

        System.out.println("Đang chạy trên hệ điều hành: " + System.getProperty("os.name"));
        System.out.println("Đường dẫn tạo ra: " + safePath.toString());

        // Kiểm tra xem đường dẫn có chứa chữ "target" không
        assertTrue(safePath.toString().contains("target"));
    }
}