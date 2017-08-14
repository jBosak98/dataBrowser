package com.jbosak.datadirectory;


import android.widget.TextView;

import org.fluttercode.datafactory.impl.DataFactory;

public class User {
    DataFactory factory = new DataFactory();
    public final String name;
    public final int id;
    public final String details;

    public DataFactory getFactory() {
        return factory;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public User(int id) {
        name = factory.getName();
        this.id = id;
        details = factory.getCity();
    }
}
