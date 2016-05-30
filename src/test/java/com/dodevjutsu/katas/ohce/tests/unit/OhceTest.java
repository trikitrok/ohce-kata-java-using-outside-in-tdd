package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class OhceTest {

    private Mockery context;
    private Notifier notifier;
    private PhraseReader phraseReader;
    private GreetingsSelector greetingsSelector;
    private Ohce ohce;
    private String userName;
    private String greeting;


    @Before
    public void setUp() {
        context = new Mockery();
        greetingsSelector = context.mock(GreetingsSelector.class);
        phraseReader = context.mock(PhraseReader.class);
        notifier = context.mock(Notifier.class);
        userName = "Koko";
        greeting = "¡Buenas días Koko!";
        ohce = new Ohce(userName, greetingsSelector, phraseReader, notifier);
    }

    @Test
    public void greets_the_user() {
        context.checking(new Expectations() {{
            oneOf(phraseReader).read();
            will(returnValue(new Phrase("not used")));

            oneOf(greetingsSelector).greetingFor(userName);
            will(returnValue(greeting));

            oneOf(notifier).greetUser(greeting);
            ignoring(notifier);
        }});

        ohce.run();

        context.assertIsSatisfied();
    }

    @Test
    public void reverses_non_palindrome_phrases() {
        Phrase nonPalindromePhrase = new Phrase("mola mola");
        Phrase reversedPhrase = new Phrase("alom alom");
        context.checking(new Expectations() {{
            oneOf(phraseReader).read();
            will(returnValue(nonPalindromePhrase));

            ignoring(greetingsSelector);

            oneOf(notifier).notifyReversedPhrase(reversedPhrase);
            ignoring(notifier);
        }});

        ohce.run();

        context.assertIsSatisfied();
    }

    @Test
    public void expresses_that_it_likes_palindromes() {
        Phrase palindrome = new Phrase("larutanatural");
        context.checking(new Expectations() {{
            oneOf(phraseReader).read();
            will(returnValue(palindrome));

            ignoring(greetingsSelector);

            oneOf(notifier).notifyPalindromesRock();
            ignoring(notifier);
        }});

        ohce.run();

        context.assertIsSatisfied();
    }
}
