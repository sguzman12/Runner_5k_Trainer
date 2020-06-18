package com.guzman.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Factory for the Timer_ViewModel class. Implements methods to allow for extra parameters
 * to be passed into the Timer_ViewModel.
 */
public class TimerFactory_ViewModel implements ViewModelProvider.Factory {
    @NonNull
    private final Application application;

    private final int intensity;

    /**
     * Default Constructor
     *
     * @param application
     * @param intensity   Level of workout.
     */
    public TimerFactory_ViewModel(@NonNull Application application, int intensity) {
        this.application = application;
        this.intensity = intensity;
    }

    /**
     * Creates a new instance of the given {@code Class}.
     * <p>
     *
     * @param modelClass a {@code Class} whose instance is requested
     * @return a newly created ViewModel
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == Timer_ViewModel.class) {
            return (T) new Timer_ViewModel(application, intensity);
        }
        return null;
    }
}
