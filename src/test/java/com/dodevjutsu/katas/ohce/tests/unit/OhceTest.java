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
    private Phrase stopPhrase;


    @Before
    public void setUp() {
        context = new Mockery();
        greetingsSelector = context.mock(GreetingsSelector.class);
        phraseReader = context.mock(PhraseReader.class);
        notifier = context.mock(Notifier.class);
        String stopPhraseContent = "Stop!";
        stopPhrase = new Phrase(stopPhraseContent);
        userName = "Koko";
        greeting = "¡Buenas días Koko!";
        ohce = new Ohce(stopPhraseContent, greetingsSelector, phraseReader, notifier);
    }

    @Test
    public void greets_the_user() {
        context.checking(new Expectations() {{
            atLeast(1).of(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(new Phrase("not used")),
                returnValue(stopPhrase)
            ));

            oneOf(greetingsSelector).greetingFor(userName);
            will(returnValue(greeting));

            oneOf(notifier).greetUser(greeting);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void reverses_non_palindrome_phrases() {
        Phrase nonPalindromePhrase = new Phrase("mola mola");
        Phrase reversedPhrase = new Phrase("alom alom");
        context.checking(new Expectations() {{
            atLeast(1).of(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(nonPalindromePhrase),
                returnValue(stopPhrase)
            ));

            ignoring(greetingsSelector);

            oneOf(notifier).notifyReversedPhrase(reversedPhrase);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void expresses_that_it_likes_palindromes() {
        Phrase palindrome = new Phrase("larutanatural");
        context.checking(new Expectations() {{
            atLeast(1).of(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(palindrome),
                returnValue(stopPhrase)
            ));

            ignoring(greetingsSelector);

            oneOf(notifier).notifyPalindromesRock();
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void says_bye_when_is_told_to_stop() {
        Phrase stopPhrase = new Phrase("Stop!");
        context.checking(new Expectations() {{
            oneOf(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(stopPhrase)
            ));

            ignoring(greetingsSelector);

            oneOf(notifier).sayByeTo(userName);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void keeps_asking_for_phrases_until_is_told_to_stop() {
        Phrase aNonStopPhrase = new Phrase("aho");
        Phrase anotherNonStopPhrase = new Phrase("ños");
        Phrase stopPhrase = new Phrase("Stop!");
        context.checking(new Expectations() {{
            exactly(3).of(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(aNonStopPhrase),
                returnValue(anotherNonStopPhrase),
                returnValue(stopPhrase)
            ));

            ignoring(greetingsSelector);

            oneOf(notifier).notifyReversedPhrase(new Phrase("oha"));
            oneOf(notifier).notifyReversedPhrase(new Phrase("soñ"));
            oneOf(notifier).sayByeTo(userName);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }
}
