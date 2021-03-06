package com.example.smartcontract.mapUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcontract.R;
import com.example.smartcontract.models.TrackModel;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;

public class TrackUserAdapter extends RecyclerView.Adapter<TrackUserAdapter.ViewHolder> {

    Context mContext;
    List<TrackModel> nodes;
    List<String> userRoles;

    public TrackUserAdapter(Context mContext, List<TrackModel> nodes, List<String> userRoles) {
        this.mContext = mContext;
        this.nodes = nodes;
        this.userRoles = userRoles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.flow_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.publicAddress.setText(nodes.get(position).getUserAddress());
        holder.role.setText("(" + userRoles.get(position) + ")");
        if (nodes.get(position).getSellingPrice().isEmpty() || nodes.get(position).getSellingPrice().equals("0")) {
            holder.sellPrice.setVisibility(View.GONE);
        } else {
            holder.sellPrice.setText("Initial Selling Price: "  + nodes.get(position).getSellingPrice());
            holder.sellPrice.setVisibility(View.VISIBLE);
        }
        if(position+1<nodes.size()) {
            if (nodes.get(position + 1).getBuyingPrice().isEmpty() || nodes.get(position + 1).getBuyingPrice().equals("0")) {
                holder.soldPrice.setVisibility(View.GONE);
            } else {
                holder.soldPrice.setText("Sold At: " + nodes.get(position + 1).getBuyingPrice());
                holder.soldPrice.setVisibility(View.VISIBLE);
            }
        }else{
            holder.soldPrice.setVisibility(View.GONE);
        }
        if (nodes.get(position).getTransactionHash().isEmpty() || nodes.get(position).getTransactionHash().equals("null")) {
            holder.txnHash.setVisibility(View.GONE);
        } else {
            holder.txnHash.setText(mContext.getResources().getString(R.string.txnEndpoint) + nodes.get(position).getTransactionHash());
            holder.txnHash.setVisibility(View.VISIBLE);
        }
        if (position == nodes.size() - 1) {
            holder.downArrow.setVisibility(View.GONE);
        }
        if(nodes.get(position).getName()!=null && !nodes.get(position).getName().isEmpty()){
            holder.userName.setText("Name: " + nodes.get(position).getName());
            holder.userLocation.setText("Location: https://www.google.com/maps/search/?api=1&query=" + nodes.get(position).getLat()+","+nodes.get(position).getLon());
            holder.backLL.setVisibility(View.VISIBLE);
            holder.loader.setVisibility(View.GONE);
        }
        else{
            holder.backLL.setVisibility(View.GONE);
            holder.loader.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View parent;
        EasyFlipView cardView;
        TextView publicAddress, role, sellPrice, soldPrice, txnHash, userName, userLocation;
        ImageView downArrow;
        ProgressBar loader;
        LinearLayout backLL;

        public ViewHolder(View itemView) {
            super(itemView);
            parent = itemView;
            cardView = itemView.findViewById(R.id.card);
            publicAddress = itemView.findViewById(R.id.publicAddress);
            role = itemView.findViewById(R.id.role);
            sellPrice = itemView.findViewById(R.id.sellPrice);
            soldPrice = itemView.findViewById(R.id.soldPrice);
            txnHash = itemView.findViewById(R.id.txnHash);
            downArrow = itemView.findViewById(R.id.downwArrow);
            userName = itemView.findViewById(R.id.userName);
            userLocation = itemView.findViewById(R.id.location);
            loader =itemView.findViewById(R.id.loader);
            backLL = itemView.findViewById(R.id.backLL);
        }
    }
}