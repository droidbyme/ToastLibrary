package com.droidbyme.toastlib;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.widget.IconTextView;

/**
 * Created by Droidbyme on 25-08-2016.
 */
public class ToastLib {

    public ToastLib(Builder builder) {

        Iconify.with(new FontAwesomeModule());

        final float scale = builder.context.getResources().getDisplayMetrics().density;

        LayoutInflater inflater = LayoutInflater.from(builder.context);
        View view = inflater.inflate(R.layout.layout_toast, null);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        IconTextView faTextView = (IconTextView) view.findViewById(R.id.faTextView);
        Button actionButton = (Button) view.findViewById(R.id.toast_btn);
        View actionButtonDivider = view.findViewById(R.id.btn_divider);

        textView.setText(builder.msg);

        if (builder.textColor != 0) {
            textView.setTextColor(builder.textColor);
            faTextView.setTextColor(builder.textColor);
        }

        if (builder.backgroundColor == 0) {
            builder.backgroundColor = ContextCompat.getColor(builder.context, android.R.color.transparent);
        }

        if (builder.icon == 0) {
            imageView.setVisibility(View.GONE);
            if (TextUtils.isEmpty(builder.faString) || builder.faString.length() == 0) {
                faTextView.setVisibility(View.GONE);
            } else {
                faTextView.setVisibility(View.VISIBLE);
                faTextView.setText(builder.faString);
            }
        } else {
            imageView.setImageResource(builder.icon);
            faTextView.setVisibility(View.GONE);
        }

        if (builder.icon != 0 && builder.iconColor != 0) {
            imageView.setColorFilter(builder.iconColor, PorterDuff.Mode.SRC_ATOP);
        }

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(builder.radius);
        shape.setStroke(builder.strokewidth, builder.strokeColor);
        shape.setColor(builder.backgroundColor);
        view.setBackground(shape);

        if (builder.typeface != null) {
            textView.setTypeface(builder.typeface);
        }

        if (builder.isBold) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        }

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, builder.size);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = (int) (builder.width * scale + 0.5f);
        layoutParams.height = (int) (builder.height * scale + 0.5f);
        imageView.setLayoutParams(layoutParams);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins((int) (scale * builder.spacing), 0, 0, 0);
        textView.setLayoutParams(params);

        view.setPadding(builder.padding, builder.padding, builder.padding, builder.padding);

        //set the action button
        if (builder.actionButtonName != null && builder.actonButtonClick != null) {
            actionButtonDivider.setVisibility(View.VISIBLE);
            actionButtonDivider.setAlpha(0.6f);

            actionButton.setVisibility(View.VISIBLE);
            actionButton.setText(builder.actionButtonName);
            actionButton.setOnClickListener(builder.actonButtonClick);
        } else {
            actionButtonDivider.setVisibility(View.GONE);
            actionButton.setVisibility(View.GONE);
        }

        // Create Toast
        Toast toast = new Toast(builder.context);
        toast.setView(view);

        if (builder.myEnum != null) {
            if (builder.myEnum.getId() == ToastEnum.LONG.getId()) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        if (builder.gravity == Gravity.TOP || builder.gravity == Gravity.BOTTOM) {
            toast.setGravity(builder.gravity, 0, builder.margin);
        } else if (builder.gravity == Gravity.START || builder.gravity == Gravity.END ||
                builder.gravity == Gravity.LEFT || builder.gravity == Gravity.RIGHT) {
            toast.setGravity(builder.gravity, builder.margin, 0);
        } else {
            toast.setGravity(builder.gravity, 0, 0);
        }

        toast.show();
    }

    public static class Builder {

        private Context context;
        private String msg;
        private ToastEnum myEnum;
        private int backgroundColor = 0, textColor = 0, icon = 0, iconColor = 0, radius = 0;
        private Typeface typeface;
        private boolean isBold;
        private int gravity = 0;
        private int margin = 0;
        private int size = 0;
        private int width = 0, height = 0;
        private int spacing = 0;
        private int strokewidth = 0;
        private int strokeColor = 0;
        private int padding = 0;
        private String actionButtonName;
        private View.OnClickListener actonButtonClick;
        private String faString = "";

        public Builder(Context context, String msg) {
            this.context = context;
            this.msg = msg;

            // default values
            backgroundColor = ContextCompat.getColor(context, android.R.color.transparent);
            textColor = ContextCompat.getColor(context, android.R.color.black);
            radius = 4;
            gravity = Gravity.BOTTOM;
            margin = 36;
            size = 20;
            width = 24;
            height = 24;
            spacing = 8;
            padding = 4;
        }

        public Builder duration(ToastEnum myEnum) {
            this.myEnum = myEnum;
            return this;
        }

        public ToastLib show() {
            return new ToastLib(this);
        }

        public Builder backgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder icon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder icon(String faString) {
            this.faString = faString;
            return this;
        }

        public Builder iconColor(int iconColor) {
            this.iconColor = iconColor;
            return this;
        }

        public Builder corner(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        public Builder isBold(boolean isBold) {
            this.isBold = isBold;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder margin(int position) {
            this.margin = position;
            return this;
        }

        public Builder padding(int padding) {
            this.padding = padding;
            return this;
        }

        public Builder textSize(int size) {
            this.size = size;
            return this;
        }

        public Builder iconSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder spacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public Builder stroke(int strokewidth, int strokeColor) {
            this.strokewidth = strokewidth;
            this.strokeColor = strokeColor;
            return this;
        }

        public Builder actionButton(String buttonName, View.OnClickListener clickListener) {
            actionButtonName = buttonName;
            actonButtonClick = clickListener;

            return this;
        }
    }

    public static void success(Context context, String msg, Typeface typeface) {
        new Builder(context, msg)
                .duration(ToastEnum.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.green))
                .textColor(ContextCompat.getColor(context, R.color.black))
                .typeface(typeface)
                .stroke(2, ContextCompat.getColor(context, R.color.dark_green))
                .icon(R.drawable.ic_check_black_24dp)
                .isBold(true)
                .textSize(18)
                .corner(16)
                .margin(56)
                .spacing(8)
                .padding(36)
                .show();
    }

    public static void error(Context context, String msg, Typeface typeface) {
        new Builder(context, msg)
                .duration(ToastEnum.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.red))
                .textColor(ContextCompat.getColor(context, R.color.white))
                .typeface(typeface)
                .isBold(true)
                .stroke(2, ContextCompat.getColor(context, R.color.dark_red))
                .icon(context.getString(R.string.fa_error))
                .textSize(18)
                .corner(16)
                .margin(56)
                .spacing(8)
                .padding(36)
                .show();
    }

    public static void progress(Context context, String msg, Typeface typeface) {
        new Builder(context, msg)
                .duration(ToastEnum.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.red))
                .textColor(ContextCompat.getColor(context, R.color.white))
                .typeface(typeface)
                .stroke(2, ContextCompat.getColor(context, R.color.dark_red))
                .isBold(true)
                .icon(context.getString(R.string.fa_spinner))
                .textSize(18)
                .corner(16)
                .margin(56)
                .spacing(8)
                .padding(36)
                .show();
    }
}