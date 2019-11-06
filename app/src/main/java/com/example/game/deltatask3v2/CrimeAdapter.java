package com.example.game.deltatask3v2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.MyViewHolder> implements Filterable {
    private List<CrimesLocAll> Crimelist;
    private List<CrimesLocAll> Crimelistfiltered;

    DatabaseAdapter db;
    private Context context;

    public Context getContext() {
        return this.context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public View mview;
        TextView idloc;
        TextView category;
        TextView info;
        TextView map;
        boolean option;

        public MyViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
            idloc = mview.findViewById(R.id.idloc);
            category = mview.findViewById(R.id.category);
            info = mview.findViewById(R.id.moreinfo);
            map=mview.findViewById(R.id.mapinfo);
            option = false;
        }

    }


    public CrimeAdapter(Context context, List<CrimesLocAll> crimes) {
        Crimelist = crimes;
        Crimelistfiltered = crimes;
        this.context = context;
    }

    @Override
    public CrimeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_rowcrime, parent, false);
        return new CrimeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.category.setText(Crimelistfiltered.get(position).getCategory());
        holder.idloc.setText(Crimelistfiltered.get(position).getId() + "");
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.category.setText("Category: " + Crimelistfiltered.get(position).getCategory());
                holder.idloc.setText("\nID: " + Crimelistfiltered.get(position).getId() + "");
                holder.info.setTextColor(Color.BLACK);
                holder.info.setText("\nLocation Type: " + Crimelistfiltered.get(position).getLocation_type());
                if (Crimelistfiltered.get(position).getLocation() != null) {
                    holder.info.append("\nlocation: {\n\tlatitude: " + Crimelistfiltered.get(position).getLocation().getLatitude() + "\n\t street: {\n\t\tid: " +
                            Crimelistfiltered.get(position).getLocation().getStreet().getId() + "\n\t\tname: " + Crimelistfiltered.get(position).getLocation().getStreet().getName() +
                            "\n\t}\n\tlongitude: " + Crimelistfiltered.get(position).getLocation().getLongitude());
                }
                holder.info.append("\n}\ncontext: " + Crimelistfiltered.get(position).getContext()
                        + "\noutcome status: {\n\tcategory: " + Crimelistfiltered.get(position).getOutcome_status().getCategory() + " \n\tdate: " + Crimelistfiltered.get(position).getOutcome_status().getDate()
                        + "\n}\npersistent id: " + Crimelistfiltered.get(position).getPersistence_id() + "\nlocation subtype: " + Crimelistfiltered.get(position).getLocation_subtype() + " \nmonth: "
                        + Crimelistfiltered.get(position).getMonth());
            }
        });
        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MapViewActivity.class);
                if(Crimelistfiltered.get(position).getLocation()!=null) {
                    intent.putExtra("Latitude", Crimelistfiltered.get(position).getLocation().getLatitude());
                    intent.putExtra("Longitude", Crimelistfiltered.get(position).getLocation().getLongitude());
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context,"Sorry Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Crimelistfiltered.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void addtofavourite(int position) {
        Toast.makeText(context, " SAVED ", Toast.LENGTH_SHORT).show();
        db = new DatabaseAdapter(context);
        db.open();
        db.insert(Crimelist.get(position).getId(), Crimelist.get(position).getCategory());
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                List<CrimesLocAll> filtered = new ArrayList<CrimesLocAll>();

                if (query.isEmpty()) {
                    filtered = Crimelist;
                } else {
                    for (CrimesLocAll f : Crimelist) {
                        if (f.getCategory().contains(query)) {
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
                Crimelistfiltered = (ArrayList<CrimesLocAll>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
