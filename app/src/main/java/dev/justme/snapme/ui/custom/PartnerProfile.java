package dev.justme.snapme.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import dev.justme.snapme.R;

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

        try {
            String name = attrs.getString(R.styleable.PartnerProfile_name);
            String bio = attrs.getString(R.styleable.PartnerProfile_bio);
            String pictureUrl = attrs.getString(R.styleable.PartnerProfile_picture_url);

            Log.d(null, String.format("Name: %s; Bio: %s; PictureUrl: %s", name, bio, pictureUrl));
        } finally {
            attrs.recycle();
        }

        View view = inflate(getContext(), R.layout.partner_profile, this);
        view.findViewById(R.id.partner_profile_name);
        view.findViewById(R.id.partner_profile_name);
    }
}
