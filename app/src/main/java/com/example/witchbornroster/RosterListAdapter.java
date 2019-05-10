package com.example.witchbornroster;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RosterListAdapter extends RecyclerView.Adapter<RosterListAdapter.RosterHolder> {
    class RosterHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private final TextView rosterNameTextView;
        private final TextView rosterTypeTextView;
        private final TextView rosterValueTextView;

        public RosterHolder(@NonNull View itemView) {
            super(itemView);
            rosterNameTextView = itemView.findViewById(R.id.WarClanNameTextView);
            rosterTypeTextView = itemView.findViewById(R.id.WarClanTypeTextView);
            rosterValueTextView = itemView.findViewById(R.id.WarClanValueTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(warClans.get(adapterPosition));
        }

        @Override
        public boolean onLongClick(View v) {
            int adapterPosition = getAdapterPosition();
            longClickHandler.onLongClick(warClans.get(adapterPosition));
            return false;
        }
    }

    private final LayoutInflater inflater;
    private List<Roster> warClans;

    private final RosterListAdapterOnClickHandler clickHandler;
    private final RosterListAdapterOnLongClickHandler longClickHandler;
    
    public RosterListAdapter(Context context, RosterListAdapterOnClickHandler clickHandler, RosterListAdapterOnLongClickHandler longClickHandler) {
        inflater = LayoutInflater.from(context);
        this.clickHandler = clickHandler;
        this.longClickHandler = longClickHandler;
    }


    @NonNull
    @Override
    public RosterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.roster_list_row, viewGroup, false);
        return new RosterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RosterHolder rosterHolder, int i) {
        if (warClans != null){
            Roster roster = warClans.get(i);

            rosterHolder.rosterNameTextView.setText(roster.getClanName());
            rosterHolder.rosterTypeTextView.setText(roster.getClanType());
            rosterHolder.rosterValueTextView.setText(roster.getValue() + "");
        }
    }

    @Override
    public int getItemCount() {
        if(warClans != null){
            return warClans.size();
        }
        return 0;
    }

    public void setWarClans(List<Roster> rosters) {
        this.warClans = rosters;
        notifyDataSetChanged();
    }

    public interface RosterListAdapterOnClickHandler{
        void onClick(Roster roster);
    }

    public interface RosterListAdapterOnLongClickHandler{
        void onLongClick(Roster roster);
    }



}
