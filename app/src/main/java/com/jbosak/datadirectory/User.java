package com.jbosak.datadirectory;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class User implements Parcelable {
    private String name;
    private final int id;
    private String details;
    private HttpURLConnection urlConnection;
    private JSONObject userJSONObject;
    private StringBuilder builder;



    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public User(int id)  {
        try {
                downloadData();
            if (!userJSONObject.isNull("name")){
                details = createDetails();
                name = createName();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.id = id;

        }



    private String createDetails() throws JSONException {
        builder = new StringBuilder();
        JSONObject jsonObject = userJSONObject.getJSONObject("location");
        builder.append(jsonObject.getString("street").substring(0,1).toUpperCase() + jsonObject.getString("street").substring(1)+ "\n")
                .append(jsonObject.getString("city").substring(0,1).toUpperCase() + jsonObject.getString("city").substring(1)+ "\n")
                .append(jsonObject.getString("state").substring(0,1).toUpperCase() + jsonObject.getString("state").substring(1)+ "\n")
                .append(jsonObject.getString("postcode").substring(0,1).toUpperCase() + jsonObject.getString("postcode").substring(1));
        return builder.toString();
    }

    private String createName() throws JSONException {
        builder = new StringBuilder();
        JSONObject jsonObject = userJSONObject.getJSONObject("name");
        builder.append(jsonObject.getString("title").toUpperCase() + " ")
                .append(jsonObject.getString("first").toUpperCase()+ " ")
                .append(jsonObject.getString("last").toUpperCase()+ " ");
        return builder.toString();
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

    public void downloadData() throws JSONException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                urlConnection = null;
                try {
                    URL url = new URL("https://randomuser.me/api/?nat=gb");
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    userJSONObject = readRandomUser(in).getJSONArray("results").getJSONObject(0);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    urlConnection.disconnect();
                }

            }

        });

        thread.start();
        try {

            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private JSONObject readRandomUser(InputStream inputStream) {
        BufferedReader bR = new BufferedReader(  new InputStreamReader(inputStream));
        String line = "";
        JSONObject result = null;

        StringBuilder responseStrBuilder = new StringBuilder();
        try {
            while((line =  bR.readLine()) != null){

                responseStrBuilder.append(line);
            }
            inputStream.close();
            result = new JSONObject(responseStrBuilder.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


        if (result != null) {
            System.out.println(result.toString());
            return result;
        }
        
        return result;
    }
}





