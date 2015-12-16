package com.example.micael.details;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.TextView;


public class ActivityDetail extends Activity implements MyListFragment.OnItemSelectedListener, DetailFragment.OnItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String message = intent.getStringExtra("ENVIAR CUENTA");

        TextView textView = (TextView) findViewById(R.id.detailsText);
        textView.setText(message);

        //set the text view as the activity layout
        //setContentView(textView);
        //setContentView(R.layout.activity_detail);
    }


    @Override
    public void onOkItemSelected(String link) {
        MyListFragment fragment2 = (MyListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
        if (fragment2 == null || !fragment2.isInLayout()) {
            Intent intentResultado = new Intent();
            intentResultado.putExtra("RESULTADO", "LISTO");
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        } else {
            fragment2.setText(link);
        }
    }

    @Override
    public void onRssItemSelected(String link) {
        MyListFragment fragment1 = (MyListFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if (fragment1 == null || !fragment1.isInLayout() && !findViewById(R.id.button2).isSelected()) {
            Intent intentResultado = new Intent();
            intentResultado.putExtra("RESULTADO", "LISTO");
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        } else {
            fragment1.setText(link);
        }
    }
}
