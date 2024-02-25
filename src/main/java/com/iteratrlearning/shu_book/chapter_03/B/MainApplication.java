package com.iteratrlearning.shu_book.chapter_03.B;

import com.iteratrlearning.shu_book.chapter_03.C.Exporter;
import com.iteratrlearning.shu_book.chapter_03.C.HtmlExporter;
import com.iteratrlearning.shu_book.chapter_03.common.BankStatementCSVParser;
import com.iteratrlearning.shu_book.chapter_03.common.BankStatementParser;

public class MainApplication {

    public static void main(final String[] args) throws Exception {

        final BankStatementAnalyzer bankStatementAnalyzer
                = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser
                = new BankStatementCSVParser();

        final Exporter exporter = new HtmlExporter();

        bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser);
    }
}
