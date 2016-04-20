package com.android.gifts.slidingmenuexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SlidingMenuFragment extends Fragment {
	RecyclerView recyclerView;

	public SlidingMenuFragment() {
		// Empty constructor required
	}

	public static SlidingMenuFragment newInstance() {
		return new SlidingMenuFragment();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fargment_sliding_menu, parent, false);

		// Instantiate image view object of the user avatar and attaching onClick listener to it.
		ImageView userAvatar = (ImageView) rootView.findViewById(R.id.user_avatar);
		userAvatar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "User Avatar Clicked", Toast.LENGTH_SHORT).show();
				((MainActivity)getActivity()).toggleMenu();
			}
		});

		// Instantiate recyclerView object
		recyclerView = (RecyclerView) rootView.findViewById(R.id.menu_recycler_view);
		// Attaching a layoutManager to it
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		// Instantiate adapter object
		MenuRecyclerViewAdapter menuAdapter = new MenuRecyclerViewAdapter(getData(), getContext());
		recyclerView.setAdapter(menuAdapter);

		recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
			@Override
			public void onClick(View view, int position) {
				Toast.makeText(getContext(), "Item Num: " + position + " Clicked", Toast.LENGTH_SHORT).show();

				// Hide the menu when item clicked
				((MainActivity)getActivity()).toggleMenu();
			}

			@Override
			public void onLongClick(View view, int position) {
				Toast.makeText(getContext(), "Item Num: " + position + " Clicked", Toast.LENGTH_SHORT).show();
			}
		}));

		return rootView;
	}

	private List<ItemMenu> getData() {
		List<ItemMenu> menuList = new ArrayList<>();
		menuList.add(new ItemMenu("Home", R.drawable.ic_home));
		menuList.add(new ItemMenu("Favorites", R.drawable.ic_favorite));
		menuList.add(new ItemMenu("Settings", R.drawable.ic_settings));
		menuList.add(new ItemMenu("Profile", R.drawable.ic_face));

		return menuList;
	}

	// Recycler view touch lister class
	class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
		private GestureDetector gestureDetector;
		private ClickListener clickListener;

		public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {

			this.clickListener = clickListener;

			gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					return true;
				}

				@Override
				public void onLongPress(MotionEvent e) {
					View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
					if (child != null && clickListener != null){
						clickListener.onLongClick(child, recyclerView.getChildPosition(child));
					}
				}
			});
		}

		@Override
		public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
			View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
			if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
				clickListener.onClick(child, rv.getChildPosition(child));
			}
			return false;
		}

		@Override
		public void onTouchEvent(RecyclerView rv, MotionEvent e) {

		}

		@Override
		public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

		}
	}

	public interface ClickListener {
		public void onClick(View view, int position);

		public void onLongClick(View view, int position);
	}
}
