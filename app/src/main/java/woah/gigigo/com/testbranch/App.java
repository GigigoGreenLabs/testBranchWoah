package woah.gigigo.com.testbranch;

import android.app.Application;

/**
 * Created by nubor on 19/01/2017.
 */
public class App extends Application {
  @Override public void onCreate() {
    super.onCreate();
    //branch6 (import io.branch.referral.Branch)
    io.branch.referral.Branch.getAutoInstance(this);
  }
}
