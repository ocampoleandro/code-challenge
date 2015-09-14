package com.appluncher.solsctice.contactsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appluncher.solsctice.contactsapp.R;
import com.appluncher.solsctice.contactsapp.domain.Contact;
import com.appluncher.solsctice.contactsapp.ui.activity.BaseActivity;
import com.appluncher.solsctice.contactsapp.ui.fragment.ContactDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leandro on 12/09/2015.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder>{

    private BaseActivity context;
    private List<Contact> contacts = new ArrayList<>();

    public ContactAdapter(BaseActivity context){
        this.context = context;
    }

    public void setData(List<Contact> contacts){
        if(contacts!=null){
            this.contacts.addAll(contacts);
            notifyDataSetChanged();
        }
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact_list_row, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        final Contact contact = contacts.get(position);
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.changeFragment(ContactDetailFragment.newInstance(contact));
            }
        });
        holder.contactName.setText(contact.getName());
        if(contact.getPhone()!=null){
            holder.phoneMobile.setText("Mobile: "+contact.getPhone().getMobile());
            holder.phoneHome.setText("Home: "+contact.getPhone().getHome());
            holder.phoneWork.setText("Work: "+contact.getPhone().getWork());
        }
        Picasso.with(context)
                .load(contact.getSmallImageURL())
                .resize(90,90)
                .into(holder.imageContact);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder{

        private View row;
        private ImageView imageContact;
        private TextView contactName;
        private TextView phoneWork;
        private TextView phoneHome;
        private TextView phoneMobile;

        public ContactHolder(View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.row);

            imageContact = (ImageView) itemView.findViewById(R.id.contact_image);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            phoneWork = (TextView) itemView.findViewById(R.id.phone_work);
            phoneHome = (TextView) itemView.findViewById(R.id.phone_home);
            phoneMobile = (TextView) itemView.findViewById(R.id.phone_mobile);
        }


    }
}
