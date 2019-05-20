package com.rootstrap.android.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import java.util.*

class LintRegistry(override val issues: List<Issue> = Collections.singletonList(ISSUE)) : IssueRegistry() { }