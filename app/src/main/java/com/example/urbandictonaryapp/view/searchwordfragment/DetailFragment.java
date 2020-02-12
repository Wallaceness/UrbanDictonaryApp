package com.example.urbandictonaryapp.view.searchwordfragment;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.urbandictonaryapp.R;
import com.example.urbandictonaryapp.model.Definition;
import com.example.urbandictonaryapp.view.mainactivity.MainActivity;
import com.example.urbandictonaryapp.viewmodel.SearchWordViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private TextView word;
    private TextView definition;
    private TextView example;
    private TextView author;
    private Button button;
    private MediaPlayer audio;
    private String url;
    private SimpleDateFormat time=new SimpleDateFormat("MM-dd-YY", Locale.US);

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_word, container, false);
        word = rootView.findViewById(R.id.definitionHeader2);
//        word.setText();
        definition = rootView.findViewById(R.id.definitionText2);
        example = rootView.findViewById(R.id.definitionExample2);
        author = rootView.findViewById(R.id.authorDate2);
        button = rootView.findViewById(R.id.audioButton2);

        return rootView;
    }



}
