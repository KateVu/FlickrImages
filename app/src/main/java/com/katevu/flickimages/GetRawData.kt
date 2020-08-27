package com.katevu.flickimages

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus {
    OK,
    IDLE,
    NOT_INITIALISED,
    FAILED_OR_EMPTY,
    PERMISSIONS_ERROR,
    ERROR
}

class GetRawData (private val listener: OnDownloadComplete): AsyncTask<String, Void, String>(){
    private val TAG = "GetRawData"
    private var downloadStatus = DownloadStatus.IDLE

//    private var listener: MainActivity? = null
//    fun setDownloadCompleteListener (callBackObject: MainActivity) {
//        listener = callBackObject
//    }

    interface OnDownloadComplete {
        fun onDownloadComplete(data: String, status: DownloadStatus)
    }

    override fun onPostExecute(result: String) {
        Log.d(TAG,"onPostExecute called")
        listener.onDownloadComplete(result,downloadStatus)
    }

    override fun doInBackground(vararg params: String?): String {
        if (params[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALISED
            return "There is no URL is set"
        }

        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALISED
                    "doInBackground: Invalid URL: ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    "doInBackGround: IO Ex: ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PERMISSIONS_ERROR
                    "doInBackGround: Security Ex: ${e.message}"
                }
                else -> {
                    downloadStatus = DownloadStatus.ERROR
                    "doInBackGround: Unknown error: ${e.message}"
                }
            }

            Log.i(TAG, errorMessage)

            return errorMessage
        }
    }

}