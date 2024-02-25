package com.iteratrlearning.shu_book.chapter_06;

import com.iteratrlearning.shu_book.chapter_06.database.DatabaseUserRepository;

public class DatabaseUserRepositoryTest extends AbstractUserRepositoryTest
{
    @Override
    protected UserRepository newRepository()
    {
        return new DatabaseUserRepository();
    }
}
