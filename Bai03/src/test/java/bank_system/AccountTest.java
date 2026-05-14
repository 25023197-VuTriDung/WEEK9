package bank_system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AccountTest {
    @Test
    public void testDeposit() {
        SavingsAccount account = new SavingsAccount(123L, 1000.0);
        account.deposit(500.0);
        // Kiểm tra xem số dư có đúng là 1500 không
        assertEquals(1500.0, account.getBalance(), "Nap tien phai tang so du");
    }

    @Test
    public void testWithdrawSuccess() {
        SavingsAccount account = new SavingsAccount(123L, 10000.0);
        account.withdraw(1000.0);
        // Kiểm tra xem số dư sau khi rút
        assertEquals(9000.0, account.getBalance(), "Rut tien phai tru so du");
    }
}