package com.example.a1738253.echec_mobile;

import com.example.a1738253.echec_mobile.Echec.Position;

import junit.framework.TestCase;

/**
 * Classe de tests pour une position.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestPosition extends TestCase {
    /**
     * Test pour la création d'une position.
     */
    public void testCreation() {
        Position p = new Position(0, 0);
        assertEquals(0, p.getX());
        assertEquals(0, p.getY());

        Position p2 = new Position(4, 7);
        assertEquals(4, p2.getX());
        assertEquals(7, p2.getY());
    }

    /**
     * Test pour la modification d'une position.
     */
    public void testModifier() {
        Position p = new Position(4, 7);
        p.modifierPosition(new Position(6,4));
        assertEquals(6, p.getX());
        assertEquals(4, p.getY());
    }

    /**
     * Méthode qui test l'égalité entre des positions
     */
    public void testEgalite() {
        Position positionA = new Position(5,5);
        Position positionB = new Position(5,5);
        assertEquals(positionA, positionB);

        Position positionC = new Position(4,4);
        assertFalse(positionA.equals(positionC));

        // Réflexivité
        assertEquals(positionA, positionA);

        // Symétrie
        assertEquals(positionB, positionA);

        // Transivité
        Position positionD = new Position(5,5);
        assertEquals(positionA, positionD);
        assertEquals(positionB, positionD);

        // Constance
        assertNotNull(positionA);
        assertFalse(positionA.equals("Position"));
    }

    /**
     * Méthode pour tester le hashage d'une position
     */
    public void testHash(){
        Position positionA = new Position(5,5);
        Position positionC = new Position(5,5);
        Position positionB = new Position(5,4);
        assertEquals(positionA.hashCode(), positionC.hashCode());
        assertNotSame(positionA.hashCode(), positionB.hashCode());
    }
}
