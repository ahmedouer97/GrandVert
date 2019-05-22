

package com.behindthemachines.grandvert.utils;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.User;

/**
 *
 * @author dsfounis
 */
public class FacebookConnector {

    /* Variables */
    private final String pageAccessToken = "EAAEPQX1kFoMBAAib4sP8dJdCkQ9FRqzLLBxME2n1uBQMAULwKZCcjEcL4vSL4nv1Dpk2dkF3NRiF6k5haAKKbt8AgZABjs4lhx9jWYT3ZBF3NjyKo0NXMIfw3toYIzAp4faCvPoyz4hmeQTbHNt81byBphg3xFopkOSwCGtRLYhQIU3ZCZBc8eKn68rdxBWgCUhF5UrfKgwZDZD";
    private final String pageID = "644963628982950";
    private FacebookClient fbClient;
    private User myuser = null;    //Store references to your user and page
    private Page mypage = null;    //for later use. In this answer's context, these
                                   //references are useless.
    private int counter = 0;

    public FacebookConnector() {
        try {

            fbClient = new DefaultFacebookClient(pageAccessToken, Version.UNVERSIONED);
            myuser = fbClient.fetchObject("me", User.class);
            mypage = fbClient.fetchObject(pageID, Page.class);
            counter = 0;
        } catch (FacebookException ex) {     //So that you can see what went wrong
            ex.printStackTrace(System.err);  //in case you did anything incorrectly
        }
    }

    public void makeTestPost() {
        fbClient.publish(pageID + "/feed", FacebookType.class, Parameter.with("link", "http://www.google.com"));
        counter++;
        System.out.println("Done !!");
    }

}
