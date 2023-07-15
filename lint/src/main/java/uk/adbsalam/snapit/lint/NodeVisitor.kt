package uk.adbsalam.snapit.lint

import uk.adbsalam.snapit.lint.helper.errors
import uk.adbsalam.snapit.lint.helper.hasSnapIt
import com.adbsalam.snapit_lint.SnapItIssues.ISSUE
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UMethod

/**
 * Node visitor to process code
 * This will help generate lint error based on code issues
 *
 * severity is set to high
 */
internal class NodeVisitor(
    private val context: JavaContext
) : UElementHandler() {

    override fun visitMethod(node: UMethod) {

        if (node.annotations.isEmpty()) return

        if (!node.hasSnapIt()) return

        val lintError = node.errors() ?: return

        context.report(
            issue = ISSUE,
            scopeClass = node,
            location = context.getNameLocation(node),
            message = lintError.msg
        )
    }
}

