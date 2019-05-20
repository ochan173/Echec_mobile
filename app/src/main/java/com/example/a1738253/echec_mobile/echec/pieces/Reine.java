package com.example.a1738253.echec_mobile.echec.Pieces;

import com.example.a1738253.echec_mobile.echec.Position;

import java.util.ArrayList;

/**
 * Classe de la pièce Reine.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class Reine extends PieceBase {

    /**
     * Constructeur de base pour une pièce.
     *
     * @param p_couleur  la couleur que la pièce recevera.
     * @param p_position La position initiale de la pièce.
     */
    private Reine(Couleur p_couleur, Position p_position) {
        super(p_couleur, TypePiece.REINE, p_position);
    }

    /**
     * Permet d'obtenir une pièce Reine.
     * @param p_couleur couleur de la pièce.
     * @param p_position position de la pièce à la création.
     * @return un nouveau Reine.
     */
    public static PieceBase obtenirPiece(Couleur p_couleur, Position p_position) {
        return new Reine(p_couleur, p_position);
    }

    @Override
    public ArrayList<Position> mouvementsPossible() {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        ArrayList<Position> mouvements = new ArrayList<>();

        for (int i = 0; i < 8;) {

            // Position horizontal
            if (i != x) {
                mouvements.add(new Position(i, y));
            }

            // Position vertical
            if (i != y) {
                mouvements.add(new Position(x, i));
            }

            i++;

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
