package com.lab;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShippingCalculatorTest {
    @Test
    public void testCalculate() {
        ShippingCalculator calc = new ShippingCalculator();

        // 5kg Standard: 5 * 3000 = 15000
        assertEquals(15000.0, calc.calculate(5, "STANDARD"));

        // 5kg Express: 5 * 5000 + 20000 = 45000
        assertEquals(45000.0, calc.calculate(5, "EXPRESS"));
    }
}