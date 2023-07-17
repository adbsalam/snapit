package uk.adbsalam.snapit.lint

import uk.adbsalam.snapit.lint.SnapItIssues.ISSUE
import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.LintClient
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.TextFormat.TEXT
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class IssueRegistryTest {

    lateinit var issueRegistry: IssueRegistry

    @Before
    fun setup() {
        LintClient.clientName = "uk.adbsalam.snapit.lint.SnapItIssueRegistry"
        issueRegistry = SnapItIssueRegistry()
    }

    @Test
    fun `validate issue registry properties are correctly set`() {
        Assert.assertEquals(1, issueRegistry.issues.size)
        Assert.assertEquals(14, issueRegistry.api)
        Assert.assertEquals(6, issueRegistry.minApi)
        Assert.assertEquals(
            issueRegistry.vendor,
            Vendor(
                vendorName = "adb_salam",
                identifier = "com.adbsalam",
                feedbackUrl = "abdulsalach32@gmail.com",
                contact = null
            )
        )
    }

    @Test
    fun `valida issue registry issue have correct properties`() {
        val issue = issueRegistry.issues[0]
        Assert.assertEquals(issue.id, ISSUE.id)
        Assert.assertEquals(issue.priority, ISSUE.priority)
        Assert.assertEquals(issue.getBriefDescription(TEXT), ISSUE.getBriefDescription(TEXT))
        Assert.assertEquals(issue.getExplanation(TEXT), ISSUE.getExplanation(TEXT))
        Assert.assertEquals(issue.category, ISSUE.category)
        Assert.assertEquals(issue.defaultSeverity, ISSUE.defaultSeverity)
    }

}