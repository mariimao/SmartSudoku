package interface_adapter.signup.cancel;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.start.StartViewModel;
import use_case.signup.cancel.CancelOutputBoundary;

public class CancelPresenter implements CancelOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final CancelViewModel cancelViewModel;
    private final StartViewModel startViewModel;

    public CancelPresenter (CancelViewModel cancelViewModel,
                            StartViewModel startViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.cancelViewModel = cancelViewModel;
        this.startViewModel = startViewModel;
    }
    @Override
    public void prepareSuccessView() {
//        SignState loginState = loginViewModel.getLoginState();
//        loginState.setUsername(signupOutputData.getUsername());
//        this.loginViewModel.setLoginState(loginState);
        // switches to start view model
        startViewModel.firePropertyChanged();

        viewManagerModel.setActiveViewName(startViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
