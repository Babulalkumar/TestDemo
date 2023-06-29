package com.example.livedataroom.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.livedataroom.utill.Comman;

@Entity(tableName = Comman.USER_TABLE)
public class UserEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name=Comman.USER_NUMBER)
    private String user_number;
    @ColumnInfo(name=Comman.USER_NAME)
    private String user_name;
    @ColumnInfo(name=Comman.USER_LASTNAME)
    private String user_last_name;

    public UserEntity(@NonNull String number, String name, String lastName) {
        this.user_number = number;
        this.user_name = name;
        this.user_last_name= lastName;
    }

    public void setUser_number(@NonNull String user_number) {
        this.user_number = user_number;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    @NonNull
    public String getUser_number() {
        return user_number;
    }
    public String getUser_name() {
        return user_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }
}
