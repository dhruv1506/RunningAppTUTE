package com.androiddevs.runningappyt.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class Converters {
    @TypeConverter
    fun toBitmap(bytes:ByteArray): Bitmap
    {
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap):ByteArray
    {
        val outPutStream=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outPutStream)
       return outPutStream.toByteArray()
    }
}