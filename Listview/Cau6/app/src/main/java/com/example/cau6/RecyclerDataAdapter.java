package com.example.cau6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.DataViewHolder> {
    private List<Employee> em;
    private Context context;
    private onClickListener onC;

    public RecyclerDataAdapter(Context context, List<Employee> em, onClickListener onC) {
        this.context = context;
        this.em = em;
        this.onC = onC;
    }

    @Override
    public int getItemCount() {
        return em == null ? 0 : em.size();
    }

    @Override
    public RecyclerDataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);;
        return new DataViewHolder(itemView, onC);
    }



    @Override
    public void onBindViewHolder(RecyclerDataAdapter.DataViewHolder holder, int position) {
        if (em.get(position).getFullName()!=null) {
            holder.tvName.setText(em.get(position).getFullName());
        }
        else holder.tvName.setText("");

        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (em.get(position).isManager())
        {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.GONE);
            holder.ivManager.setImageResource(R.drawable.ic_manager);
        }
        else
        {
            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder. tvPosition.setText(context.getString(R.string.staff));
        }

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

        private TextView tvName;
        private TextView tvPosition;
        private ImageView ivManager;
        private LinearLayout llParent;
        onClickListener onClick;

        public DataViewHolder(View itemView, onClickListener onClick) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPosition = (TextView) itemView.findViewById(R.id.tvStaff);
            ivManager = (ImageView) itemView.findViewById(R.id.ivManager);
            llParent = (LinearLayout) itemView.findViewById(R.id.item_employee_ll_parent);
            this.onClick = onClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClick.onClick(getAdapterPosition());
        }
    }

    public interface onClickListener{
        void onClick(int position);
    }
}
