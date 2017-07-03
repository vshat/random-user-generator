
package com.github.vshat.randomusergenerator.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;
    public final static Parcelable.Creator<Id> CREATOR = new Creator<Id>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Id createFromParcel(Parcel in) {
            Id instance = new Id();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.value = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Id[] newArray(int size) {
            return (new Id[size]);
        }

    }
    ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
