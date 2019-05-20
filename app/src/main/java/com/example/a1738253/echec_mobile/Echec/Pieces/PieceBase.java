package com.example.a1738253.echec_mobile.Echec.Pieces;


import com.example.a1738253.echec_mobile.Echec.Position;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe de base pour toutes les pièces.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public abstract class PieceBase {

    /**
     * Couleur disponible pour les pièces d'un jeu d'échec.
     * Permet aussi aux pions de savoir dans quelle direction ils avancent.
     */
    public enum Couleur {
        BLANC(1),
        NOIR(-1);

        private int m_direction;

        /**
         * Constructeur pour la couleur
         *
         * @param p_direction valeur entière qui sera utilisée pour déterminer le sens
         *                    dans lequel un pion va avancer.
         */
        Couleur(int p_direction) {
            m_direction = p_direction;
        }

        /**
         * Donne la direction, sur l'axe des Y, des pions en fonction de sa couleur.
         *
         * @return la valeur de déplacement utilisé en Y.
         */
        public int getDirection() {
            return m_direction;
        }
    }

    /**
     * Énum des types de pièce disponibles.
     */
    public enum TypePiece {ROI, REINE, TOUR, CAVALIER, PION, FOU}

    private Position m_position;
    private Couleur m_couleur;
    private TypePiece m_type;
    private boolean m_aBougee;

    public boolean getABougee() {
        return m_aBougee;
    }

    public Couleur getCouleur() {
        return m_couleur;
    }

    public Position getPosition() {
        return m_position;
    }

    public TypePiece getType() {
        return m_type;
    }

    /**
     * Constructeur de base pour une pièce.
     *
     * @param p_couleur  la couleur que la pièce recevera.
     * @param p_type     Type de la pièce.
     * @param p_position La position initiale de la pièce.
     */
    protected PieceBase(Couleur p_couleur, TypePiece p_type, Position p_position) {
        m_couleur = p_couleur;
        m_type = p_type;
        m_position = p_position;
        m_aBougee = false;
    }

    /**
     * Déplace la pièce avec une nouvelle position.
     *
     * @param p_nouvellePosition la nouvelle position pour la pièce.
     * @return True si le déplacement est possible sinon False
     */
    public boolean deplacer(Position p_nouvellePosition) {
        if (mouvementsPossible().contains(p_nouvellePosition) || zoneAttaques().contains(p_nouvellePosition)) {
            m_position.modifierPosition(p_nouvellePosition);
            m_aBougee = true;
            return true;
        }
        return false;
    }

    public void roquer(Position p_nouvellePosition) {
        m_position.modifierPosition(p_nouvellePosition);
        m_aBougee = true;
    }


    /**
     * Permet d'obtenir les positions pouvant être attaquées par la pièce.
     *
     * @return les postions que la pièce peut attaquer.
     */
    public ArrayList<Position> zoneAttaques() {
        return mouvementsPossible();
    }

    /**
     * Permet de retourner toutes les positions (sans contraintes) d'une pièce
     * en fonction du type de mouvement qu'elle possède.
     *
     * @return Une collection de positions.
     */
    public abstract ArrayList<Position> mouvementsPossible();

    @Override
    public boolean equals(Object p_o) {
        if (this == p_o) return true;
        if (!(p_o instanceof PieceBase)) return false;
        PieceBase piece = (PieceBase) p_o;
        return Objects.equals(m_couleur, piece.m_couleur) &&
                Objects.equals(m_position, piece.m_position);
    }
}
