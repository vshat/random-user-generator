package com.github.vshat.randomusergenerator;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private List<String> stringList;

    public UsersAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = stringList.get(position);
        holder.stringView.setText(text);

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView stringView;


        ViewHolder(final View itemView) {
            super(itemView);

            stringView = (TextView) itemView.findViewById(R.id.textview_user_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }
}