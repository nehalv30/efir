package com.example.e_fir;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Complaint_list_class extends ArrayAdapter<Complaints> {
    private Activity context;
    private List<Complaints> complaintsList;

    public Complaint_list_class(Activity context,List<Complaints> complaintsList){
        super(context,R.layout.complaint_list,complaintsList);
        this.context=context;
        this.complaintsList=complaintsList;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflator=context.getLayoutInflater();
        View ListViewItem=inflator.inflate(R.layout.complaint_list,null,true);
        TextView textViewtype=ListViewItem.findViewById(R.id.textViewtype);
        TextView textViewdate=ListViewItem.findViewById(R.id.textViewdate);
       Complaints complaints=complaintsList.get(position);
       int day=complaints.getDate();
       int month=complaints.getMonth();
       int year=complaints.getYear();
       textViewdate.setText(day+"/"+month+"/"+year);
       textViewtype.setText(complaints.getType());
       return ListViewItem;
    }
}
