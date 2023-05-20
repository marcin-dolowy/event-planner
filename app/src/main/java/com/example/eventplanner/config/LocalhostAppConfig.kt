package com.example.eventplanner.config

import javax.inject.Inject

class LocalhostAppConfig @Inject constructor(): IAppConfig {

    override val backandUrl: String = "http://10.0.2.2:3001"
}