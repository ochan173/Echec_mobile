package com.example.a1738253.echec_mobile.testPiece;

import com.example.a1738253.echec_mobile.echec.pieces.Cavalier;
import com.example.a1738253.echec_mobile.echec.pieces.PieceBase;
import com.example.a1738253.echec_mobile.echec.Position;

import java.util.ArrayList;

/**
 * Classe test pour la pièce Cavalier.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestCavalier extends TestPieceBase {

    @Override
    public void setUp() {
        super.m_pieceBase = creerPiece(PieceBase.Couleur.BLANC, new Position(3,3));
    }

    @Override
    protected PieceBase creerPiece(PieceBase.Couleur p_couleur, Position p_position) {
        return Cavalier.obtenirPiece(p_couleur, p_position);
    }

    @Override
    protected PieceBase.TypePiece obtenirTypePiece() {
        return PieceBase.TypePiece.CAVALIER;
    }

    @Override
    protected ArrayList<Position> obtenirMouvementPossible() {
        ArrayList<Position> positionsAttendues = new ArrayList<>();

        // Inverse x <-> y
        // Besoin de trouver chaque coin, puis inverser x et y pour obtenir la deuxième position dans le même coin
        positionsAttendues.add(new Position(4,5));
        positionsAttendues.add(new Position(5,4));

        positionsAttendues.add(new Position(2,1));
        positionsAttendues.add(new Position(1,2));

        positionsAttendues.add(new Position(4,1));
        positionsAttendues.add(new Position(1,4));

        positionsAttendues.add(new Position(5,2));
        positionsAttendues.add(new Position(2,5));

        return positionsAttendues;
    }

    /**
     * Test les déplacements de base valide pour un Cavalier.
     */
    public void testDeplacementValide() {

        // VALIDATION NÉGATIVE

        assertFalse(super.m_pieceBase.deplacer(new Position(3,4)));
        assertEquals(new Position(3,3), m_pieceBase.getPosition());

        assertFalse(super.m_pieceBase.deplacer(new Position(4,4)));
        assertEquals(new Position(3,3), m_pieceBase.getPosition());

        // VALIDATION POSITIVE

        assertTrue(super.m_pieceBase.deplacer(new Position(4,5)));
        assertEquals(new Position(4,5), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(3,3)));

        assertTrue(super.m_pieceBase.deplacer(new Position(5,4)));
        assertEquals(new Position(5,4), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(3,3)));

        assertTrue(super.m_pieceBase.deplacer(new Position(4,1)));
        assertEquals(new Position(4,1), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(3,3)));

        assertTrue(super.m_pieceBase.deplacer(new Position(1,4)));
        assertEquals(new Position(1,4), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(3,3)));

        assertTrue(super.m_pieceBase.deplacer(new Position(1,2)));
        assertEquals(new Position(1,2), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(3,3)));

        assertTrue(super.m_pieceBase.deplacer(new Position(1,2)));
        assertEquals(new Position(1,2), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(3,3)));

        assertTrue(super.m_pieceBase.deplacer(new Position(5,2)));
        assertEquals(new Position(5,2), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(3,3)));

        assertTrue(super.m_pieceBase.deplacer(new Position(2,5)));
        assertEquals(new Position(2,5), m_pieceBase.getPosition());
    }
}
