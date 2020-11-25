package com.example.cloudrecovery.DoctorPlanFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.cloudrecovery.R;
import com.example.cloudrecovery.DoctorPlanAdapter.PatientAdapter;
import com.example.cloudrecovery.DoctorPlanEntity.Patient;
import com.example.cloudrecovery.DoctorPlanView.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment {
    private EditText etSearch;
    private MyListView lvPatient;

    private List<Patient> patientList = new ArrayList<>();

    public PlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_plan,
                container,
                false) ;

        initData();
        PatientAdapter adapter = new PatientAdapter(getContext(), R.layout.patient_item_layout, patientList);
        lvPatient = view.findViewById(R.id.lv_plan_patient);
        lvPatient.setAdapter(adapter);

3
        return view;
    }

    public void initData(){
        for(int i = 0; i < 5; i++){
            Patient patient = new Patient("patient"+i, "boy", "something"+i);
            patientList.add(patient);
        }
    }

}
