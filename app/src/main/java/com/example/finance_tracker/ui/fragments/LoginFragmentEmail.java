package com.example.finance_tracker.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.finance_tracker.R;


public class LoginFragmentEmail extends Fragment {

    private OnEmailSelectedListener mListener;

    public interface OnEmailSelectedListener{
        void onEmailSelected(String email, String password);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_email, container, false);

        if(getActivity() instanceof OnEmailSelectedListener){
            mListener = (OnEmailSelectedListener) getActivity();
        }
        Button button6 = view.findViewById(R.id.button6); // Кнопка со стрелочкой
        EditText textEmail = view.findViewById(R.id.editTextTextEmailAddress);
        EditText textPassword = view.findViewById(R.id.editTextTextPassword);


        button6.setOnClickListener(v -> {
            String email = textEmail.getText().toString();
            String pass = textPassword.getText().toString();

            // Проверка на пустые поля
            if(mListener != null){
                if(email.isEmpty() && pass.isEmpty()){
                    textPassword.setHint("Пожалуйста, введите ваш password");
                    textPassword.setHintTextColor(Color.RED);
                    textEmail.setHint("Пожалуйста, введите ваш email");
                    textEmail.setHintTextColor(Color.RED);
                } else if (pass.isEmpty()) {
                    textPassword.setHint("Пожалуйста, введите ваш password");
                    textPassword.setHintTextColor(Color.RED);
                }
                else if (email.isEmpty()) {
                    textEmail.setHint("Пожалуйста, введите ваш email");
                    textEmail.setHintTextColor(Color.RED);
                } else{
                    mListener.onEmailSelected(email,pass);
                }
            }

        });

        Button phoneButton = view.findViewById(R.id.buttonPhone);
        phoneButton.setOnClickListener(v -> openLoginFragmentPhone()); //Открывает фрагмент с полем для логина через телефон

        return view;
    }


    private void openLoginFragmentPhone(){ //метод для октрытия фрагмента с полем телефона
        Fragment phoneFragment = new LoginFragmentPhone();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.container1);
        if (currentFragment != null) {
            transaction.remove(currentFragment);
        }

        transaction.add(R.id.container1, phoneFragment);
        transaction.commit();
    }

}