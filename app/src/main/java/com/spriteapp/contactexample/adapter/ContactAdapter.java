package com.spriteapp.contactexample.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spriteapp.contactexample.ContactDetailActivity;
import com.spriteapp.contactexample.R;
import com.spriteapp.contactexample.model.ContactModel;

import java.util.List;

/**
 * Created by kuangxiaoguo on 2018/2/8.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<ContactModel> mContactList;
    private Context mContext;

    public ContactAdapter(List<ContactModel> mContactList) {
        this.mContactList = mContactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ContactModel model = mContactList.get(position);
        String name = model.getName();
        holder.contactNameTextView.setText(name);
        holder.contactNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String transitionName = mContext.getResources().getString(R.string.contact_transition_name);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,
                        holder.contactNameTextView, transitionName).toBundle();
                Intent intent = new Intent(mContext, ContactDetailActivity.class);
                intent.putExtra(ContactDetailActivity.TEXT_TAG, model.getName());
                ActivityCompat.startActivity(mContext, intent, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView contactNameTextView;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            contactNameTextView = itemView.findViewById(R.id.contact_name_textView);
            linearLayout = itemView.findViewById(R.id.outer_layout);
        }

    }
}
