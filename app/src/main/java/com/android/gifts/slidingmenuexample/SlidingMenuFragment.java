package com.android.gifts.slidingmenuexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SlidingMenuFragment extends Fragment {
	public SlidingMenuFragment() {
		// Empty constructor required
	}

	public static SlidingMenuFragment newInstance() {
		return new SlidingMenuFragment();
	}
	RecyclerView recyclerView;
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.menu_frame, parent, false);

		// Instantiate recyclerView object
		recyclerView = (RecyclerView) rootView.findViewById(R.id.menu_recycler_view);

		// Attaching a layoutManager to it
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		// Preparing menu items
		List<ItemMenu> menuList = new ArrayList<>();
		menuList.add(new ItemMenu("Home", R.drawable.ic_home));
		menuList.add(new ItemMenu("Favorites", R.drawable.ic_favorite));
		menuList.add(new ItemMenu("Settings", R.drawable.ic_settings));
		menuList.add(new ItemMenu("Profile", R.drawable.ic_face));

		// Instantiate adapter object
		MenuRecyclerViewAdapter menuAdapter = new MenuRecyclerViewAdapter(menuList, getActivity());

		recyclerView.setAdapter(menuAdapter);

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Preparing menu items
		List<ItemMenu> menuList = new ArrayList<>();
		menuList.add(new ItemMenu("Home", R.drawable.ic_home));
		menuList.add(new ItemMenu("Favorites", R.drawable.ic_favorite));
		menuList.add(new ItemMenu("Settings", R.drawable.ic_settings));
		menuList.add(new ItemMenu("Profile", R.drawable.ic_face));

		// Instantiate adapter object
		MenuRecyclerViewAdapter menuAdapter = new MenuRecyclerViewAdapter(menuList, getActivity());

		recyclerView.setAdapter(menuAdapter);
	}
}
