package com.tz.clockontools;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tz.clockontools.bean.ClockRecordBean;
import com.tz.clockontools.database.dao.ClockRecordDao;
import com.tz.clockontools.database.table.ClockRecordTable;
import com.tz.clockontools.helpers.DatabaseHelper;
import com.tz.clockontools.utils.DateUtils;

import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ClockRecordDao clockRecordDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据库
        DatabaseHelper.init(this);
        clockRecordDao = new ClockRecordDao();
        //绑定事件
        ImageButton imageButton = findViewById(R.id.iBtnClock);
        imageButton.setOnClickListener(this);
        //渲染页面
        refreshText();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iBtnClock:
                //打卡
                clockRecordDao.add();
                refreshText();
                break;
            default:
                break;
        }
    }

    private void refreshText() {
        //渲染页面
        TextView textView = findViewById(R.id.tViewRecordList);
        ClockRecordBean morning = clockRecordDao.queryFistForToday();
        ClockRecordBean night = clockRecordDao.queryLastForToday();
        String text = "";
        if (morning != null) {
            text += "上班：" + DateUtils.getHHMMString(morning.clockTime) + "\n";

        }
        if (night != null) {
            text += "\n下班：" + DateUtils.getHHMMString(night.clockTime);
        }
        textView.setText(text);
    }
}
