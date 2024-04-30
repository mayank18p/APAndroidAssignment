package com.example.apandroidassignment.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apandroidassignment.R;
import com.example.apandroidassignment.model.ApiResponse;
import com.example.apandroidassignment.network.ApiService;
import com.example.apandroidassignment.network.RetrofitClient;
import com.example.apandroidassignment.pagination.PaginationAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    NestedScrollView nestedSV;
    private ApiService apiService;
    private List<ApiResponse> responseList;
    private PaginationAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    // offset is no. of items per page i.e; 10 in starting, that will increase after every request
    // limit is per page item request (10 item per page)
    // and total means assuming total 100 items to be displayed from Api.
    int offset = 10, limit = 10, total = 100;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        responseList = new ArrayList<>();

        apiService = RetrofitClient.getClient().create(ApiService.class);
        gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        nestedSV = (NestedScrollView) findViewById(R.id.nestedSV);

        loadPageData(offset, total);

        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // In this method we are incrementing page items, making progress bar visible and calling API.
                    offset+= limit;
                    progressBar.setVisibility(View.VISIBLE);
                    loadPageData(offset, total);
                }
            }
        });
    }

    private void loadPageData(int items, int total) {
        if (items > total) {
            // Checking if the page items are greater than limit, display toast message in that case.
            Toast.makeText(this, "All 100 items displayed.", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        apiService.getImages(items).enqueue(new Callback<List<ApiResponse>>() {
            @Override
            public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    responseList = response.body();
                    adapter = new PaginationAdapter(MainActivity.this, responseList);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.app_name), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ApiResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
