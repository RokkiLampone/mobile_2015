package com.example.Lampone;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;

public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.keyboard, container);
        return v;
    }
}
