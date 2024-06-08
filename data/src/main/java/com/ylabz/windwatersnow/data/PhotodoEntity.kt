package com.ylabz.windwatersnow.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ylabz.windwatersnow.data.converter.Converters

@Entity(tableName = "windwatersnow_table")
@TypeConverters(Converters::class)
data class windwatersnowEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val todoId: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    // @ColumnInfo(name = "timestamp") val timestamp : ZonedDateTime = ZonedDateTime.now(),
    @ColumnInfo(name = "date") val timestamp: kotlinx.datetime.LocalDateTime? = null,
    // kotlinx.datetime.Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    @ColumnInfo(name = "lat") val lat: Double = 0.0,
    @ColumnInfo(name = "lon") val lon: Double = 0.0,
    @ColumnInfo(name = "alarmOn") val alarmOn: Boolean = false,
    @ColumnInfo(name = "completed") val isCompleted: Boolean = false,
    @ColumnInfo(name = "image_path") val imgPath: Uri? = null,
    @ColumnInfo(name = "audio_path") val audioPath: Uri? = null

)

data class windwatersnowUpdate(
    val id: Int,
    val alarmOn: Boolean
)


// how to hold an image https://medium.com/@uttam.cooch/save-images-in-room-persistence-library-c71b60865b7e
//https://www.ronnystudio.com/how-to-insert-image-in-room-persistence-library-and-fetch-all-the-data-display-using-recyclerview-in-android-with-android-room-library-example/

// how to hold an image
//https://github.com/mrasif/Room-ORM-Store-Images/blob/master/app/src/main/java/com/example/asif/roomormstoreimages/models/MyImage.java