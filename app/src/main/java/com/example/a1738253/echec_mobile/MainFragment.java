package com.example.a1738253.echec_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainFragment extends Fragment {
    TableLayout m_boardEchichier;
    private Button[][] m_boardXY;
    private static int m_orientation = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater p_inflater, @Nullable ViewGroup p_container, @Nullable Bundle savedInstanceState) {
       View v = p_inflater.inflate(R.layout.fragment_main, p_container, false);

        m_boardEchichier = v.findViewById(R.id.echiquier);

        //onConfigurationChanged();
        genererBoard();
        m_orientation *= -1;
        return v;
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



    public void genererBoard() {
        //rangee.setLayoutParams();

        m_boardXY = new Button[8][8];

//        Display display = getActivity().getWindowManager().getDefaultDisplay();
//        int stageWidth = display.getWidth();
//        int stageHeight = display.getHeight();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
        int couleur = 0;

        for (int y = 7; y >= 0; y--) {
            TableRow rangee = new TableRow(this.getContext());

            if (y % 2 == 1) {
                couleur = -1;
            }
            else {
                couleur = 1;
            }

            //rangee.setLayoutParams((new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT)));
            for (int x = 0; x <= 7; x++) {
                Button b = new Button(this.getContext());
                b.setBackgroundResource(outValue.resourceId);

                if (couleur == -1) {
                    b.setBackgroundColor(Color.rgb(255,248,220));
                }
                else {
                    b.setBackgroundColor(Color.rgb(205,133,63));
                }

                couleur *= -1;

                m_boardXY[x][y] = b;
                // android.widget.TableLayout.LayoutParams lp = new TableLayout.LayoutParams(20,30); // 60 is height you can set it as u need

                //b.setLayoutParams(lp);
                b.setText(x + "," + y);
                if (m_orientation == -1) {
                    rangee.addView(m_boardXY[x][y], width/10, height/10);
                } else if (m_orientation == 1){
                    rangee.addView(m_boardXY[x][y], width/8, height/10);
                }
            }
            m_boardEchichier.addView(rangee);
        }
        genererFooter();
    }
}

