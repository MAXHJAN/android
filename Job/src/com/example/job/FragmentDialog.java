package com.example.job;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class FragmentDialog extends DialogFragment{
	/**
	 * ����
	 */
	 public Dialog onCreateDialog(Bundle savedInstanceState)  
	     {  
	         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  
	        // Get the layout inflater  
	        LayoutInflater inflater = getActivity().getLayoutInflater();  
	         View view = inflater.inflate(R.layout.dialog, null); 
	        builder.setView(view)
	        .setIcon(R.drawable.fan)
	        .setTitle("����")
	        .setPositiveButton("����",  
                       new DialogInterface.OnClickListener()  
                       {  
                           @Override  
                            public void onClick(DialogInterface dialog, int id)  
                            {  
                           }  
                       }).setNegativeButton("ȡ��", null);  

	        
	        
	        return builder.create();  
	     }

}
