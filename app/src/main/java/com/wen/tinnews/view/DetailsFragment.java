package com.wen.tinnews.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wen.tinnews.model.Article;
import com.wen.tinnews.databinding.FragmentDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding uniBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uniBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        return uniBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Article article = DetailsFragmentArgs.fromBundle(getArguments()).getArticle();
        uniBinding.detailsTitleTextView.setText(article.title);
        uniBinding.detailsAuthorTextView.setText(article.author);
        uniBinding.detailsDateTextView.setText(article.publishedAt);
        uniBinding.detailsDescriptionTextView.setText(article.description);
        uniBinding.detailsContentTextView.setText(article.content);
        Picasso.get().load(article.urlToImage).into(uniBinding.detailsImageView);
    }
}