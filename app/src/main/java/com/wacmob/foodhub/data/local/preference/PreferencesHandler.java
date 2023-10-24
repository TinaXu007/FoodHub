package com.wacmob.foodhub.data.local.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wacmob.foodhub.dagger.ApplicationContext;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHandler {

    private static final String SHARED_PREFERENCES_KEY = "com.wacmob.promode.musicapp";
    private Context mContext;
    static PreferencesHandler mThis;

    @Inject
    PreferencesHandler(@ApplicationContext Context c) {

        mContext = c;
    }

    public static PreferencesHandler getInstance(Context c) {

        if (mThis == null) {
            mThis = new PreferencesHandler(c);
        }
        return mThis;
    }

    /**
     * @return {@link SharedPreferences} for the application that has been saved
     * with the key DwConstants.SHARED_PREFERENCES_KEY and in
     * Context.MODE_PRIVATE mode
     */
    private SharedPreferences getAppSharedPreferencesObject() {

        return mContext.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Returns the value from the app's {@link SharedPreferences} in private
     * mode with the <b>key</b> provided
     * <p/>
     *
     * @param key : Key used to save the value in the
     *            {@linkplain SharedPreferences}<br>
     * @return {@link Object} the value that has been saved with the respective
     * <b>key</b>. Returns <b>null</b> if no value is found
     * <p/>
     * <i><b>Note:</b> The {@link Object} can be {@link String},
     * {@link Float}, {@link Long}, {@link Boolean} or
     * {@link Integer} </i>
     */
    public Object getValue(String key) {

        if (key == null) {
            return null;
        }
        Map<String, ?> allPreferences;
        SharedPreferences preferences = getAppSharedPreferencesObject();
        allPreferences = preferences.getAll();
        for (Map.Entry<String, ?> entry : allPreferences.entrySet()) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return null;
    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, boolean object) {

        if (key == null) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putBoolean(key, object);
        return sharedPreferencesEditor.commit();


    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, float object) {

        if (key == null) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putFloat(key, object);
        return sharedPreferencesEditor.commit();

    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, int object) {

        if (key == null) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putInt(key, object);
        return sharedPreferencesEditor.commit();

    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, long object) {

        if (key == null) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putLong(key, object);
        return sharedPreferencesEditor.commit();

    }

    /**
     * Saves the value to the app's {@link SharedPreferences} in private mode
     * with the <b>key</b> provided
     * <p/>
     *
     * @param key    : Key used to save the value in the
     *               {@linkplain SharedPreferences}<br>
     * @param object : This object will be saved to the {@link SharedPreferences}
     *               <b> with key</b><br>
     * @return {@link Boolean}; <b>true</b>, if the value is saved successfully,
     * else returns <b>false </b>
     */
    public boolean saveSharedPreferences(String key, String object) {

        if ((key == null) || (object == null)) {
            return false;
        }
        SharedPreferences preferences = getAppSharedPreferencesObject();
        Editor sharedPreferencesEditor = preferences.edit();
        sharedPreferencesEditor.putString(key, object);
        return sharedPreferencesEditor.commit();
    }


    public void saveUserToken(String userToken) {

        saveSharedPreferences("userToken", userToken);
    }

    public String getUserToken() {

        Object prefObj = getValue("userToken");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }

    public boolean getBoolean(String key) {

        Object prefObj = getValue(key);
        if (prefObj != null) {
            return (Boolean) prefObj;
        }
        return false;

    }



    public void saveAppConfiguration(String configuration) {

        saveSharedPreferences("configuration", configuration);
    }

    public String getAppConfiguration() {

        Object prefObj = getValue("configuration");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }





    public void saveGoogleToken(String googleToken) {

        saveSharedPreferences("googleToken", googleToken);
    }

    public String getGoogleToken() {

        Object prefObj = getValue("googleToken");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }

    public void saveGoogleId(String googleId) {

        saveSharedPreferences("googleId", googleId);
    }

    public String getGoogleId() {

        Object prefObj = getValue("googleId");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }



    public void saveFacbookToken(String facebookToken) {

        saveSharedPreferences("facebookToken", facebookToken);
    }

    public String getFacebookToken() {

        Object prefObj = getValue("facebookToken");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }



    public void saveFacebookId(String facebookId) {

        saveSharedPreferences("facebookId", facebookId);
    }

    public String getFacebookId() {

        Object prefObj = getValue("facebookId");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }



    public void saveEmail(String emailId) {

        saveSharedPreferences("emailId", emailId);
    }

    public String getEmail() {

        Object prefObj = getValue("emailId");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }



    public void saveLoginType(String loginType) {

        saveSharedPreferences("loginType", loginType);
    }

    public String getLoginType() {

        Object prefObj = getValue("loginType");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }


    public void saveDeviceRegId(String deviceRegId) {

        saveSharedPreferences("deviceRegId", deviceRegId);
    }

    public String getDeviceRegId() {

        Object prefObj = getValue("deviceRegId");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }


    public void savePasssword(String password) {

        saveSharedPreferences("password", password);
    }

    public String getPassword() {

        Object prefObj = getValue("password");
        if (prefObj != null) {
            return (String) prefObj;
        }
        return (String) prefObj;

    }

    public String getPreferenceValue(String key) {

        Object prefObj = getValue(key);
        if (prefObj != null) {
            return (String) prefObj;
        }
        return "";

    }

    public int getIntPreferenceValue(String key) {

        Object prefObj = getValue(key);
        if (prefObj != null) {
            if (prefObj instanceof Integer)
            return (int) prefObj;
            else if (prefObj instanceof Float)
                return Math.round((Float) prefObj);
        }
        return -1;

    }

}
