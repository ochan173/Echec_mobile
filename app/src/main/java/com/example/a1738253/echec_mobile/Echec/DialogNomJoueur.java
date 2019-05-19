package com.example.a1738253.echec_mobile.Echec;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import com.example.a1738253.echec_mobile.R;

public class DialogNomJoueur extends Dialog {

    private Activity m_activity;
    private  Dialog m_dialog;
    private EditText m_joueur1, m_joueur2;

    public DialogNomJoueur(Activity p_activity) {
        super(p_activity);

        this.m_activity = p_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_joueur);
        m_joueur1 = findViewById(R.id.joueur1);
        m_joueur2 = findViewById(R.id.joueur2);
    }
}
