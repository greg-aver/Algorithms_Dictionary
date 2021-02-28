package org.gregory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

class NativeDictionaryTest {
    NativeDictionary<Integer> dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new NativeDictionary<>(19, Integer.class);
    }

    @AfterEach
    void tearDown() {
        dictionary = null;
    }

    @Test
    void testHashCode() {
        assertThat(dictionary.hashCode("ab"), is(5));
        assertThat(dictionary.hashCode("ba"), is(5));
    }

    @Test
    void hashFunNoCollisions() {
        assertThat(dictionary.hashFun("ab"), is(5));
        assertThat(dictionary.hashFun("ba"), is(5));
    }

    @Test
    void hashFunCollisions() {
        assertThat(dictionary.hashFun("ab"), is(5));
        assertThat(dictionary.hashFun("ba"), is(5));
        dictionary.put("ab", 1);
        assertThat(dictionary.hashFun("ba"), is(8));
    }


    @Test
    void is_key() {
        dictionary.put("ab", 1);
        assertThat(dictionary.is_key("ab"), is(true));
        assertThat(dictionary.is_key("ba"), is(false));
        dictionary.put("ba", 2);
        assertThat(dictionary.is_key("ab"), is(true));
        assertThat(dictionary.is_key("ba"), is(true));
    }

    @Test
    void get() {
        assertThat(dictionary.get("ab"), nullValue());
        dictionary.put("ab", 1);
        assertThat(dictionary.get("ab"), is(1));
        assertThat(dictionary.get("ba"), nullValue());
        dictionary.put("ba", 2);
        assertThat(dictionary.get("ab"), is(1));
        assertThat(dictionary.get("ba"), is(2));
    }
}