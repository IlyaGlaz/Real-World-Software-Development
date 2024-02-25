package com.iteratrlearning.shu_book.chapter_06;

import com.iteratrlearning.shu_book.chapter_06.database.DatabaseTwootRepository;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class DatabaseTwootRepositoryTest extends AbstractTwootRepositoryTest {

    @BeforeEach
    void setUp() throws IOException {
        repository = new DatabaseTwootRepository();
    }
}
