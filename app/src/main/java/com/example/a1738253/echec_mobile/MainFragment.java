package com.example.a1738253.echec_mobile;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.a1738253.echec_mobile.Echec.DialogNomJoueur;
import com.example.a1738253.echec_mobile.Echec.Echiquier;
import com.example.a1738253.echec_mobile.Echec.Pieces.PieceBase;
import com.example.a1738253.echec_mobile.Echec.Position;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    TableLayout m_boardEchichier;
    private ImageButton[][] m_boardXY;
    private static int m_orientation = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DialogNomJoueur dlg = new DialogNomJoueur(getActivity());
        dlg.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater p_inflater, @Nullable ViewGroup p_container, @Nullable Bundle savedInstanceState) {
       View v = p_inflater.inflate(R.layout.fragment_main, p_container, false);

        m_boardEchichier = v.findViewById(R.id.echiquier);

        genererBoard();
        m_orientation *= -1;

        Echiquier.getInstance().remplir();

        afficherEchiquier();

        return v;
    }

    private void afficherEchiquier() {
        for (final PieceBase p : Echiquier.getInstance().getEchiquier()) {

            //TODO if pas en echec
            m_boardXY[p.getPosition().getX()][p.getPosition().getY()].setImageDrawable(getResources().getDrawable(getRepresentation(p)));

            if (Echiquier.getInstance().mouvementsPiece(p.getPosition()).size() > 0) {
                m_boardXY[p.getPosition().getX()][p.getPosition().getY()].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Echiquier.getInstance().set_pieceCourante(p);
                        afficherPositionsPossible(p);
                    }
                });
            }
        }
    }

    public void afficherPositionsPossible(final PieceBase p_piece) {
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

    private void desactiverOnClick(ArrayList<Position> p_positions) {
        for (Position position : p_positions) {
            m_boardXY[position.getX()][position.getY()].setOnClickListener(null);
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

    public int getRepresentation(PieceBase p) {
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


    public void genererFooter() {
        TableRow footer = new TableRow(this.getContext());
        TextView tourJoueur = new TextView(this.getContext());
        tourJoueur.setText(R.string.tour_joueur);
        TextView joueur = new TextView(this.getContext());
        joueur.setText("test");

        TableLayout.LayoutParams tableRowParams=
                new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

        int leftMargin=10;
        int topMargin=35;
        int rightMargin=10;
        int bottomMargin=2;

        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        footer.setLayoutParams(tableRowParams);

        footer.addView(tourJoueur);
        footer.addView(joueur);
        m_boardEchichier.addView(footer);
    }

    private void colorerEchiquier() {
        int couleur = 0;

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

    public void genererBoard() {
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
        genererFooter();
    }


}

