package com.example.a1738253.echec_mobile.Echec;

import com.example.a1738253.echec_mobile.Echec.Pieces.PieceBase;

/**
 * Classe de base pour un joueur
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class Joueur {
    private String m_nom;
    private PieceBase.Couleur m_couleur;

    public Joueur(String p_nom) {
        m_nom = p_nom;
    }

    public String get_nom() {
        return m_nom;
    }

    public PieceBase.Couleur get_couleur() {
        return m_couleur;
    }

    public void set_couleur(PieceBase.Couleur m_couleur) {
        this.m_couleur = m_couleur;
    }
}
