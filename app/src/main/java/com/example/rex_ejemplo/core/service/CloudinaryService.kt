package com.example.rex_ejemplo.core.services

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.cloudinary.android.MediaManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class CloudinaryService(context: Context) {
    init {
        val config: MutableMap<String, String> = HashMap()
        config["cloud_name"] = "dtwk2yash"
        config["api_key"] = "177879634358569"
        config["api_secret"] = "PHT0mHrEEB79xJzodNMZ6hsMzHU"
        MediaManager.init(context, config)
    }

    suspend fun uploadImageToCloudinary(uri: Uri): String? {
        return withContext(Dispatchers.IO) {
            try {
                val result = MediaManager.get().upload(uri)
                    .option("folder", "recipes")
                    .dispatch()

                val imageUrl = result
                Log.d("Cloudinary", "Imagen subida exitosamente: $imageUrl")
                return@withContext imageUrl
            } catch (e: Exception) {
                Log.e("Cloudinary", "Error al subir imagen", e)
                null
            }
        }
    }
}
