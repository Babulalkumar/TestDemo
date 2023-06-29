package com.example.livedataroom.adptor;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.livedataroom.R;
import com.example.livedataroom.databinding.UserTaskBinding;
import com.example.livedataroom.room.UserEntity;


import java.util.List;

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.HolderClass> {
    private final  List<UserEntity> list;
    UserTaskBinding binding;
    @NonNull
    @Override
    public UserAdaptor.HolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate (LayoutInflater.from (parent.getContext ()), R.layout.user_task, parent, false);
        return new HolderClass(binding.getRoot());
    }

    public UserAdaptor(List<UserEntity> list) {
        this.list = list;

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdaptor.HolderClass holder, int position) {
        UserEntity data=list.get(position);
        binding.setViewModel (data);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderClass extends RecyclerView.ViewHolder {
        public HolderClass(@NonNull View itemView) {
            super(itemView);
        }
    }
}
