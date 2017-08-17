package com.jbosak.datadirectory;


import android.widget.TextView;

import org.fluttercode.datafactory.impl.DataFactory;

public class User {
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
        builder.append("Address: " + factory.getAddress()).append(factory.getCity());
        return builder.toString();
    }

    public User(int id) {
        name = factory.getName();
        this.id = id;
        details = factory.getCity();
    }
}
