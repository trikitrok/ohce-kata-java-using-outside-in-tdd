package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.GreetingsSelector;
import com.dodevjutsu.katas.ohce.Notifier;
import com.dodevjutsu.katas.ohce.Ohce;
import com.dodevjutsu.katas.ohce.PhraseReader;
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
            ignoring(phraseReader);

            oneOf(greetingsSelector).greetingFor(userName);
            will(returnValue(greeting));

            oneOf(notifier).greetUser(greeting);
        }});

        ohce.run();

        context.assertIsSatisfied();
    }
}
