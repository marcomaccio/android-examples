package name.marmac.tutorials.android.bankaccountanalyzer.activities.auth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import name.marmac.tutorials.android.bankaccountanalyzer.R;

import static name.marmac.tutorials.android.bankaccountanalyzer.activities.auth.AuthenticatorActivity.ARG_ACCOUNT_TYPE;

/**
 * In charge of the Sign up process. Since it's not an AuthenticatorActivity decendent,
 * it returns the result back to the calling activity, which is an AuthenticatorActivity,
 * and it return the result back to the MarmacAuthenticator
 *
 * Created by marcomaccio on 31/07/2015.
 */
public class SignUpActivity extends Activity {

    private String TAG = getClass().getSimpleName();
    private String mAccountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAccountType = getIntent().getStringExtra(ARG_ACCOUNT_TYPE);

        setContentView(R.layout.act_signup);

        findViewById(R.id.alreadyMember).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createAccount();
            }
        });
    }
}
