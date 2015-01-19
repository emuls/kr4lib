package com.kr4.kr4lib.fragments;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;

public class GoogleMapFragment extends SupportMapFragment {

	private static final String SUPPORT_MAP_BUNDLE_KEY = "MapOptions";
	private OnGoogleMapFragmentListener mCallback;

	private WeakReference<OnGoogleMapFragmentListener> listener;

	public static interface OnGoogleMapFragmentListener {
		void onMapReady(GoogleMap map);
	}

	public static GoogleMapFragment newInstance() {
		return new GoogleMapFragment();
	}

	public static GoogleMapFragment newInstance(GoogleMapOptions options) {
		Bundle arguments = new Bundle();
		arguments.putParcelable(SUPPORT_MAP_BUNDLE_KEY, options);

		GoogleMapFragment fragment = new GoogleMapFragment();
		fragment.setArguments(arguments);
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnGoogleMapFragmentListener) {
			mCallback = (OnGoogleMapFragmentListener) getActivity();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		if (listener != null && listener.get() != null) {
			listener.get().onMapReady(getMap());
		} else if (mCallback != null
				&& mCallback instanceof OnGoogleMapFragmentListener) {
			mCallback.onMapReady(getMap());
		}
		return view;
	}

	public void setOnGoogleMapFragmentListener(
			OnGoogleMapFragmentListener listener) {
		this.listener = new WeakReference<OnGoogleMapFragmentListener>(listener);
	}
}
