package com.example.turniraplikacija;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterPoluvrijeme extends RecyclerView.Adapter<AdapterPoluvrijeme.ViewHolderPoluvrijeme> {

    Context context;
    Game game;

    public AdapterPoluvrijeme(Context context, Game game) {
        this.context = context;
        this.game = game;
    }

    @NonNull
    @Override
    public AdapterPoluvrijeme.ViewHolderPoluvrijeme onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPoluvrijeme.ViewHolderPoluvrijeme holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderPoluvrijeme extends RecyclerView.ViewHolder{

        public ViewHolderPoluvrijeme(@NonNull View itemView) {
            super(itemView);
        }
    }
}
