package com.example.finance_tracker.ui.fragments.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


//**************************************************************************************//
//* Этот класс нужен для хранения и управления данными в нашем случае login и password *//
//**************************************************************************************//

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<String> userLogin = new MutableLiveData<>();
    private final MutableLiveData<String> userPassword = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isButtonEnabled = new MutableLiveData<>(true);
    public void setUserLogin(String mail){
        userLogin.setValue(mail);
        validateData();
    }

    public void setUserPassword(String pass){
        userPassword.setValue(pass);
        validateData();
    }

    public LiveData<String> getUserLogin(){
        return userLogin;
    }

    public LiveData<String> getUserPassword(){
        return userPassword;
    }

    public LiveData<Boolean> getIsButtonEnabled() { // <-- Геттер для кнопки
        return isButtonEnabled;
    }

    //Метод который проверяет является ли пустыми поля лоигна и пароля
    private void validateData() {
        boolean valid = userLogin.getValue() != null && !userLogin.getValue().isEmpty() &&
                userPassword.getValue() != null && !userPassword.getValue().isEmpty();

        isButtonEnabled.setValue(valid);
    }
    public void resetButtonState() {
        userLogin.setValue("");  // Очистка логина
        userPassword.setValue(""); // Очистка пароля
        isButtonEnabled.setValue(false);  // Сбрасываем кнопку на неактивную
    }

    //Пока не реализовано
    private boolean isValidPhone() {
        return userLogin.getValue() != null && userLogin.getValue().matches("\\+\\d{11,14}"); // Пример проверки номера с кодом страны
    }

}
