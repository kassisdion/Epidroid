package com.pony.epidroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.pony.epidroid.R;
import com.pony.epidroid.api.Api;
import com.pony.epidroid.api.ApiData;
import com.pony.epidroid.api.listeners.data.LoginListener;
import com.pony.epidroid.preferences.PreferencesConstants;
import com.pony.epidroid.preferences.PreferencesManager;
import com.pony.epidroid.utils.ActivityHelper;


public class LoginActivity extends Activity
{
    private static EditText loginTextView = null;
    private static EditText passwordTextView = null;
    private static TextView statusMessageTextView = null;

    public static void start(Activity parent, boolean killParent)
    {
        ActivityHelper.startActivity(parent, LoginActivity.class, killParent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Reset API handler to take this class as call context.
        Api.reset(this);

        // Init default values for preferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        final PreferencesManager prefs = new PreferencesManager(this);
        final PreferencesManager sharedPrefs = new PreferencesManager(PreferenceManager.getDefaultSharedPreferences(this));

        loginTextView = (EditText) findViewById(R.id.LoginEditText);
        passwordTextView = (EditText) findViewById(R.id.PasswordEditText);
        statusMessageTextView = (TextView) findViewById(R.id.ErrorMsgTextView);

        boolean savePassword = sharedPrefs.getBoolean(PreferencesConstants.TAG_SAVE_PASSWORD, false);
        final String savedLogin = prefs.getPrefs().getString(PreferencesConstants.TAG_LOGIN, null);
        final String savedPassword = prefs.getPrefs().getString(PreferencesConstants.TAG_PASSWORD, null);

        if (savedLogin != null && savePassword)
        {
            loginTextView.setText(savedLogin);
        }
        if (savedPassword != null && savePassword)
        {
            passwordTextView.setText(savedPassword);
        }

        Button signInButton = (Button) findViewById(R.id.SignInButton);
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (loginTextView.getText().toString().isEmpty())
                {
                    statusMessageTextView.setText(R.string.login_error);
                    return;
                }
                if (passwordTextView.getText().toString().isEmpty())
                {
                    statusMessageTextView.setText(R.string.password_error);
                    return;
                }
                statusMessageTextView.setText(R.string.try_connect);

                final String login = loginTextView.getText().toString();
                final String password = passwordTextView.getText().toString();

                Api.login(login, password, new LoginListener()
                {
                    @Override
                    public void onSuccess(String token)
                    {
                        ApiData.token = token;
                        ApiData.login = login;
                        prefs.saveLoginData(login, password);
                        MainMenuActivity.start(LoginActivity.this, true);
                    }

                    @Override
                    public void onError(VolleyError error)
                    {
                        statusMessageTextView.setText(R.string.connection_error);
                    }
                });
            }
        });
    }
}
