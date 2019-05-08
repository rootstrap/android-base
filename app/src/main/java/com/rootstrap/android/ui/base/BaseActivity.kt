package com.rootstrap.android.ui.base

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rootstrap.android.models.Session

/**
 * @author Amaury Ricardo Miranda 2019
 * */
open class BaseActivity : AppCompatActivity() {

    var baseViewModel: BaseViewModel? = null

    open fun startActivity(intent: Intent, finish: Boolean) {
        startActivity(intent)
        if (finish) finish()
    }

    fun useViewModel(baseViewModel: BaseViewModel) {
        this.baseViewModel = baseViewModel
        subscribeSession()
    }

    //Subscribe to session.
    private fun subscribeSession() {
        baseViewModel!!.session.observe(this, Observer<Session> {
            if (!it!!.open)
                onCloseSession()
            else
                onUpdateSession()
        })
    }

    fun onCloseSession() {
        TODO() //do something when the section is close
    }

    fun onUpdateSession() {
        TODO() //do something when the section is update
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
