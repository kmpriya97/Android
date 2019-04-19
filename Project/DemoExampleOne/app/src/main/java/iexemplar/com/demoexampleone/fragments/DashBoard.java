package iexemplar.com.demoexampleone.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import iexemplar.com.demoexampleone.R;

public class DashBoard extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_dashboard, container, false );
        ButterKnife.bind( this, rootView );
        return rootView;
    }
}
