package com.example.databinding.app;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.databinding.app.databinding.ActivityMainBinding;
import com.example.databinding.app.databinding.UserLayoutBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private final List<User> users = createUsers();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.title.setText("Hello world!");

        include();
        recycleView();
        bindingTwoWay();
    }

    private void include() {
        binding.setChangedText(new ChangedText(null, Color.GREEN, false, true));
        binding.setChangedText(new ChangedText("Not visible", Color.BLACK, false, false));
        binding.setChangedText(new ChangedText("Hello world!", Color.BLUE, true, true));
    }


    private void bindingTwoWay() {
        binding.setUserForm(new UserFormViewModel(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserFormViewModel user = binding.getUserForm();
                users.add(new User(user.getFirstName(), user.getLastName()));
                binding.recyclerView.getAdapter().notifyDataSetChanged();
            }
        }));
    }

    private void recycleView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.BindingHolder> {

        @Override
        public BindingHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            UserLayoutBinding binding = UserLayoutBinding.inflate(getLayoutInflater(), viewGroup, false);
            return new BindingHolder(binding);
        }

        @Override
        public void onBindViewHolder(BindingHolder holder, int i) {
            User user = users.get(i);
            holder.getBinding().setUser(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        public class BindingHolder extends RecyclerView.ViewHolder {
            private UserLayoutBinding binding;

            public BindingHolder(UserLayoutBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public UserLayoutBinding getBinding() {
                return binding;
            }
        }
    }

    private static List<User> createUsers() {
        return new ArrayList<>(Arrays.asList(
                new User("Ivan", "Ivanov"),
                new User("Jon", "Smith"),
                new User("Guy", "Richard"),
                new User("Sherlock", "Hols"),
                new User("Janeta", "Snow")));
    }

}

