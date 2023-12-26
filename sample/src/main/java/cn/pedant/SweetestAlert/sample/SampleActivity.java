package cn.pedant.SweetestAlert.sample;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import cn.pedant.SweetestAlert.SweetAlertDialog;

public class SampleActivity extends Activity implements View.OnClickListener {

    private int i = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity);
        findViewById(R.id.basic_test).setOnClickListener(this);
        findViewById(R.id.under_text_test).setOnClickListener(this);
        findViewById(R.id.error_text_test).setOnClickListener(this);
        findViewById(R.id.success_text_test).setOnClickListener(this);
        findViewById(R.id.warning_confirm_test).setOnClickListener(this);
        findViewById(R.id.warning_cancel_test).setOnClickListener(this);
        findViewById(R.id.custom_img_test).setOnClickListener(this);
        findViewById(R.id.progress_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.basic_test) {
            SweetAlertDialog sd = new SweetAlertDialog(this);
            sd.setCancelable(true);
            sd.setCanceledOnTouchOutside(true);
            sd.show();
        } else if (v.getId() == R.id.under_text_test) {
            new SweetAlertDialog(this)
                    .setContentText("It's pretty, isn't it?")
                    .show();
        } else if (v.getId() == R.id.error_text_test) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Something went wrong!")
                    .show();
        } else if (v.getId() == R.id.success_text_test) {
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("You clicked the button!")
                    .show();
        } else if (v.getId() == R.id.warning_confirm_test) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setConfirmText("Yes,delete it!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.setTitleText("Deleted!")
                                    .setContentText("Your imaginary file has been deleted!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        }
                    })
                    .show();
        } else if (v.getId() == R.id.warning_cancel_test) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setCancelText("Cancel")
                    .setConfirmText("Yes,delete it!")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.setTitleText("Cancelled!")
                                    .setContentText("Your imaginary file is safe :)")
                                    .setConfirmText("OK")
                                    .showCancelButton(false)
                                    .setCancelClickListener(null)
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.setTitleText("Deleted!")
                                    .setContentText("Your imaginary file has been deleted!")
                                    .setConfirmText("OK")
                                    .showCancelButton(false)
                                    .setCancelClickListener(null)
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        }
                    })
                    .show();
        } else if (v.getId() == R.id.custom_img_test) {
            new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setTitleText("Sweet!")
                    .setContentText("Here's a custom image.")
                    .setCustomImage(R.drawable.custom_img)
                    .show();
        } else if (v.getId() == R.id.progress_dialog) {
            final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("Loading");
            pDialog.show();
            pDialog.setCancelable(false);
            new CountDownTimer(800 * 7, 800) {
                public void onTick(long millisUntilFinished) {
                    i++;
                    if (i == 0) {
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                    } else if (i == 1) {
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                    } else if (i == 2) {
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                    } else if (i == 3) {
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                    } else if (i == 4) {
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                    } else if (i == 5) {
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                    } else if (i == 6) {
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                    }
                }

                public void onFinish() {
                    i = -1;
                    pDialog.setTitleText("Success!")
                            .setConfirmText("OK")
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                }
            }.start();
        }
    }

}
