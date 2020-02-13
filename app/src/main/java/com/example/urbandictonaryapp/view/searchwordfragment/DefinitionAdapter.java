package com.example.urbandictonaryapp.view.searchwordfragment;

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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        holder.audioUrls = def.getSoundUrls();
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
        List<String> audioUrls;
        SimpleDateFormat time=new SimpleDateFormat("MM-dd-YY", Locale.US);
        MediaPlayer audio;
        int index=0;

        DefinitionHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.definitionHeader);
            definition = itemView.findViewById(R.id.definitionText);
            example = itemView.findViewById(R.id.definitionExample);
            author = itemView.findViewById(R.id.authorDate);
            button = itemView.findViewById(R.id.audioButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (audioUrls!=null){
                        try{
                            playAudio(audioUrls.get(index));
                        }
                        catch(Exception e){
                            System.out.println("MEDIAPLAYER FAILED!!");
                            System.out.println(e.getMessage());
                        }
                    }
                }
            });
        }

        public void playAudio(String url) throws IOException {

            MediaPlayer a = new MediaPlayer();
            a.setDataSource(url);
            a.prepare();
            a.start();
            index+=1;
            if (index>audioUrls.size()-1){
                index=0;
            }

            //another way to do it.
            //audio = MediaPlayer.create(itemView.getContext(), Uri.parse(url));
            //audio.start();
        }
    }
}
