package producttracking.iexemplar.com.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import producttracking.iexemplar.com.R;


public class DialogUtil extends Dialog {

    private static final String TAG = DialogUtil.class.getSimpleName();
    String messageTxt;
    int action = 0;
    int color = R.color.colorAccent;
    Activity context;

    public DialogUtil(Activity context, String message) {
        super(context, R.style.Theme_Dialog);
        this.context = context;
        this.messageTxt = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_alert);
        setCancelable(false);
        TextView tvMessage = findViewById(R.id.tv_message);
        tvMessage.setText(messageTxt);
        Button ok = findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }

        });
    }
}
