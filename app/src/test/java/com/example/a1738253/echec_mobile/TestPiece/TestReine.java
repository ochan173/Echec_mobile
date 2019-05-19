package com.example.a1738253.echec_mobile.TestPiece;

import com.example.a1738253.echec_mobile.Echec.Pieces.PieceBase;
import com.example.a1738253.echec_mobile.Echec.Pieces.Reine;
import com.example.a1738253.echec_mobile.Echec.Position;

import java.util.ArrayList;

/**
 * Classe de test pour la pièce Reine.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestReine extends TestPieceBase {
    @Override
    protected PieceBase creerPiece(PieceBase.Couleur p_couleur, Position p_position) {
        return Reine.obtenirPiece(p_couleur, p_position);
    }

    @Override
    protected PieceBase.TypePiece obtenirTypePiece() {
        return PieceBase.TypePiece.REINE;
    }

    @Override
    protected ArrayList<Position> obtenirMouvementPossible() {
        ArrayList<Position> positionsAttendues = new ArrayList<Position>();
        // Vertical Haut
        positionsAttendues.add(new Position(1,2));
        positionsAttendues.add(new Position(1,3));
        positionsAttendues.add(new Position(1,4));
        positionsAttendues.add(new Position(1,5));
        positionsAttendues.add(new Position(1,6));
        positionsAttendues.add(new Position(1,7));

        // Vertical Bas
        positionsAttendues.add(new Position(1,0));

        // Horizontale Gauche
        positionsAttendues.add(new Position(0,1));

        // Horizontale Droite
        positionsAttendues.add(new Position(2,1));
        positionsAttendues.add(new Position(3,1));
        positionsAttendues.add(new Position(4,1));
        positionsAttendues.add(new Position(5,1));
        positionsAttendues.add(new Position(6,1));
        positionsAttendues.add(new Position(7,1));

        // Diagonale Haute Gauche
        positionsAttendues.add(new Position(0,2));

        // Diagonale Haute Droite
        positionsAttendues.add(new Position(2,2));
        positionsAttendues.add(new Position(3,3));
        positionsAttendues.add(new Position(4,4));
        positionsAttendues.add(new Position(5,5));
        positionsAttendues.add(new Position(6,6));
        positionsAttendues.add(new Position(7,7));

        // Diagonale Bas Gauche
        positionsAttendues.add(new Position(0,0));

        // Diagonale Bas Droite
        positionsAttendues.add(new Position(2,0));

        return positionsAttendues;
    }

    /**
     * Test les déplacements de base valide pour une Reine.
     */
    public void testDeplacementValide() {

        // VALIDATION NÉGATIVE

        assertFalse(super.m_pieceBase.deplacer(new Position(3,2)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        assertFalse(super.m_pieceBase.deplacer(new Position(4,3)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        // VALIDATION POSITIVE

        assertTrue(super.m_pieceBase.deplacer(new Position(3,3)));
        assertEquals(new Position(3,3), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(1,5)));
        assertEquals(new Position(1,5), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(3,7)));
        assertEquals(new Position(3,7), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(7,3)));
        assertEquals(new Position(7,3), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(2,3)));
        assertEquals(new Position(2,3), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(0,3)));
        assertEquals(new Position(0,3), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(0,0)));
        assertEquals(new Position(0,0), m_pieceBase.getPosition());
    }
}
