package com.iteratrlearning.shu_book.chapter_03.B;

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
                        final BankStatementParser bankStatementParser) throws IOException {
        /** ---------------------------------------------------------------- */
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);
        /** ---------------------------------------------------------------- */

        /** ---------------------------------------------------------------- */
        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessorV2 bankStatementProcessorV2 = new BankStatementProcessorV2(bankTransactions);
        /** ---------------------------------------------------------------- */

        List<BankTransaction> transactions =
                bankStatementProcessorV2.findTransactions(bunkTransaction -> bunkTransaction.getAmount() > 1000);

        System.out.println(transactions);
    }
}
