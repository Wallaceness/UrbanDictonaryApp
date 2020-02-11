package com.example.urbandictonaryapp.view.searchwordfragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.urbandictonaryapp.R;
import com.example.urbandictonaryapp.model.Definition;
import com.example.urbandictonaryapp.view.BaseFragment;
import com.example.urbandictonaryapp.view.mainactivity.MainActivity;
import com.example.urbandictonaryapp.viewmodel.SearchWordViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchWordFragment extends BaseFragment {
    private EditText searchInput;
    private Button searchButton;
    private SearchWordViewModel viewModel;
    private MainActivity activity;
    private RecyclerView recycler;
    private ArrayList<Definition> definitionList=new ArrayList<>();
    DefinitionAdapter adapter;

    public SearchWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_word, container, false);
        searchInput = rootView.findViewById(R.id.editText1);
        searchButton = rootView.findViewById(R.id.searchButton2);
        activity = (MainActivity) getActivity();
        recycler = rootView.findViewById(R.id.recycleGrid);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(activity.getApplication()).create(SearchWordViewModel.class);
        setUpObservers();

        //set up recyclerview
        adapter = new DefinitionAdapter(definitionList);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getDefinitionsObservable(searchInput.getText().toString());
            }
        });

        return rootView;
    }

    private void setUpObservers() {
        viewModel.getDefinitions().observe(getViewLifecycleOwner(), new Observer<List<Definition>>() {
            @Override
            public void onChanged(List<Definition> definitions) {
                definitionList = new ArrayList<>(definitions);
                adapter.changeList(definitionList);
            }
        });

        viewModel.getErrors().observe(getViewLifecycleOwner(), s -> Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show());
    }


}
