package com.example.eventplanner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EventModel::class], version = 1)
abstract class EventDatabase: RoomDatabase() {

    abstract fun eventDao(): EventDao
}
