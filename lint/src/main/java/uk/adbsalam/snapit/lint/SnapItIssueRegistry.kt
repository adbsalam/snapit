package uk.adbsalam.snapit.lint

import com.adbsalam.snapit_lint.SnapItIssues
import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API

class SnapItIssueRegistry : IssueRegistry() {
    override val issues = listOf(SnapItIssues.ISSUE)
    override val api: Int = CURRENT_API
    override val minApi: Int = 6
    override val vendor: Vendor = Vendor(
        feedbackUrl = "abdulsalach32@gmail.com",
        identifier = "com.adbsalam",
        vendorName = "adb_salam"
    )
}