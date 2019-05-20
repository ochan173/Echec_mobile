package com.example.a1738253.echec_mobile;

import com.example.a1738253.echec_mobile.echec.Joueur;
import com.example.a1738253.echec_mobile.echec.Pieces.PieceBase;

import junit.framework.TestCase;

public class TestJoueur extends TestCase {
    public void testCreer() {
        Joueur joueur1 = new Joueur("Master");
        joueur1.set_couleur(PieceBase.Couleur.NOIR);
        assertEquals("Master", joueur1.get_nom());
        assertEquals(PieceBase.Couleur.NOIR, joueur1.get_couleur());
    }
}
