package com.rajesh.newsify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFragment extends Fragment {
    String api = "d3636539d2c34b56aac37c7ba4be70dc";
    ArrayList<ModalClass> modelList;
    Adapter adapter;
    String country = "in";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.health_fragment , null);

        RecyclerView rvh = v.findViewById(R.id.health_view);
        modelList = new ArrayList<>();
        rvh.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelList);
        rvh.setAdapter(adapter);
        findNews();
        return v;
    }

    private void findNews() {

        String category = "health";
        ApiUtility.getApiInterface().getCategoryNews(country, category, 100, api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                    modelList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }
}

