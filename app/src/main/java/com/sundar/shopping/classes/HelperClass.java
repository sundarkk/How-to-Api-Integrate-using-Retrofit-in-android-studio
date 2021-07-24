package com.sundar.shopping.classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.sundar.shopping.R;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.sundar.shopping.R;

import com.sundar.shopping.activities.LoginActivity;
import com.sundar.shopping.dialog.CustomLoader;
import com.sundar.shopping.dialog.CustomLoaderWithMessages;

import org.aviran.cookiebar2.CookieBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperClass {
    static CustomLoader customLoader;
    static CustomLoaderWithMessages customLoaderWithMessages;

    static boolean status = false;
    static String TAG = "HelperClassTag";


    public static void setTopBarGradient(Activity activity) {
        Window window = activity.getWindow();
      //  Drawable background = activity.getResources().getDrawable(R.drawable.screen_background_gradient);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
      //  window.setBackgroundDrawable(background);

        //TODO my side added code wifi local_ip_address......like Ip: 223.236.151.176
        //   getLocalIpAddress();

    }

    public static void showLoader(Activity activity) {
        try {
            customLoader = new CustomLoader(activity);
            customLoader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            customLoader.show();
            Log.d(TAG, "Showing loader from: " + activity.getLocalClassName());

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void hideLoader() {
        try {
            customLoader.dismiss();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void showLoader(Activity activity, boolean status) {
        try {
            customLoader = new CustomLoader(activity);
            customLoader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            customLoader.setCancelable(status);
            customLoader.setCanceledOnTouchOutside(false);
            customLoader.show();
            Log.d(TAG, "Showing loader from: " + activity.getLocalClassName());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void hideLoaderWithMessages() {
        try {
            if ((customLoaderWithMessages != null) && customLoaderWithMessages.isShowing()) {
                customLoaderWithMessages.dismiss();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void updateLoaderMessage(String m) {
        try {
            customLoaderWithMessages.setMessage(m);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static boolean getNetworkInfo(Context activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() == true;
    }


    public static void setUsername(String name, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("UserName", name);
        editor.apply();
    }

    public static String getUserName(Context sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        return pref.getString("UserName", "");
    }


    public static void setProductID(String productId, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("PRODUCT_ID", productId);
        editor.apply();
    }

    public static String getProductID(Context sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        return pref.getString("PRODUCT_ID", "");
    }

    public static void showErrorBarAlert(Activity activity, String title, String message, int icon, int gravity) {
        CookieBar.build(activity)
                .setTitle(title)
                .setTitleColor(R.color.white)
                .setBackgroundColor(R.color.resend_color)
                .setIcon(icon)
                .setCookiePosition(gravity)
                .setMessage(message)
                .setMessageColor(R.color.white)
                .setDuration(3000) // 3 seconds
                .show();
    }

    public static void snackbar(Context context, ViewGroup layout, String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
                .setAction(context.getString(R.string.hide), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(context.getResources().getColor(R.color.white))
                .show();
    }

    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        //Todo new pattern addded 23/03/2021
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+[com]$";
        //Todo my side added mail pattern(without dot)
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 9 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
                // txtPhone.setError("Not Valid Number");
            } else {
                check = android.util.Patterns.PHONE.matcher(phone).matches();
            }
        } else {
            check = false;
        }
        return check;
    }


//    public static boolean isValidMobile(String phone) {
////        String regexStr = "^[0-9]*$";
////        return phone.matches(regexStr);
//
//        if(!Pattern.matches("^[0-9]*$", phone)) {
//            return phone.length() > 6 && phone.length() <= 13;
//        }
//        return true;
//
//    }

    //Todo my workin 14 july...........
   /* public void saveData(String recentItemID, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        List<recentItemID> productIDS = null;
        String json = gson.toJson(productIDS);
        editor.putString("task list", json);
        editor.apply();
    }
    public void getData(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        Gson gson = new Gson();
        String json = pref.getString("task list", null);
        Type type = new RecentImesId<ArrayList<ProductID>>() {}.getType();
        productIDS = gson.fromJson(json, type);
        if (productIDS == null) {
            productIDS = new ArrayList<>();
        }
    }

*/


    public static void setName(String name, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Name", name);
        editor.apply();
    }

    public static String getName(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        return pref.getString("Name", "");
    }

    public static void  setEmail(String email, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Email", email);
        editor.apply();
    }

    public static String getEmail(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        return pref.getString("Email", "");
    }

    public static void setUserImage(String userImage, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("UserImage", userImage);
        editor.apply();
    }

    public static String getUserImage(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        return pref.getString("UserImage", "");
    }

    public static void setMobileNumber(String mobileNumber, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("MobileNumber", mobileNumber);
        editor.apply();
    }

    public static String getMobileNumber(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        return pref.getString("MobileNumber", "");
    }

    public static void setBirthDate(String birthDate, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("BirthDate", birthDate);
        editor.apply();
    }

    public static String getBirthDate(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        return pref.getString("BirthDate", "");
    }

    public static void setPassword(String password, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Password", password);
        editor.apply();
    }

    public static String getPassword(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        return pref.getString("Password", "");
    }

    public static void setConformPassword(String ConformPassword, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("ConformPassword", ConformPassword);
        editor.apply();
    }

    public static String getConformPassword(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.PREFRENCES, 0);
        return pref.getString("ConformPassword", "");
    }

    public static void setDeviceId(String deviceId, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("DeviceId", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("DeviceId", deviceId);
        editor.apply();
    }

    public static String getDeviceId(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("DeviceId", 0);
        return pref.getString("DeviceId", "");
    }

    public static void setToken(String token, Context sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("DeviceToken", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Token", token);
        editor.apply();
    }

    public static String getToken(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("DeviceToken", 0);
        return pref.getString("Token", "");
    }

    public static void setEmailNotificationStatus(int status, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("NotificationStatus", status);
        editor.apply();
    }

    public static Integer getEmailNotificationStatus(Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        return pref.getInt("NotificationStatus", 0);
    }

    //Todo dialog data visible
    public static void showCamerSettingDialog(final Activity activity, String message, String buttonText, boolean cancelable, final boolean finishActivity) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);

        builder1.setMessage(message);
        builder1.setCancelable(cancelable);
        builder1.setPositiveButton(
                buttonText,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
//                        if (finishActivity) {
//                            activity.finish();
//                        }
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                        intent.setData(uri);
                        activity.startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                buttonText,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (finishActivity) {
                            activity.finish();
                        }

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static void showSimpleAlertDialog(final Activity activity, String message, String buttonText, boolean cancelable, final boolean finishActivity) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
        builder1.setMessage(message);
        builder1.setCancelable(cancelable);
        builder1.setPositiveButton(
                buttonText,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (finishActivity) {
                            activity.finish();
                        }
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    public static void showSimpleAlertDialog(final Activity activity, String title, String message, String buttonText, boolean cancelable, final boolean finishActivity) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
        builder1.setMessage(message);
        builder1.setTitle(title);
        builder1.setCancelable(cancelable);
        builder1.setPositiveButton(
                buttonText,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (finishActivity) {
                            activity.finish();
                        }
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

//    public static void showSimpleAlertDialog(final Activity activity, String message, String buttonText, boolean cancelable, final ButtonPressListener listener) {
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
//        builder1.setMessage(message);
//        builder1.setCancelable(cancelable);
//        builder1.setPositiveButton(
//                buttonText,
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                        listener.onButtonPressed(true);
//                    }
//                });
//
//        AlertDialog alert11 = builder1.create();
//        alert11.show();
//    }


    public static void showBarAlert(Activity activity, String title, String message, int icon, int gravity) {
        CookieBar.build(activity)
                .setTitle(title)
                .setTitleColor(R.color.white)
                .setBackgroundColor(R.color.colorPrimaryDark)
                .setIcon(icon)
                .setCookiePosition(gravity)
                .setMessage(message)
                .setMessageColor(R.color.white)
                .setDuration(3000) // 3 seconds
                .show();
    }


    public static void showSuccessBarAlert(Activity activity, String title, String message, int gravity) {
        CookieBar.build(activity)
                .setTitle(title)
                .setTitleColor(R.color.white)
                .setBackgroundColor(R.color.black)
                .setCookiePosition(gravity)
                .setMessage(message)
                .setMessageColor(R.color.white)
                .setDuration(3000) // 3 seconds
                .show();
    }

   /* public static void showDecisionDialog(final Activity activity, final String message, final ButtonPressListener listener) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
        builder1.setTitle(activity.getString(R.string.app_name));
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                activity.getString(R.string.remove),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        listener.onButtonPressed(true);
                    }
                });

        builder1.setNegativeButton(
                activity.getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        listener.onButtonPressed(false);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }*/
    public static void enableButton(Button button) {
        button.setClickable(true);
    }

    public static void disableButton(Button button) {
        button.setClickable(false);
    }

    public static void enableButton(ViewGroup button) {
        button.setClickable(true);
    }

    public static void disableButton(ViewGroup button) {
        button.setClickable(false);
    }

    public static void toast(Context mContext, String Message) {
        Toast.makeText(mContext, Message, Toast.LENGTH_SHORT).show();
    }

    public static void setImage(String url, ImageView imageView, Activity activity) {
        if (url == null) {
            Picasso.with(activity).load(R.drawable.fff).into(imageView);
        } else if (url.isEmpty()) {
            Picasso.with(activity).load(R.drawable.fff).into(imageView);
        } else {
            Picasso.with(activity).load(url).placeholder(R.drawable.fff).into(imageView);
        }
    }



    public static void setBannerImage(String url, ImageView imageView, Activity activity) {
        if (url == null) {
            Picasso.with(activity).load(R.drawable.banner_placeholder).into(imageView);
        } else if (url.isEmpty()) {
            Picasso.with(activity).load(R.drawable.banner_placeholder).into(imageView);
        } else {
            Picasso.with(activity).load(url).placeholder(R.drawable.banner_placeholder).into(imageView);
        }
    }

   /* public static void setBannerImage(String url, ImageView imageView, Activity activity, int height, int width) {
        if (url == null) {
            Picasso.with(activity).load(R.drawable.banner_placeholder).into(imageView);
        } else if (url.isEmpty()) {
            Picasso.with(activity).load(R.drawable.banner_placeholder).into(imageView);
        } else {
            Picasso.with(activity).load(url).resize(width, height).placeholder(R.drawable.banner_placeholder).into(imageView);

        }
    }*/



    public static void setImage(String url, ImageView imageView, int placeholder, Activity activity) {
        if (url == null) {
            Picasso.with(activity).load(placeholder).into(imageView);
        } else if (url.isEmpty()) {
            Picasso.with(activity).load(placeholder).into(imageView);
        } else {
            Picasso.with(activity).load(url).placeholder(placeholder).into(imageView);
        }
    }

    public static void setImage(String url, ImageView imageView, Activity activity, int height, int width) {
        if (url == null) {
            Picasso.with(activity).load(R.drawable.fff).into(imageView);
        } else if (url.isEmpty()) {
            Picasso.with(activity).load(R.drawable.fff).into(imageView);
        } else {
           Picasso.with(activity).load(url).resize(width, height).placeholder(R.drawable.fff).into(imageView);

        }
    }


//    public static void setImage(String url, ImageView imageView, int placeholder, Activity activity) {
//        if (url == null) {
//            imageView.setImageResource(placeholder);
//        } else if (url.isEmpty()) {
//            imageView.setImageResource(placeholder);
//        } else {
//            Picasso.with(activity).load(url).placeholder(placeholder).into(imageView);
//        }
//
//    }
//
//    public static void setImage(String url, ImageView imageView, Activity activity) {
//        if (url == null) {
//            // Picasso.with(activity).load(R.drawable.ic_profile_nav_header).into(imageView);
//            Picasso.with(activity).load(R.drawable.fff).into(imageView);
//        } else if (url.isEmpty()) {
//            Picasso.with(activity).load(R.drawable.fff).into(imageView);
//        } else {
//            Picasso.with(activity).load(url).placeholder(R.drawable.fff).into(imageView);
//        }
//
//    }
//
//    public static void setImage(String url, ImageView imageView, Activity activity, int height, int width) {
//        if (url == null) {
//            Picasso.with(activity).load(R.drawable.fff).into(imageView);
//        } else if (url.isEmpty()) {
//            Picasso.with(activity).load(R.drawable.fff).into(imageView);
//        } else {
//            Picasso.with(activity).load(url).resize(width, height).placeholder(R.drawable.fff).into(imageView);
//        }
//    }
//
//    public static void setSquareImage(String url, ImageView imageView, Activity activity) {
//        if (url == null) {
//            Picasso.with(activity).load(R.drawable.fff).into(imageView);
//        } else if (url.isEmpty()) {
//            Picasso.with(activity).load(R.drawable.fff).into(imageView);
//        } else {
//            Picasso.with(activity).load(url).placeholder(R.drawable.fff).into(imageView);
//        }
//    }
//
//    public static void setSquareImage(final String url, final ImageView imageView, final Activity activity, final int height, final int width) {
//        if (url == null) {
//            Picasso.with(activity).load(R.drawable.fff).into(imageView);
//        } else if (url.isEmpty()) {
//            Picasso.with(activity).load(R.drawable.fff).into(imageView);
//        } else {
//            Picasso.with(activity).load(url).networkPolicy(NetworkPolicy.OFFLINE).
//                    resize(width, height).placeholder(R.drawable.fff).into(imageView, new Callback() {
//                @Override
//                public void onSuccess() {
//
//                }
//
//                @Override
//                public void onError() {
//                    Picasso.with(activity).load(url).resize(width, height).placeholder(R.drawable.fff).into(imageView);
//                }
//
//            });
//        }
//    }


    public static class phonehelper {

        int image;
        String title;
        GradientDrawable color;

        public phonehelper(GradientDrawable color, int image, String title) {
            this.image = image;
            this.title = title;
            this.color = color;
        }

        public int getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public Drawable getgradient() {
            return color;
        }

    }

    public static Integer getSessionExpired(Context context) {
        SharedPreferences pref = context.getSharedPreferences("LogInSession", 0);
        return pref.getInt("SessionStatus", 0);
    }

/*    public static void askTologout(final Activity activity) {
        final Dialog confirmation_logout = new Dialog(activity);
        confirmation_logout.setContentView(R.layout.confirmation_dialog);
        confirmation_logout.getWindow().getAttributes().windowAnimations = R.style.CustomAnimation_slide_bottom;
        confirmation_logout.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        confirmation_logout.setCancelable(true);
        Button yes = confirmation_logout.findViewById(R.id.yes);
        Button cancel = confirmation_logout.findViewById(R.id.cancel);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmation_logout.dismiss();
                logOut(activity);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmation_logout.dismiss();

            }
        });
        confirmation_logout.show();

    }*/

    public static void logOut(Activity activity) {
        SharedPreferences.Editor sharedPreferences = activity.getSharedPreferences(Constants.MY_PREFRENCES, 0).edit();
        if (sharedPreferences != null) {
            sharedPreferences.clear();
            sharedPreferences.apply();
            Log.e("Prefrences", "Cleared");
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    public static void setHomeSelectedPagerPosition(int value, Context sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HomeSelectedPagerPosition", value);
        editor.apply();

    }

    public static int getHomeSelectedPagerPosition(Context sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        return pref.getInt("HomeSelectedPagerPosition", 0);

    }
    //Todo good marning and good evening bottom show
//    public static String getWishType(Activity activity) {
//        Calendar c = Calendar.getInstance();
//        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
//        String wish_type = "";
//        if (timeOfDay >= 0 && timeOfDay < 12) {
//            wish_type = "Good morning,";
//        } else if (timeOfDay >= 12 && timeOfDay < 16) {
//            wish_type = "Good afternoon,";
//        } else if (timeOfDay >= 16 && timeOfDay < 24) {
//            wish_type = "Good evening,";
//        }
//
//        setLastWish(wish_type, activity);
//        return wish_type;
//    }

    private static void setLastWish(String value, Context sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("LastWish", value);
        editor.apply();
    }

    public static String getLastWish(Context sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences(Constants.MY_PREFRENCES, 0);
        return pref.getString("LastWish", "");
    }

    public static void playVibrator(Context activity, int milliseconds) {
        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        if (v != null) {
            v.vibrate(milliseconds);
        }
    }


}
