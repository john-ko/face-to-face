package com.example.facetoface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.facetoface.R;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private int currentFilterPosition  = 0;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment;
        View theView = (View) inflater.inflate(R.layout.fragment_second, container, false);

        this.spinner = (Spinner) theView.findViewById(R.id.filter_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.filterChoicesArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(adapter);
        //this.spinner.setSelection(currentFilterPosition, false);

//        TextView text = (TextView) theView.findViewById(R.id.thetext);
//        text.setText(currentFilterPosition);

        //Spinner spinner = (Spinner) theView.findViewById(R.id.spinner);
        //spinner.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d("hi,", String.valueOf(position));
                //Log.d("uh", view.toString());
                //refreshFragment();
//                getActivity().setContentView(R.layout.fragment_second);
//                Fragment general = getFragmentManager().findFragmentByTag("general");
//                View generalView = general.getView();
//                //view.findViewById()
//                TextView tv = (TextView) generalView.findViewById(R.id.thetext);
//                tv.setText(String.valueOf(currentFilterPosition));
                change(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return theView;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        System.out.println(parent.getItemAtPosition(pos));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback

    }

    public void refreshFragment(){
        if (! this.isDetached()) {
            getFragmentManager().beginTransaction()
                    .detach(this)
                    .attach(this)
                    .commit();
        }
//        FragmentTransaction tr = getFragmentManager().beginTransaction();
//        tr.replace(R.layout.activity_analytic, this);
//        tr.commit();
    }

    public void change(int position){
        Log.e("reached ", String.valueOf(position));
        //getActivity().setContentView(R.layout.fragment_second);
        //Fragment general = getFragmentManager().findFragmentByTag("general");
        //View generalView = general.getView();
        //view.findViewById()
        currentFilterPosition = position;
        TextView tv = (TextView) getView().findViewById(R.id.thetext);
        tv.setText(String.valueOf(currentFilterPosition));

    }

}
