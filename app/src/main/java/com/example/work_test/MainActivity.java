package com.example.work_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    //---選取時段的宣告---
    private Button selectBeginTime,selectEndTime;
    int beginTimeHour,beginTimeMinute,endTimeHour,endTimeMinute;
    private ImageButton pickTimeInfo;
    //---選取日期的宣告 month have bug. If need to return or bundle , it need to +1 on parameter---
    private Button selectBeginDate,selectEndDate;
    private int mYear1,mMonth1,mDate1;
    private int mYear2,mMonth2,mDate2;
    private ImageButton pickDateInfo;
    //---chooseColor dialog---
    private MaterialButton selectColor;
    private CharSequence[] colorSequence;
    //---notification dialog---
    private MaterialButton selectNotificationTime;
    private CharSequence[] notificationTimeSequence;
    //---checkbox 宣告----
    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    private String optionCollect;   //將點選的資料變成一條字串
    private String[] optionCollectSplit; //把一條字串split成陣列使用

    //tag test
    private int tag01;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---選取時段的find---
        selectBeginTime = findViewById(R.id.add_course_pickbegintime);
        selectEndTime = findViewById(R.id.add_course_pickendtime);
        pickTimeInfo = findViewById(R.id.add_course_picktimedialog);
        //--------------------------------------------------------------------------
        //---選取日期的find---
        selectBeginDate = findViewById(R.id.add_course_pickbegindate);
        selectEndDate = findViewById(R.id.add_course_pickenddate);
        pickDateInfo = findViewById(R.id.add_course_pickdatedialog);
        //---------------------------------------------------------------------------
        //---checkbox的find---
        ch1 = findViewById(R.id.add_course_monday);
        ch2 = findViewById(R.id.add_course_tuesday);
        ch3 = findViewById(R.id.add_course_wednesday);
        ch4 = findViewById(R.id.add_course_thursday);
        ch5 = findViewById(R.id.add_course_friday);
        ch6 = findViewById(R.id.add_course_saturday);
        ch7 = findViewById(R.id.add_course_sunday);


        //-----------------------------------------------------------------------------
        //-----------------------------------------------------------------------------
        //checkbox.java
        StringBuffer stringBuffer = new StringBuffer();
        if(ch1.isChecked()){stringBuffer.append("1"); stringBuffer.append(",");}
        if(ch2.isChecked()){stringBuffer.append("2"); stringBuffer.append(",");}
        if(ch3.isChecked()){stringBuffer.append("3"); stringBuffer.append(",");}
        if(ch4.isChecked()){stringBuffer.append("4"); stringBuffer.append(",");}
        if(ch5.isChecked()){stringBuffer.append("5"); stringBuffer.append(",");}
        if(ch6.isChecked()){stringBuffer.append("6"); stringBuffer.append(",");}
        if(ch7.isChecked()){stringBuffer.append("7"); stringBuffer.append(",");}
        //---限制式(當頻率欄位未勾選擇觸發toast msg , 可能要寫在新增的setonclicklistener限制式中---
        if(!ch1.isChecked()&&!ch2.isChecked()&&!ch3.isChecked()&&!ch4.isChecked()&&
                !ch5.isChecked()&&!ch6.isChecked()&&!ch7.isChecked()){
            Toast.makeText(MainActivity.this,"頻率欄至少要勾選一個",Toast.LENGTH_LONG).show();
        }
        optionCollect =stringBuffer.toString();
        optionCollectSplit =optionCollect.split(",");




        //---選開始日期的dialog---
        selectBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final Calendar calendar = Calendar.getInstance();
            mYear1 = calendar.get(Calendar.YEAR);
            mMonth1 = calendar.get(Calendar.MONTH);
            mDate1 = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int date) {
                    selectBeginDate.setText(year + "-"+(month+1)+"-"+date);
                }
            },mYear1,mMonth1,mDate1);
            datePickerDialog.show();
            }
        });
        //---選結束日期的dialog---
        selectEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final Calendar calendar = Calendar.getInstance();
            mYear2 = calendar.get(Calendar.YEAR);
            mMonth2 = calendar.get(Calendar.MONTH);
            mDate2 = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int date) {
                    selectEndDate.setText(year+"-"+(month+1)+"-"+date);
                    tag01=0;

                    if (mDate2 > mDate1) {
                        selectEndDate.setText(year + "-" + (month + 1) + "-" + date);
                        tag01 = -1;
                    } else {
                        selectEndDate.setText("Error");
                        tag01 = 1;
                    }
                }
            },mYear2,mMonth2,mDate2);
            datePickerDialog.show();
            }
        });

        //---color select 的 find ---
        selectColor =findViewById(R.id.add_course_chooseColor);
        colorSequence = new CharSequence[]{"Red","Pink","Orange","Yellow","Teal","Green","Blue","Navy","Purple","Gray"};

        selectColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder =new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setTitle("選擇顏色");
                builder.setSingleChoiceItems(colorSequence, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //not finish code
                    }
                });
                builder.setIcon(R.drawable.ic_color_lens_24);
                builder.setBackground(getResources().getDrawable(R.drawable.dialog_rounded_bg));

                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //not finish code
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //not finish code
                    }
                });

                builder.show();
            }
        });

        //---notification select 的 find ----
        selectNotificationTime = findViewById(R.id.add_course_notification);
        notificationTimeSequence = new CharSequence[]{"10分鐘","30分鐘","1小時","3小時","不提醒"};

        selectNotificationTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setTitle("選取時間");
                builder.setSingleChoiceItems(notificationTimeSequence, 4, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //not finish code
                    }
                });
                builder.setIcon(R.drawable.ic_notifications_24);
                builder.setBackground(getResources().getDrawable(R.drawable.dialog_rounded_bg));

                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // not finish code
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // not finish code
                    }
                });
                builder.show();

            }
        });
        //---選取時間的notice---
        pickTimeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setIcon(R.drawable.ic_error_24)
                        .setTitle("說明")
                        .setMessage("結束時間不可早於起始時間")
                        .setBackground(getResources().getDrawable(R.drawable.dialog_rounded_bg))
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }
        });

        //---選取日期的notice---
        pickDateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setIcon(R.drawable.ic_error_24)
                        .setTitle("說明")
                        .setMessage("結束日期不可早於起始日期")
                        .setBackground(getResources().getDrawable(R.drawable.dialog_rounded_bg))
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

    }


    //---選取時段的begin time onclick---
    public void popTime01(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectHour, int selectMinute) {
                beginTimeHour = selectHour;
                beginTimeMinute = selectMinute;
                selectBeginTime.setText(String.format(Locale.getDefault(),"%02d : %02d",beginTimeHour,beginTimeMinute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,beginTimeHour,beginTimeMinute,true);

        timePickerDialog.setTitle("選取時間");
        timePickerDialog.show();

    }

    //---選取時段的end time onclick---
    public void popTime02(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectHour, int selectMinute) {
                endTimeHour = selectHour;
                endTimeMinute = selectMinute;
                selectEndTime.setText(String.format(Locale.getDefault(),"%02d : %02d",endTimeHour,endTimeMinute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,endTimeHour,endTimeMinute,true);

        timePickerDialog.setTitle("選取時間");
        timePickerDialog.show();

    }
}