package producttracking.iexemplar.com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.app.ApplicationContext;

/*******************************************************************************
 * Created by iExemplar on ${Date}.
 * <p>
 * Copyright (c) 2016 iExemplar. All rights reserved.
 *******************************************************************************/

public class Reset extends Fragment implements ApplicationContext {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }




}
