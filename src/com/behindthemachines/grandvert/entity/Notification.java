package com.behindthemachines.grandvert.entity;

import java.util.Date;

/**
 *
 * @author ROCH
 */
public class Notification {
    private int id;
    private int id_user;
    private String title;
    private String description;
    private Date notification_date;
    private String root;
    private int seen;

    public Notification(int id, int id_user, String title, String description, Date notification_date, String root, int seen) {
        this.id = id;
        this.id_user = id_user;
        this.title = title;
        this.description = description;
        this.notification_date = notification_date;
        this.root = root;
        this.seen = seen;
    }

    public Notification(int id_user, String title, String description, Date notification_date, String root, int seen) {
        this.id_user = id_user;
        this.title = title;
        this.description = description;
        this.notification_date = notification_date;
        this.root = root;
        this.seen = seen;
    }

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNotification_date() {
        return notification_date;
    }

    public void setNotification_date(Date notification_date) {
        this.notification_date = notification_date;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", id_user=" + id_user + ", title=" + title + ", description=" + description + ", notification_date=" + notification_date + ", root=" + root + ", seen=" + seen + '}';
    }

    
    

}
