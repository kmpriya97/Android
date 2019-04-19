package iexemplar.com.demoexampleone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import iexemplar.com.demoexampleone.R;
import iexemplar.com.demoexampleone.app.AppBaseActivity;

public class FirstScreen extends AppBaseActivity {

    @BindView(R.id.btn_Clickme)
    Button btnCLick;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.btn_Clickme)
    void btnClick()
    {
      Intent intent  =new Intent(FirstScreen.this, NavigationActivity.class);
      startActivity(intent);
    }
}
