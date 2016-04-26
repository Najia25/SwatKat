package co.gobd.tracker.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import co.gobd.tracker.network.AuthenticationApi;
import co.gobd.tracker.network.JobApi;
import co.gobd.tracker.network.TrackerApi;
import co.gobd.tracker.service.authentication.AuthenticationService;
import co.gobd.tracker.service.authentication.AuthenticationServiceImpl;
import co.gobd.tracker.service.job.JobService;
import co.gobd.tracker.service.job.JobServiceImpl;
import co.gobd.tracker.service.tracker.TrackerService;
import co.gobd.tracker.service.tracker.TrackerServiceImpl;
import co.gobd.tracker.utility.Constant;
import co.gobd.tracker.utility.SessionManager;
import co.gobd.tracker.utility.SessionManagerImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by tonmoy on 11-Apr-16.
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Context context) {
        return context.getSharedPreferences(Constant.SharedPrefs.KEY_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public TrackerService providesTrackerService(TrackerApi trackerApi) {
        return new TrackerServiceImpl(trackerApi);
    }

    @Provides
    @Singleton
    public AuthenticationService providesAuthenticationService(AuthenticationApi authenticationApi) {
        return new AuthenticationServiceImpl(authenticationApi);
    }

    @Provides
    @Singleton
    public SessionManager providesSessionManager(SharedPreferences sharedPreferences){
        return new SessionManagerImpl(sharedPreferences);
    }

    @Provides
    @Singleton
    public JobService providesJobService(JobApi jobApi) {
        return new JobServiceImpl(jobApi);
    }


}
