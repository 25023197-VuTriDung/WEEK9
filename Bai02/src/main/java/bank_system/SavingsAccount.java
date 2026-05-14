package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản tiết kiệm - Thực thi các quy định về rút tiền và nạp tiền.
 */
public class SavingsAccount extends Account {
    private static final Logger logger = LoggerFactory.getLogger(SavingsAccount.class);

    public static final double MAX_WITHDRAW_LIMIT = 1000.0;
    public static final double MIN_BALANCE_REQUIRED = 5000.0;

    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();
        // Bọc trong try-catch để xử lý các Exception từ lớp cha
        try {
            doDepositing(amount);
            double finalBalance = getBalance();

            Transaction t = new Transaction(
                    Transaction.TYPE_DEPOSIT_SAVINGS, amount, initialBalance, finalBalance);
            addTransaction(t);

            logger.info("Nap tien vao tai khoan {} thanh cong: +{}", getAccountNumber(), amount);
        } catch (Exception e) {
            // Sửa lỗi Unhandled Exception bằng cách catch Exception chung hoặc cụ thể
            logger.error("Loi nap tien: {}", e.getMessage());
        }
    }

    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            if (amount > MAX_WITHDRAW_LIMIT) {
                throw new InvalidFundingAmountException(amount);
            }
            if (initialBalance - amount < MIN_BALANCE_REQUIRED) {
                throw new InsufficientFundsException(amount);
            }

            // DÒNG GÂY LỖI: doWithdrawing(amount) ném ra Exception
            doWithdrawing(amount);

            double finalBalance = getBalance();

            Transaction t = new Transaction(
                    Transaction.TYPE_WITHDRAW_SAVINGS, amount, initialBalance, finalBalance);
            addTransaction(t);

            logger.info("[SAVINGS] Rut {} thanh cong. So du con: {}", amount, finalBalance);
        } catch (Exception e) {
            // Cửa sổ lỗi sẽ biến mất khi bạn catch Exception ở đây
            logger.error("Rut tien bi loi: {}", e.getMessage());
        }
    }
}