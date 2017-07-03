
package com.github.vshat.randomusergenerator.model.data;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<User> users = null;
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<ApiResponse> CREATOR = new Creator<ApiResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ApiResponse createFromParcel(Parcel in) {
            ApiResponse instance = new ApiResponse();
            in.readList(instance.users, (User.class.getClassLoader()));
            instance.info = ((Info) in.readValue((Info.class.getClassLoader())));
            instance.error = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ApiResponse[] newArray(int size) {
            return (new ApiResponse[size]);
        }

    }
    ;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(users);
        dest.writeValue(info);
        dest.writeValue(error);
    }

    public int describeContents() {
        return  0;
    }

}
