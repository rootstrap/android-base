package com.rootstrap.android.util

import com.google.android.material.textfield.TextInputLayout
import com.rootstrap.android.network.models.ErrorModel

class ErrorUtil {

    companion object {
        fun handleCustomError(error: ErrorModel): String {
            var message = ""
            if (error.errors != null) {
                if (error.errors is List<*> && !error.errors.isEmpty()) {
                    if (error.errors.first() is String) {
                        message = error.errors.first() as String
                    }
                } else if (error.errors is Map<*, *> && error.errors.keys.first() is String &&
                    error.errors.values.first() is List<*>
                ) {
                    val errors = error.errors as Map<String, List<String>>
                    message = errors.getValue("full_messages").first()
                }
            } else if (error.error != null && !error.error.isEmpty()) {
                message = error.error
            }

            return message
        }

        fun displayError(inputLayout: TextInputLayout, message: String) {
            inputLayout.isErrorEnabled = true
            inputLayout.error = message
        }
    }

    class ErrorsEvent(val error: String)
}
