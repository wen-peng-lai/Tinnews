package com.wen.tinnews.controller;
import com.squareup.picasso.Picasso;
import com.wen.tinnews.model.Article;
import com.wen.tinnews.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wen.tinnews.databinding.SearchNewsItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {

    public interface ItemCallback {
        void onOpenDetails(Article article);
    }
    private ItemCallback itemCallback;

    public void setItemCallback (ItemCallback itemCallback){
        this.itemCallback = itemCallback;
    }

    // 1. Supporting data:
    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.itemTitleTextView.setText(article.title);
        if (article.urlToImage != null) {
            Picasso.get().load(article.urlToImage).resize(200, 200).into(holder.itemImageView);
            holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
            }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    // 2. SearchNewsViewHolder:
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding uniBinding = SearchNewsItemBinding.bind(itemView);
            itemImageView = uniBinding.searchItemImage;
            itemTitleTextView = uniBinding.searchItemTitle;
        }
    }


    // 3. Adapter overrides:
    // TODO

}
