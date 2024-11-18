package com.application.agenda.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.application.agenda.R;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contato> {

    public ContactAdapter(@NonNull Context context, @NonNull List<Contato> contacts){
        super(context, 0, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contato contato = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }
        TextView nomeTextView = convertView.findViewById(R.id.nomeTextView);
        TextView foneTextView = convertView.findViewById(R.id.foneTextView);
        TextView emailTextView = convertView.findViewById(R.id.emailTextView);

        nomeTextView.setText(contato.getNome());
        foneTextView.setText(contato.getFone());
        emailTextView.setText(contato.getEmail());

        return convertView;
    }
}
