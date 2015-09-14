package com.appluncher.solsctice.contactsapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leandro on 12/09/2015.
 */
public class Contact implements Parcelable{

    private String name;
    private Long employeeId;
    private String company;
    private String birthdate;
    private Phone phone;
    private String smallImageURL;
    private String detailsURL;
    private ContactDetail contactDetail;

    public Contact(){

    }

    public Contact(Parcel in){
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();
        employeeId = in.readLong();
        company = in.readString();
        birthdate = in.readString();
        phone = in.readParcelable(getClass().getClassLoader());
        smallImageURL = in.readString();
        detailsURL = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(employeeId);
        dest.writeString(company);
        dest.writeString(birthdate);
        dest.writeParcelable(phone,flags);
        dest.writeString(smallImageURL);
        dest.writeString(detailsURL);
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getDetailsURL() {
        return detailsURL;
    }

    public void setDetailsURL(String detailsURL) {
        this.detailsURL = detailsURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }
}
