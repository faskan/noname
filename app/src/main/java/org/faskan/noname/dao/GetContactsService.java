package org.faskan.noname.dao;

import org.faskan.noname.contact.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetContactsService {

    @GET("/contacts")
    Call<List<Contact>> getAllContacts();
}
