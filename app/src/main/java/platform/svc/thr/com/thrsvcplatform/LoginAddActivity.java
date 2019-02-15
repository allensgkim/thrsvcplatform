package platform.svc.thr.com.thrsvcplatform;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class LoginAddActivity extends AppCompatActivity {

    private static final String TAG = "LoginAddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_add);

        // Login -> Google FireBase UserInfo
        final Intent data = getIntent();
        FirebaseUser user = (FirebaseUser) data.getExtras().get("user");

        // 초기 세팅
        defUserInfo(user);
    }

    /**
     * 로그인에서 구글 정보를 넘겨받은 firebase User 정보를 기본으로 세팅한다.
     * @param user
     */
    private void defUserInfo( final FirebaseUser user ) {

        Log.d(TAG, user.getDisplayName());
        Log.d(TAG, user.getEmail());
        Log.d(TAG, user.getProviderId());
        Log.d(TAG, user.getUid());
        Log.d(TAG, user.zzs());
        Log.d(TAG, user.zzt());

        // 사용자 아이디 -> Google.FireBase.getEmail
        TextView usr_cel_phn_no = findViewById(R.id.usr_eml_addr);
        usr_cel_phn_no.setText(user.getPhoneNumber());
    }

}
