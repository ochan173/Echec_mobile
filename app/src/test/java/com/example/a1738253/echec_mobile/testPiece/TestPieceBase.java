package com.example.a1738253.echec_mobile.TestPiece;

import com.example.a1738253.echec_mobile.echec.Pieces.PieceBase;
import com.example.a1738253.echec_mobile.echec.Position;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Classe de test pour la pièce de base.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public abstract class TestPieceBase extends TestCase {

    /**
     * Pièce utilisée dans les tests.
     */
    PieceBase m_pieceBase;

    /**
     * Permet de créer une pièce d'un enfant pour tester les fonctions de base de toutes les pièces.
     * @param p_couleur couleur donnée à la pièce.
     * @return Une pièce d'une classe enfant.
     */
    abstract protected PieceBase creerPiece(PieceBase.Couleur p_couleur, Position p_position);

    /**
     * Permet d'obtenir le type de la pièce enfant testée
     * @return le type de la pièce testée
     */
    abstract protected PieceBase.TypePiece obtenirTypePiece();

    /**
     * Obtient les positions des mouvements possible de la classe enfant testée
     * @return une collection de positions
     */
    abstract protected ArrayList<Position> obtenirMouvementPossible();

    /**
     * Initialise les valeurs d'une pièce avant chaque test
     */
    public void setUp() {
        m_pieceBase = creerPiece(PieceBase.Couleur.BLANC, new Position(1,1));
    }

    /**
     * Test la création d'une pièce de base.
     */
    public void testCreer() {
        m_pieceBase = creerPiece(PieceBase.Couleur.BLANC, new Position(1,1));
        assertEquals(PieceBase.Couleur.BLANC, m_pieceBase.getCouleur());
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        PieceBase pieceBaseNoire = creerPiece(PieceBase.Couleur.NOIR, new Position(1,4));
        assertEquals(PieceBase.Couleur.NOIR, pieceBaseNoire.getCouleur());
        assertEquals(new Position(1,4), pieceBaseNoire.getPosition());
    }

    /**
     * Test le type de la pièce creer.
     */
    public void testType() {
        assertEquals(obtenirTypePiece(), m_pieceBase.getType());
    }

    /**
     * Test les mouvements possible d'un roi.
     */
    public void testMouvementPossible() {
        //assertEquals(obtenirMouvementPossible(), m_pieceBase.mouvementsPossible());
        ArrayList<Position> mouvementsObtenu = obtenirMouvementPossible();
        for (Position p :m_pieceBase.mouvementsPossible()) {
            assertTrue(mouvementsObtenu.contains(p));
        }
    }

    /**
     * Test la zone que la pièce menace.
     */
    public void testZoneAttaque() {
        //assertEquals(obtenirMouvementPossible(), m_pieceBase.zoneAttaques());

        ArrayList<Position> mouvementsObtenu = obtenirMouvementPossible();
        for (Position p :m_pieceBase.zoneAttaques()) {
            assertTrue(mouvementsObtenu.contains(p));
        }
    }
}
