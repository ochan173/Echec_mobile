package com.example.a1738253.echec_mobile.echec.pieces;

import com.example.a1738253.echec_mobile.echec.Position;

import java.util.ArrayList;

/**
 * Classe de la pièce Fou.
 *
 * @author Olivier Chan
 * @author  David Goulet
 */
public class Fou extends PieceBase {

    /**
     * Constructeur de base pour une pièce.
     *
     * @param p_couleur  la couleur que la pièce recevera.
     * @param p_position La position initiale de la pièce.
     */
    private Fou(Couleur p_couleur, Position p_position) {
        super(p_couleur, TypePiece.FOU, p_position);
    }

    /**
     * Permet d'obtenir une pièce Fou.
     * @param p_couleur couleur de la pièce.
     * @param p_position position de la pièce à la création.
     * @return un nouveau Fou.
     */
    public static PieceBase obtenirPiece(Couleur p_couleur, Position p_position) {
        return new Fou(p_couleur, p_position);
    }

    @Override
    public ArrayList<Position> mouvementsPossible() {
        ArrayList<Position> mouvements = new ArrayList<>();

        for (int i = 1; i < 8 ; i++) {

            // Positions vers la Droite
            if (this.getPosition().getX() + i <= 7) {

                // Position Sup. Droite
                if (this.getPosition().getY() + i <= 7) {
                    mouvements.add(new Position(this.getPosition().getX() + i , this.getPosition().getY() + i ));
                }

                // Position Inf. Droite
                if (this.getPosition().getY() - i >= 0) {
                    mouvements.add(new Position(this.getPosition().getX() + i , this.getPosition().getY() - i ));
                }
            }

            // Positions vers la gauche
            if (this.getPosition().getX() - i >= 0) {

                // Position Sup. Gauche
                if (this.getPosition().getY() + i <= 7) {
                    mouvements.add(new Position(this.getPosition().getX() - i , this.getPosition().getY() + i ));
                }

                // Position Inf. Gauche
                if (this.getPosition().getY() - i >= 0) {
                    mouvements.add(new Position(this.getPosition().getX() - i , this.getPosition().getY() - i ));
                }
            }
        }

        return mouvements;
    }
}
