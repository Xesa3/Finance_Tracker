package com.example.finance_tracker.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.finance_tracker.R;


public class LoginFragmentPhone extends Fragment {

    //Создаем интерфейс для передачи данных из фрагмента в активити
    private OnPhoneSelectedListener mListener;
    public interface OnPhoneSelectedListener{
        void onPhoneSelected(String phone,String password);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_phone, container, false);

        if(getActivity() instanceof OnPhoneSelectedListener){
            mListener = (OnPhoneSelectedListener) getActivity();
        }


        Button button6 = view.findViewById(R.id.button6);
        EditText textPhone = view.findViewById(R.id.editTextPhone);
        EditText textPassword = view.findViewById(R.id.editTextTextPassword);

        button6.setOnClickListener(v -> validDateAndSendData(textPhone,textPassword));


        Button emailButton = view.findViewById(R.id.buttonEmail);//Переход с фрагмент с email
        emailButton.setOnClickListener(v -> openLoginFragmentEmail());

        return view;
    }


    private void openLoginFragmentEmail(){
        Fragment emailFragment = new LoginFragmentEmail();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.container1);
        if (currentFragment != null) {
            transaction.remove(currentFragment);
        }

        transaction.add(R.id.container1, emailFragment);
        transaction.commit();
    }

    //Метод проверки на валидность
    private void validDateAndSendData(EditText textPhone, EditText textPassword){
        String email = textPhone.getText().toString();
        String pass = textPassword.getText().toString();

        boolean isValid = true;

        // Проверка на пустые поля
        if(email.isEmpty()){
            textPhone.setHint("Пожалуйста введие ваш phone");
            textPhone.setHintTextColor(Color.RED);
            isValid = false;
        }else{
            textPhone.setHintTextColor(Color.GRAY);
        }

        if(pass.isEmpty()){
            textPassword.setHint("Пожалуйста введие ваш pass");
            textPassword.setHintTextColor(Color.RED);
            isValid = false;
        }

        if(isValid && mListener != null){
            mListener.onPhoneSelected(email,pass);
        }
    }

}