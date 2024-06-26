package com.iteratrlearning.shu_book.chapter_06;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.iteratrlearning.shu_book.chapter_06.FollowStatus.ALREADY_FOLLOWING;
import static com.iteratrlearning.shu_book.chapter_06.TestData.OTHER_USER_ID;
import static com.iteratrlearning.shu_book.chapter_06.TestData.PASSWORD_BYTES;
import static com.iteratrlearning.shu_book.chapter_06.TestData.SALT;
import static com.iteratrlearning.shu_book.chapter_06.TestData.USER_ID;
import static com.iteratrlearning.shu_book.chapter_06.TestData.twootAt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Disabled
public abstract class AbstractUserRepositoryTest {
    private ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);
    private UserRepository repository;

    protected abstract UserRepository newRepository();

    @BeforeEach
    public void setUp() {
        repository = newRepository();

        repository.clear();
    }

    @Test
    public void shouldLoadSavedUsers() {
        repository.add(userWith(USER_ID));

        assertThat(repository.get(USER_ID).get(), matchesUser());
    }

    @Test
    public void shouldNotAllowDuplicateUsers() {
        assertTrue(repository.add(userWith(USER_ID)));

        assertFalse(repository.add(userWith(USER_ID)));
    }

    @Test
    public void shouldRecordFollowerRelationships() {
        final User user = userWith(USER_ID);
        final User otherUser = userWith(OTHER_USER_ID);

        repository.add(user);
        repository.add(otherUser);
        repository.follow(user, otherUser);

        final UserRepository reloadedRepository = newRepository();
        final User userReloaded = reloadedRepository.get(USER_ID).get();
        final User otherUserReloaded = reloadedRepository.get(OTHER_USER_ID).get();
        assertEquals(ALREADY_FOLLOWING, otherUserReloaded.addFollower(userReloaded));
    }

    @Test
    public void shouldRecordPositionUpdates() {
        final String id = "1";

        final Position newPosition = new Position(2);
        final User user = userWith(USER_ID);
        repository.add(user);
        assertEquals(Position.INITIAL_POSITION, user.getLastSeenPosition());

        user.receiveTwoot(twootAt(id, newPosition));
        repository.update(user);

        final UserRepository reloadedRepository = newRepository();
        final User reloadedUser = reloadedRepository.get(USER_ID).get();
        assertEquals(newPosition, user.getLastSeenPosition());
        assertEquals(newPosition, reloadedUser.getLastSeenPosition());
    }

    @AfterEach
    public void shutdown() throws Exception {
        repository.close();
    }

    private User userWith(final String userId) {
        final User user = new User(userId, PASSWORD_BYTES, SALT, Position.INITIAL_POSITION);
        user.onLogon(receiverEndPoint);
        return user;
    }

    private Matcher<User> matchesUser() {
        return allOf(
                hasProperty("id", equalTo(USER_ID)),
                hasProperty("password", equalTo(PASSWORD_BYTES)));
    }
}
