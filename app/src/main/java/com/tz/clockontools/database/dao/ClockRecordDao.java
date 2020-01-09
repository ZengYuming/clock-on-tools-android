package com.tz.clockontools.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tz.clockontools.bean.ClockRecordBean;
import com.tz.clockontools.database.table.ClockRecordTable;
import com.tz.clockontools.helpers.DatabaseHelper;
import com.tz.clockontools.utils.DateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ClockRecordDao {
    private SQLiteDatabase db;

    public ClockRecordDao() {
        this.db = DatabaseHelper.getDatabase();
    }

    public boolean add() {
        ContentValues values = new ContentValues();
        values.put(ClockRecordTable.FIELD_NAME_CLOCK_TIME, DateUtils.getCurrentDateTimeString());
        values.put(ClockRecordTable.FIELD_NAME_YEAR, DateUtils.getCurrentTimestamp().getYear());
        values.put(ClockRecordTable.FIELD_NAME_MONTH, DateUtils.getCurrentTimestamp().getMonth());
        values.put(ClockRecordTable.FIELD_NAME_DAY, DateUtils.getCurrentTimestamp().getDay());
        long result = db.insert(ClockRecordTable.TABLENAME, null, values);
        return result != -1;
    }

    public List<ClockRecordBean> queryByYear(int year) {
        Cursor cursor = db.query(ClockRecordTable.TABLENAME, null, ClockRecordTable.FIELD_NAME_YEAR + "=?",
                new String[]{year + ""}, null, null, null);
        List<ClockRecordBean> list = mapping(cursor);
        cursor.close();
        return list;
    }

    public List<ClockRecordBean> queryForToday() {
        Timestamp timestamp = DateUtils.getCurrentTimestamp();
        Cursor cursor = db.query(ClockRecordTable.TABLENAME, null,
                ClockRecordTable.FIELD_NAME_YEAR + "=?"
                        + " and " + ClockRecordTable.FIELD_NAME_MONTH + "=?"
                        + " and " + ClockRecordTable.FIELD_NAME_DAY + "=?",
                new String[]{timestamp.getYear() + "", timestamp.getMonth() + "", timestamp.getDay() + ""}, null, null, null);
        List<ClockRecordBean> list = mapping(cursor);
        cursor.close();
        if(list==null||list.size()==0){
            return null;
        }
        return list;
    }

    /**
     * 今天第一次打卡
     *
     * @return
     */
    public ClockRecordBean queryFistForToday() {
        Timestamp timestamp = DateUtils.getCurrentTimestamp();
        Cursor cursor = db.query(ClockRecordTable.TABLENAME, null,
                ClockRecordTable.FIELD_NAME_YEAR + "=?"
                        + " and " + ClockRecordTable.FIELD_NAME_MONTH + "=?"
                        + " and " + ClockRecordTable.FIELD_NAME_DAY + "=?",
                new String[]{timestamp.getYear() + "", timestamp.getMonth() + "", timestamp.getDay() + ""}, null, null, ClockRecordTable.FIELD_NAME_CLOCK_TIME + " asc ", " 1 ");
        List<ClockRecordBean> list = mapping(cursor);
        cursor.close();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 今天最后一次打卡
     *
     * @return
     */
    public ClockRecordBean queryLastForToday() {
        Timestamp timestamp = DateUtils.getCurrentTimestamp();
        Cursor cursor = db.query(ClockRecordTable.TABLENAME, null,
                ClockRecordTable.FIELD_NAME_YEAR + "=?"
                        + " and " + ClockRecordTable.FIELD_NAME_MONTH + "=?"
                        + " and " + ClockRecordTable.FIELD_NAME_DAY + "=?",
                new String[]{timestamp.getYear() + "", timestamp.getMonth() + "", timestamp.getDay() + ""}, null, null, ClockRecordTable.FIELD_NAME_CLOCK_TIME + " desc ", " 1 ");
        List<ClockRecordBean> list = mapping(cursor);
        cursor.close();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<ClockRecordBean> queryByYearAndMonthAndDay(int year, int month, int day) {
        Cursor cursor = db.query(ClockRecordTable.TABLENAME, null,
                ClockRecordTable.FIELD_NAME_YEAR + "=?"
                        + " and " + ClockRecordTable.FIELD_NAME_MONTH + "=?"
                        + " and " + ClockRecordTable.FIELD_NAME_DAY + "=?",
                new String[]{year + "", month + "", day + ""}, null, null, null);
        List<ClockRecordBean> list = mapping(cursor);
        cursor.close();
        return list;
    }

    public List<ClockRecordBean> queryAll() {
        Cursor cursor = db.query(ClockRecordTable.TABLENAME, null, null, null, null, null, null);
        List<ClockRecordBean> list = mapping(cursor);
        cursor.close();
        return list;
    }

    public void updateAll(List<ClockRecordBean> list) {
    }

    public boolean removeAll() {
        int result = db.delete(ClockRecordTable.TABLENAME, null, null);
        return result != -1;
    }

    private static List<ClockRecordBean> mapping(Cursor cursor) {
        List<ClockRecordBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            ClockRecordBean bean = new ClockRecordBean();
            bean.setClockTime(cursor.getString(ClockRecordTable.ID_CLOCK_TIME));
            bean.setYear(cursor.getInt(ClockRecordTable.ID_YEAR));
            bean.setMonth(cursor.getInt(ClockRecordTable.ID_MONTH));
            bean.setDay(cursor.getInt(ClockRecordTable.ID_DAY));
            list.add(bean);
        }
        return list;
    }
}