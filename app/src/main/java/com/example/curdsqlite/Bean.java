package com.example.curdsqlite;

import android.graphics.Bitmap;

public class Bean {
    String Id;
    String Name;
    String Surname;
    String Marks;
   /* Bitmap edit;
    Bitmap delete;*/

    public Bean(String id, String name, String surname, String marks) {
        Id = id;
        Name = name;
        Surname = surname;
        Marks = marks;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getMarks() {
        return Marks;
    }

    public void setMarks(String marks) {
        Marks = marks;
    }

   /* public Bitmap getEdit() {
        return edit;
    }

    public void setEdit(Bitmap edit) {
        this.edit = edit;
    }

    public Bitmap getDelete() {
        return delete;
    }

    public void setDelete(Bitmap delete) {
        this.delete = delete;
    }*/
}
