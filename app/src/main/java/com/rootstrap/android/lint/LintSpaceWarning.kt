package com.rootstrap.android.lint

import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.ULiteralExpression
import org.jetbrains.uast.UElement
import java.util.*
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext


val ISSUE = Issue.create(
    "AddSpace", "Add Space",
    "Bla bla bla bla",
    Category.CORRECTNESS,
    6,
    Severity.WARNING,
    Implementation(
        LintSpaceWarning::class.java,
        Scope.CLASS_FILE_SCOPE)
)

class LintSpaceWarning : Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return Collections.singletonList(ULiteralExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {
            override fun visitLiteralExpression(node: ULiteralExpression) {

                if (node.isString) {
                    val check : String = node.value as String
                    if (check.contains("{") && check.matches(("* " +
                                "{").toRegex())) {
                        context.report(
                            ISSUE, node, context.getLocation(node),
                            "Warning add space before brackets"
                        )
                    } else if (check.contains(",") && check.matches(("* " +
                                ",").toRegex())) {
                        context.report(
                            ISSUE, node, context.getLocation(node),
                            "Warning add space before ,"
                        )
                    }
                }
            }
        }
    }
}