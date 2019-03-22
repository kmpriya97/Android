package producttracking.iexemplar.com.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import producttracking.iexemplar.com.R;

import pl.droidsonroids.gif.GifImageView;

public class ProgressUtil {

    public static ProgressDialog showDialog(Activity mActivity) {
        ProgressDialog mProgressDialog = ProgressDialog.show(mActivity, null, null, true);
        mProgressDialog.setContentView(R.layout.dialog_progress);
        mProgressDialog.setCancelable(false);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        GifImageView gifImageView = mProgressDialog.findViewById(R.id.GifImageView);
        gifImageView.setImageResource(R.drawable.loading);
        mProgressDialog.show();
        return mProgressDialog;
    }
}
