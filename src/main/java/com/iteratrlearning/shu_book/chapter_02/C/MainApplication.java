package com.iteratrlearning.shu_book.chapter_02.C;

import com.iteratrlearning.shu_book.chapter_02.B.BankStatementCSVParser;
import com.iteratrlearning.shu_book.chapter_02.B.BankStatementParser;

public class MainApplication {

    public static void main(String[] args) throws Exception {

        final BankStatementAnalyzer bankStatementAnalyzer
                = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser
                = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser);
    }
}
