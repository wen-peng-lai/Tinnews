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

import com.wen.tinnews.controller.CardSwipeAdapter;
import com.wen.tinnews.model.Article;
import com.wen.tinnews.databinding.FragmentHomeBinding;
import com.wen.tinnews.model.UniNewsRepository;
import com.wen.tinnews.model.NewsViewModelFactory;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements CardStackListener {

    private HomeViewModel uniViewModel;
    private FragmentHomeBinding uniBinding;
    private CardStackLayoutManager layoutManager;
    private List<Article> articles;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uniBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return uniBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardSwipeAdapter swipeAdapter = new CardSwipeAdapter();
        layoutManager = new CardStackLayoutManager(requireContext(), this);
        layoutManager.setStackFrom(StackFrom.Top);
        uniBinding.homeCardStackView.setLayoutManager(layoutManager);
        uniBinding.homeCardStackView.setAdapter(swipeAdapter);

        UniNewsRepository uniNewsRepository = new UniNewsRepository();
        uniViewModel = new ViewModelProvider(this, new NewsViewModelFactory(uniNewsRepository)).get(HomeViewModel.class);
        uniViewModel.setUniCountryInput("us");
        uniViewModel
                .getUniTopHeadlines()
                .observe(
                        getViewLifecycleOwner(),
                        newsResponse -> {
                            if (newsResponse != null) {
                                Log.d("HomeFragment", newsResponse.toString());
                                articles = newsResponse.uniArticles;
                                swipeAdapter.setArticles(articles);
                            }
                        }
                );
        uniBinding.homeLikeButton.setOnClickListener(v -> swipeCard(Direction.Right));
        uniBinding.homeUnlikeButton.setOnClickListener(v -> swipeCard(Direction.Left));
    }

    private void swipeCard(Direction direction) {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                .setDirection(direction)
                .setDuration(Duration.Normal.duration)
                .build();
        layoutManager.setSwipeAnimationSetting(setting);
        uniBinding.homeCardStackView.swipe();
    }

    @Override
    public void onCardDragging(Direction direction, float v) {

    }

    @Override
    public void onCardSwiped(Direction direction) {
        if (direction == Direction.Left) {
            Log.d("CardStackView", "Unliked " + layoutManager.getTopPosition());
        } else if (direction == Direction.Right) {
            Log.d("CardStackView", "Liked "  + layoutManager.getTopPosition());
            uniViewModel.setUniFavoriteArticleInput(articles.get(layoutManager.getTopPosition() - 1));
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int i) {

    }

    @Override
    public void onCardDisappeared(View view, int i) {

    }
}