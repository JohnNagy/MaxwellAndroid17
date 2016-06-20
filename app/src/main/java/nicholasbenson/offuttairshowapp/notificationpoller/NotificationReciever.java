package nicholasbenson.offuttairshowapp.notificationpoller;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import nicholasbenson.offuttairshowapp.R;

public class NotificationReciever extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        date_format.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        Date date = new Date();
        date.setTime(date.getTime() - 180000); //3 minutes in past to catch updates TODO cache time update instead of guessing
        String iso_8601_date = date_format.format(date);
        String poll_url = String.format("https://public-api.wordpress.com/rest/v1.1/sites/%s/posts?number=1&after=%s", "offuttairshow.com", iso_8601_date);

        String notification = null;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest json_request = new JsonObjectRequest(Request.Method.GET, poll_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response==null||response.isNull("posts")||response.getJSONArray("posts").isNull(0)){
                                Log.d("NotificationPoller", "No update found.");
                            }
                            else {
                                try {
                                    sendNotification(context, android.text.Html.fromHtml(response.getJSONArray("posts").getJSONObject(0).getString("excerpt")).toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("NotificationPoller", error.toString());
                    }
        });
        queue.add(json_request);
    }

    private void sendNotification(Context context, String message){
        NotificationCompat.Builder notification_builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.support.design.R.drawable.notification_template_icon_bg)
                .setContentTitle("Offutt Air Show")
                .setContentText(message)
                .setContentIntent(null)
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.reveille))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        NotificationManager notification_manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notification_manager.notify(0, notification_builder.build());
    }
}