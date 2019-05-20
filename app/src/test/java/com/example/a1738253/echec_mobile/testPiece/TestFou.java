package com.example.a1738253.echec_mobile.testPiece;

import com.example.a1738253.echec_mobile.echec.pieces.Fou;
import com.example.a1738253.echec_mobile.echec.pieces.PieceBase;
import com.example.a1738253.echec_mobile.echec.Position;

import java.util.ArrayList;

/**
 * Classe de test pour la pièce Fou.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestFou extends TestPieceBase {

    @Override
    protected PieceBase creerPiece(PieceBase.Couleur p_couleur, Position p_position) {
        return Fou.obtenirPiece(p_couleur, p_position);
    }

    @Override
    protected PieceBase.TypePiece obtenirTypePiece() {
        return PieceBase.TypePiece.FOU;
    }

    @Override
    protected ArrayList<Position> obtenirMouvementPossible() {
        ArrayList<Position> positionsAttendues = new ArrayList<>();
        positionsAttendues.add(new Position(0,0));
        positionsAttendues.add(new Position(2,0));
        positionsAttendues.add(new Position(0,2));
        positionsAttendues.add(new Position(2,2));
        positionsAttendues.add(new Position(3,3));
        positionsAttendues.add(new Position(4,4));
        positionsAttendues.add(new Position(5,5));
        positionsAttendues.add(new Position(6,6));
        positionsAttendues.add(new Position(7,7));
        return positionsAttendues;
    }

    /**
     * Test les déplacement de base valide pour un Fou
     */
    public void testDeplacementValide() {

        // VALIDATION NÉGATIVE

        assertFalse(super.m_pieceBase.deplacer(new Position(1,2)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        assertFalse(super.m_pieceBase.deplacer(new Position(2,1)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        // VALIDATION POSITIVE

        assertTrue(super.m_pieceBase.deplacer(new Position(2,2)));
        assertEquals(new Position(2,2), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(1,1)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(0,2)));
        assertEquals(new Position(0,2), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(1,1)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

    }
}