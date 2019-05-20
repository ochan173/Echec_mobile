package com.example.a1738253.echec_mobile.TestPiece;

import com.example.a1738253.echec_mobile.echec.Pieces.PieceBase;
import com.example.a1738253.echec_mobile.echec.Pieces.Tour;
import com.example.a1738253.echec_mobile.echec.Position;

import java.util.ArrayList;

/**
 * Classe test pour la pièce Tour.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestTour extends TestPieceBase {
    @Override
    protected PieceBase creerPiece(PieceBase.Couleur p_couleur, Position p_position) {
        return Tour.obtenirPiece(p_couleur, p_position);
    }

    @Override
    protected PieceBase.TypePiece obtenirTypePiece() {
        return PieceBase.TypePiece.TOUR;
    }

    @Override
    protected ArrayList<Position> obtenirMouvementPossible() {
        ArrayList<Position> positionsAttendues = new ArrayList<>();
        positionsAttendues.add(new Position(1,0));
        positionsAttendues.add(new Position(0,1));

        positionsAttendues.add(new Position(1,2));
        positionsAttendues.add(new Position(1,3));
        positionsAttendues.add(new Position(1,4));
        positionsAttendues.add(new Position(1,5));
        positionsAttendues.add(new Position(1,6));
        positionsAttendues.add(new Position(1,7));

        positionsAttendues.add(new Position(2,1));
        positionsAttendues.add(new Position(3,1));
        positionsAttendues.add(new Position(4,1));
        positionsAttendues.add(new Position(5,1));
        positionsAttendues.add(new Position(6,1));
        positionsAttendues.add(new Position(7,1));

        return positionsAttendues;
    }

    /**
     * Test les déplacements de base valide pour une Tour.
     */
    public void testDeplacementValide() {

        // VALIDATION NÉGATIVE
        assertFalse(super.m_pieceBase.deplacer(new Position(2,3)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        assertFalse(super.m_pieceBase.deplacer(new Position(0,0)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        // VALIDATION POSITIVE

        assertTrue(super.m_pieceBase.deplacer(new Position(1,0)));
        assertEquals(new Position(1,0), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(0,0)));
        assertEquals(new Position(0,0), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(0,5)));
        assertEquals(new Position(0,5), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(5,5)));
        assertEquals(new Position(5,5), m_pieceBase.getPosition());
    }
}
