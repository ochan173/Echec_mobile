package com.example.a1738253.echec_mobile;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Classe qui test la saisie des nom des joueurs
 *
 * @author Olivier Chan
 * @author David Goulet
 */
@RunWith(AndroidJUnit4.class)
public class TestDialogue {
    @Rule
    public ActivityTestRule<com.example.a1738253.echec_mobile.echec.gui.MainActivity> m_activityRule = new ActivityTestRule<>(com.example.a1738253.echec_mobile.echec.gui.MainActivity.class);

    /**
     * Test du dialogue pour la saisie des noms
     */
    @Test
    public void testDialogue() {
        final String nomJ1 = "Bob";
        final String nomJ2 = "Allo";
        onView(withId(R.id.btnConfirmer)).perform(click());
        onView(withId(R.id.joueur1)).check(matches(withText(R.string.nom_invalide)));

        onView(withId(R.id.btnConfirmer)).perform(click());
        onView(withId(R.id.joueur2)).check(matches(withText(R.string.nom_invalide)));

        onView(withId(R.id.joueur1)).perform(clearText());
        onView(withId(R.id.joueur2)).perform(clearText());

        onView(withId(R.id.joueur1)).perform(replaceText(nomJ1), closeSoftKeyboard());
        onView(withId(R.id.joueur1)).check(matches(withText(nomJ1)));

        onView(withId(R.id.joueur2)).perform(typeText(nomJ2), closeSoftKeyboard());
        onView(withId(R.id.joueur2)).check(matches(withText(nomJ2)));
    }
}
