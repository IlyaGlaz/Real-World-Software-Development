package com.iteratrlearning.shu_book.chapter_02.B;

import java.util.List;

/**
 * Интерфейс для классов - парсеров
 */
public interface BankStatementParser {
    BankTransaction parseFrom(String line);

    List<BankTransaction> parseLinesFrom(List<String> lines);
}
