package com.example.a1738253.echec_mobile.Echec;

import com.example.a1738253.echec_mobile.Echec.Pieces.PieceBase;
import com.example.a1738253.echec_mobile.Echec.Pieces.*;

import java.util.ArrayList;

/**
 * Classe de l'échiquier
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class Echiquier {
    public enum etatPartie {NORMAL, ECHEC, ECHECMATE}

    private static Echiquier m_instance;
    private ArrayList<PieceBase> m_echiquier;
    private PieceBase m_pieceCourante;
    private PieceBase.Couleur tourJoueur = PieceBase.Couleur.BLANC;

    private Echiquier() {
        m_echiquier = new ArrayList<>();
    }

    /**
     * Méthode pour avoir l'instance de l'échiquier
     *
     * @return l'instance de l'échiquier
     */
    public static Echiquier getInstance() {
        if (m_instance == null) {
            m_instance = new Echiquier();
        }

        return m_instance;
    }

    /**
     * Méthode qui retourne les pièces adverses qui mettent en échec le roi
     *
     * @param p_roi Roi à analyser
     * @return la liste des pièces qui mettent le roi en echec
     */
    private ArrayList<PieceBase> detectionEchec(PieceBase p_roi) {
        if (p_roi.getType() != PieceBase.TypePiece.ROI) {
            throw new IllegalArgumentException("La pièce n'est pas un roi");
        }

        ArrayList<PieceBase> piecesDangereuses = new ArrayList<>();

        for (PieceBase p : m_echiquier) {
            if (p.getCouleur() != p_roi.getCouleur()) {
                if (mouvementsPieceEnnemi(p.getPosition()).contains(p_roi.getPosition())) {
                    piecesDangereuses.add(p);
                }
            }
        }

        return piecesDangereuses;
    }

    /**
     * Méthode interne utilisé afin de calculé les mouvements d'une pièce ennemi
     * Elle est sensiblement la même chose que mouvementPieces, avec certaines contraintes en moins
     *
     * Permet d'obtenir les mouvements valides d'une pièce en fonction des autres pièces sur l'échiquier
     * si la piece sur la position est de même couleur on la retire de la liste de mouvements
     * si la piece sur la position est de de couleur différente est de la même couleur on la laisse pour la manger
     *
     * @param p_position la position de la pièce utilisée
     * @return les positions où la pièce peut se déplacer
     */
    private ArrayList<Position> mouvementsPieceEnnemi(Position p_position) {
        PieceBase piece = getPiece(p_position);
        ArrayList<Position> mouvements = piece.zoneAttaques();

        if (piece.getType() == PieceBase.TypePiece.CAVALIER) {
            for (Position p : piece.mouvementsPossible()) {
                if (!positionEstLibre(p) && getPiece(p).getCouleur() == piece.getCouleur()) {
                    mouvements.remove(p);
                }
            }
        } else {
            for (Position p : piece.mouvementsPossible()) {
                if (contientPosition(p)) {

                    if (p.getX() == piece.getPosition().getX()) {
                        int x = p.getX();
                        int y = p.getY();

                        if (getPiece(p).getCouleur() == piece.getCouleur()) {
                            if (y > piece.getPosition().getY()) {

                                while (y <= 7) {
                                    mouvements.remove(new Position(x, y++));
                                }
                            } else if (y < piece.getPosition().getY()) {
                                while (y >= 0) {
                                    mouvements.remove(new Position(x, y--));
                                }
                            }
                        } else {

                            if (y > piece.getPosition().getY()) {

                                while (y < 7) {
                                    mouvements.remove(new Position(x, ++y));
                                }
                            } else if (y < piece.getPosition().getY()) {
                                while (y > 0) {
                                    mouvements.remove(new Position(x, --y));
                                }
                            }
                        }
                    } else if (p.getY() == piece.getPosition().getY()) {
                        int x = p.getX();
                        int y = p.getY();

                        if (getPiece(p).getCouleur() == piece.getCouleur()) {

                            if (x > piece.getPosition().getX()) {

                                while (x <= 7) {
                                    mouvements.remove(new Position(x, y));
                                    x++;
                                }
                            } else if (x < piece.getPosition().getX()) {
                                while (x >= 0) {
                                    mouvements.remove(new Position(x, y));
                                    x--;
                                }
                            }
                        } else {

                            if (x > piece.getPosition().getX()) {

                                while (x < 7) {
                                    mouvements.remove(new Position(++x, y));
                                }
                            } else if (x < piece.getPosition().getX()) {
                                while (x > 0) {
                                    mouvements.remove(new Position(--x, y));
                                }
                            }
                        }
                    }

                    // DIAGONALS

                    else if (p.getX() > piece.getPosition().getX() && p.getY() > piece.getPosition().getY()) {
                        int x = p.getX();
                        int y = p.getY();
                        if (getPiece(p).getCouleur() == piece.getCouleur()) {
                            while (x <= 7 && y <= 7) {
                                mouvements.remove(new Position(x++, y++));
                            }
                        } else {
                            while (x < 7 && y < 7) {
                                mouvements.remove(new Position(++x, ++y));
                            }
                        }
                    } else if (p.getX() < piece.getPosition().getX() && p.getY() > piece.getPosition().getY()) {
                        int x = p.getX();
                        int y = p.getY();
                        if (getPiece(p).getCouleur() == piece.getCouleur()) {

                            while (x >= 0 && y <= 7) {
                                mouvements.remove(new Position(x--, y++));
                            }
                        } else {
                            while (x > 0 && y < 7) {
                                mouvements.remove(new Position(--x, ++y));
                            }
                        }
                    } else if (p.getX() < piece.getPosition().getX() && p.getY() < piece.getPosition().getY()) {
                        int x = p.getX();
                        int y = p.getY();
                        if (getPiece(p).getCouleur() == piece.getCouleur()) {

                            while (x >= 0 && y >= 0) {
                                mouvements.remove(new Position(x--, y--));
                            }
                        } else {
                            while (x > 0 && y > 0) {
                                mouvements.remove(new Position(--x, --y));
                            }
                        }
                    } else if (p.getX() > piece.getPosition().getX() && p.getY() < piece.getPosition().getY()) {
                        int x = p.getX();
                        int y = p.getY();
                        if (getPiece(p).getCouleur() == piece.getCouleur()) {
                            while (x <= 7 && y >= 0) {
                                mouvements.remove(new Position(x++, y--));
                            }
                        } else {
                            while (x < 7 && y > 0) {
                                mouvements.remove(new Position(++x, --y));
                            }
                        }
                    }
                }
            }
        }

        return mouvements;
    }

    /**
     * Permet d'obtenir les mouvements valides d'une pièce en fonction des autres pièces sur l'échiquier
     * si la piece sur la position est de même couleur on la retire de la liste de mouvements
     * si la piece sur la position est de de couleur différente est de la même couleur on la laisse pour la manger
     *
     * @param p_position la position de la pièce utilisée
     * @return les positions où la pièce peut se déplacer
     */
    public ArrayList<Position> mouvementsPiece(Position p_position) {
        PieceBase piece = getPiece(p_position);
        ArrayList<Position> mouvements = piece.mouvementsPossible();

        if (piece.getType() == PieceBase.TypePiece.ROI) {

            ArrayList<Position> zoneDanger = zoneDangerRoi(piece.getCouleur());
            for (Position p : piece.mouvementsPossible()) {
                if (zoneDanger.contains(p)) {
                    mouvements.remove(p);
                }
            }
        }

        ArrayList<PieceBase> menace = detectionEchec(obtenirRoiCouleur());
        if (menace.size() > 0) {
            if (menace.size() == 1) {
                ArrayList<Position> cheminBloquable = cheminEntrePieces(menace.get(0).getPosition(), obtenirRoiCouleur().getPosition());
                if (piece.getType() != PieceBase.TypePiece.ROI) {

                    for (int i = 0; i < mouvements.size(); i++) {
                        if (!cheminBloquable.contains(mouvements.get(i))) {
                            mouvements.remove(i);
                            i--;
                        }
                    }
                }
            } else {
                if (piece.getType() != PieceBase.TypePiece.ROI) {
                    mouvements.clear();
                }
            }
        }
        if (piece.getType() == PieceBase.TypePiece.CAVALIER) {
            for (Position p : piece.mouvementsPossible()) {
                if (!positionEstLibre(p) && getPiece(p).getCouleur() == piece.getCouleur()) {
                    mouvements.remove(p);
                }
            }
        } else if (piece.getType() == PieceBase.TypePiece.PION) {
            for (Position p : piece.mouvementsPossible()) {
                if (!positionEstLibre(p)) {
                    mouvements.remove(p);
                }
            }
            for (Position p : piece.zoneAttaques()) {
                if (!positionEstLibre(p) && getPiece(p).getCouleur() != piece.getCouleur()) {
                    mouvements.add(p);
                }
            }
        }
        for (Position p : piece.mouvementsPossible()) {
            if (contientPosition(p)) {

                if (p.getX() == piece.getPosition().getX()) {
                    int x = p.getX();
                    int y = p.getY();

                    if (getPiece(p).getCouleur() == piece.getCouleur()) {
                        if (y > piece.getPosition().getY()) {

                            while (y <= 7) {
                                mouvements.remove(new Position(x, y++));
                            }
                        } else if (y < piece.getPosition().getY()) {
                            while (y >= 0) {
                                mouvements.remove(new Position(x, y--));
                            }
                        }
                    } else {

                        if (y > piece.getPosition().getY()) {

                            while (y < 7) {
                                mouvements.remove(new Position(x, ++y));
                            }
                        } else if (y < piece.getPosition().getY()) {
                            while (y > 0) {
                                mouvements.remove(new Position(x, --y));
                            }
                        }
                    }
                } else if (p.getY() == piece.getPosition().getY()) {
                    int x = p.getX();
                    int y = p.getY();

                    if (getPiece(p).getCouleur() == piece.getCouleur()) {

                        if (x > piece.getPosition().getX()) {

                            while (x <= 7) {
                                mouvements.remove(new Position(x, y));
                                x++;
                            }
                        } else if (x < piece.getPosition().getX()) {
                            while (x >= 0) {
                                mouvements.remove(new Position(x, y));
                                x--;
                            }
                        }
                    } else {

                        if (x > piece.getPosition().getX()) {

                            while (x < 7) {
                                mouvements.remove(new Position(++x, y));
                            }
                        } else if (x < piece.getPosition().getX()) {
                            while (x > 0) {
                                mouvements.remove(new Position(--x, y));
                            }
                        }
                    }
                }

                // DIAGONALS

                else if (p.getX() > piece.getPosition().getX() && p.getY() > piece.getPosition().getY()) {
                    int x = p.getX();
                    int y = p.getY();
                    if (getPiece(p).getCouleur() == piece.getCouleur()) {
                        while (x <= 7 && y <= 7) {
                            mouvements.remove(new Position(x++, y++));
                        }
                    } else {
                        while (x < 7 && y < 7) {
                            mouvements.remove(new Position(++x, ++y));
                        }
                    }
                } else if (p.getX() < piece.getPosition().getX() && p.getY() > piece.getPosition().getY()) {
                    int x = p.getX();
                    int y = p.getY();
                    if (getPiece(p).getCouleur() == piece.getCouleur()) {

                        while (x >= 0 && y <= 7) {
                            mouvements.remove(new Position(x--, y++));
                        }
                    } else {
                        while (x > 0 && y < 7) {
                            mouvements.remove(new Position(--x, ++y));
                        }
                    }
                } else if (p.getX() < piece.getPosition().getX() && p.getY() < piece.getPosition().getY()) {
                    int x = p.getX();
                    int y = p.getY();
                    if (getPiece(p).getCouleur() == piece.getCouleur()) {

                        while (x >= 0 && y >= 0) {
                            mouvements.remove(new Position(x--, y--));
                        }
                    } else {
                        while (x > 0 && y > 0) {
                            mouvements.remove(new Position(--x, --y));
                        }
                    }
                } else if (p.getX() > piece.getPosition().getX() && p.getY() < piece.getPosition().getY()) {
                    int x = p.getX();
                    int y = p.getY();
                    if (getPiece(p).getCouleur() == piece.getCouleur()) {
                        while (x <= 7 && y >= 0) {
                            mouvements.remove(new Position(x++, y--));
                        }
                    } else {
                        while (x < 7 && y > 0) {
                            mouvements.remove(new Position(++x, --y));
                        }
                    }
                }
            }
        }

        return mouvements;
    }

    /**
     * Permet d'obtenir les positions menacer par les pièces de l'autre équipe
     *
     * @param p_couleur la couleur du roi menacé
     * @return la collection de toutes les positions menacées par l'autre équipe
     */
    private ArrayList<Position> zoneDangerRoi(PieceBase.Couleur p_couleur) {
        ArrayList<Position> zone = new ArrayList<>();

        for (PieceBase p : m_echiquier) {
            if (p.getCouleur() != p_couleur) {
                for (Position pos : mouvementsPieceEnnemi(p.getPosition())) {
                    zone.add(new Position(pos.getX(), pos.getY()));
                }
                //zone.addAll(mouvementsPieceEnnemi(p.getPosition()));
            }
        }

        return zone;
    }

    /**
     * Permet d'obtenir le Roi du joueur en cours
     *
     * @return le roi
     */
    private PieceBase obtenirRoiCouleur() {
        for (PieceBase p : m_echiquier) {
            if (p.getCouleur() == tourJoueur && p.getType() == PieceBase.TypePiece.ROI) {
                return p;
            }
        }

        throw new NullPointerException("Aucun roi trouver de la couleur donnée");
    }

    /**
     * Permet d'obtenir toutes les positions entre 2 positions
     *
     * @param p_position1 première position
     * @param p_position2 deuxième position
     * @return la collection de toutes les positions entre les deux fournis en paramètre
     */
    private ArrayList<Position> cheminEntrePieces(Position p_position1, Position p_position2) {
        ArrayList<Position> chemin = new ArrayList<>();

        if (p_position1.getY() == p_position2.getY()) {
            if (p_position1.getX() > p_position2.getX()) {
                for (int i = p_position2.getX(); i < p_position1.getX(); i++) {
                    chemin.add(new Position(i, p_position2.getY()));
                }
            } else {
                for (int i = p_position1.getX(); i < p_position2.getX(); i++) {
                    chemin.add(new Position(i, p_position1.getY()));
                }
            }
        } else if (p_position1.getX() == p_position2.getX()) {
            if (p_position1.getY() > p_position2.getY()) {
                for (int i = p_position2.getY(); i < p_position1.getY(); i++) {
                    chemin.add(new Position(p_position2.getX(), i));
                }
            } else {
                for (int i = p_position1.getY(); i < p_position2.getY(); i++) {
                    chemin.add(new Position(p_position1.getX(), i));
                }
            }
        } else {
            int x = p_position1.getX() - p_position2.getX();
            int y = p_position1.getY() - p_position2.getY();
            int xAbs = Math.abs(x);
            int yAbs = Math.abs(y);
            if (xAbs == yAbs) {
                for (int i = 1; i < xAbs; i++) {
                    chemin.add(new Position(p_position1.getX() - (x / xAbs) * i, p_position1.getY() - (y / yAbs) * i));
                }
            }
        }

        return chemin;
    }

    /**
     * Cette méthode vérifie si une pièce occupe la position voulue
     *
     * @param p_position position à vérifier
     * @return Vrai si il n'y a aucune pièce à la position désirée sinon False
     */
    public boolean positionEstLibre(Position p_position) {
        for (PieceBase piece : m_echiquier) {
            if (piece.getPosition().equals(p_position)) {
                if (getPiece(p_position).getCouleur() == piece.getCouleur())
                    return false;
            }
        }

        return true;
    }

    /**
     * Permet de déplacer la pièce sélectionnée
     *
     * @param p_position nouvelle position désirée pour la pièce.
     */
    public void deplacerPieceCourante(Position p_position) {
        if (mouvementsPiece(m_pieceCourante.getPosition()).contains(p_position)) {
            PieceBase pieceCible = getPiece(p_position);
            if (pieceCible != null) {
                m_echiquier.remove(pieceCible);
            }

            m_pieceCourante.deplacer(p_position);

            if (m_pieceCourante.getType() == PieceBase.TypePiece.PION) {
                if (m_pieceCourante.getCouleur() == PieceBase.Couleur.BLANC && m_pieceCourante.getPosition().getY() == 7
                        || m_pieceCourante.getCouleur() == PieceBase.Couleur.NOIR && m_pieceCourante.getPosition().getY() == 0) {
                    m_echiquier.remove(m_pieceCourante);
                    m_echiquier.add(Reine.obtenirPiece(m_pieceCourante.getCouleur(), m_pieceCourante.getPosition()));
                }
            }

            m_pieceCourante = null;
        }
    }

    /**
     * Permet de savoir si l'échiquier a une pièce a la position donnée
     *
     * @param p_position position qui est vérifiée
     * @return si la position est occupée par une pièce
     */
    private boolean contientPosition(Position p_position) {
        for (PieceBase p : m_echiquier) {
            if (p.getPosition().equals(p_position)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Méthode pour remplir l'échiquier au début de la partie
     */
    public void remplir() {
        //Pions blancs
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(0, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(1, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(2, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(3, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(4, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(5, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(6, 1)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 1)));

        //Pions noirs
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(1, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(2, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(5, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(6, 6)));
        m_echiquier.add(Pion.obtenirPiece(PieceBase.Couleur.NOIR, new Position(7, 6)));

        //Tours blanches
        m_echiquier.add(Tour.obtenirPiece(PieceBase.Couleur.BLANC, new Position(0, 0)));
        m_echiquier.add(Tour.obtenirPiece(PieceBase.Couleur.BLANC, new Position(7, 0)));

        //Tours noires
        m_echiquier.add(Tour.obtenirPiece(PieceBase.Couleur.NOIR, new Position(0, 7)));
        m_echiquier.add(Tour.obtenirPiece(PieceBase.Couleur.NOIR, new Position(7, 7)));

        //Cavaliers blancs
        m_echiquier.add(Cavalier.obtenirPiece(PieceBase.Couleur.BLANC, new Position(1, 0)));
        m_echiquier.add(Cavalier.obtenirPiece(PieceBase.Couleur.BLANC, new Position(6, 0)));

        //Cavaliers noirs
        m_echiquier.add(Cavalier.obtenirPiece(PieceBase.Couleur.NOIR, new Position(1, 7)));
        m_echiquier.add(Cavalier.obtenirPiece(PieceBase.Couleur.NOIR, new Position(6, 7)));

        //Fous blancs
        m_echiquier.add(Fou.obtenirPiece(PieceBase.Couleur.BLANC, new Position(2, 0)));
        m_echiquier.add(Fou.obtenirPiece(PieceBase.Couleur.BLANC, new Position(5, 0)));

        //Fous noirs
        m_echiquier.add(Fou.obtenirPiece(PieceBase.Couleur.NOIR, new Position(2, 7)));
        m_echiquier.add(Fou.obtenirPiece(PieceBase.Couleur.NOIR, new Position(5, 7)));

        //Reine blanche
        m_echiquier.add(Reine.obtenirPiece(PieceBase.Couleur.BLANC, new Position(3, 0)));

        //Reine noire
        m_echiquier.add(Reine.obtenirPiece(PieceBase.Couleur.NOIR, new Position(3, 7)));

        //Roi blanc
        m_echiquier.add(Roi.obtenirPiece(PieceBase.Couleur.BLANC, new Position(4, 0)));

        //Roi noir
        m_echiquier.add(Roi.obtenirPiece(PieceBase.Couleur.NOIR, new Position(4, 7)));
    }

    /**
     * Cette méthode retire toutes les pièces d'un échiquier
     */
    public void resetEchiquier() {
        m_echiquier = new ArrayList<>();
        tourJoueur = PieceBase.Couleur.BLANC;
    }

    /**
     * Permet de changer le tour du joueur actif
     */
    public void changerTour() {
        switch (tourJoueur) {
            case NOIR:
                tourJoueur = PieceBase.Couleur.BLANC;
                break;
            case BLANC:
                tourJoueur = PieceBase.Couleur.NOIR;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Permet d'obtenir une pièce en fonction d'une position donnée
     *
     * @param p_position la position cibler pour obtenir une pièce
     * @return la pièce située à la position donnée ou null si aucune pièce présente
     */
    public PieceBase getPiece(Position p_position) {
        for (PieceBase piece : m_echiquier) {
            if (piece.getPosition().equals(p_position)) {
                return piece;
            }
        }

        return null;
    }

    /**
     * Permet de savoir dans quel état la partie est
     *
     * @return si le roi est en échec, échec et mate ou normal
     */
    public etatPartie getEtat() {
        etatPartie etatCourant;

        if (detectionEchec(obtenirRoiCouleur()).size() != 0) {
            etatCourant = etatPartie.ECHEC;
            if (mouvementsPiece(obtenirRoiCouleur().getPosition()).size() == 0) {
                etatCourant = etatPartie.ECHECMATE;
            }
        } else {
            etatCourant = etatPartie.NORMAL;
        }

        return etatCourant;
    }

    public PieceBase.Couleur getTourJoueur() {
        return tourJoueur;
    }

    public ArrayList<PieceBase> getEchiquier() {
        return m_echiquier;
    }

    public void set_pieceCourante(PieceBase m_pieceCourante) {
        this.m_pieceCourante = m_pieceCourante;
    }

    public PieceBase get_pieceCourante() {
        return m_pieceCourante;
    }
}