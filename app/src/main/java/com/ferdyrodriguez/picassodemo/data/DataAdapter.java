package com.ferdyrodriguez.picassodemo.data;

/**
 * Created by ferdyrod on 12/9/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ferdyrodriguez.picassodemo.R;
import com.ferdyrodriguez.picassodemo.model.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Character> characters;
    private Context context;

    public DataAdapter(Context context,ArrayList<Character> characters) {
        this.context = context;
        this.characters = characters;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView characterImage;
        public ViewHolder(View view) {
            super(view);
            characterImage = (ImageView)view.findViewById(R.id.imageView);
        }
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Picasso.with(context)
                .setIndicatorsEnabled(true);
        Picasso.with(context)
                .load(characters.get(position).getThumbnail())
                .placeholder(R.mipmap.marvel_placeholder)
                .fit()
                .into(viewHolder.characterImage)
        ;
        final Character pos = characters.get(position);
        viewHolder.characterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked on " + pos.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

}
