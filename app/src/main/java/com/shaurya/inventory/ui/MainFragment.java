package com.shaurya.inventory.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shaurya.inventory.db.DBHelper;
import com.shaurya.inventory.interfaces.FragmentClickListener;
import com.shaurya.inventory.model.MainObject;
import com.shaurya.inventory.R;
import com.shaurya.inventory.util.Maps;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by shaurya on 07/04/18.
 */

public class MainFragment extends Fragment {
    private FragmentClickListener mListener;
    private AutoCompleteTextView etName;
    private EditText etQuantity, etBatchNo, etLotNo;
    private TextView tvCompanyName, tvDate, tvCase, tvPeti;
    private Button btnSubmit;
    private MainObject mainObject;
    private int companyID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainObject = (MainObject) getArguments().getSerializable(getString(R.string.key_main_object));
        companyID = mainObject.getCompanyID();
        initView(view);
    }

    private void initView(View view) {
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String currTime = sdf.format(cal.getTime());
        etName = (AutoCompleteTextView) view.findViewById(R.id.etName);
        DBHelper dbHelper = DBHelper.getInstance(getActivity());
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,dbHelper.getCustomers());
        etName.setAdapter(adapter);
        etName.setThreshold(1);
        etQuantity = (EditText)view.findViewById(R.id.etQuantity);
        etBatchNo = (EditText)view.findViewById(R.id.etBatchNo);
        etLotNo = (EditText)view.findViewById(R.id.etLotNo);
        tvCompanyName = (TextView)view.findViewById(R.id.tvCompanyName);
        tvDate = (TextView)view.findViewById(R.id.tvDate);
        tvCase = (TextView)view.findViewById(R.id.tvCase);
        tvPeti = (TextView)view.findViewById(R.id.tvPeti);
        btnSubmit = (Button)view.findViewById(R.id.btn_submit);
        String s = Maps.companyMap.get(companyID);
        tvCompanyName.setText(Maps.companyMap.get(companyID));
        tvDate.setText(currTime);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etQuantity.getText().toString().length()>0)
                    submitButtonClicked(etName.getText().toString().trim(),
                            Integer.parseInt(etQuantity.getText().toString()),
                            etBatchNo.getText().toString(),
                            etLotNo.getText().toString(),
                            sdf.format(cal.getTime()));
                else
                    Toast.makeText(getContext(),"Enter Valid Quantity",Toast.LENGTH_SHORT).show();
            }
        });
        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()!=0 && !charSequence.toString().equalsIgnoreCase("")) {
                    int quantity_fixed = mainObject.getVariant().getQuantity_fixed();
                    int quantity_variable = mainObject.getVariant().getQuantity_variable();
                    int quantity_entered = Integer.parseInt(charSequence.toString());
                    if (quantity_entered % quantity_fixed == 0) {
                        tvCase.setText(String.valueOf(quantity_entered / quantity_fixed).concat(" Cases"));
                        if (quantity_entered%(quantity_fixed*quantity_variable)==0)
                            tvPeti.setText(String .valueOf(quantity_entered/(quantity_fixed*quantity_variable)).concat(" Peti"));
                        else
                            tvPeti.setText("");
                    }
                    else {
                        tvCase.setText("");
                        tvPeti.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public static MainFragment getInstance(Context context, MainObject mainObject){
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putSerializable(context.getString(R.string.key_main_object),mainObject);
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof FragmentClickListener)
            mListener = (FragmentClickListener) getActivity();
        else
            throw new ClassCastException(getActivity().toString() + " must implement FragmentClickListener");
    }

    public void submitButtonClicked(String name, int quantity, String batchNo, String lotNo, String date){
        if (name.length()>0 && quantity>0 && batchNo.length()>0 && lotNo.length()>0)
            mListener.onSubmitted(name, quantity, batchNo, lotNo, date);
        else
            Toast.makeText(getContext(),"Enter Valid Values",Toast.LENGTH_SHORT).show();
    }
}
