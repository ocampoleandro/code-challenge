package com.appluncher.solsctice.contactsapp.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appluncher.solsctice.contactsapp.R;
import com.appluncher.solsctice.contactsapp.domain.Address;
import com.appluncher.solsctice.contactsapp.domain.Contact;
import com.appluncher.solsctice.contactsapp.domain.ContactDetail;
import com.appluncher.solsctice.contactsapp.domain.Phone;
import com.appluncher.solsctice.contactsapp.service.http.ContactService;
import com.appluncher.solsctice.contactsapp.service.http.builder.ServiceBuilder;
import com.appluncher.solsctice.contactsapp.ui.activity.BaseActivity;
import com.appluncher.solsctice.contactsapp.util.DateUtil;
import com.appluncher.solsctice.contactsapp.util.LayoutUtil;
import com.squareup.picasso.Picasso;

import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ContactDetailFragment extends Fragment {
    private static final String ARG_CONTACT = "CONTACT";

    // TODO: Rename and change types of parameters
    private Contact mContact;


    public static ContactDetailFragment newInstance(Contact contact) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = getArguments().getParcelable(ARG_CONTACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_contact_detail, container, false);
        loadContent(view);
        return view;
    }

    private void loadContent(final View view){
        LayoutUtil.getInstance().blockScreen(getActivity());

        ContactService service = ServiceBuilder.createService(ContactService.class);
        service.detail(mContact.getEmployeeId(), new Callback<ContactDetail>() {
            @Override
            public void success(ContactDetail contactDetail, Response response) {
                mContact.setContactDetail(contactDetail);
                fillView(view);
                LayoutUtil.getInstance().unblockScreen();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("ERROR", error.getLocalizedMessage());
                LayoutUtil.getInstance().unblockScreen();
                Toast.makeText(getActivity(), "Connection error", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void fillView(View view){
        ImageView imageContact = (ImageView) view.findViewById(R.id.contact_image);
        Picasso.with(getActivity())
                .load(mContact.getContactDetail().getLargeImageURL())
                .resize(125, 125)
                .into(imageContact);
        TextView contactName = (TextView) view.findViewById(R.id.contact_name);
        contactName.setText(mContact.getName());
        TextView companyName = (TextView) view.findViewById(R.id.company_name);
        companyName.setText(mContact.getCompany());
        if(mContact.getContactDetail().getFavorite()!=null &&
                mContact.getContactDetail().getFavorite()){
            view.findViewById(R.id.favorite).setVisibility(View.VISIBLE);
        }
        if(mContact.getPhone()!=null){
            Phone phone = mContact.getPhone();
            if(phone.getHome()!=null && !phone.getHome().isEmpty()){
                TextView phoneHome = (TextView) view.findViewById(R.id.phone_home);
                phoneHome.setText(phone.getHome());
                view.findViewById(R.id.container_phone_home).setVisibility(View.VISIBLE);
            }
            if(phone.getMobile()!=null && !phone.getMobile().isEmpty()){
                TextView phoneMobile = (TextView) view.findViewById(R.id.phone_mobile);
                phoneMobile.setText(phone.getMobile());
                view.findViewById(R.id.container_phone_mobile).setVisibility(View.VISIBLE);
            }
            if(phone.getWork()!=null && !phone.getWork().isEmpty()){
                TextView phoneWork = (TextView) view.findViewById(R.id.phone_work);
                phoneWork.setText(phone.getWork());
                view.findViewById(R.id.container_phone_work).setVisibility(View.VISIBLE);
            }
        }
        TextView birthdate = (TextView) view.findViewById(R.id.birthdate);
        String formattedDate = DateUtil.
                getFormattedDateCalendar(new Date(Long.parseLong(mContact.getBirthdate())));
        birthdate.setText(formattedDate);
        TextView email = (TextView) view.findViewById(R.id.email);
        email.setText(mContact.getContactDetail().getEmail());
        Address address =mContact.getContactDetail().getAddress();
        if(address!=null && address.getStreet()!=null && address.getCity()!=null){
            TextView addressView = (TextView) view.findViewById(R.id.address);
            addressView.setText(address.getStreet()+" "+address.getCity());
        }
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
