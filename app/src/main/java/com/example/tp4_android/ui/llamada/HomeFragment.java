package com.example.tp4_android.ui.llamada;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import com.example.tp4_android.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Configurar el contexto
        homeViewModel.setContext(binding.getRoot().getContext());

        binding.btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLlamada();
            }
        });

        // Observar el LiveData para mostrar el resultado de la llamada
        homeViewModel.getLlamadaExitosa().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean exitosa) {
                homeViewModel.errorLlamada(exitosa);
            }
        });

        return root;
    }

    private void realizarLlamada() {
        String numero = binding.etNumero.getText().toString().trim();
        homeViewModel.llamar(numero);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
