package com.iteratrlearning.shu_book.chapter_06;

import com.iteratrlearning.shu_book.chapter_06.in_memory.InMemoryTwootRepository;
import org.junit.jupiter.api.BeforeEach;

public class InMemoryTwootRepositoryTest extends AbstractTwootRepositoryTest
{
    @BeforeEach
    public void setUp()
    {
        repository = new InMemoryTwootRepository();
    }
}
