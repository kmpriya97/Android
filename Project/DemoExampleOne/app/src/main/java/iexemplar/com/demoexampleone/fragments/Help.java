package iexemplar.com.demoexampleone.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import iexemplar.com.demoexampleone.R;
import iexemplar.com.demoexampleone.ui.MainScreen;

/*******************************************************************************
 * Created by iExemplar on ${Date}.
 * <p>
 * Copyright (c) 2016 iExemplar. All rights reserved.
 *******************************************************************************/

public class Help extends Fragment {
    @BindView(R.id.btn_help)
    Button btHelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_help, container, false );
        ButterKnife.bind( this, rootView );
        return rootView;
    }

    @OnClick(R.id.btn_help)
    void Click() {
      Intent intent =new Intent(getActivity(),MainScreen.class);
      startActivity( intent );
    }

}







