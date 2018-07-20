package com.jmj.mariejulio.mycontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Contact> arrayList;


    public CustomAdapter(Context context, ArrayList<Contact> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.delegate_listview, parent, false);
            ViewHolder myViewHolder = new ViewHolder();
            myViewHolder.tv_nom = (TextView) convertView.findViewById(R.id.nom);
            myViewHolder.tv_prenom = (TextView) convertView.findViewById(R.id.preNom);
            convertView.setTag(myViewHolder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.tv_nom.setText(arrayList.get(position).getNom());
        holder.tv_prenom.setText(arrayList.get(position).getPrenom());

        // get current item to be displayed
//        Contact currentItem = (Contact) getItem(position);

//        // get the TextView for item name and item description
//        TextView textViewItemName = (TextView)
//                convertView.findViewById(R.id.nom);
//        TextView textViewItemp = (TextView)
//                convertView.findViewById(R.id.preNom);

        //sets the text for item name and item description from the current item object
//        textViewItemName.setText(currentItem.getNom());
//        textViewItemp.setText(currentItem.getPrenom());

//        ViewHolder myViewHolder;
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.delegate_listview, parent, false);
//            myViewHolder = new ViewHolder(convertView);
//            convertView.setTag(myViewHolder);
//        }else{
//            myViewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        Contact contact = (Contact) getItem(position);
//
//        myViewHolder.tv_nom.setText(contact.getNom());
//        myViewHolder.tv_prenom.setText(contact.getPrenom());

        return convertView;
    }

    private class ViewHolder {

         TextView tv_nom ;
         TextView tv_prenom ;

//        public ViewHolder (View item) {
//            TextView tv_nom = (TextView) item.findViewById(R.id.nom);
//            TextView tv_prenom = (TextView) item.findViewById(R.id.preNom);
//        }

//        public void setTextNom(String textNom) {
//            tv_nom.setText(textNom);
//        }
//
//        public void setTextPrenom(String textPrenom) {
//            tv_nom.setText(textPrenom);
//        }
    }
}