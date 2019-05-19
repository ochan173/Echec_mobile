package com.example.a1738253.echec_mobile;

import com.example.a1738253.echec_mobile.Echec.Echiquier;
import com.example.a1738253.echec_mobile.Echec.Position;

import com.example.a1738253.echec_mobile.Echec.Pieces.*;
import com.example.a1738253.echec_mobile.Echec.Pieces.PieceBase;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Classe de test pour l'échiquier
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestEchiquier extends TestCase {
    private ArrayList<PieceBase> m_echiquier = new ArrayList<>();

    /**
     * Setup pour l'échiquier de test
     */
    public void setUp() {
        Echiquier.getInstance().resetEchiquier();
        Echiquier.getInstance().remplir();


        //pions blancs
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(0, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(1, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(2, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(3, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(4, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(5, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(6, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 1)));

        //Pions noirs
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(1, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(2, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(5, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(6, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(7, 6)));

        //Tours blanches
        m_echiquier.add(Tour.obtenirPiece(PieceBase.Couleur.BLANC, new Position(0, 0)));
        m_echiquier.add(Tour.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 0)));

        //Tours noires
        m_echiquier.add(Tour.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 7)));
        m_echiquier.add(Tour.obtenirPiece(PieceBase.Couleur.NOIR, new Position(7, 7)));

        //Cavaliers blancs
        m_echiquier.add(Cavalier.obtenirPiece(PieceBase.Couleur.BLANC, new Position(1, 0)));
        m_echiquier.add(Cavalier.obtenirPiece(PieceBase.Couleur.BLANC, new Position(6, 0)));

        //Cavaliers noirs
        m_echiquier.add(Cavalier.obtenirPiece(PieceBase.Couleur.NOIR, new Position(1, 7)));
        m_echiquier.add(Cavalier.obtenirPiece(PieceBase.Couleur.NOIR, new Position(6, 7)));

        //Fous blancs
        m_echiquier.add(Fou.obtenirPiece(PieceBase.Couleur.BLANC, new Position(2, 0)));
        m_echiquier.add(Fou.obtenirPiece(PieceBase.Couleur.BLANC, new Position(5, 0)));

        //Fous noirs
        m_echiquier.add(Fou.obtenirPiece(PieceBase.Couleur.NOIR, new Position(2, 7)));
        m_echiquier.add(Fou.obtenirPiece(PieceBase.Couleur.NOIR, new Position(5, 7)));

        //Reine blanche
        m_echiquier.add(Reine.obtenirPiece(PieceBase.Couleur.BLANC, new Position(3, 0)));

        //Reine noire
        m_echiquier.add(Reine.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 7)));

        //Roi blanc
        m_echiquier.add(Roi.obtenirPiece(PieceBase.Couleur.BLANC, new Position(4, 0)));

        //Roi noir
        m_echiquier.add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 7)));
    }

    /**
     * Méthode de test pour un nouvel échiquier
     */
    public void testEchiquierDepart() {
        for (PieceBase piece : Echiquier.getInstance().getEchiquier()) {
            assertTrue(m_echiquier.contains(piece));
        }
    }

    /**
     * Méthode de test pour le reset d'un échiquier
     */
    public void testReset() {
        Echiquier.getInstance().resetEchiquier();
        assertEquals(0, Echiquier.getInstance().getEchiquier().size());
    }

    /**
     * Méthode pour tester si une position est libre sur l'échiquier
     */
    public void testPositionLibreSurEchiquier() {
        assertTrue(Echiquier.getInstance().positionEstLibre(new Position(0, 2)));
        assertTrue(Echiquier.getInstance().positionEstLibre(new Position(3, 3)));

        assertFalse(Echiquier.getInstance().positionEstLibre(new Position(0, 1)));
        assertFalse(Echiquier.getInstance().positionEstLibre(new Position(5, 7)));

    }

    /**
     * Méthode pour tester les mouvements possibles d'une pièce
     */
    public void testMouvementsPiece() {
        Echiquier echiquier = Echiquier.getInstance();

        echiquier.resetEchiquier();
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 0)));
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 6)));
        ArrayList<Position> t = echiquier.mouvementsPiece(new Position(7, 6));
        ArrayList<Position> t2 = echiquier.getPiece(new Position(7, 6)).mouvementsPossible();

        echiquier.resetEchiquier();

        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 0)));
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 7)));
        echiquier.getEchiquier().add(Reine.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 3)));

        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(3, 2)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(3, 3)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(3, 4)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(4, 2)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(4, 4)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(5, 2)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(5, 3)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(5, 4)));

        assertEquals(8, echiquier.mouvementsPiece(new Position(4, 3)).size());


        echiquier.resetEchiquier();

        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 0)));
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 7)));
        echiquier.getEchiquier().add(Reine.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 3)));

        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 2)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 3)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 4)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 2)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 4)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(5, 2)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(5, 3)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(5, 4)));

        assertEquals(0, echiquier.mouvementsPiece(new Position(4, 3)).size());


        echiquier.resetEchiquier();
        echiquier.remplir();

        PieceBase cavalierBlanc = echiquier.getPiece(new Position(6, 0));

        assertEquals(PieceBase.TypePiece.CAVALIER, cavalierBlanc.getType());

        assertFalse(echiquier.mouvementsPiece(cavalierBlanc.getPosition()).contains(new Position(4, 1)));

        cavalierBlanc.deplacer(new Position(5, 2));
        cavalierBlanc.deplacer(new Position(4, 4));
        Position cible = new Position(5, 6);
        assertTrue(echiquier.mouvementsPiece(cavalierBlanc.getPosition()).contains(cible));

        echiquier.resetEchiquier();
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 0)));
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 7)));

        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(3, 3)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(2, 4)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 4)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 4)));
        assertEquals(2, echiquier.mouvementsPiece(new Position(3, 3)).size());

        echiquier.resetEchiquier();
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 0)));
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 7)));

        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 5)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(2, 4)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 4)));
        echiquier.getEchiquier().add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 4)));
        assertEquals(0, echiquier.mouvementsPiece(new Position(3, 5)).size());


        echiquier.resetEchiquier();
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 0)));
        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 7)));

        echiquier.getEchiquier().add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 3)));

        echiquier.getEchiquier().add(Tour.obtenirPiece(PieceBase.Couleur.BLANC, new Position(1, 2)));
        echiquier.getEchiquier().add(Tour.obtenirPiece(PieceBase.Couleur.BLANC, new Position(2, 1)));
        echiquier.getEchiquier().add(Tour.obtenirPiece(PieceBase.Couleur.BLANC, new Position(4, 5)));
        echiquier.getEchiquier().add(Tour.obtenirPiece(PieceBase.Couleur.BLANC, new Position(5, 4)));

        assertEquals(0, echiquier.mouvementsPiece(new Position(3, 3)).size());
    }

    /**
     * Méthode pour tester les déplacements d'une pièce
     */
    public void testDeplacementPiece() {
        Echiquier m_echiquier = Echiquier.getInstance();
        m_echiquier.resetEchiquier();
        m_echiquier.remplir();

        assertEquals(32, m_echiquier.getEchiquier().size());
        m_echiquier.set_pieceCourante(m_echiquier.getPiece(new Position(6, 1)));
        m_echiquier.deplacerPieceCourante(new Position(6, 3));
        assertEquals(PieceBase.TypePiece.PION, m_echiquier.getPiece(new Position(6, 3)).getType());

        m_echiquier.set_pieceCourante(m_echiquier.getPiece(new Position(6, 3)));
        m_echiquier.deplacerPieceCourante(new Position(6, 4));
        assertEquals(PieceBase.TypePiece.PION, m_echiquier.getPiece(new Position(6, 4)).getType());

        m_echiquier.set_pieceCourante(m_echiquier.getPiece(new Position(5, 0)));
        assertEquals(PieceBase.TypePiece.FOU, m_echiquier.get_pieceCourante().getType());

        m_echiquier.deplacerPieceCourante(new Position(7, 2));
        m_echiquier.set_pieceCourante(m_echiquier.getPiece(new Position(7, 2)));
        m_echiquier.deplacerPieceCourante(new Position(3, 6));
        assertEquals(PieceBase.TypePiece.FOU, m_echiquier.getPiece(new Position(3, 6)).getType());
        assertEquals(31, m_echiquier.getEchiquier().size());
    }
}