package com.example.livedataroom.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import com.example.livedataroom.room.UserDetailsDatabase;
import com.example.livedataroom.room.UserEntity;
import com.example.livedataroom.utill.Comman;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserViewModel extends AndroidViewModel {

   private final UserDetailsDatabase database;
   public MutableLiveData<UserEntity>  addObserver;
   public MutableLiveData<UserEntity> updateObserver;
   public MutableLiveData<List<UserEntity>> allDataObserver;
   public MutableLiveData<String> errorObserver;
   private final Executor executor= Executors.newSingleThreadExecutor();


    public UserViewModel(@NonNull Application application) {
        super(application);
        database= Room.databaseBuilder(application.getApplicationContext(),UserDetailsDatabase.class, Comman.USER_DATABASE).build();
        addObserver=new MutableLiveData<>();
        updateObserver=new MutableLiveData<>();
        allDataObserver=new MutableLiveData<>();
        errorObserver=new MutableLiveData<>();

    }

  public void addUser(UserEntity userEntity)
  {
    executor.execute(()->{
        try{

            database.getDao().addNewUser(userEntity);
            addObserver.postValue(userEntity);

        }catch (Exception ee)
        {
          errorObserver.postValue(ee.getMessage());

        }
    });


  }

    public void updateUser(UserEntity userEntity)
    {
        executor.execute(()->{
            try{

                database.getDao().userUpdate(userEntity);
                updateObserver.postValue(userEntity);
            }catch (Exception ee)
            {
                //show toast here if you want
            }
        });


    }

    public void getAllUserList()
    {
        executor.execute(()->{
        List<UserEntity> list =  database.getDao().getAllUsers();
            allDataObserver.postValue(list);
        });
    }

}
