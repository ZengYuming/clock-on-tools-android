package com.tz.clockontools.database.table;


public class ClockRecordTable {

    /**
     * 头条号信息表
     */
    public static final String TABLENAME = "t_clock_record";

    /**
     * 字段部分
     */
    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_CLOCK_TIME = "clock_time";
    public static final String FIELD_NAME_YEAR = "year";
    public static final String FIELD_NAME_MONTH = "month";
    public static final String FIELD_NAME_DAY = "day";


    /**
     * 字段ID 数据库操作建立字段对应关系 从0开始
     */
    public static final int ID_ID = 0;
    public static final int ID_CLOCK_TIME = 1;
    public static final int ID_YEAR = 2;
    public static final int ID_MONTH = 3;
    public static final int ID_DAY = 4;

    /**
     * 创建表
     */
    public static final String CREATE_TABLE = "create table if not exists " + TABLENAME + "(" +
            FIELD_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_NAME_CLOCK_TIME + " text, " +
            FIELD_NAME_YEAR + " text, " +
            FIELD_NAME_MONTH + " text, " +
            FIELD_NAME_DAY + " text) ";
}