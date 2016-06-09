package com.ssum.android24alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    //상수 값들
    private static final int DIALOG_YES_NO_MESSAGE = 1;
    private static final int DIALOG_YES_NO_LONG_MESSAGE = 2;
    private static final int DIALOG_LIST = 3;
    private static final int DIALOG_PROGRESS = 4;
    private static final int DIALOG_SINGLE_CHOICE = 5;
    private static final int DIALOG_MULTIPLE_CHOICE = 6;
    private static final int DIALOG_TEXT_ENTRY = 7;
    private static final int DIALOG_MULTIPLE_CHOICE_CURSOR = 8;
    private static final int DIALOG_YES_NO_ULTRA_LONG_MESSAGE = 9;
    private static final int DIALOG_YES_NO_OLD_SCHOOL_MESSAGE = 10;
    private static final int DIALOG_YES_NO_HOLO_LIGHT_MESSAGE = 11;

    private static final int MAX_PROGRESS = 100;

    private ProgressDialog mProgressDialog;
    private int mProgress;
    private Handler mProgressHandler;

    //버튼한테 상수값을 주어서 switch-case문으로 해줌.
    protected Dialog onCreateDialog(int id) { //id를 받아온다.
        switch (id) {
            case DIALOG_YES_NO_MESSAGE:
                return new AlertDialog.Builder(MainActivity.this)
                        .setIconAttribute(android.R.attr.alertDialogIcon) //경고아이콘
                        .setTitle(R.string.alert_dialog_two_buttons_title) //타이틀

                        //positive Button = ok버튼
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                /* User clicked OK so do some stuff */
                            }
                        })

                        //negative Button = cancel버튼
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                /* User clicked Cancel so do some stuff */
                            }
                        })
                        .create(); //dialog만들기

            case DIALOG_YES_NO_OLD_SCHOOL_MESSAGE:
                return new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_TRADITIONAL)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_two_buttons_title)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .create();
            case DIALOG_YES_NO_HOLO_LIGHT_MESSAGE:
                return new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_two_buttons_title)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .create();
            case DIALOG_YES_NO_LONG_MESSAGE:
                return new AlertDialog.Builder(MainActivity.this)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_two_buttons_msg)
                        .setMessage(R.string.alert_dialog_two_buttons2_msg)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked OK so do some stuff */
                            }
                        })
                        .setNeutralButton(R.string.alert_dialog_something, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked Something so do some stuff */
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked Cancel so do some stuff */
                            }
                        })
                        .create();
            case DIALOG_YES_NO_ULTRA_LONG_MESSAGE:
                return new AlertDialog.Builder(MainActivity.this)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_two_buttons_msg)
                        .setMessage(R.string.alert_dialog_two_buttons2ultra_msg)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked OK so do some stuff */
                            }
                        })
                        .setNeutralButton(R.string.alert_dialog_something, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked Something so do some stuff */
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked Cancel so do some stuff */
                            }
                        })
                        .create();
            case DIALOG_LIST:
                return new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.select_dialog)
                        .setItems(R.array.select_dialog_items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                        /* User clicked so do some stuff */
                                String[] items = getResources().getStringArray(R.array.select_dialog_items);
                                new AlertDialog.Builder(MainActivity.this)
                                        .setMessage("You selected: " + which + " , " + items[which])
                                        .show();
                            }
                        })
                        .create();

            case DIALOG_PROGRESS:
                mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setIconAttribute(android.R.attr.alertDialogIcon);
                mProgressDialog.setTitle(R.string.select_dialog);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setMax(MAX_PROGRESS);
                mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                        getText(R.string.alert_dialog_hide), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked Yes so do some stuff */
                            }
                        });
                mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                        getText(R.string.alert_dialog_cancel), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked No so do some stuff */
                            }
                        });
                return mProgressDialog;

            case DIALOG_SINGLE_CHOICE:
                return new AlertDialog.Builder(MainActivity.this)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_single_choice)
                        .setSingleChoiceItems(R.array.select_dialog_items2, 0, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked on a radio button do some stuff */
                            }
                        })
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked Yes so do some stuff */
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked No so do some stuff */
                            }
                        })
                        .create();

            case DIALOG_MULTIPLE_CHOICE:
                return new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_popup_reminder)
                        .setTitle(R.string.alert_dialog_multi_choice)
                        .setMultiChoiceItems(R.array.select_dialog_items3,
                                new boolean[]{false, true, false, true, false, false, false},
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton,
                                                        boolean isChecked) {

                                /* User clicked on a check box do some stuff */
                                    }
                                })
                        .setPositiveButton(R.string.alert_dialog_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked Yes so do some stuff */
                                    }
                                })
                        .setNegativeButton(R.string.alert_dialog_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked No so do some stuff */
                                    }
                                })
                        .create();
            case DIALOG_MULTIPLE_CHOICE_CURSOR:
                String[] projection = new String[] {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.SEND_TO_VOICEMAIL
                };
                Cursor cursor = managedQuery(ContactsContract.Contacts.CONTENT_URI,
                        projection, null, null, null);
                return new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_popup_reminder)
                        .setTitle(R.string.alert_dialog_multi_choice_cursor)
                        .setMultiChoiceItems(cursor,
                                ContactsContract.Contacts.SEND_TO_VOICEMAIL,
                                ContactsContract.Contacts.DISPLAY_NAME,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton,
                                                        boolean isChecked) {
                                        Toast.makeText(MainActivity.this,
                                                "Readonly Demo Only - Data will not be updated",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .create();

            //입력받고 text를 넣을 수 있도록 만든 dialog.
            //view안에 view를 넣은것
            case DIALOG_TEXT_ENTRY:
                // adding a custom layout to an AlertDialog
                // layoutInflater는 뷰안에 뷰를 넣어주는것이다.
                LayoutInflater factory = LayoutInflater.from(this);

                //dialog안에 id와 password를 포함시켜준것.
                //factory.~~ ==> 하나의 객체가 된다. textview나 버튼처럼
                final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);

                //객체선언
                final EditText username_edit;
                final EditText password_edit;

                //가장 중요. onCreate메소드를 보면 layout이
                username_edit = (EditText)textEntryView.findViewById(R.id.username_edit);
                password_edit = (EditText)textEntryView.findViewById(R.id.password_edit);


                //리턴하기 전에 username과 password에 관한 객체를 위에서 만들어주자.
                return new AlertDialog.Builder(MainActivity.this)
                        .setIconAttribute(android.R.attr.alertDialogIcon) //아이콘설정
                        .setTitle(R.string.alert_dialog_text_entry)
                        .setView(textEntryView) //얹어주기
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            /* User clicked OK so do some stuff */
                            Toast.makeText(getApplicationContext(),username_edit.getText().toString()+password_edit.getText().toString(),Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            /* User clicked cancel so do some stuff */
                            }
                        })
                        .create();
        }
        return null;
    }

    //앱이 켜졌을때 가장먼저 실행되는 메소드
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog); //여기 레이아웃에 있는 id들만 findViewById해준다.

        /* Display a text message with yes/no buttons and handle each message as well as the cancel action */
        Button twoButtonsTitle = (Button) findViewById(R.id.two_buttons);
        twoButtonsTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_MESSAGE);
            }
        });

        /* Display a long text message with yes/no buttons and handle each message as well as the cancel action */
        Button twoButtons2Title = (Button) findViewById(R.id.two_buttons2);
        twoButtons2Title.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_LONG_MESSAGE);
            }
        });


        /* Display an ultra long text message with yes/no buttons and handle each message as well as the cancel action */
        Button twoButtons2UltraTitle = (Button) findViewById(R.id.two_buttons2ultra);
        twoButtons2UltraTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_ULTRA_LONG_MESSAGE);
            }
        });


        /* Display a list of items */
        Button selectButton = (Button) findViewById(R.id.select_button);
        selectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_LIST);
            }
        });

        /* Display a custom progress bar */
        Button progressButton = (Button) findViewById(R.id.progress_button);
        progressButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_PROGRESS);
                mProgress = 0;
                mProgressDialog.setProgress(0);
                mProgressHandler.sendEmptyMessage(0);
            }
        });

        /* Display a radio button group */
        Button radioButton = (Button) findViewById(R.id.radio_button);
        radioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_SINGLE_CHOICE);
            }
        });

        /* Display a list of checkboxes */
        Button checkBox = (Button) findViewById(R.id.checkbox_button);
        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_MULTIPLE_CHOICE);
            }
        });

        /* Display a list of checkboxes, backed by a cursor */
        Button checkBox2 = (Button) findViewById(R.id.checkbox_button2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_MULTIPLE_CHOICE_CURSOR);
            }
        });

        /* Display a text entry dialog */
        Button textEntry = (Button) findViewById(R.id.text_entry_button);
        textEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_TEXT_ENTRY);
            }
        });

        /* Two points, in the traditional theme */
        Button twoButtonsOldSchoolTitle = (Button) findViewById(R.id.two_buttons_old_school);
        twoButtonsOldSchoolTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_OLD_SCHOOL_MESSAGE);
            }
        });

        /* Two points, in the light holographic theme */
        Button twoButtonsHoloLightTitle = (Button) findViewById(R.id.two_buttons_holo_light);
        twoButtonsHoloLightTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_HOLO_LIGHT_MESSAGE);
            }
        });

        mProgressHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (mProgress >= MAX_PROGRESS) {
                    mProgressDialog.dismiss();
                } else {
                    mProgress++;
                    mProgressDialog.incrementProgressBy(1);
                    mProgressHandler.sendEmptyMessageDelayed(0, 100);
                }
            }
        };
    }
}
