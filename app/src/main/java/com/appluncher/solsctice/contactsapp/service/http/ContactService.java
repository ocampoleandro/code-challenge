package com.appluncher.solsctice.contactsapp.service.http;

import com.appluncher.solsctice.contactsapp.domain.Contact;
import com.appluncher.solsctice.contactsapp.domain.ContactDetail;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by leandro on 12/09/2015.
 */
public interface ContactService {

    @GET("/external/contacts.json")
    void contactList(Callback<List<Contact>> contacts);

    @GET("/external/Contacts/id/{id}.json")
    void detail(@Path("id") long contactId,Callback<ContactDetail> contactDetail);
}
