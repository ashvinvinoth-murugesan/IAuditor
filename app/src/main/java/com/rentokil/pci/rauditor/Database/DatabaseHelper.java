package com.rentokil.pci.rauditor.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "IAUDITOR.db",ACTIVITY_SELECT="ACTIVITY_SELECT",BRANCH_AND_TECH_MSOT="BRANCH_AND_TECH_MSOT",INSERT_MAIN_ID="INSERT_MAIN_ID";
    public static final String USER_PROFILE_TABLE ="USER_PROFILE_TABLE",AUDIT_ACCESS_TB ="AUDIT_ACCESS_TB",BRANCH_AUDITBY_TABLE ="BRANCH_AUDITBY_TABLE",DASHBOARD_TB ="DASHBOARD_TB",    PMCA_COMPLETE_STATUS_TABLE ="PMCA_COMPLETE_STATUS_TABLE",QSR_COMPLETE_STATUS_TABLE ="QSR_COMPLETE_STATUS_TABLE", IPM_COMPLETE_STATUS_TABLE ="IPM_COMPLETE_STATUS_TABLE",AIB_COMPLETE_STATUS_TABLE ="AIB_COMPLETE_STATUS_TABLE",    USER_COMPLETE_STATUS ="USER_COMPLETE_STATUS",    CHECK_STATUS_TABLE ="CHECK_STATUS_TABLE",    ACTIVITIES_TABLE ="ACTIVITIES_TABLE",    ACTIVITIES_SELECTED_STRING ="ACTIVITIES_SELECTED_STRING",    ACTIVITIES_PAGE_1 ="ACTIVITIES_PAGE_1",    ACTIVITIES_PAGE_2 ="ACTIVITIES_PAGE_2",    MSOT_TABLE ="MSOT_TABLE",    AREA ="AREA",    MSOT_LIST_SELECT ="MSOT_LIST_SELECT",    MSOT_ACTIVITY_MAS_DB ="MSOT_ACTIVITY_MAS_DB",    MSOT_QUESTION_MAS_DB ="MSOT_QUESTION_MAS_DB",
    MSOT_ACT_QUS_DB ="MSOT_ACT_QUS_DB",    MSOT_TYPE_TABLE ="MSOT_TYPE_TABLE",    MSOT_MAIN_COUNTRY_TABLE="MSOT_MAIN_COUNTRY_TABLE",    MSOT_QUESTION_ID_TABLE ="MSOT_QUESTION_ID_TABLE",    INSERTED_MSOT_MAIN ="INSERTED_MSOT_MAIN",     KEY_ID = "KEY_ID",    DELETED = "DELETED",    STATE = "STATE",    USER_NAME="USER_NAME",    USER_MAIL="USER_MAIL",    COUNTRY="COUNTRY",    BRANCH="BRANCH",    STATUS="STATUS",    MAIN_ID="MAIN_ID",    STATUS_1="STATUS_1",    STATUS_2="STATUS_2",    COMPLETE_STATUS="COMPLETE_STATUS",    QSR_COMPLETE_STATUS="QSR_COMPLETE_STATUS",    PMCA_COMPLETE_STATUS="PMCA_COMPLETE_STATUS",    IPM_COMPLETE_STATUS="IPM_COMPLETE_STATUS",    AIB_COMPLETE_STATUS="AIB_COMPLETE_STATUS",    AUDIT_BY_NAME="AUDIT_BY_NAME",    JOB_TITLE="JOB_TITLE",    REP_BRANCH="REP_BRANCH",    POSITION_ID="POSITION_ID",    AUDIT_NAME="AUDIT_NAME",    COMPLETED="COMPLETED",    IN_PROGRESS="IN_PROGRESS",    YEAR="YEAR",    MONTH="MONTH",    ACCESS="ACCESS",    WEEK="WEEK",    COUNT="COUNT",    DATE="DATE",    MSOT_MAIN_DB="MSOT_MAIN_DB",    MSOT_PAGE_A_DB="MSOT_PAGE_A_DB",    MSOT_PAGE_B_DB="MSOT_PAGE_B_DB",    MSOT_PAGE_C_DB="MSOT_PAGE_C_DB",    MSOT_PAGE_D_DB="MSOT_PAGE_D_DB",    MSOT_PAGE_E_DB="MSOT_PAGE_E_DB",    MSOT_PAGE_F_DB="MSOT_PAGE_F_DB",    MSOT_PAGE_G_DB="MSOT_PAGE_G_DB",    MSOT_PAGE_H_DB="MSOT_PAGE_H_DB",    MSOT_PAGE_I_DB="MSOT_PAGE_I_DB",    MSOT_PAGE_J_DB="MSOT_PAGE_J_DB",    MSOT_PAGE_K_DB="MSOT_PAGE_K_DB",    MSOT_PAGE_L_DB="MSOT_PAGE_L_DB",    MSOT_PAGE_M_DB="MSOT_PAGE_M_DB",    MSOT_PAGE_N_DB="MSOT_PAGE_N_DB",    MSOT_PAGE_O_DB="MSOT_PAGE_O_DB",    MSOT_IMAGE_TB="MSOT_IMAGE_TB",    PAGE_ID="PAGE_ID",    MSOT_SIGN_DB="MSOT_SIGN_DB",    COMMANDS="COMMANDS",    QUESTION_ID="QUESTION_ID",    et1="et1",    et2="et2",    et3="et3",    et4="et4",    et5="et5",    et6="et6",    et7="et7",    et8="et8",    et9="et9",    et10="et10",    et11="et11",    et12="et12",    et13="et13",    et14="et14",    et15="et15",    et16="et16",    et17="et17",    et18="et18",    IMAGE_1="IMAGE_1",
    IMAGE_2="IMAGE_2",    SERVICE_ADDRESS="SERVICE_ADDRESS",    TIME_MS="TIME_MS",  ACTIVITY_NAME="ACTIVITY_NAME",
            TECH_NAME="TECH_NAME",    TECH_ID="TECH_ID",BRANCH_DB="BRANCH_DB",     AUDIT_TECH="AUDIT_TECH",ACTIVITY_ID="ACTIVITY_ID",    AUDIT_BRANCH="AUDIT_BRANCH",    JOB_STAFF="JOB_STAFF",    TYPE_WORK="TYPE_WORK",    MSOT_NAME="MSOT_NAME",    BRANCH_CO_NAME="BRANCH_CO_NAME",    BRANCH_MANAGE="BRANCH_MANAGE";
    public static final int    DATABASE_VERSION =19;
    public static final String    TECH_START_DATE ="TECH_START_DATE";
    public static final String    BRANCH_START_DATE ="BRANCH_START_DATE";
    public static final String    TECH_END_DATE ="TECH_END_DATE";
    public static final String    BRANCH_END_DATE ="BRANCH_END_DATE";
    public static final String    MAN_SIGN ="MAN_SIGN";
    public static final String    ACC_SIGN ="ACC_SIGN";
    public static final String    CO_SIGN ="CO_SIGN";
    public static final String    STAFF_SIGN ="STAFF_SIGN";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TECH_LIST_TABLE="CREATE TABLE " +USER_PROFILE_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_NAME + " TEXT,"
                +USER_MAIL+ " TEXT,"
                +BRANCH+ " TEXT,"
                +COUNTRY+ " TEXT,"
                +ACCESS+ " TEXT,"
                +STATUS+" TEXT)";

        String CREATE_AUDIT_ACCESS_TB="CREATE TABLE " +AUDIT_ACCESS_TB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AUDIT_NAME + " TEXT,"
                + DATE + " DATE,"
                +DELETED+" TEXT)";
        String CREATE_DASHBOARD_TB="CREATE TABLE " +DASHBOARD_TB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AUDIT_NAME + " TEXT,"
                +COMPLETED+ " TEXT,"
                +IN_PROGRESS+ " TEXT,"
                +STATE+ " TEXT,"
                +DATE+ " TEXT,"
                +DELETED+" TEXT)";

        String CREATE_BRANCH_AUDITBY="CREATE TABLE " +BRANCH_AUDITBY_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AUDIT_BY_NAME + " TEXT,"
                + JOB_TITLE + " TEXT,"
                +REP_BRANCH+" TEXT)";
        String CREATE_BRANCH_DB="CREATE TABLE " +BRANCH_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COUNTRY + " TEXT,"
                + BRANCH + " TEXT,"
                +DELETED+" INTEGER)";
        String CREATE_COMPLETE_STATUS="CREATE TABLE " +USER_COMPLETE_STATUS+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COMPLETE_STATUS+" TEXT)";


        String CREATE_INSERTED_MAIN="CREATE TABLE " +INSERTED_MSOT_MAIN+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +INSERT_MAIN_ID+" INTEGER)";


        String CREATE_PMCA_COMPLETE_STATUS="CREATE TABLE " +PMCA_COMPLETE_STATUS_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +PMCA_COMPLETE_STATUS+" TEXT)";

        String CREATE_QSR_COMPLETE_STATUS="CREATE TABLE " +QSR_COMPLETE_STATUS_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +QSR_COMPLETE_STATUS+" TEXT)";


   String CREATE_IPM_COMPLETE_STATUS="CREATE TABLE " +IPM_COMPLETE_STATUS_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +IPM_COMPLETE_STATUS+" TEXT)";


        String CREATE_AIB_COMPLETE_STATUS="CREATE TABLE " +AIB_COMPLETE_STATUS_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +AIB_COMPLETE_STATUS+" TEXT)";


        String CREATE_CHECK_STATUS="CREATE TABLE " +CHECK_STATUS_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +STATUS+" TEXT)";

        String CREATE_MS_ACTIVITY_MAS="CREATE TABLE " +MSOT_ACTIVITY_MAS_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER,"
                + DATE+" DATE,"
                + ACTIVITY_NAME+" TEXT,"
                +DELETED+" INTEGER)";
       String CREATE_MS_QUESTION_MAS="CREATE TABLE " +MSOT_QUESTION_MAS_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER,"
                + QUESTION_ID+" TEXT,"
                +DELETED+" INTEGER)";
       String CREATE_MS_ACT_QUS_MAS="CREATE TABLE " +MSOT_ACT_QUS_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER,"
                + ACTIVITY_ID+" INTEGER,"
                + QUESTION_ID+" INTEGER,"
                +DELETED+" INTEGER)";



        String CREATE_ACTIVITIES_STATUS="CREATE TABLE " +ACTIVITIES_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER,"
                +POSITION_ID+" TEXT)";


        String CREATE_ACTIVITIES_SELECTED_STRING="CREATE TABLE " +ACTIVITIES_SELECTED_STRING+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ACTIVITY_SELECT+" TEXT)";

        String CREATE_MSOT_QUESTION_ID="CREATE TABLE " +MSOT_QUESTION_ID_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER,"
                +QUESTION_ID+" TEXT)";

        String CREATE_MSOT_MAIN_COUNTRY="CREATE TABLE " +MSOT_MAIN_COUNTRY_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COUNTRY+" TEXT,"
                +BRANCH+" TEXT)";

        String CREATE_MSOT_IMAGE_BD="CREATE TABLE " +MSOT_IMAGE_TB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER,"
                + PAGE_ID+" TEXT,"
                + QUESTION_ID+" TEXT,"
                + IMAGE_1+" BLOB,"
                + COMMANDS+" BLOB,"
                +BRANCH+" TEXT)";
        String CREATE_MSOT_SIGN="CREATE TABLE " +MSOT_SIGN_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER,"
                + STAFF_SIGN+" BLOB,"
                + MAN_SIGN+" BLOB,"
                + ACC_SIGN+" BLOB,"
                + CO_SIGN+" BLOB,"
                +DELETED+" BLOB)";
        String CREATE_MSOT_PAGE_A="CREATE TABLE " +MSOT_PAGE_A_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +MAIN_ID+" INTEGER," +DELETED+" INTEGER)";

        String CREATE_MSOT_PAGE_B="CREATE TABLE " +MSOT_PAGE_B_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +et3+" TEXT," +et4+" TEXT," +MAIN_ID+" INTEGER," +DELETED+" INTEGER)";
        String CREATE_MSOT_PAGE_C="CREATE TABLE " +MSOT_PAGE_C_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +et3+" TEXT," +et4+" TEXT,"+et5+" TEXT,"+et6+" TEXT,"+et7+" TEXT,"+et8+" TEXT,"+et9+" TEXT,"+et10+" TEXT,"+et11+" TEXT,"+et12+" TEXT, "+MAIN_ID+" INTEGER," +DELETED+" INTEGER)";
        String CREATE_MSOT_PAGE_D="CREATE TABLE " +MSOT_PAGE_D_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +et3+" TEXT," +et4+" TEXT,"+et5+" TEXT," +MAIN_ID+" INTEGER," +DELETED+" INTEGER)";
        String CREATE_MSOT_PAGE_E="CREATE TABLE " +MSOT_PAGE_E_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER," + et1+" TEXT," + et2+" TEXT," + et3+" TEXT," + et4+" TEXT," + et5+" TEXT," + et6+" TEXT," + et7+" TEXT,"
                +et8+" TEXT,"
                + et9+" TEXT,"
                + et10+" TEXT,"
                + et11+" TEXT,"
                + et12+" TEXT,"
                + et13+" TEXT,"
                + et14+" TEXT,"
                + et15+" TEXT,"
                + et16+" TEXT,"
                + et17+" TEXT,"
                + et18+" TEXT,"
                +DELETED+" INTEGER)";
        String CREATE_MSOT_PAGE_F="CREATE TABLE " +MSOT_PAGE_F_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER," + et1+" TEXT," + et2+" TEXT," + et3+" TEXT," + et4+" TEXT," + et5+" TEXT," + et6+" TEXT,"
                +DELETED+" INTEGER)";

        String CREATE_MSOT_PAGE_G="CREATE TABLE " +MSOT_PAGE_G_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER," + et1+" TEXT," + et2+" TEXT," + et3+" TEXT," + et4+" TEXT," + et5+" TEXT," + et6+" TEXT," + et7+" TEXT,"
                +DELETED+" INTEGER)";

        String CREATE_MSOT_PAGE_H="CREATE TABLE " +MSOT_PAGE_H_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER," + et1+" TEXT," + et2+" TEXT," + et3+" TEXT," + et4+" TEXT," + et5+" TEXT," + et6+" TEXT," + et7+" TEXT,"
                + et8+" TEXT,"
                + et9+" TEXT,"
                + et10+" TEXT,"
                +DELETED+" INTEGER)";
        String CREATE_MSOT_PAGE_I="CREATE TABLE " +MSOT_PAGE_I_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER," + et1+" TEXT," + et2+" TEXT," + et3+" TEXT," + et4+" TEXT," + et5+" TEXT,"+ AREA+" TEXT,"

                +DELETED+" INTEGER)";
        String CREATE_MSOT_TYPE="CREATE TABLE " +MSOT_TYPE_TABLE+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +BRANCH_AND_TECH_MSOT+" TEXT)";

        String CREATE_MSOT_MAIN="CREATE TABLE " +MSOT_MAIN_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_MAIL+" TEXT,"
                + SERVICE_ADDRESS+" TEXT,"
                + TYPE_WORK+" TEXT,"
                + MSOT_NAME+" TEXT,"
                + TIME_MS+" TEXT,"
                + DATE+" DATE,"
                + TECH_NAME+" TEXT,"
                + TECH_ID+" TEXT,"
                + JOB_STAFF+" TEXT,"
                + AUDIT_TECH+" TEXT,"
                + AUDIT_BRANCH+" TEXT,"
                + BRANCH+" TEXT,"
                + COUNTRY+" TEXT,"
                + BRANCH_CO_NAME+" TEXT,"
                + BRANCH_MANAGE+" TEXT,"
                + TECH_END_DATE+" TEXT,"
                + TECH_START_DATE+" TEXT,"
                + BRANCH_START_DATE+" TEXT,"
                + BRANCH_END_DATE+" TEXT,"
                + STATUS+" TEXT,"
                +DELETED+" TEXT)";

        String CREATE_MSOT_PAGE_J="CREATE TABLE " +MSOT_PAGE_J_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +MAIN_ID+" INTEGER," +DELETED+" INTEGER)";

        String CREATE_MSOT_PAGE_K="CREATE TABLE " +MSOT_PAGE_K_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID+" INTEGER," + et1+" TEXT," + et2+" TEXT," + et3+" TEXT," + et4+" TEXT," + et5+" TEXT," + et6+" TEXT," + et7+" TEXT,"
                +DELETED+" INTEGER)";
        String CREATE_MSOT_PAGE_M="CREATE TABLE " +MSOT_PAGE_M_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +MAIN_ID+" INTEGER," +DELETED+" INTEGER)";

        String CREATE_MSOT_PAGE_N="CREATE TABLE " +MSOT_PAGE_N_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +et3+" TEXT," +et4+" TEXT," +MAIN_ID+" INTEGER," +DELETED+" INTEGER)";

        String CREATE_MSOT_PAGE_O="CREATE TABLE " +MSOT_PAGE_O_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +et3+" TEXT," +et4+" TEXT," +et5+" TEXT,"
                +MAIN_ID+" INTEGER," +DELETED+" INTEGER)";

        String CREATE_MSOT_PAGE_L="CREATE TABLE " +MSOT_PAGE_L_DB+"("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +et1+" TEXT," +et2+" TEXT," +MAIN_ID+" INTEGER," +DELETED+" INTEGER)";
        db.execSQL(CREATE_TECH_LIST_TABLE);
        db.execSQL(CREATE_BRANCH_DB);
        db.execSQL(CREATE_MSOT_PAGE_A);
        db.execSQL(CREATE_MSOT_PAGE_B);
        db.execSQL(CREATE_MSOT_PAGE_C);
        db.execSQL(CREATE_MSOT_PAGE_D);
        db.execSQL(CREATE_MSOT_PAGE_E);
        db.execSQL(CREATE_MSOT_PAGE_F);
        db.execSQL(CREATE_MSOT_PAGE_G);
        db.execSQL(CREATE_MSOT_PAGE_H);
        db.execSQL(CREATE_MSOT_PAGE_I);
        db.execSQL(CREATE_MSOT_PAGE_J);
        db.execSQL(CREATE_MSOT_PAGE_K);
        db.execSQL(CREATE_MSOT_PAGE_L);
        db.execSQL(CREATE_MSOT_PAGE_M);
        db.execSQL(CREATE_MSOT_PAGE_N);
        db.execSQL(CREATE_MSOT_PAGE_O);
        db.execSQL(CREATE_MSOT_IMAGE_BD);
        db.execSQL(CREATE_PMCA_COMPLETE_STATUS);
        db.execSQL(CREATE_QSR_COMPLETE_STATUS);
        db.execSQL(CREATE_IPM_COMPLETE_STATUS);
        db.execSQL(CREATE_AIB_COMPLETE_STATUS);
        db.execSQL(CREATE_BRANCH_AUDITBY);
        db.execSQL(CREATE_CHECK_STATUS);
        db.execSQL(CREATE_COMPLETE_STATUS);
        db.execSQL(CREATE_ACTIVITIES_STATUS);
        db.execSQL(CREATE_INSERTED_MAIN);
        db.execSQL(CREATE_ACTIVITIES_SELECTED_STRING);
        db.execSQL(CREATE_MSOT_QUESTION_ID);
        db.execSQL(CREATE_MSOT_MAIN_COUNTRY);
        db.execSQL(CREATE_MSOT_TYPE);
        db.execSQL(CREATE_DASHBOARD_TB);
        db.execSQL(CREATE_AUDIT_ACCESS_TB);
        db.execSQL(CREATE_MSOT_MAIN);
        db.execSQL(CREATE_MS_ACTIVITY_MAS);
        db.execSQL(CREATE_MS_ACT_QUS_MAS);
        db.execSQL(CREATE_MS_QUESTION_MAS);
        db.execSQL(CREATE_MSOT_SIGN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public int getLogincount(SQLiteDatabase db) {
        int count_user=0;
        String countQuery = "SELECT  * FROM "+ USER_PROFILE_TABLE;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            count_user=cursor.getCount();
        }
        cursor.close();

        return count_user;
    } public int get_audit_count(SQLiteDatabase db) {
        int count_user=0;
        String countQuery = "SELECT  * FROM "+ AUDIT_ACCESS_TB;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            count_user=cursor.getCount();
        }
        cursor.close();

        return count_user;
    }
    public int last_insert_id(SQLiteDatabase db) {
        int count_user=0;
        String countQuery = "SELECT  * FROM "+ INSERTED_MSOT_MAIN;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToLast();
        if(cursor.getCount()!=0)
        {
            count_user=cursor.getInt(cursor.getColumnIndex(INSERT_MAIN_ID));
          //  Log.e("OOOOOOO auto id",""+count_user);
        }
        cursor.close();

        return count_user;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ USER_PROFILE_TABLE);
        db.close();
    }
    public List<String> getALL_ACTIVITE(SQLiteDatabase sd,int cus_id){
        List<String> lable=new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + ACTIVITIES_TABLE +" where MAIN_ID = '"+cus_id+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            do {
                lable.add(cursor.getString(2));
                Log.e("UUUUUU",""+cursor.getString(2));
            }while (cursor.moveToNext());


        }
         return lable;
    } public int get_check_audit(SQLiteDatabase sd,String name){

        String selectQuery = "SELECT * FROM "+AUDIT_ACCESS_TB+" where AUDIT_NAME ='"+name+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
         return cursor.getCount();
    }
    public String get_country(SQLiteDatabase sd){
        String Country_Name="";
        String countQuery = "SELECT  * FROM "+ MSOT_MAIN_COUNTRY_TABLE;
        sd = this.getReadableDatabase();
        Cursor cursor = sd.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            Country_Name=cursor.getString(cursor.getColumnIndex(COUNTRY));
        }
        cursor.close();
        return Country_Name;
    }
    public  int get_main_id(SQLiteDatabase sd){
        int main_id=0;
        Cursor c5;
        c5 = sd.rawQuery("Select * from " +INSERTED_MSOT_MAIN, null);
        c5.moveToFirst();
        if(c5.getCount()!=0){

            main_id= c5.getInt(c5.getColumnIndex(INSERT_MAIN_ID));
        }
        return main_id;
    }
    public  int get_msot_main_count(SQLiteDatabase sd){
        int main_id=0;
        Cursor c5;
        c5 = sd.rawQuery("Select * from " +MSOT_MAIN_DB, null);
        c5.moveToFirst();

        return c5.getCount();
    }
    public  int get_msot_main_tb_last_id(SQLiteDatabase sd){
        int last_id=0;
        Cursor c5;
        String Query="SELECT KEY_ID from MSOT_MAIN_DB order by KEY_ID DESC limit 1";
        c5 = sd.rawQuery(Query, null);
        c5.moveToFirst();
        return last_id=c5.getInt(c5.getColumnIndex(KEY_ID));
    }
}
