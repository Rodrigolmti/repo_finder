package br.com.rodrigolmti.kotlin_utils

import java.net.InetAddress
import java.net.UnknownHostException
import javax.inject.Inject

private const val GOOGLE_URL = "www.google.com"

interface IConnectionChecker {
    fun isDeviceConnected(): Boolean
}

class ConnectionChecker @Inject constructor() : IConnectionChecker {
    override fun isDeviceConnected(): Boolean {
        try {
            val address: InetAddress = InetAddress.getByName(GOOGLE_URL)
            return !address.equals("")
        } catch (e: UnknownHostException) {
        }
        return false
    }
}