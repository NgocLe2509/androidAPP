package com.example.cau3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.DataViewHolder> {
    private List<Contact> con;
    private Context context;
    private onClickListener onC;

    public ContactAdapter(Context context, List<Contact> con, onClickListener onC) {
        this.context = context;
        this.con = con;
        this.onC = onC;
    }

    @Override
    public int getItemCount() {
        return con == null ? 0 : con.size();
    }

    @Override
    public ContactAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new DataViewHolder(itemView, onC);
    }



    @Override
    public void onBindViewHolder(ContactAdapter.DataViewHolder holder, int position) {
        if (con.get(position).getId() != -1) {
            holder.tvId.setText(Integer.toString(con.get(position).getId()));
        }
        else holder.tvId.setText("");

        if (con.get(position).getName() != null) {
            holder.tvName.setText(con.get(position).getName());
        }
        else holder.tvId.setText("");

        if (con.get(position).getPhoneNumber() != null) {
            holder.tvTelephone.setText(con.get(position).getPhoneNumber());
        }
        else holder.tvId.setText("");

        // Show different color backgrounds for 2 continuous employees
        if (position%2==0)
        {
            holder.llParent.setBackgroundResource(R.color.white);
        }
        else
        {
            holder.llParent.setBackgroundResource(R.color.light_blue);
        }
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvId;
        private TextView tvName;
        private TextView tvTelephone;
        private LinearLayout llParent;
        onClickListener onClick;

        public DataViewHolder(View itemView, onClickListener onClick) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTelephone = (TextView) itemView.findViewById(R.id.tvTelephone);
            llParent = (LinearLayout) itemView.findViewById(R.id.llParent);
            this.onClick = onClick;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onClick.onLongItemClick(getAdapterPosition());
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            onClick.onClick(getAdapterPosition());
        }

    }

    public interface onClickListener{
        void onClick(int position);
        void onLongItemClick(int position);
    }
}