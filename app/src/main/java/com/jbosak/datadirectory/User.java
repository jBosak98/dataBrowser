package com.jbosak.datadirectory;


import android.os.Parcel;
import android.os.Parcelable;

import org.fluttercode.datafactory.impl.DataFactory;

public class User implements Parcelable {
    DataFactory factory = new DataFactory();
    public final String name;
    public final int id;
    public final String details;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n" + factory.getAddress())
                .append("\n" + factory.getCity())
                .append("\n" + factory.getEmailAddress())
                .append("\n" + factory.getStreetName() + " " + factory.getStreetSuffix());
        return builder.toString();
    }

    public User(int id) {
        name = factory.getName();
        this.id = id;
        details = factory.getCity();

        }

    public User(Parcel input){
        id = input.readInt();
        details = input.readString();
        name = input.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(details);
        dest.writeInt(id);


    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
