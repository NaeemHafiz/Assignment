package com.naeem.assignment;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.naeem.assignment.Adapters.UserAdapter;
import com.naeem.assignment.Classes.RetrofitClient;
import com.naeem.assignment.Interfaces.ApiInterface;
import com.naeem.assignment.Interfaces.ItemClick;
import com.naeem.assignment.Models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ItemClick {
    private List<User> userList = new ArrayList<>();
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private ApiInterface apiInterface;
    AlertDialog.Builder alertdialogue;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        alertdialogue = new AlertDialog.Builder(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        // set a LinearLayoutManager with default vertical orientation
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getUsers();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.itemj);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("search");
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    private void getUsers() {
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        Call<List<User>> user = apiInterface.getUsers();
        user.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList = response.body();
                // call the constructor of HakayatAdapter to send the reference and data to Adapter
                userAdapter = new UserAdapter(userList, getApplicationContext());
                userAdapter.setItemClick(MainActivity.this);
                recyclerView.setAdapter(userAdapter); // set the Adapter to RecyclerView
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.wtf("message", t.getMessage());
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        s = s.toLowerCase();
        List<User> myList = new ArrayList<>();
        for (User userItem : userList) {
            String login_Name = userItem.getLogin().toLowerCase();
            if (login_Name.contains(s))
                myList.add(userItem);
        }
        userAdapter.setFilter(myList);
        return false;
    }

    private void showAlertDialogue() {
        alertdialogue.setMessage("Write your message here.");
        alertdialogue.setCancelable(true);
        alertdialogue.show();
    }

    @Override
    public void onclick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("hmm", userList.get(position));
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();

    }
}
