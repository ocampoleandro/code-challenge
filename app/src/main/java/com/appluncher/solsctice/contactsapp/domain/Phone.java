package com.appluncher.solsctice.contactsapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leandro on 12/09/2015.
 */
public class Phone implements Parcelable{

    private String work;
    private String home;
    private String mobile;

    public Phone(){

    }

    public Phone(Parcel in){
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        work = in.readString();
        home = in.readString();
        mobile = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(work);
        dest.writeString(home);
        dest.writeString(mobile);
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
