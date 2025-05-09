package day6.은행계좌이체시스템;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private boolean locked;
    private TransactionLogger logger;

    public BankAccount(String accountHolder, double initialBalance) {
        this();
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // 계좌 관련 기본 예외클래스 -> 모든 계좌 관련 예외의 부모클래스
    public static class BankAccountException extends Exception {
        public BankAccountException(String message) {
            super(message);
        }

        public BankAccountException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // 잔액부족
    @Getter
    public static class InsufficientBalanceException extends BankAccountException {
        private final double requested;
        private final double available;

        public InsufficientBalanceException(double requested, double available) {
            super(String.format("Insufficient balance. Requested: %.2f, Available: %.2f",
                    requested, available));
            this.requested = requested;
            this.available = available;
        }
    }

    // 유효하지 않은 송금액 예외 -> 0이하 금액이나 유효 하지않은 송금일 때
    public static class InvalidTransferAmountException extends BankAccountException {
        public InvalidTransferAmountException(String message) {
            super(message);
        }
    }

    // 계좌 잠금 예외 -> 계좌 잠겨있을 때 작업을 시도할 경우
    public static class AccountLockedException extends BankAccountException {
        public AccountLockedException(String message) {
            super("Account is locked: " + message);
        }
    }

    // 계좌 검증 예외 -> 유효하지않은 계좌로 작업을 시도할경우
    public static class AccountValidationException extends BankAccountException {
        public AccountValidationException(String message) {
            super(message);
        }
    }

    // 시스템 예외 -> 시스템 내부 오류발생시 -> 언체크 예외 (RuntimeException)
    public static class BankSystemException extends RuntimeException {
        public BankSystemException(String message) {
            super(message);
        }

        public BankSystemException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // 트랜잭션 로거 인터페이스 -> 트랜잭션 로깅을 위해 만든것.
    public interface TransactionLogger {
        void logTransaction(String message);
        void logError(String message, Throwable error);
    }

    public BankAccount() {
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
        this.locked = false;
        this.logger = new ConsoleTransactionLogger();
    }

    private class ConsoleTransactionLogger implements TransactionLogger {

        @Override
        public void logTransaction(String message) {
            System.out.println("[Transaction] "  + message);
        }

        @Override
        public void logError(String message, Throwable error) {
            System.out.println("[Error] " + message);
            if (error != null) {
                error.printStackTrace();
            }
        }
    }

    private String generateAccountNumber() {
        return "ACCT-" + UUID.randomUUID().toString().substring(0, 8);
    }

    // 입금
    public void deposit(double amount) throws InvalidTransferAmountException, AccountLockedException {
        try {
            // 입금액 검증
            if (amount <= 0) {
                throw new InvalidTransferAmountException("Deposit amount must be positive");
            }

            // 계좌 잠금확인
            if (locked) {
                throw new AccountLockedException(accountNumber);
            }

            // 입금 실행
            this.balance += amount;
            logger.logTransaction(String.format("Deposited %.2f to account %s. New balance: %.2f", amount, accountNumber, balance));
        } catch (InvalidTransferAmountException | AccountLockedException e) {
            // 예외를 먼저 로깅한 후 다시 던질 것
            logger.logError("Deposit failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            // 예상치 못한 에러
            logger.logError("Unexpected error during deposit: " + e.getMessage(), e);
            throw new BankSystemException("System error occurred during deposit operation", e);
        }
    }

    // 출금
    public void withdraw(double amount) throws InvalidTransferAmountException, AccountLockedException, InsufficientBalanceException{
        try {
            // 출금액 검증
            if (amount <= 0) {
                throw new InvalidTransferAmountException("Withdrawal amount must be positive");
            }

            // 계좌 잠금확인
            if (locked) {
                throw new AccountLockedException(accountNumber);
            }

            if (amount > balance) {
                throw new InsufficientBalanceException(amount, balance);
            }

            // 출금 실행
            this.balance -= amount;
            logger.logTransaction(String.format("Withdrew %.2f from account %s. New balance: %.2f", amount, accountNumber, balance));
        } catch (InvalidTransferAmountException | AccountLockedException | InsufficientBalanceException e) {
            // 예외를 먼저 로깅한 후 다시 던질 것
            logger.logError("Withdrawal failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            // 예상치 못한 에러
            logger.logError("Unexpected error during withdrawal: " + e.getMessage(), e);
            throw new BankSystemException("System error occurred during withdrawal operation", e);
        }
    }

    /**
     * 계좌 이체 메서드
     * @param recipient 수취인 계좌
     * @param amount 이체 금액
     * @throws AccountValidationException 유효하지 않은 수취인 계좌
     * @throws InvalidTransferAmountException 유효하지 않은 이체 금액
     * @throws InsufficientBalanceException 잔액 부족
     * @throws AccountLockedException 계좌 잠금 상태
     */
    public void transfer(BankAccount recipient, double amount)
            throws AccountValidationException, InvalidTransferAmountException,
            InsufficientBalanceException, AccountLockedException {
        // 트랜잭션 ID 생성
        String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8);

        try {
            // 이체 검증
            validateTransfer(recipient, amount);

            // 이체 실행
            executeTransfer(recipient, amount, transactionId);

            // 성공 로깅
            logger.logTransaction(String.format("Transfer completed: %.2f from %s to %s (Transaction ID: %s)",
                    amount, accountNumber, recipient.getAccountNumber(), transactionId));

        } catch (AccountValidationException | InvalidTransferAmountException |
                 InsufficientBalanceException | AccountLockedException e) {
            // 예외 로깅 후 다시 던지기(rethrow)
            logger.logError("Transfer failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            // 예상치 못한 예외 포장
            logger.logError("Unexpected error during transfer", e);
            throw new BankSystemException("System error occurred during transfer operation", e);
        } finally {
            // 트랜잭션 정리 작업 - 예외 발생 여부와 관계없이 항상 실행
            closeTransaction(transactionId);
        }
    }

    /**
     * 이체 검증 메서드
     */
    private void validateTransfer(BankAccount recipient, double amount)
            throws AccountValidationException, InsufficientBalanceException,
            InvalidTransferAmountException, AccountLockedException {
        // 수취인 계좌 검증
        if (recipient == null) {
            throw new AccountValidationException("Recipient account cannot be null");
        }

        if (this.accountNumber.equals(recipient.getAccountNumber())) {
            throw new AccountValidationException("Cannot transfer to the same account");
        }

        // 송금액 검증
        if (amount <= 0) {
            throw new InvalidTransferAmountException("Transfer amount must be positive");
        }

        // 계좌 잠금 확인
        if (this.locked) {
            throw new AccountLockedException(this.accountNumber);
        }

        if (recipient.isLocked()) {
            throw new AccountLockedException(recipient.getAccountNumber());
        }

        // 잔액 확인
        if (amount > this.balance) {
            throw new InsufficientBalanceException(amount, this.balance);
        }
    }

    /**
     * 이체 실행 메서드
     */
    private void executeTransfer(BankAccount recipient, double amount, String transactionId) {
        // 실제 송금 처리 로직
        this.balance -= amount;
        recipient.balance += amount;

        logger.logTransaction(String.format("Transfer executed: %.2f from %s to %s (Transaction ID: %s)",
                amount, accountNumber, recipient.getAccountNumber(), transactionId));
    }

    /**
     * 트랜잭션 종료 메서드
     */
    private void closeTransaction(String transactionId) {
        try {
            // 트랜잭션 종료 처리, 리소스 정리 등
            logger.logTransaction("Transaction closed: " + transactionId);
        } catch (Exception e) {
            // 종료 과정에서의 예외는 로깅만 하고 전파하지 않음
            logger.logError("Error while closing transaction: " + transactionId, e);
        }
    }

    /**
     * 계좌 잠금 상태 확인
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * 계좌 잠금 메서드
     */
    public void lockAccount() {
        this.locked = true;
        logger.logTransaction("Account locked: " + accountNumber);
    }

    /**
     * 계좌 잠금 해제 메서드
     */
    public void unlockAccount() {
        this.locked = false;
        logger.logTransaction("Account unlocked: " + accountNumber);
    }

    /**
     * toString 메서드 오버라이드
     */
    @Override
    public String toString() {
        return String.format("BankAccount{number=%s, holder=%s, balance=%.2f, locked=%s}",
                accountNumber, accountHolder, balance, locked);
    }
}
