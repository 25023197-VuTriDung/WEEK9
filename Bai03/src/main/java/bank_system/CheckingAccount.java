package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản vãng lai.
 */
public class CheckingAccount extends Account {
    private static final Logger logger = LoggerFactory.getLogger(CheckingAccount.class);

    public CheckingAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();
            Transaction t = new Transaction(
                    Transaction.TYPE_DEPOSIT_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(t);
            logger.info("Nap tien vao tai khoan {} thanh cong", getAccountNumber());
        } catch (Exception e) { // Sửa lỗi Unhandled Exception tại đây
            logger.error("Loi nap tien: {}", e.getMessage());
        }
    }

    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            doWithdrawing(amount);
            double finalBalance = getBalance();
            Transaction t = new Transaction(
                    Transaction.TYPE_WITHDRAW_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(t);
            logger.info("Rut tien tu tai khoan {} thanh cong", getAccountNumber());
        } catch (Exception e) { // Phải bắt Exception chung vì doWithdrawing có 'throws Exception'
            logger.error("Loi rut tien: {}", e.getMessage());
        }
    }
}