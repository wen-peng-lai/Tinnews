package com.wen.tinnews.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wen.tinnews.controller.SavedNewsAdapter;
import com.wen.tinnews.model.Article;
import com.wen.tinnews.databinding.FragmentSaveBinding;
import com.wen.tinnews.model.UniNewsRepository;
import com.wen.tinnews.model.NewsViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SaveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SaveFragment extends Fragment {
    private FragmentSaveBinding uniBinding;
    private SaveViewModel uniViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SavedNewsAdapter savedNewsAdapter = new SavedNewsAdapter();
        savedNewsAdapter.setItemCallback(new SavedNewsAdapter.ItemCallback() {
            @Override
            public void onOpenDetails(Article article) {
                Log.d("onOpenDetails", article.toString());
                SaveFragmentDirections.ActionNavigationSaveToNavigationDetails direction = SaveFragmentDirections.actionNavigationSaveToNavigationDetails(article);
                NavHostFragment.findNavController(SaveFragment.this).navigate(direction);
            }

            @Override
            public void onRemoveFavorite(Article article) {
                uniViewModel.deleteSavedUniArticle(article);
            }
        });

        uniBinding.newsResultsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        uniBinding.newsResultsRecyclerView.setAdapter(savedNewsAdapter);

        UniNewsRepository uniNewsRepository = new UniNewsRepository();
        uniViewModel = new ViewModelProvider(this, new NewsViewModelFactory(uniNewsRepository)).get(SaveViewModel.class);
        uniViewModel.getAllSavedUniArticles().observe(getViewLifecycleOwner(), savedArticles -> {
            if (savedArticles != null){
                Log.d("SaveFragment", savedArticles.toString());
                savedNewsAdapter.setArticles(savedArticles);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uniBinding =  FragmentSaveBinding.inflate(inflater, container, false);
        return uniBinding.getRoot();
    }
}