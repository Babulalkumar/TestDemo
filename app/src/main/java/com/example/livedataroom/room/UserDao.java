package com.example.livedataroom.room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.livedataroom.utill.Comman;
import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM "+Comman.USER_TABLE)
    List<UserEntity> getAllUsers();

    //todo Custom Add  User Through Query is Also Working/
 /*   @Query("Insert into "+Command.USER_TABLE+"(user_number,user_name,user_last_name) values(:name,:lastName,:number)")
    void getAdd(String name,String lastName,String number);*/

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void addNewUser(UserEntity detailsEntity);


    //todo Custom Update User Through Query is Also Working/
  /*  @Update("update "+Command.USER_TABLE+"set"+Command.USER_NAME+"=:name,"+Command.USER_LASTNAME+"=:lastName where "+Command.USER_NUMBER+"=:num")
    void userUser(String name,String lastName,String num);*/

    @Update
    void userUpdate(UserEntity userDetailsEntity);
}
