package com.example.finance_tracker.ui.fragments.Register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


//**************************************************************************************//
//*  *//
//**************************************************************************************//

public class RegisterViewModel extends ViewModel {

    private final MutableLiveData<String> userMail = new MutableLiveData<>();
    private final MutableLiveData<String> userPhone = new MutableLiveData<>();
    private final MutableLiveData<String> userPassword = new MutableLiveData<>();
    private final MutableLiveData<String> userAcceptPassword = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isButtonEnabled = new MutableLiveData<>(false);
    public void setUserMail(String mail){
        userMail.setValue(mail);
        validateData();
    }

    public void setUserPhone(String phone){
        userPhone.setValue(phone);
        validateData();
    }

    public void setUserPassword(String pass){
        userPassword.setValue(pass);
        validateData();
    }

    public void setuserAcceptPassword(String Acceptpass){
        userAcceptPassword.setValue(Acceptpass);
        validateData();
    }

    public LiveData<String> getuserMail(){
        return userMail;
    }

    public LiveData<String> getuserPhone(){
        return userPhone;
    }

    public LiveData<Boolean> getIsButtonEnabled() { // <-- Геттер для кнопки
        return isButtonEnabled;
    }

    //Метод который проверяет является ли пустыми поля лоигна и пароля
    private void validateData() {
        boolean valid = userMail.getValue() != null && !userMail.getValue().isEmpty() &&
                userPhone.getValue() != null && !userPhone.getValue().isEmpty() &&
                userPhone.getValue() != null && !userPhone.getValue().isEmpty()&&
                userPassword.getValue() != null && !userPassword.getValue().isEmpty()&&
                userAcceptPassword.getValue() != null && !userAcceptPassword.getValue().isEmpty();

        isButtonEnabled.setValue(valid);
    }
    public void resetButtonStateLogin() {
        isButtonEnabled.setValue(false);  // Сбрасываем кнопку на неактивную
    }


}
