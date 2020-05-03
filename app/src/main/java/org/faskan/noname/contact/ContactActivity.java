package org.faskan.noname.contact;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.faskan.noname.R;
import org.faskan.noname.model.Contact;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Contact contact = (Contact) getIntent().getSerializableExtra("contact");
        setTitle(contact.getName());
    }
}
