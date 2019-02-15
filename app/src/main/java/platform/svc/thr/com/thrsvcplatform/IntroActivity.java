package platform.svc.thr.com.thrsvcplatform;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    private boolean isLoggedIn  = false;
    private boolean isAutoLogIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // SharedPreference에서 자동 로그인 체크되어 있는지 가져오기, 자동이라면 로그인 정보 가져오기
        isAutoLogIn = false;
        if( isAutoLogIn ) {
            // SharedPreference에서 로그인 true면 아이디, 패스워드 가져오기
            isLoggedIn = true;
        }

        // 로그인 체크
        if( isLoggedIn ) {
            // 1. 자동 로그인 검사 ok 된 사람이므로 메인 화면으로
            new Handler().postDelayed( () -> {
                    Intent intentMain = new Intent(getApplicationContext(), MainSellerActivity.class);
                    startActivity(intentMain);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish(); // callback 받을 수 있다.
            }, 2000);

        }else{
            // 2. 로그인 안 돼 있으므로 로그인 화면으로
            new Handler().postDelayed( () -> {
                    Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentLogin);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
            }, 2000);
        }
    }
}
