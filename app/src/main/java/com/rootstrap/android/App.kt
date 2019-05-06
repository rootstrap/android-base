package com.rootstrap.android

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.rootstrap.android.infraestructure.interceptors.HeadersKey
import com.rootstrap.android.models.Profile
import com.rootstrap.android.models.Session
import com.rootstrap.android.utils.Constants.Companion.PREF_KEY
import com.rootstrap.android.utils.Constants.Companion.PREF_SESSION
import com.rootstrap.android.utils.TinyDB
import net.danlew.android.joda.JodaTimeAndroid
import java.util.*
import kotlin.jvm.*

/**
 * @author Amaury Ricardo Miranda 2019
 * */

class App : Application() {

    var mPreferences: TinyDB? = null
    private var mPreferencesName: String? = null
    var mSession: Session? = null
    var mObservableSession : MutableLiveData<Session> = MutableLiveData()
    
    companion object {
        lateinit var mE: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mE = this
        setupPreferences()
        activeSession()
        JodaTimeAndroid.init(this)
    }

    private fun setupPreferences() {
        mPreferencesName = setupPreferenceName()
        mPreferences = TinyDB(applicationContext, mPreferencesName!!)
    }

    private fun setupPreferenceName(): String {
        if (!TinyDB(applicationContext).contains(PREF_KEY))
            TinyDB(applicationContext).putString(PREF_KEY, UUID.randomUUID().toString())
        
        return TinyDB(applicationContext).getString(PREF_KEY,"")!!
    }
    
    fun saveHeaderAuthenticationParams(accessToken: String?, client: String?, uid: String?) {
            mPreferences!!.putString(HeadersKey.ACCESS_TOKEN, accessToken!!)
            mPreferences!!.putString(HeadersKey.CLIENT, client!!)
            mPreferences!!.putString(HeadersKey.UID, uid!!)
    }

    fun activeSession() {
        if (mPreferences!!.contains(PREF_SESSION)) {
            mSession = mPreferences!!.getObject(PREF_SESSION, Session::class.java) as Session
            notifySession()
        }
    }

    fun closeSession()  {
        mSession!!.open = false
        TinyDB(applicationContext).remove(PREF_KEY)
        setupPreferenceName()
        mPreferences!!.putObject(PREF_SESSION, mSession!!)
        notifySession()
    }

    fun saveSession() {
        mPreferences!!.putObject(PREF_SESSION, mSession!!)
        notifySession()
    }

    fun openNewSession(user: Profile) {
        mSession = Session(UUID.randomUUID().toString(), user,true)
        mPreferences!!.putObject(PREF_SESSION, mSession!!)
        notifySession()
    }

    fun notifySession() = mObservableSession.postValue(mSession)

}
