package dev.justme.snapme.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import dev.justme.snapme.R;
import dev.justme.snapme.helpers.AsyncImageLoader;

public class PartnerProfile extends FrameLayout {

    public PartnerProfile(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    public PartnerProfile(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public PartnerProfile(Context context) {
        super(context);
        initView(context, null);
    }

    private void initView(Context context, @Nullable AttributeSet rawAttrs) {
        if (rawAttrs == null) Log.e(null, "rawAttrs have to be passed!");
        TypedArray attrs = context.getTheme().obtainStyledAttributes(rawAttrs, R.styleable.PartnerProfile, 0, 0);

        View view = inflate(getContext(), R.layout.partner_profile, this);

        try {
            String name = attrs.getString(R.styleable.PartnerProfile_name);
            String bio = attrs.getString(R.styleable.PartnerProfile_bio);
            String pictureUrl = attrs.getString(R.styleable.PartnerProfile_picture_url);

            ((TextView)view.findViewById(R.id.partner_profile_name)).setText(name);
            ((TextView)view.findViewById(R.id.partner_profile_bio)).setText(bio);
            new AsyncImageLoader(view.findViewById(R.id.partner_profile_image)).execute(pictureUrl);
        } finally {
            attrs.recycle();
        }
    }
}
