package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import model.PaysItem;
import com.example.safedrive1.R;

import java.util.ArrayList;

public class PaysAdapter extends ArrayAdapter<PaysItem> {
    public PaysAdapter(Context context, ArrayList<PaysItem> PaysList){
        super(context,0, PaysList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
    private View initView (int position, View convertView, ViewGroup parent){
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.pays_spinner, parent, false
            );
        }
            ImageView ImgDrapeau = convertView.findViewById(R.id.imageViewDrapeau);
            TextView NomPays = convertView.findViewById(R.id.textViewNomPays);
            PaysItem currentItem = getItem(position);

        if(currentItem != null){
            ImgDrapeau.setImageResource(currentItem.getDrapImage());
            NomPays.setText(currentItem.getNomPays());
        }
        return convertView;
    }
}
