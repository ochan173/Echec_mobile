package com.example.a1738253.echec_mobile.TestPiece;

import com.example.a1738253.echec_mobile.echec.Pieces.PieceBase;
import com.example.a1738253.echec_mobile.echec.Pieces.Pion;
import com.example.a1738253.echec_mobile.echec.Position;

import java.util.ArrayList;

/**
 * Classe de test pour la pièce Pion(blanc).
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestPionBlanc extends TestPieceBase {
    @Override
    protected PieceBase creerPiece(PieceBase.Couleur p_couleur, Position p_position) {
        return Pion.obtenirPiece(p_couleur, p_position);
    }

    @Override
    protected PieceBase.TypePiece obtenirTypePiece() {
        return PieceBase.TypePiece.PION;
    }

    @Override
    protected ArrayList<Position> obtenirMouvementPossible() {
        ArrayList<Position> positionsAttendues = new ArrayList<>();
        positionsAttendues.add(new Position(1,2));
        positionsAttendues.add(new Position(1,3));
        return positionsAttendues;
    }

    @Override
    public void testZoneAttaque() {
        m_pieceBase = Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(1,1));
        ArrayList<Position> zoneAttaques = new ArrayList<>();
        zoneAttaques.add(new Position(0,2));
        zoneAttaques.add(new Position(2,2));
        assertEquals(zoneAttaques, m_pieceBase.zoneAttaques());
    }

    /**
     * Test les déplacements de base valide pour un Pion(blanc).
     */
    public void testDeplacementValide() {

        // VALIDATION NÉGATIVE

        assertFalse(super.m_pieceBase.deplacer(new Position(2,1)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        assertFalse(super.m_pieceBase.deplacer(new Position(0,0)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        assertFalse(super.m_pieceBase.deplacer(new Position(1,0)));
        assertEquals(new Position(1,1), m_pieceBase.getPosition());

        // VALIDATION POSITIVE

        assertTrue(super.m_pieceBase.deplacer(new Position(1,3)));
        assertEquals(new Position(1,3), m_pieceBase.getPosition());

        // Ne peut pas avancer de 2 après la position de départ
        assertFalse(super.m_pieceBase.deplacer(new Position(1,5)));
        assertEquals(new Position(1,3), m_pieceBase.getPosition());

        assertTrue(super.m_pieceBase.deplacer(new Position(1,4)));
        assertEquals(new Position(1,4), m_pieceBase.getPosition());
    }
}
