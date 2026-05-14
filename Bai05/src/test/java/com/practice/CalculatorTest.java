package com.practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        // Gọi hàm add để JaCoCo tính là đã "bao phủ" dòng đó
        assertEquals(5, calc.add(2, 3));
    }
}