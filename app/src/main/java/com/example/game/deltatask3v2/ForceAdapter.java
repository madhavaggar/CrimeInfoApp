package com.example.game.deltatask3v2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Filterable;
import java.util.ArrayList;
import java.util.List;

public class ForceAdapter extends RecyclerView.Adapter<ForceAdapter.MyViewHolder>
    implements Filterable{
    private List<Forces> forcelist;
    private List<Forces> forcelistfiltered;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public View mview;
        TextView idno;
        TextView name;
        TextView info;

        public MyViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
            idno = mview.findViewById(R.id.idno);
            name = mview.findViewById(R.id.name);
            info = mview.findViewById(R.id.moreinfo);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, SpecForceInfo.class);
                    intent.putExtra("id", idno.getText());
                    context.startActivity(intent);
                }
            });
        }
    }

    public ForceAdapter(Context context, List<Forces> f) {
        forcelist=f;
        forcelistfiltered=f;
        this.context=context;
    }
    @Override
    public ForceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_rowforce, parent, false);
        return new ForceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.idno.setText(forcelistfiltered.get(position).getID());
        holder.name.setText(forcelistfiltered.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return forcelistfiltered.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                List<Forces> filtered = new ArrayList<Forces>();

                if (query.isEmpty()) {
                    filtered = forcelist;
                } else {
                    for (Forces f : forcelist) {
                        if (f.getName().contains(query)) {
                            filtered.add(f);

                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                forcelistfiltered = (ArrayList<Forces>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}

