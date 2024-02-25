package com.iteratrlearning.shu_book.chapter_03.A;

import com.iteratrlearning.shu_book.chapter_03.common.BankTransaction;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для вычисления статистики по банковским операциям
 */
public class BankStatementProcessorV1 {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessorV1(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {

                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    // --- Добавляем методы по поиску транзакций по разным критериям ---

    /**
     * Поиск транзакций на сумму больше заданной
     */
    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getAmount() >= amount) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    /**
     * Поиск транзакций в определенном месяце
     */
    public List<BankTransaction> findTransactionsInMonth(final Month month) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    /**
     * Поиск банковских операций на определенную сумму
     * и за определенный месяц
     */
    public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month,
                                                                   final int amount) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month && bankTransaction.
                    getAmount() >= amount) {
                result.add(bankTransaction);
            }
        }
        return result;
    }
}
