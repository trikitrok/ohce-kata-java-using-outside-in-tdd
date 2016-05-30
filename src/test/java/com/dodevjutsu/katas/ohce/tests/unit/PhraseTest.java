package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.Phrase;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PhraseTest {
    @Test
    public void reverses_itself() {
        Phrase phrase = new Phrase("koko");

        assertThat(phrase.reverse(), is(new Phrase("okok")));
    }

    @Test
    public void knows_if_it_is_a_palindrome() {
        assertThat(new Phrase("koko").isPalindrome(), is(false));
        assertThat(new Phrase("ana").isPalindrome(), is(true));
    }
}
