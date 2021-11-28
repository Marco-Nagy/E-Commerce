package com.marco_nagy.e_commerce.authentication.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.data.SharedPref;
import com.marco_nagy.e_commerce.databinding.FragmentLogInBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogInFragment extends Fragment {
    private static final String TAG = "LogInFragment";
    NavController navController;
    FragmentLogInBinding binding;
   static String token;
    String deviceToken;
    String email;
    String password;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.forgotPasswordTextVlogIn.setOnClickListener(v -> {
                    navController.navigate(R.id.action_logInFragment_to_forgotPasswordFragment);
        });
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String token) {
                Log.i(TAG, "onSuccess: This is your Firebase token "+token);
                deviceToken = token;

            }
        });
        binding.logInBtn.setOnClickListener(v -> {
             email = Objects.requireNonNull(binding.emailETLogin.getText()).toString().trim();
             password = Objects.requireNonNull(binding.passwordETLogin.getText()).toString().trim();
            LoginRequest loginRequest = new LoginRequest(email,password,deviceToken);
            checkLoginValidation(loginRequest);

        });
    }
    private void checkLoginValidation(LoginRequest loginRequest){
        if (TextUtils.isEmpty(Objects.requireNonNull(loginRequest).getEmail())) {
            binding.emailETLogin.setError("Enter an E-Mail Address");
            binding.emailETLogin.requestFocus();
        }
        if (!loginRequest.isEmailValid()) {
            binding.emailETLogin.setError("Enter a Valid E-mail Address");
            binding.emailETLogin.requestFocus();
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(loginRequest).getPassword())) {
            binding.passwordETLogin.setError("Enter a Password");
            binding.passwordETLogin.requestFocus();
        }
        if (!loginRequest.isPasswordLengthGreaterThan5()) {
            binding.passwordETLogin.setError("Enter at least 6 Digit password");
            binding.passwordETLogin.requestFocus();
        }
        else
            onLoginClicked(loginRequest);

    }
    private void onLoginClicked(LoginRequest loginRequest)   {
        AppNetworkBuilder.getClient().login(loginRequest).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {


                if (!response.isSuccessful()){
                    assert response.errorBody() != null;
                    LoginResponseError message = new Gson().fromJson(response.errorBody().charStream(), LoginResponseError.class);
                    Toast.makeText(getContext(), "" + message.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onResponse Error: "+message.getMessage());
                    Log.i(TAG, "onResponse Error: "+token);


                return;
                }else  {
                    assert response.body() != null;
                    token = response.body().getData().getAccessToken();
//
//                    SharedPreferences preferences = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor myEdit = preferences.edit();
//                    myEdit.putString("email",email);
//                    myEdit.apply();
//                    preferences.edit().putString("token",token).apply();


                    SharedPref.write(SharedPref.Token, token);//save string in shared preference.
                    SharedPref.write(SharedPref.EMAIL, email);//save int in shared preference.

                    Toast.makeText(getContext(), "Welcome", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onResponse Success: "+"token "+token);
                    Log.i(TAG, "onResponse Success: "+response.headers().toString());
                    Log.i(TAG, "onResponse Success: "+token);
                    navController.navigate(R.id.action_logInFragment_to_homeActivity);
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(getContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}