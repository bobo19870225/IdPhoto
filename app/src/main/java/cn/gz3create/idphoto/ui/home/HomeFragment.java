package cn.gz3create.idphoto.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cgfay.camera.fragment.NormalMediaSelector;
import com.cgfay.picker.MediaPicker;

import cn.gz3create.idphoto.BR;
import cn.gz3create.idphoto.R;
import cn.gz3create.idphoto.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding mViewDataBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mViewDataBinding.setVariable(BR.model, homeViewModel);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewDataBinding.album.setOnClickListener(v -> MediaPicker.from(HomeFragment.this)
                .showImage(true)
                .showVideo(false)
                .setMediaSelector(new NormalMediaSelector())
                .show());

    }
}
