package com.example.profisraelproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<DataModel> mList;
    private Context mContext;
    private int expandedPosition = -1;

    public ItemAdapter(List<DataModel> mList, Context context){
        this.mList = mList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        DataModel model = mList.get(position);
        holder.mTextView.setText(model.getItemText());
        boolean isExpandable = position == expandedPosition;

        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (isExpandable) {
            holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.logo_color));
            holder.mArrowImage.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        } else {
            holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            holder.mArrowImage.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        }

        NestedAdapter adapter = new NestedAdapter(model.getNestedList(), mContext);
        holder.nestedRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.nestedRecyclerView.setHasFixedSize(true);
        holder.nestedRecyclerView.setAdapter(adapter);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandedPosition == position) {
                    expandedPosition = -1;
                } else {
                    expandedPosition = position;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;
        private RelativeLayout expandableLayout;
        private TextView mTextView;
        private ImageView mArrowImage;
        private RecyclerView nestedRecyclerView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.extendble_layout);
            mTextView = itemView.findViewById(R.id.itemTv);
            mArrowImage = itemView.findViewById(R.id.arrow_image_view);
            nestedRecyclerView = itemView.findViewById(R.id.child_rv);
        }
    }
}