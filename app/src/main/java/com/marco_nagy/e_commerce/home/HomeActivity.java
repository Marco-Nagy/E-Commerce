package com.marco_nagy.e_commerce.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.marco_nagy.e_commerce.cart.CartFragment;
import com.marco_nagy.e_commerce.notification.NotificationsActivity;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.databinding.ActivityHomeBinding;
import com.marco_nagy.e_commerce.messages.MessagesActivity;

public class HomeActivity extends AppCompatActivity {
ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
        binding.notificationBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), NotificationsActivity.class)));
        binding.messagesBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MessagesActivity.class)));

        getNavigationToCartFragment();

}

    private void getNavigationToCartFragment() {
        Intent i = getIntent();
        String data = i.getStringExtra("FromReservation");

        if (data != null && data.contentEquals("1")) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, new CartFragment());
            fragmentTransaction.commitNow();

            binding.navView.setSelectedItemId(R.id.navigation_cart);
        }
    }
}