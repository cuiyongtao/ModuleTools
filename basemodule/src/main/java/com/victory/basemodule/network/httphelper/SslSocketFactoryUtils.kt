package com.victory.basemodule.network.httphelper

import android.content.Context
import android.util.Log


import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * @author:Victory
 * @time: 2018/4/8
 * @Email:949021037@qq.com
 * @QQ:949021037
 * @explain;
 */
object SslSocketFactoryUtils {
    private val keyServerStroreID = 1

    /**
     * 获得信任所有服务器端证书库
     */
    val turstAllManager: Array<TrustManager>
        get() = arrayOf<TrustManager>(MyX509TrustManager())

    fun createSSLSocketFactory(context: Context): SSLSocketFactory? {
        var mSSLSocketFactory: SSLSocketFactory? = null
        if (mSSLSocketFactory == null) {
            synchronized(SslSocketFactoryUtils::class.java) {
                if (mSSLSocketFactory == null) {

                    val trustStream = context.resources.openRawResource(keyServerStroreID)
                    val sslContext: SSLContext
                    try {
                        sslContext = SSLContext.getInstance("TLS")
                    } catch (e: NoSuchAlgorithmException) {
                        Log.e("httpDebug", "createSingleSSLSocketFactory", e)
                        return null
                    }

                    //获得服务器端证书
                    val turstManager = getTurstManager(trustStream)

                    //初始化ssl证书库
                    try {
                        sslContext.init(null, turstManager, SecureRandom())
                    } catch (e: KeyManagementException) {
                        Log.e("httpDebug", "createSingleSSLSocketFactory", e)
                    }

                    //获得sslSocketFactory
                    mSSLSocketFactory = sslContext.socketFactory
                }
            }
        }
        return mSSLSocketFactory
    }

    /**
     * 获得指定流中的服务器端证书库
     */

    fun getTurstManager(vararg certificates: InputStream): Array<TrustManager> {
        try {
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null, null)
            var index = 0
            for (certificate in certificates) {
                if (certificate == null) {
                    continue
                }
                val certificate1: Certificate
                try {
                    certificate1 = certificateFactory.generateCertificate(certificate)
                } finally {
                    certificate.close()
                }

                val certificateAlias = Integer.toString(index++)
                keyStore.setCertificateEntry(certificateAlias, certificate1)
            }

            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory
                    .getDefaultAlgorithm())

            trustManagerFactory.init(keyStore)
            return trustManagerFactory.trustManagers

        } catch (e: Exception) {
            Log.e("httpDebug", "SSLSocketFactoryUtils", e)
        }

        return turstAllManager
    }

    class MyX509TrustManager : X509TrustManager {
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            println("cert: " + chain[0].toString() + ", authType: " + authType)
        }

        override fun getAcceptedIssuers(): Array<X509Certificate>? {
            return null
        }
    }


    fun createTrustAllManager(): X509TrustManager? {
        var tm: X509TrustManager? = null
        try {
            tm = object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    //do nothing，接受任意客户端证书
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    //do nothing，接受任意服务端证书
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }
            }
        } catch (e: Exception) {

        }

        return tm
    }


    class TrustAllHostnameVerifier : HostnameVerifier {

        override fun verify(hostname: String, session: SSLSession): Boolean {
            return true
        }
    }
}