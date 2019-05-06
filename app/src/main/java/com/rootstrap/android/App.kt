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

    var preferences: TinyDB? = null
    private var preferencesName: String? = null
    var session: Session? = null
    var observableSession : MutableLiveData<Session> = MutableLiveData()
    
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
        preferencesName = setupPreferenceName()
        preferences = TinyDB(applicationContext, preferencesName!!)
    }

    private fun setupPreferenceName(): String {
        if (!TinyDB(applicationContext).contains(PREF_KEY))
            TinyDB(applicationContext).putString(PREF_KEY, UUID.randomUUID().toString())
        
        return TinyDB(applicationContext).getString(PREF_KEY,"")!!
    }
    
    fun saveHeaderAuthenticationParams(accessToken: String?, client: String?, uid: String?) {
            preferences!!.putString(HeadersKey.ACCESS_TOKEN, accessToken!!)
            preferences!!.putString(HeadersKey.CLIENT, client!!)
            preferences!!.putString(HeadersKey.UID, uid!!)
    }

    fun activeSession() {
        if (preferences!!.contains(PREF_SESSION)) {
            session = preferences!!.getObject(PREF_SESSION, Session::class.java) as Session
            notifySession()
        }
    }

    fun closeSession()  {
        session!!.open = false
        TinyDB(applicationContext).remove(PREF_KEY)
        setupPreferenceName()
        preferences!!.putObject(PREF_SESSION, session!!)
        notifySession()
    }

    fun saveSession() {
        preferences!!.putObject(PREF_SESSION, session!!)
        notifySession()
    }

    fun openNewSession(user: Profile) {
        session = Session(UUID.randomUUID().toString(), user,true)
        preferences!!.putObject(PREF_SESSION, session!!)
        notifySession()
    }

    fun notifySession() = observableSession.postValue(session)

}
