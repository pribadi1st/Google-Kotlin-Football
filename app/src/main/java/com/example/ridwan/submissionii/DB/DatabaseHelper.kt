package com.example.ridwan.submissionii.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import org.w3c.dom.Text

class DatabaseHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx,"Favorite.db",null,1) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) : DatabaseHelper{
            if(instance == null){
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORITE,
            true,
            Favorite.EVENT_ID to TEXT+PRIMARY_KEY,
            Favorite.HOME_ID to TEXT,
            Favorite.AWAY_ID to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_SCORE to TEXT,
            Favorite.HOME_NAME to TEXT,
            Favorite.AWAY_NAME to TEXT,
            Favorite.DATE to TEXT
        )
        db.createTable(
            FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(
            Favorite.TABLE_FAVORITE,true
        )
        db.dropTable(
            FavoriteTeam.TABLE_FAVORITE_TEAM,true
        )
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)
