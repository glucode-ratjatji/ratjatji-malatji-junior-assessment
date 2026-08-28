package com.example.glucode_getitdone_to_do_list.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClientInterface {
    fun getLocationUpdates(interval: Long): Flow<Location>

    class LocationException(message: String): Exception()
}

