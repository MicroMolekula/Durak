package com.lstu.durak

import com.badlogic.gdx.Files
import kotlin.jvm.JvmStatic
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.lstu.durak.Durak

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = Lwjgl3ApplicationConfiguration()
        config.setTitle("Durak")
        config.setIdleFPS(60)
        //config.resizable = false
        Lwjgl3Application(Durak(), config)
    }
}