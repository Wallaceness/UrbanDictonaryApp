package com.example.urbandictonaryapp.view.searchwordfragment;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbandictonaryapp.R;
import com.example.urbandictonaryapp.model.Definition;

import java.util.ArrayList;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.DefinitionHolder> {

    private ArrayList<Definition> definitions;

    public DefinitionAdapter(ArrayList<Definition> definitions){
        this.definitions=definitions;
    }

    public void changeList(ArrayList<Definition> def){
        definitions = def;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DefinitionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.definition_item,parent,false);
        return new DefinitionHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionHolder holder, int position) {
        Definition def=definitions.get(position);
        holder.word.setText(def.getWord());
        holder.definition.setText(def.getDefinition());
        holder.example.setText(def.getExample());
        holder.author.setText("by "+def.getAuthor()+" "+def.getWrittenOn());
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    class DefinitionHolder extends RecyclerView.ViewHolder{

        TextView word;
        TextView definition;
        TextView example;
        TextView author;

        DefinitionHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.definitionHeader);
            definition = itemView.findViewById(R.id.definitionText);
            example = itemView.findViewById(R.id.definitionExample);
            author = itemView.findViewById(R.id.authorDate);
        }
    }
}
