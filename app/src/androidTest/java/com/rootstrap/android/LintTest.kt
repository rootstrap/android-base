package com.rootstrap.android

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.rootstrap.android.lint.LintRegistry
import com.rootstrap.android.lint.LintSpaceWarning

class LintTest : LintDetectorTest() {

     fun testBasic() {
            lint().files(
            java(
                "" +
                "package test.pkg;\n" +
                "public class TestClass1{\n" +
                "    // In a comment, mentioning \"lint\" has no effect\n" +
                "    private static String s1 = \"Ignore non-word usages: linting\";\n" +
                "    private static String s2 = \"Let's say it: lint\";\n" +
                "}"
            ))
            .run()
                .expectMatches("Warning*")
     }

    override fun getDetector(): Detector {
        return LintSpaceWarning()
    }

    override fun getIssues(): MutableList<Issue> {
        return LintRegistry().issues.toMutableList()
    }
}