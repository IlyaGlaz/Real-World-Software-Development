package com.iteratrlearning.shu_book.chapter_03.C;

import com.iteratrlearning.shu_book.chapter_03.B.BankTransactionFilter;
import com.iteratrlearning.shu_book.chapter_03.C.BankStatementProcessorV3;
import com.iteratrlearning.shu_book.chapter_03.common.BankTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

public class BankStatementProcessorV3Test {

    @Test
    public void shouldFilterTransactionsInFebruary() {

        final BankTransaction februarySalary
                = new BankTransaction(LocalDate.of(2019, Month.FEBRUARY, 14), 2_000, "Salary");

        final BankTransaction marchRoyalties
                = new BankTransaction(LocalDate.of(2019, Month.MARCH, 20), 500, "Royalties");

        final List<BankTransaction> bankTransactions
                = List.of(februarySalary,
                          marchRoyalties);

        final BankStatementProcessorV3 bankStatementProcessorV3 = new BankStatementProcessorV3(bankTransactions);
        final List<BankTransaction> transactions
                = bankStatementProcessorV3.findTransactions(new BankTransactionIsInFebruaryAndExpensive());

        assertThat(transactions, contains(februarySalary));
        assertThat(transactions, hasSize(1));
    }


    class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {
        @Override
        public boolean test(final BankTransaction bankTransaction) {
            return bankTransaction.getDate().getMonth() == Month.FEBRUARY && bankTransaction.getAmount() >= 1_000;
        }
    }
}
