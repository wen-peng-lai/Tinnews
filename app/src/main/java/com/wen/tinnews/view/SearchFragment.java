package com.wen.tinnews.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wen.tinnews.controller.SearchNewsAdapter;
import com.wen.tinnews.databinding.FragmentSearchBinding;
import com.wen.tinnews.model.UniNewsRepository;
import com.wen.tinnews.model.NewsViewModelFactory;

import androidx.appcompat.widget.SearchView;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private FragmentSearchBinding uniBinding;

    private SearchViewModel uniViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uniBinding = FragmentSearchBinding.inflate(inflater, container, false);
        return uniBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchNewsAdapter newsAdapter = new SearchNewsAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        uniBinding.newsResultsRecyclerView.setLayoutManager(gridLayoutManager);
        uniBinding.newsResultsRecyclerView.setAdapter(newsAdapter);

        uniBinding.newsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                          @Override
                                                          public boolean onQueryTextSubmit(String query) {
                                                              if (!query.isEmpty()) {
                                                                  uniViewModel.setSearchInput(query);
                                                              }
                                                              uniBinding.newsSearchView.clearFocus();
                                                              return true;
                                                          }

                                                          @Override
                                                          public boolean onQueryTextChange(String newText) {
                                                              return false;
                                                          }
                                                      });


        UniNewsRepository uniNewsRepository = new UniNewsRepository();
        uniViewModel = new ViewModelProvider(this, new NewsViewModelFactory(uniNewsRepository)).get(SearchViewModel.class);
        uniViewModel
                .searchNews()
                .observe(
                        getViewLifecycleOwner(),
                        newsResponse -> {
                            if (newsResponse != null) {
                                Log.d("SearchFragment", newsResponse.toString());
                                newsAdapter.setArticles(newsResponse.uniArticles);
                            }
                        });

        newsAdapter.setItemCallback(article -> {
            SearchFragmentDirections.ActionNavigationSearchToNavigationDetails direction = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(article);
            NavHostFragment.findNavController(SearchFragment.this).navigate(direction);
        });
    }
}