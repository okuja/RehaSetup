package com.okujajoshua.reha.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.okujajoshua.reha.database.transaction.Transaction
import com.okujajoshua.reha.database.transaction.TransactionDao
import com.okujajoshua.reha.database.user.RehaUser
import com.okujajoshua.reha.database.user.RehaUserDao

@Database(entities = [RehaUser::class, Transaction::class],version = 1, exportSchema = false)
abstract class RehaDatabase : RoomDatabase(){


    /**
     * Connects the user to the DAO.
     * can have many dao
     */
    abstract val rehaUserDao: RehaUserDao
    abstract val transactionDao: TransactionDao

    companion object{

        @Volatile
        private var INSTANCE : RehaDatabase? = null

        fun getInstance(context: Context): RehaDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RehaDatabase::class.java,
                        "reha_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}