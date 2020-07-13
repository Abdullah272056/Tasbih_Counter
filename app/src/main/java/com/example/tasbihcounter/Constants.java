package com.example.tasbihcounter;

public class Constants {
    public  static final String DATABASE_NAME="ZikirInformation.db";
    public  static final int DATABASE_Version=1;
    public  static final String TABLE_NAME="ZikirInformation";
    public  static final String COLUMN_ID="id";
    public  static final String COLUMN_ZIKIR_NAME="ZikirName";
    public  static final String COLUMN_ZIKIR_COUNT_VALUE="ZikirCountValue";
    public static final String COLUMN_DATE        = "date";

    public static final String CREATE_TABLE  = " CREATE TABLE "+TABLE_NAME+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_ZIKIR_NAME+" TEXT, "
            +COLUMN_DATE+" TEXT, "
            +COLUMN_ZIKIR_COUNT_VALUE+" INTEGER "
            +")";
}
