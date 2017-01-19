package woah.gigigo.com.testbranch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import io.branch.referral.Branch; //branch import, like application ;)
import io.branch.referral.BranchError;//branch import
import org.json.JSONObject;//import for branch

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


  }

  //region branch8 (if the activity is fragment activity check install guide)
  @Override public void onStart() {
    super.onStart();
    Branch branch = Branch.getInstance();

    branch.initSession(new Branch.BranchReferralInitListener() {
      @Override public void onInitFinished(JSONObject referringParams, BranchError error) {
        //los referringparams es esto: https://dev.branch.io/getting-started/configuring-links/
        if (error == null) {
          // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
          // params will be empty if no data found
          // ... insert custom logic here ...
          Toast.makeText(MainActivity.this, "ReferringParams: " + referringParams.toString(),
              Toast.LENGTH_LONG).show();

          //al siguiete se le puede llamar en cualkier momento, depende del intent no de branch
          Toast.makeText(MainActivity.this, "Deeplink: " + getDeepLinking(), Toast.LENGTH_LONG).show();
        } else {
          Log.i("MyApp", error.getMessage());
        }
      }
    }, this.getIntent().getData(), this);
  }

  @Override public void onNewIntent(Intent intent) {
    this.setIntent(intent);
  }

  private static final String INTENT_FILTER_VIEW = "android.intent.action.VIEW";

  private String getDeepLinking() {
    Intent intent = getIntent();
    String action = intent.getAction();
    Uri data = intent.getData();

    if ((action != null && action.equals(INTENT_FILTER_VIEW)) && data != null) {
      String host = data.getHost();
      String path = data.getPath();

      if (!host.isEmpty() && !path.isEmpty()) {
        return (host + "|" + path); //asv aqui llamais al presenter para q haga el deeplink ^_^
      }
    }
    //fixme la primera vez es VIEW
    return "so fuck =_= | 0_o";
  }
}