package com.androidsdk.snaphy.snaphyandroidsdk.models;

/**
 * Created by snaphy on 12/9/16.
 */


import com.strongloop.android.remoting.Repository;


import java.util.Map;


/**
 * A local representative of a single user instance on the server. Derived from Model,
 * the data is immediately accessible locally, but can be saved, destroyed, etc. from the
 * server easily.
 */

public class User extends Model {

    private String realm;
    private String email;
    private String password;
    private Boolean emailVerified;
    private String status;

    public User(Repository repository,
                Map<String, ? extends Object> creationParameters) {
        super(repository, creationParameters);
    }

    public User() {
        this(null, null);
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getRealm() {
        return realm;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
