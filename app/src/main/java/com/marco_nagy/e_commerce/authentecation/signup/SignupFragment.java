package com.marco_nagy.e_commerce.authentecation.signup;

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
import com.marco_nagy.e_commerce.databinding.FragmentSignUpBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupFragment extends Fragment {
    private static final String TAG = "RegisterMainActivity";
    NavController navController;
    private FragmentSignUpBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.logInTextV.setOnClickListener(v -> navController.navigate(R.id.action_signUp_Fragment_to_logInFragment));
        binding.forgotPasswordTextV.setOnClickListener(v -> navController.navigate(R.id.action_signUp_Fragment_to_forgotPasswordFragment));
        binding.signUpBtn.setOnClickListener(v -> {
             String email = Objects.requireNonNull(binding.emailETSignUp.getText()).toString().trim();
             String userName = Objects.requireNonNull(binding.userNameETSignUp.getText()).toString().trim();
             String password = Objects.requireNonNull(binding.passwordETSignUp.getText()).toString().trim();
             String confirmPassword = Objects.requireNonNull(binding.confirmPasswordETSignUp.getText()).toString().trim();
             SignupRequest signupRequest = new SignupRequest(userName,email,password,confirmPassword);
           checkValidation(signupRequest);

        });
    }

    private void onSignupClicked(SignupRequest signupRequest)   {
        AppNetworkBuilder.getClient().signup(signupRequest).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(@NotNull Call<SignupResponse> call, @NotNull Response<SignupResponse> response) {

                if (!response.isSuccessful()){
                    assert response.errorBody() != null;

                    SignupResponseError message = new Gson().fromJson(response.errorBody().charStream(), SignupResponseError.class);
                    Toast.makeText(getContext(), "" + message.getData(), Toast.LENGTH_SHORT).show();
                    return;
                }else
                    Toast.makeText(getContext(), "Account Created", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_signUp_Fragment_to_homeActivity);

                 Log.i(TAG, "onResponse: "+response.body().getData());

            }

            @Override
            public void onFailure(@NotNull Call<SignupResponse> call, @NotNull Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(getContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void checkValidation(SignupRequest signupRequest){
        if (TextUtils.isEmpty(Objects.requireNonNull(signupRequest).getEmail())) {
            binding.emailETSignUp.setError("Enter an E-Mail Address");
            binding.emailETSignUp.requestFocus();
        }
        if (!signupRequest.isEmailValid()) {
            binding.emailETSignUp.setError("Enter a Valid E-mail Address");
            binding.emailETSignUp.requestFocus();
        } if(TextUtils.isEmpty(Objects.requireNonNull(signupRequest).getName())){
            binding.userNameETSignUp.setError("Enter UserData Name.");
            binding.userNameETSignUp.requestFocus();
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(signupRequest).getPassword())) {
            binding.passwordETSignUp.setError("Enter a Password");
            binding.passwordETSignUp.requestFocus();
        }
        if (!signupRequest.isPasswordLengthGreaterThan5()) {
            binding.passwordETSignUp.setError("Enter at least 6 Digit password");
            binding.passwordETSignUp.requestFocus();
        } if(TextUtils.isEmpty(Objects.requireNonNull(signupRequest).getConfirmPassword())){
            binding.confirmPasswordETSignUp.setError("Confirm Password please.");
            binding.confirmPasswordETSignUp.requestFocus();
        } if (binding.passwordETSignUp.equals(binding.confirmPasswordETSignUp)) {
            binding.confirmPasswordETSignUp.setError("Confirm Password not matched.");
            binding.confirmPasswordETSignUp.requestFocus();
        } else
        onSignupClicked(signupRequest);

    }

}