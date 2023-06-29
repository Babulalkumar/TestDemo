package com.example.livedataroom.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={UserEntity.class}, version=2)
public abstract class UserDetailsDatabase extends RoomDatabase {
     public abstract UserDao getDao();
}
