package com.appluncher.solsctice.contactsapp.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appluncher.solsctice.contactsapp.R;
import com.appluncher.solsctice.contactsapp.adapter.ContactAdapter;
import com.appluncher.solsctice.contactsapp.domain.Contact;
import com.appluncher.solsctice.contactsapp.service.http.ContactService;
import com.appluncher.solsctice.contactsapp.service.http.builder.ServiceBuilder;
import com.appluncher.solsctice.contactsapp.ui.activity.BaseActivity;
import com.appluncher.solsctice.contactsapp.util.LayoutUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ContactListFragment extends Fragment {



    private ContactAdapter mAdapter;
    private ArrayList<Contact> mContacts = new ArrayList<>();

    public static ContactListFragment newInstance() {
        ContactListFragment fragment = new ContactListFragment();
        return fragment;
    }


    public ContactListFragment() {
        // Required empty public constructor
    }



    private void loadContent(){
        LayoutUtil.getInstance().blockScreen(getActivity());
        ContactService service = ServiceBuilder.createService(ContactService.class);
        service.contactList(new Callback<List<Contact>>() {
            @Override
            public void success(List<Contact> contacts, Response response) {
                mContacts.addAll(contacts);
                mAdapter.setData(contacts);
                LayoutUtil.getInstance().unblockScreen();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("ERROR",error.getLocalizedMessage());
                Toast.makeText(getActivity(),"Connection error",Toast.LENGTH_LONG).show();
                LayoutUtil.getInstance().unblockScreen();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("contacts", mContacts);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ContactAdapter((BaseActivity)getActivity());
        mAdapter.setData(mContacts);
        recyclerView.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            mContacts = savedInstanceState.getParcelableArrayList("contacts");
            mAdapter.setData(mContacts);
        }else{
            if(mContacts.size()==0) {
                loadContent();
            }
        }

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
