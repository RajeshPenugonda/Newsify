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

public class SportsFragment extends Fragment {
    String api = "d3636539d2c34b56aac37c7ba4be70dc";
    ArrayList<ModalClass> modelList;
    Adapter adapter;
    String country = "in";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sports_fragment , null);

        RecyclerView rvs = v.findViewById(R.id.sports_view);
        modelList = new ArrayList<>();
        rvs.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelList);
        rvs.setAdapter(adapter);
        findNews();
        return v;
    }

    private void findNews() {

        String category = "sports";
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
