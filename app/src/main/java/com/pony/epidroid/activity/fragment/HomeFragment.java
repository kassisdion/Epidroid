package com.pony.epidroid.activity.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.pony.epidroid.R;
import com.pony.epidroid.api.Api;
import com.pony.epidroid.api.ApiData;
import com.pony.epidroid.api.data.Infos;
import com.pony.epidroid.api.listeners.data.InfosListener;
import com.pony.epidroid.api.listeners.data.PhotoListener;

import org.json.JSONException;

import java.io.InputStream;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        final ImageView photo = (ImageView) view.findViewById(R.id.image_view_picture);
        final TextView title = (TextView) view.findViewById(R.id.text_view_title);
        final TextView time = (TextView) view.findViewById(R.id.text_view_time);
        final TextView notification = (TextView) view.findViewById(R.id.text_view_notification_description);

        Api.getPhoto(ApiData.login, new PhotoListener() {
            @Override
            public void onPhoto(String url) {
                new DownloadImageTask(photo).execute(url);
            }

            @Override
            public void onError(VolleyError error) {
            }
        });
        Api.getInfos(new InfosListener() {
            @Override
            public void onInfos(Infos infos) throws JSONException {
                title.setText(infos.infos.getString("title"));
                time.setText(infos.current.getString("active_log"));
                notification.setText(Html.fromHtml(infos.history.get(3).getString("title")));
            }

            @Override
            public void onError(VolleyError error) {
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        final ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap profil = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                profil = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return profil;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
