package com.example.micael.details;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.security.KeyStore;


public class MainActivity extends Activity implements MyListFragment.OnItemSelectedListener, DetailFragment.OnItemListener, MyListFragment.OnErase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getResources().getBoolean(R.bool.dual_panel);//para elegir land o portrait
        setContentView(R.layout.activity_main);

// get fragment manager
        FragmentManager fm = getFragmentManager();

// add
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.detailFragment, new DetailFragment());
// alternatively add it with a tag
// trx.add(R.id.your_placehodler, new YourFragment(), "detail");
        ft.commit();

    }

    //metodos implementados
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
        DetailFragment fragment1 = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if ((fragment1 == null || !fragment1.isInLayout())) {

                Intent intent = new Intent(this, ActivityDetail.class);
                String newTime = String.valueOf(System.currentTimeMillis());
                intent.putExtra("ENVIAR CUENTA", newTime);
                startActivityForResult(intent, 1);   //request code

        } else{

            fragment1.setText(link);
        }

    }


    //metodos cambiar de activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){

                TextView textView = (TextView) findViewById(R.id.devuelveText);
                textView.setText(data.getStringExtra("RESULTADO"));
            }
        }
    }

    @Override
    public void OnEraseItemSelected(String link) {
        DetailFragment fragment1 = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        MyListFragment fragment2 = (MyListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
        if ((fragment1 == null || !fragment1.isInLayout())) {
            fragment2.erase();


        } else{

            fragment2.erase();
        }
    }
}
