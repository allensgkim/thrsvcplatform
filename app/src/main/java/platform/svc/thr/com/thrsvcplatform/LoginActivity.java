package platform.svc.thr.com.thrsvcplatform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG        = "LoginActivity";
    private static final int    RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth firebaseAuth;
    // [END declare_auth]

    // [START mGoogleSignInClient]
    private GoogleSignInClient mGoogleSignInClient;
    // [END mGoogleSignInClient]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.google_login_button).setOnClickListener(this);

        // [START config_signin]
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.key_default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_firebaseAuth]
        firebaseAuth = FirebaseAuth.getInstance();
        // [END initialize_firebaseAuth]
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);

    }


    private void updateUI(FirebaseUser currentUser) {

        if( currentUser != null ) {
            Log.w(TAG,"updateUI is not null");
        } else {
            Log.w(TAG,"updateUI is     null");
        }
    }

    /**
     * 구글 로그인 아이디
     */
    private void signIn() {
        Log.w(TAG, "signIn");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.w(TAG, "account.getDisplayName() : " + account.getDisplayName());
                Log.w(TAG, "account.getEmail() : " + account.getEmail());
                Log.w(TAG, "account.getFamilyName() : " + account.getFamilyName());
                Log.w(TAG, "account.getGivenName() : " + account.getGivenName());
                Log.w(TAG, "account.getId() : " + account.getId());
                Log.w(TAG, "account.getIdToken() : " + account.getIdToken());
                Log.w(TAG, "account.getObfuscatedIdentifier() : " + account.getObfuscatedIdentifier());
                Log.w(TAG, "account.getServerAuthCode() : " + account.getServerAuthCode());
                Log.w(TAG, "account.toJson() : " + account.toJson());
                Log.w(TAG, "account.toJsonForStorage() : " + account.toJsonForStorage());

                // firebase Google
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // fail ...
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle( final GoogleSignInAccount account ) {

        Log.d(TAG, "firebaseAuthWithGoogle : " + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential( account.getIdToken(), null );

        firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(listenerAuth -> {

                    });



        Log.w(TAG, credential.getProvider());
        Log.w(TAG, credential.getSignInMethod());
    }
    // [END auth_with_google]

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.google_login_button : signIn();
                break;
            default: break;
        }
    }
}
