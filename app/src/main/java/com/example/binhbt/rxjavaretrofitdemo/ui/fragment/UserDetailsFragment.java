package com.example.binhbt.rxjavaretrofitdemo.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.binhbt.rxjavaretrofitdemo.R;
import com.example.binhbt.rxjavaretrofitdemo.model.User;
import com.example.binhbt.rxjavaretrofitdemo.net.NetworkRequest;
import com.example.binhbt.rxjavaretrofitdemo.net.RestAPI;
import com.example.binhbt.rxjavaretrofitdemo.net.RestAPIBuilder;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by binhbt on 6/8/2016.
 */
public class UserDetailsFragment extends BaseFragment {
    @Bind(R.id.iv_cover)
    ImageView iv_cover;
    @Bind(R.id.tv_fullname)
    TextView tv_fullname;
    @Bind(R.id.tv_email) TextView tv_email;
    @Bind(R.id.tv_followers) TextView tv_followers;
    @Bind(R.id.tv_description) TextView tv_description;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry) RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;
    public UserDetailsFragment() {
        setRetainInstance(true);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }
    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            this.loadUserDetails();
        }
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    private void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    private void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    private void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    private void showError(String message) {
        this.showToastMessage(message);
    }
    private void renderUser(User user) {
        if (user != null) {
            Picasso.with(getActivity()).load(user.getCoverUrl()).into(this.iv_cover);
            this.tv_fullname.setText(user.getFullName());
            this.tv_email.setText(user.getEmail());
            this.tv_followers.setText(String.valueOf(user.getFollowers()));
            this.tv_description.setText(user.getDescription());
        }
    }
    private void loadUserDetails() {
        User user= (User)getArguments().getSerializable("user");

        RestAPI api = RestAPIBuilder.buildRetrofitService();
        hideRetry();
        showLoading();
        mGetPostSubscription =  NetworkRequest.performAsyncRequest(api.userEntityById(user.getUserId()), (data) -> {
            // Update UI on the main thread
            hideLoading();
            renderUser(data);
            Log.e("response", data.toString());
        }, (error) -> {
            // Handle error
            hideLoading();
            showError("Request failed");
            showRetry();
        });
        /*
        User user= (User)getArguments().getSerializable("user");
        Call<User> call= new ApiService().getApi().userEntityById(user.getUserId());
        call.enqueue(new VegaRequest<User>() {
            @Override
            public void onStart() {
                hideRetry();
                showLoading();
            }

            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                hideLoading();
                Log.e("response", response.body().toString());
                renderUser(response.body());
                hideRetry();
            }

            @Override
            public void onFailure(Throwable t) {
                hideLoading();
                showError("Request failed");
                showRetry();
            }
        });
        */

    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserDetailsFragment.this.loadUserDetails();
    }

    private Subscription mGetPostSubscription;
    @Override
    public void onStop(){
        if (mGetPostSubscription != null) {
            mGetPostSubscription.unsubscribe();
        }
        super.onStop();
    }
}
