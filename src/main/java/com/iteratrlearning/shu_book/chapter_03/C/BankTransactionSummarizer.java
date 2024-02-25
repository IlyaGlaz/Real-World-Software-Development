package com.iteratrlearning.shu_book.chapter_03.C;

import com.iteratrlearning.shu_book.chapter_03.common.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction bankTransaction);
}

