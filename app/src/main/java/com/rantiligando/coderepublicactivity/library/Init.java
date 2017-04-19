package com.rantiligando.coderepublicactivity.library;

/**
 * Created by rantiligando on 4/11/2017.
 */

public class Init {

    public static final String DATABASE_NAME = "CodeRepublic.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "users";
    public static final String CREATE_USERS = "" +
            " CREATE TABLE IF NOT EXISTS users (" +
            "	_id INTEGER PRIMARY KEY  NOT NULL  UNIQUE , " +
            "	first_name TEXT, " +
            "	last_name TEXT,  " +
            "	deleted TEXT  " +
            ");";
}
