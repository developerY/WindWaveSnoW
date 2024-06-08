package com.ylabz.windwatersnow.data.mapper

import com.ylabz.windwatersnow.data.windwatersnowEntity

// Extension function

typealias windwatersnow = windwatersnowEntity

fun windwatersnowEntity.toPhoto(): windwatersnow {
    return this
    /*return windwatersnow(
        title = title,
        description = description,
        //@ColumnInfo(name = "timestamp") val timestamp : ZonedDateTime = ZonedDateTime.now(),
        timestamp = timestamp, //= Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        lat = lat,
        lon = lon,
        alarm_on = alarm_on,
        isCompleted = isCompleted,
        imgPath = imgPath,
        audioPath = audioPath,
        todoId = todoId
    )*/
}

// Extension function
typealias windwatersnowEntity = windwatersnow

fun windwatersnow.towindwatersnowEntity(): windwatersnowEntity {
    return this
    /*return windwatersnowEntity(
        title = title,
        description = description,
        //@ColumnInfo(name = "timestamp") val timestamp : ZonedDateTime = ZonedDateTime.now(),
        timestamp = timestamp, //= Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        lat = lat,
        lon = lon,
        alarm_on = alarm_on,
        isCompleted = isCompleted,
        imgPath = imgPath,
        audioPath = audioPath,
        todoId = todoId// auto created on insert
    )*/
}
