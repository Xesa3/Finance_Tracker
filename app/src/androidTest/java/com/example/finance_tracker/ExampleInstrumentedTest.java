
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ExampleInstrumentedTest extends AppCompatActivity {

    private TextInputEditText emailInput, passwordInput;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        // Обработчик кнопки входа
        loginButton.setOnClickListener(v -> attemptLogin());

        // Обработчики кликов по тексту
        findViewById(R.id.forgotPassword).setOnClickListener(v ->
                showToast("Password recovery feature coming soon!"));

        findViewById(R.id.signUp).setOnClickListener(v ->
                showToast("Sign up screen coming soon!"));
    }

    private void attemptLogin() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty()) {
            showToast("Please fill all fields");
        } else {
            // Здесь должна быть реальная логика авторизации
            showToast("Login attempt processing...");
            // startActivity(new Intent(this, HomeActivity.class));
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}