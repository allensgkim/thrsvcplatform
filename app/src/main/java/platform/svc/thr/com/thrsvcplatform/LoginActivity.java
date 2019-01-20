package platform.svc.thr.com.thrsvcplatform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    // [START declare_auth]
    private FirebaseAuth firebaseAuth;
    // [END declare_auth]

    private static final String TAG        = "LoginActivity";
    private static final int    RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // [START initialize_auth]
        firebaseAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }

    /**
     * 구글 로그인 아이디
     * @param view
     */
    private void signIn(View view) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.w(TAG, RC_SIGN_IN + " : " + requestCode );

    }
}
