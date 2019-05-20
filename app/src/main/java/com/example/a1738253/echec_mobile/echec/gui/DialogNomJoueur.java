package com.example.a1738253.echec_mobile.echec.gui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a1738253.echec_mobile.R;

class DialogNomJoueur extends Dialog {
    static boolean result = true;
    private EditText m_joueur1, m_joueur2;
    //private Button m_confirmer;
    static  String m_nomJ1, m_nomJ2;

    DialogNomJoueur(Activity p_activity) {
        super(p_activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button m_confirmer;

        setContentView(R.layout.dialog_joueur);
        m_joueur1 = findViewById(R.id.joueur1);
        m_joueur2 = findViewById(R.id.joueur2);
        m_confirmer = findViewById(R.id.btnConfirmer);

        m_confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_joueur1.getText().length() == 0) {
                    m_joueur1.setText(R.string.nom_invalide);
                }
                else if (m_joueur2.getText().length() == 0) {
                    m_joueur2.setText(R.string.nom_invalide);
                }
                else {
                    m_nomJ1 = m_joueur1.getText().toString();
                    m_nomJ2 = m_joueur2.getText().toString();
                    result = false;
                    dismiss();
                }
            }
        });
    }
}