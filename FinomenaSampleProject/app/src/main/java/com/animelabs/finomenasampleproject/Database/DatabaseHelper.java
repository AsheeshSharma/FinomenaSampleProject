package com.animelabs.finomenasampleproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.animelabs.finomenasampleproject.Model.QuestionItemModel;
import com.animelabs.finomenasampleproject.Model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by asheeshsharma on 09/04/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG = DatabaseHelper.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "questionAnswerManager";

    // Table Names
    private static final String TABLE_QUESTIONS = "questions_table";
    private static final String TABLE_USERS = "user_table";

    // Common column names
    private static final String KEY_QUESTION_ID = "id";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_OPTIONS = "options";

    // NOTES Table - column nmaes
    private static final String KEY_USER_ANSWER = "answer";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE "
            + TABLE_QUESTIONS + "(" + KEY_QUESTION_ID + " INTEGER PRIMARY KEY," + KEY_QUESTION
            + " TEXT," + KEY_ANSWER + " TEXT," + KEY_OPTIONS
            + " TEXT" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_USER_RESPONSE = "CREATE TABLE " + TABLE_USERS
            + "(" + KEY_QUESTION_ID + " INTEGER PRIMARY KEY," + KEY_USER_ANSWER + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_USER_RESPONSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // create new tables
        onCreate(db);
    }

    // ------------------------ "todos" table methods ----------------//

    /**
     * Creating a todo
     */
    public long createQuestion(QuestionItemModel questionItemModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION_ID, questionItemModel.getmQuestionID());
        values.put(KEY_QUESTION, questionItemModel.getmQuestion());
        values.put(KEY_ANSWER, questionItemModel.getmAnswer());
        values.put(KEY_OPTIONS, questionItemModel.getOptionsInString());

        // insert row
        long todo_id = db.insert(TABLE_QUESTIONS, null, values);


        return todo_id;
    }

    /**
     * get single todo
     */
    public QuestionItemModel getTodo(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS + " WHERE "
                + KEY_QUESTION_ID + " = " + todo_id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        QuestionItemModel questionItemModel = new QuestionItemModel();
        questionItemModel.setmQuestionID(c.getInt(c.getColumnIndex(KEY_QUESTION_ID)));
        questionItemModel.setmQuestion(c.getString(c.getColumnIndex(KEY_QUESTION)));
        questionItemModel.setmAnswer(c.getString(c.getColumnIndex(KEY_ANSWER)));
        questionItemModel.setmOptions(questionItemModel.getOptionsInArrat(c.getString(c.getColumnIndex(KEY_OPTIONS))));

        return questionItemModel;
    }

    /**
     * getting all todos
     * */
    public List<QuestionItemModel> getAllToDos() {
        List<QuestionItemModel> todos = new ArrayList<QuestionItemModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                QuestionItemModel questionItemModel = new QuestionItemModel();
                questionItemModel.setmQuestionID(c.getInt(c.getColumnIndex(KEY_QUESTION_ID)));
                questionItemModel.setmQuestion(c.getString(c.getColumnIndex(KEY_QUESTION)));
                questionItemModel.setmAnswer(c.getString(c.getColumnIndex(KEY_ANSWER)));
                questionItemModel.setmOptions(questionItemModel.getOptionsInArrat(c.getString(c.getColumnIndex(KEY_OPTIONS))));

                // adding to todo list
                todos.add(questionItemModel);
            } while (c.moveToNext());
        }

        return todos;
    }


    /**
     * getting todo count
     */
    public int getToDoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a question
     */


    /**
     * Deleting a todo
     */


    // ------------------------ "tags" table methods ----------------//

    /**
     * Creating tag
     */
    public long createUserResponse(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION_ID, userModel.getmQuestionID());
        values.put(KEY_USER_ANSWER, userModel.getAnswer());

        // insert row
        long tag_id = db.insert(TABLE_USERS, null, values);

        return tag_id;
    }

    /**
     * getting all tags
     * */
    public List<UserModel> getAllResponses() {
        List<UserModel> tags = new ArrayList<UserModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserModel t = new UserModel();
                t.setmQuestionID(c.getInt(c.getColumnIndex(KEY_QUESTION_ID)));
                t.setAnswer(c.getString(c.getColumnIndex(KEY_USER_ANSWER)));

                // adding to tags list
                tags.add(t);
            } while (c.moveToNext());
        }
        return tags;
    }

    public UserModel getResponse(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                + KEY_QUESTION_ID + " = " + todo_id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        UserModel t = new UserModel();
        t.setmQuestionID(c.getInt(c.getColumnIndex(KEY_QUESTION_ID)));
        t.setAnswer(c.getString(c.getColumnIndex(KEY_USER_ANSWER)));

        return t ;
    }

    /**
     * Updating a tag
     */


    /**
     * Deleting a tag
     */


    // ------------------------ "todo_tags" table methods ----------------//

    /**
     * Creating todo_tag
     */


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
