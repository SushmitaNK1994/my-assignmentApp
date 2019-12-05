package com.example.assignmentapp.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.assignmentapp.R;
import com.example.assignmentapp.model.DataDTO;
import com.example.assignmentapp.model.FactsDTO;



public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.ViewHolder> {
    private Context context;
    private DataDTO mDataDto;

    public FactsAdapter(Context mContext, DataDTO dataDTO) {
        this.context = mContext;
        this.mDataDto = dataDTO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.facts_cardview, parent, false);
        return new FactsAdapter.ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FactsDTO factsDTO = mDataDto.getRowsList().get(position);
        if (factsDTO.getTitle() == null && factsDTO.getImageHref() == null && factsDTO.getDescription() == null) {
            mDataDto.getRowsList().remove(position);
        }
        holder.title_text.setText(factsDTO.getTitle());
        holder.desc_text.setText(factsDTO.getDescription());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        Glide.with(context).load(factsDTO.getImageHref()).apply(requestOptions).into(holder.image_view);
    }

    @Override
    public int getItemCount() {
        if (mDataDto.getRowsList() != null) {
            return mDataDto.getRowsList().size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title_text;
        TextView desc_text;
        ImageView image_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.fact_card);
            title_text = itemView.findViewById(R.id.title_textView);
            desc_text = itemView.findViewById(R.id.desc_textView);
            image_view = itemView.findViewById(R.id.icon_imageView);
        }
    }
}
