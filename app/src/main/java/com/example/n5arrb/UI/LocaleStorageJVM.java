package com.example.n5arrb.UI;

import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import java.util.Locale;


public class LocaleStorageJVM {
        Context context;
     SharedPreferences preferences;
            SharedPreferences.Editor editor;

    public LocaleStorageJVM(Context context ) {
        this.context = context;
//        this.preferences = preferences;
//        this.editor = editor;
        preferences = context.getSharedPreferences("LangfuageApp", MODE_PRIVATE);
        editor = preferences.edit();
    }

    public Context onAttach() {
       String lang = getDefaultLanguage(Locale.getDefault().getLanguage());
       return setLocale(lang);
    }

    public Context onAttach(String defaultLanguage) {
        String lang = getDefaultLanguage(defaultLanguage);
        return setLocale(lang);
    }

    public String getLanguage() {
        return getDefaultLanguage(Locale.getDefault().getLanguage());
    }

    private Context setLocale(String language) {
        setDefaultLanguage(language);
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return    updateResources(language);
        } else
             return updateResourcesLegacy(language);
      }

  //  @Suppress(names = "DEPRECATION")
    private Context updateResourcesLegacy(String language) {

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        return context;
    }


  //  @TargetApi(Build.VERSION_CODES.N)
    private Context updateResources(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);
        return context.createConfigurationContext(config);
    }

    public void setDefaultLanguage(String language) {
        editor.putString("typLanguage", language);
        editor.apply();
    }

    private String getDefaultLanguage(String defaultLanguage) {
        return preferences.getString("typLanguage", defaultLanguage);
    }

   //public void setLocale(String lang) {
   //    Locale myLocale = new Locale(lang);
   //    Resources resources = context.getResources();
   //    DisplayMetrics dm = getResources().getDisplayMetrics();
   //    Configuration conf = getResources().getConfiguration();
   //    conf.locale = myLocale;
   //    getResources().updateConfiguration(conf, dm);
   //    // Intent refresh = new Intent(SplashActivity.this, MainActivity5.class);
   //    // startActivity(refresh);
   //}
}
