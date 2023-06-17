package com.example.eventplanner.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM EventModel")
    fun getEvents(): Flow<List<EventModel>>

    @Delete
    suspend fun deleteEvent(eventModel: EventModel)

    @Insert
    suspend fun addEvent(eventModel: EventModel)

    @Query("DELETE FROM EventModel")
    suspend fun deleteAllEvents()

    @Insert
    suspend fun addEvents(events: List<EventModel>)

    @Transaction
    suspend fun replaceEvents(newEvents: List<EventModel>) {
        deleteAllEvents()
        addEvents(newEvents)
    }
}
