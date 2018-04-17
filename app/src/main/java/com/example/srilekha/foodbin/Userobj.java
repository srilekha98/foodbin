package com.example.srilekha.foodbin;

/**
 * Created by Srilekha on 17-04-2018.
 */

public class Userobj {

    String email;
    String username;
    String password;
    public Userobj()
    {

    }
    public Userobj(String email1,String username1,String password1)
    {
        email=email1;username=username1;password=password1;
    }
    boolean ifequal(Userobj a)
    {
        if(email.equals(a.email) && password.equals(a.password))
            return true;
        return false;
    }
    boolean emaileq(String email1)
    {
        if(email.equals(email1))
        {
            return true;
        }
        return false;
    }

}
