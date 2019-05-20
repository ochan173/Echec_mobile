package com.example.a1738253.echec_mobile.Echec.Pieces;

import com.example.a1738253.echec_mobile.Echec.Position;

import java.util.ArrayList;

/**
 * Classe de la pièce Tour.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class Tour extends PieceBase {

    /**
     * Constructeur de base pour une pièce.
     *
     * @param p_couleur  la couleur que la pièce recevera.
     * @param p_type     Type de la pièce.
     * @param p_position La position initiale de la pièce.
     */
    private Tour(Couleur p_couleur, TypePiece p_type, Position p_position) {
        super(p_couleur, p_type, p_position);
    }

    /**
     * Permet d'obtenir une pièce Tour.
     *
     * @param p_couleur  couleur de la pièce.
     * @param p_position position de la pièce à la création.
     * @return une nouvelle Tour.
     */
    public static PieceBase obtenirPiece(Couleur p_couleur, Position p_position) {
        return new Tour(p_couleur, TypePiece.TOUR, p_position);
    }

    @Override
    public ArrayList<Position> mouvementsPossible() {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        ArrayList<Position> mouvements = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            if (i != x) {
                mouvements.add(new Position(i, y));
            }

            if (i != y) {
                mouvements.add(new Position(x, i));
            }
        }

        return mouvements;
    }
}
