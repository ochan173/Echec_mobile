package com.example.a1738253.echec_mobile.echec.gui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1738253.echec_mobile.echec.Echiquier;
import com.example.a1738253.echec_mobile.echec.Joueur;
import com.example.a1738253.echec_mobile.echec.pieces.PieceBase;
import com.example.a1738253.echec_mobile.echec.Position;
import com.example.a1738253.echec_mobile.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private TableLayout m_boardEchichier;
    private ImageButton[][] m_boardXY;
    private TextView m_tour;
    private Joueur m_joueur1, m_joueur2;
    private static int m_orientation = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater p_inflater, @Nullable ViewGroup p_container, @Nullable Bundle savedInstanceState) {
       View v = p_inflater.inflate(R.layout.fragment_main, p_container, false);

        if (DialogNomJoueur.result) {
            DialogNomJoueur dlg = new DialogNomJoueur(getActivity());
            dlg.show();
            m_joueur1 = new Joueur(DialogNomJoueur.m_nomJ1);
            m_joueur1.set_couleur(PieceBase.Couleur.BLANC);

            m_joueur2 = new Joueur(DialogNomJoueur.m_nomJ2);
            m_joueur2.set_couleur(PieceBase.Couleur.NOIR);
        }

        m_boardEchichier = v.findViewById(R.id.echiquier);

        genererBoard();
        genererFooter();
        m_orientation *= -1;

        afficherEchiquier();

        return v;
    }

    private void afficherEchiquier() {
        for (final PieceBase p : Echiquier.getInstance().getEchiquier()) {

            if (Echiquier.getInstance().getEtat() != Echiquier.etatPartie.ECHECMATE) {

                m_boardXY[p.getPosition().getX()][p.getPosition().getY()].setImageDrawable(getResources().getDrawable(getRepresentation(p)));

                if (p.getCouleur() == Echiquier.getInstance().getTourJoueur()) {
                    jouerTour(p);
                } else {
                    desactiverCouleur();
                }
            }
            else {
                gameOver();
            }
        }
    }

    private void jouerTour(final PieceBase p_piece) {
        if (Echiquier.getInstance().mouvementsPiece(p_piece.getPosition()).size() == 0) {
            m_boardXY[p_piece.getPosition().getX()][p_piece.getPosition().getY()].setEnabled(false);
            return;
        }

        if (p_piece.getType() == PieceBase.TypePiece.ROI) {
            m_boardXY[p_piece.getPosition().getX()][p_piece.getPosition().getY()].setEnabled(true);
        }
        m_boardXY[p_piece.getPosition().getX()][p_piece.getPosition().getY()].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Echiquier.getInstance().set_pieceCourante(p_piece);
                afficherPositionsPossible(p_piece);
            }
        });
    }

    private void afficherPositionsPossible(final PieceBase p_piece) {
        desactiverBoutons();
        final Position positionInitiale = new Position(p_piece.getPosition().getX(), p_piece.getPosition().getY());
        m_boardXY[p_piece.getPosition().getX()][p_piece.getPosition().getY()].setEnabled(true);
        final ArrayList<Position> positions = Echiquier.getInstance().mouvementsPiece(p_piece.getPosition());

        for (final Position p : positions) {
            m_boardXY[p.getX()][p.getY()].setEnabled(true);
            m_boardXY[p.getX()][p.getY()].setBackgroundColor(Color.rgb(0, 255, 0));

            m_boardXY[p_piece.getPosition().getX()][p_piece.getPosition().getY()].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colorerEchiquier();
                    activerBoutons();
                    afficherEchiquier();
                }
            });

            m_boardXY[p.getX()][p.getY()].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!p.equals(p_piece.getPosition())) {
                        Echiquier.getInstance().deplacerPieceCourante(p);
                        m_tour.setText(Echiquier.getInstance().getTourJoueur().toString());
                        m_boardXY[positionInitiale.getX()][positionInitiale.getY()].setImageDrawable(null);
                        m_boardXY[positionInitiale.getX()][positionInitiale.getY()].setOnClickListener(null);
                        desactiverOnClick(positions);
                    }
                    colorerEchiquier();
                    activerBoutons();
                    afficherEchiquier();
                }
            });
        }
    }

    private void gameOver()  {
        desactiverBoutons();
        String vainqueur = getVainqueur();
        Toast toast = Toast.makeText(getContext(), "Echec et Math, Vainqueur : " + vainqueur, Toast.LENGTH_LONG);
        toast.show();
    }

    private void nettoyerEchiquier() {
        for (int y = 0; y <= 7; y++) {
            for (int x = 0; x <= 7; x++) {
                m_boardXY[x][y].setImageDrawable(null);
            }
        }
    }

    private String getVainqueur() {
        Echiquier.getInstance().changerTour();

        if (Echiquier.getInstance().getTourJoueur() == m_joueur1.get_couleur()) {
            return m_joueur1.get_nom();
        }
        else {
            return m_joueur2.get_nom();
        }
    }

    private void desactiverOnClick(ArrayList<Position> p_positions) {
        for (Position position : p_positions) {
            m_boardXY[position.getX()][position.getY()].setOnClickListener(null);
        }
    }

    private void desactiverCouleur() {
        for (PieceBase pieceBase : Echiquier.getInstance().getEchiquier()) {
            if (pieceBase.getCouleur() != Echiquier.getInstance().getTourJoueur()) {
                m_boardXY[pieceBase.getPosition().getX()][pieceBase.getPosition().getY()].setOnClickListener(null);
            }
        }
    }

    private void desactiverBoutons() {
      for (int y = 0; y <= 7; y++) {
          for (int x = 0; x <= 7; x++) {
              m_boardXY[x][y].setEnabled(false);
          }
      }
    }

    private void activerBoutons() {
        for (int y = 0; y <= 7; y++) {
            for (int x = 0; x <= 7; x++) {
                m_boardXY[x][y].setEnabled(true);
            }
        }
    }

    private int getRepresentation(PieceBase p) {
        if (p.getCouleur() == PieceBase.Couleur.BLANC) {
            switch (p.getType()) {
                case CAVALIER: return R.drawable.cavalier_blanc;
                case FOU: return R.drawable.fou_blanc;
                case ROI: return R.drawable.roi_blanc;
                case TOUR: return R.drawable.tour_blanc;
                case REINE: return R.drawable.reine_blanc;
                default: return R.drawable.pion_blanc;
            }
        }
        else {
            switch (p.getType()) {
                case CAVALIER: return R.drawable.cavalier_noir;
                case FOU: return R.drawable.fou_noir;
                case ROI: return R.drawable.roi_noir;
                case TOUR: return R.drawable.tour_noir;
                case REINE: return R.drawable.reine_noir;
                default: return R.drawable.pion_noir;
            }
        }
    }

    private void genererFooter() {
        Button m_reset;
        TableRow footer = new TableRow(this.getContext());
        TextView tourJoueur = new TextView(this.getContext());

        m_reset = new Button(getContext());
        m_reset.setLayoutParams(new TableLayout.LayoutParams
                (TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT));
        m_reset.setText(R.string.reset);

        tourJoueur.setText(R.string.tour_joueur);
        m_tour = new TextView(this.getContext());
        m_tour.setText(Echiquier.getInstance().getTourJoueur().toString());
        TableLayout.LayoutParams tableRowParams=
                new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);
        m_reset.setBackgroundColor(Color.GRAY);

        m_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Echiquier.getInstance().resetEchiquier();
                Echiquier.getInstance().remplir();

                nettoyerEchiquier();
                afficherEchiquier();
                m_tour.setText(Echiquier.getInstance().getTourJoueur().toString());
                activerBoutons();
            }
        });

        int leftMargin=10;
        int topMargin=35;
        int rightMargin=10;
        int bottomMargin=2;

        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        footer.setLayoutParams(tableRowParams);

        footer.addView(tourJoueur);
        footer.addView(m_tour);
        footer.addView(m_reset, 20, 100);
        m_boardEchichier.addView(footer);
    }

    private void colorerEchiquier() {
        int couleur;

        for (int y = 0; y <= 7; y++) {

            if (y % 2 == 1) {
                couleur = -1;
            }
            else {
                couleur = 1;
            }

            for (int x = 0; x <= 7; x++) {
                if (couleur == -1) {
                    m_boardXY[x][y].setBackgroundColor(Color.rgb(255,248,220));
                }
                else {
                    m_boardXY[x][y].setBackgroundColor(Color.rgb(205,133,63));
                }

                couleur *= -1;
            }
        }
    }


    private void genererBoard() {
        m_boardXY = new ImageButton[8][8];

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);

        for (int y = 7; y >= 0; y--) {
            TableRow rangee = new TableRow(this.getContext());

            for (int x = 0; x <= 7; x++) {
                ImageButton b = new ImageButton(this.getContext());
                b.setBackgroundResource(outValue.resourceId);

                m_boardXY[x][y] = b;

                if (m_orientation == -1) {
                    rangee.addView(m_boardXY[x][y], width/10, height/10);
                } else if (m_orientation == 1){
                    rangee.addView(m_boardXY[x][y], width/8, height/10);
                }
            }
            m_boardEchichier.addView(rangee);
        }
        colorerEchiquier();
    }
}