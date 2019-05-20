package com.example.a1738253.echec_mobile.testPiece;

import com.example.a1738253.echec_mobile.echec.pieces.PieceBase;
import com.example.a1738253.echec_mobile.echec.pieces.Roi;
import com.example.a1738253.echec_mobile.echec.Position;

import java.util.ArrayList;

/**
 * Classe de test pour la pièce Roi.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestRoi extends TestPieceBase {
    @Override
    protected PieceBase creerPiece(PieceBase.Couleur p_couleur, Position p_position) {
        return Roi.obtenirPiece(p_couleur, p_position);
    }

    @Override
    protected PieceBase.TypePiece obtenirTypePiece() {
        return PieceBase.TypePiece.ROI;
    }

    @Override
    protected ArrayList<Position> obtenirMouvementPossible() {
        ArrayList<Position> positionsAttendues = new ArrayList<>();
        positionsAttendues.add(new Position(0,0));
        positionsAttendues.add(new Position(1,0));
        positionsAttendues.add(new Position(2,0));
        positionsAttendues.add(new Position(0,1));
        positionsAttendues.add(new Position(2,1));
        positionsAttendues.add(new Position(0,2));
        positionsAttendues.add(new Position(1,2));
        positionsAttendues.add(new Position(2,2));
        return positionsAttendues;
    }

    /**
     * Test les déplacements de base valide pour un Roi.
     */
    public void testDeplacementValide() {

        // VALIDATION NÉGATIVE

        assertFalse(super.m_pieceBase.deplacer(new Position(3,1)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        assertFalse(super.m_pieceBase.deplacer(new Position(1,3)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        // VALIDATION POSITIVE

        assertTrue(super.m_pieceBase.deplacer(new Position(2,2)));
        assertEquals(new Position(2,2), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(1,2)));
        assertEquals(new Position(1,2), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(0,1)));
        assertEquals(new Position(0,1), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(0,0)));
        assertEquals(new Position(0,0), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(1,0)));
        assertEquals(new Position(1,0), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(1,1)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(2,0)));
        assertEquals(new Position(2,0), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(1,1)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());
    }
}
