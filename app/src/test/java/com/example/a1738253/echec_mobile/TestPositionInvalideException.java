package com.example.a1738253.echec_mobile;

import com.example.a1738253.echec_mobile.echec.Position;
import com.example.a1738253.echec_mobile.echec.PositionInvalideException;

import junit.framework.TestCase;

/**
 * Classe de tests pour une position
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestPositionInvalideException extends TestCase{

    /**
     * Test pour la création d'une PositionInvalideException
     */
    public void testLancementPositionInvalideException() {

        Position pValide = new Position(0, 7);

        try {
            Position p = new Position(-1, 0);
            fail("La position en X est invalide, inf à 0");
        }
        catch (PositionInvalideException p_e) {
            //Succès
        }

        try {
            Position p = new Position(0, -1);
            fail("La position en Y est invalide, inf à 0");
        }
        catch (PositionInvalideException p_e) {
            // Succès
        }

        try {
            Position p = new Position(8, 0);
            fail("La position en X est invalide, sup à 7");
        }
        catch (PositionInvalideException p_e) {
            // Succès
        }

        try {
            Position p = new Position(0, 8);
            fail("La position en Y est invalide, sup à 7");
        }
        catch (PositionInvalideException p_e) {
            //Succès
        }

        try {
            Position p = new Position(-1, 8);
            fail("La position en Y et en X sont invalide, sup à 7 et inf à 0");
        }
        catch (PositionInvalideException p_e) {
            //Succès
        }
    }
}
