package com.example.urbandictonaryapp.view.searchwordfragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbandictonaryapp.R;
import com.example.urbandictonaryapp.model.Definition;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
    public void onBindViewHolder(@NonNull DefinitionHolder holder, int position){
        Definition def=definitions.get(position);
        holder.word.setText(def.getWord());
        holder.definition.setText(def.getDefinition());
        holder.example.setText(def.getExample());
        Date dateTime;
        try{
            dateTime =holder.time.parse(def.getWrittenOn());
        }
        catch(Exception e){
            throw new Error(e);
        }
        holder.author.setText("by "+def.getAuthor()+" "+holder.time.format(dateTime));
        if (def.getSoundUrls().size()>0){
            holder.url = def.getSoundUrls().get(0);
        }
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
        Button button;
        MediaPlayer audio;
        String url;
        SimpleDateFormat time=new SimpleDateFormat("MM-dd-YY", Locale.US);

        DefinitionHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.definitionHeader);
            definition = itemView.findViewById(R.id.definitionText);
            example = itemView.findViewById(R.id.definitionExample);
            author = itemView.findViewById(R.id.authorDate);
            audio= new MediaPlayer();
            audio.setAudioStreamType(AudioManager.STREAM_MUSIC);
            button = itemView.findViewById(R.id.audioButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        audio.setDataSource(url);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    if (audio.isPlaying()){
                        audio.start();
                    }
                }
            });
        }
    }
}
