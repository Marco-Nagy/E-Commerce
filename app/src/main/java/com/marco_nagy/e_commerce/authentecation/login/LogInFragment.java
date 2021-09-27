package com.marco_nagy.e_commerce.authentecation.login;

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

import com.google.gson.Gson;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.databinding.FragmentLogInBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LogInFragment extends Fragment {
    private static final String TAG = "LogInFragment";
    NavController navController;
    FragmentLogInBinding binding;

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
        binding.logInBtn.setOnClickListener(v -> {
            String email = Objects.requireNonNull(binding.emailETLogin.getText()).toString().trim();
            String password = Objects.requireNonNull(binding.passwordETLogin.getText()).toString().trim();
            LoginRequest loginRequest = new LoginRequest(email,password);
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
                return;
                }else  {

                    Toast.makeText(getContext(), "Welcome", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onResponse: "+response.headers().toString());
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