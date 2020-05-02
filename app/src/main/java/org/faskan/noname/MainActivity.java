package org.faskan.noname;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.faskan.noname.contact.Contact;

public class MainActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(savedInstanceState == null){
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_container, ContactFragment.newInstance(1))
//                    .commitNow();
//        }
    }

    @Override
    public void onListFragmentInteraction(Contact contact) {

    }
}
