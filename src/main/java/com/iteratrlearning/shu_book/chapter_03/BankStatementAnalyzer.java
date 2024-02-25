package com.iteratrlearning.shu_book.chapter_03;

import com.iteratrlearning.shu_book.chapter_03.C.BankStatementProcessorV3;
import com.iteratrlearning.shu_book.chapter_03.C.Exporter;
import com.iteratrlearning.shu_book.chapter_03.C.SummaryStatistics;
import com.iteratrlearning.shu_book.chapter_03.common.BankStatementParser;
import com.iteratrlearning.shu_book.chapter_03.common.BankTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final String fileName,
                        final BankStatementParser bankStatementParser,
                        final Exporter exporter) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankStatementProcessorV3 bankStatementProcessorV3 = new BankStatementProcessorV3(bankTransactions);

        final SummaryStatistics summaryStatistics = bankStatementProcessorV3.summarizeTransactions();

        System.out.println(exporter.export(summaryStatistics));
    }
}
