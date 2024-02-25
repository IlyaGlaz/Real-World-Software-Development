package com.iteratrlearning.shu_book.chapter_03.B;

import com.iteratrlearning.shu_book.chapter_03.common.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}