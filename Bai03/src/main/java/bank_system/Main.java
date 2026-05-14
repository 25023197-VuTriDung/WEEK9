package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("He thong ngan hang dang khoi dong...");

        try {
            // Tao mot khach hang va tai khoan tiet kiem
            SavingsAccount savings = new SavingsAccount(123456789L, 10000.0);

            // Thu nap tien
            savings.deposit(5000.0);

            // Thu rut tien
            savings.withdraw(2000.0);

            logger.info("Cac giao dich da duoc thuc hien thanh cong!");

        } catch (Exception e) {
            logger.error("Da xay ra loi trong qua trinh van hanh: {}", e.getMessage());
        }
    }
}
