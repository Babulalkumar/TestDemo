package com.example.livedataroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.livedataroom.adptor.UserAdaptor;
import com.example.livedataroom.databinding.ActivityMainBinding;
import com.example.livedataroom.room.UserEntity;
import com.example.livedataroom.viewmodel.UserViewModel;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private List<UserEntity> list;
    private UserViewModel userViewModel;
    private UserAdaptor userAdaptor;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();
        initObserver();


    }

    @SuppressLint("NotifyDataSetChanged")
    private void initObserver() {

        userViewModel.allDataObserver.observe(this, allList -> {
            if (allList.size()>0)
            {
                list.addAll(allList);
                setData();
            }

        });
        userViewModel.addObserver.observe(this, userEntity -> {
            Toast.makeText(this, "User Successfully Added", Toast.LENGTH_SHORT).show();
            list.add(userEntity);
            binding.userlistRv.setVisibility(View.VISIBLE);
            userAdaptor.notifyDataSetChanged();
        });


        userViewModel.updateObserver.observe(this, userEntity -> {
            Toast.makeText(this, "User Successfully Updated", Toast.LENGTH_SHORT).show();
            int i = -1;
            for (UserEntity userEntity1 : list) {
                i++;
                if (userEntity.getUser_number().equals(userEntity1.getUser_number())) {
                    list.get(i).setUser_name(userEntity.getUser_name());
                    list.get(i).setUser_last_name(userEntity.getUser_last_name());
                    userAdaptor.notifyItemChanged(i);
                    break;
                }
            }

        });
        userViewModel.errorObserver.observe(this,message-> Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show());

    }

    private void initView() {
        list = new ArrayList<>();
        userAdaptor = new UserAdaptor(list);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.setDataset(userAdaptor);
        userViewModel.getAllUserList();

        binding.addBtn.setOnClickListener(view -> {
            Log.w(TAG, "Add user!!!");
            if (list.size() > 0) {
                Toast.makeText(MainActivity.this, "one user already------- register", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = binding.nameEd.getText().toString();
            String lastName = binding.lastnameEd.getText().toString();
            String number = binding.mobileEd.getText().toString();
            UserEntity userEntity = new UserEntity(number, name, lastName);
            userViewModel.addUser(userEntity);



        });

        binding.updateBtn.setOnClickListener(view -> {
            String name = binding.nameEd.getText().toString();
            String lastName = binding.lastnameEd.getText().toString();
            String number = binding.mobileEd.getText().toString();
            UserEntity userEntity = new UserEntity(number, name, lastName);
            userViewModel.updateUser(userEntity);


        });


    }

    @SuppressLint("NotifyDataSetChanged")
    private void setData() {
        if (list.size() > 0) {
            binding.textView.setVisibility(View.GONE);
            binding.userlistRv.setVisibility(View.VISIBLE);
            userAdaptor.notifyDataSetChanged();

        } else {
            binding.textView.setVisibility(View.VISIBLE);
            binding.userlistRv.setVisibility(View.GONE);


        }
    }


}