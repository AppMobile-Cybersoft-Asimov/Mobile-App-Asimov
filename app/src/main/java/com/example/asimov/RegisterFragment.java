package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.RegisterRequest;
import com.example.asimov.data.model.RegisterResponse;
import com.example.asimov.data.service.DirectorService;
import com.example.asimov.databinding.FragmentRegisterBinding;
import com.example.asimov.ui.login.fragments.LoginFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnRegRegistrar.setOnClickListener(v -> registerDirector());
    }

    private void registerDirector() {
        if (
                binding.tboxRegFirstName.getText().toString().equals("")
                        || binding.tboxRegLastName.getText().toString().equals("")
                        || binding.tboxRegPhone.getText().toString().equals("")
                        || binding.tboxRegBirthdate.getText().toString().equals("")
                        || binding.tboxRegEmail.getText().toString().equals("")
                        || binding.tboxRegPass.getText().toString().equals("")
                        || !binding.tboxRegEmail.getText().toString().contains("@")
        ) {

            Toast.makeText(getContext(), "Ingrese todos los datos solicitados", Toast.LENGTH_LONG).show();
        } else {
            DirectorService directorService = RetrofitClient.createInstance().create(DirectorService.class);
            RegisterRequest registerRequest = new RegisterRequest()
                    .setFirstName(binding.tboxRegFirstName.getText().toString())
                    .setLastName(binding.tboxRegLastName.getText().toString())
                    .setPhone(binding.tboxRegPhone.getText().toString())
                    .setEmail(binding.tboxRegEmail.getText().toString())
                    .setPassword(binding.tboxRegPass.getText().toString())
                    .setAge(55);

            directorService.register(registerRequest).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getContext(), "Error creating Director ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast toast = Toast.makeText(getContext(), "Director registrado con éxito", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 300);
                    toast.show();
                    goToLogin();
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                }
            });

        }
    }

    private void goToLogin() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
    }
}