package uk.adbsalam.snapit.lint

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.intellij.lang.annotations.Language
import uk.adbsalam.snapit.lint.helper.LintMessage

internal const val functionNoErr = """
                @SnapIt
                @Composable
                fun composeFunction(){}
            """

internal const val functionPrivateErr = """
                @SnapIt
                @Composable
                private fun composeFunction(){}
            """

internal val functionWithValue = """
                @SnapIt
                @Composable
                fun composeFunction(msg: String){}
            """

internal const val functionNoCompose = """
                @SnapIt
                fun composeFunction(msg: String){}
            """

internal fun validateFunction(
    @Language(value = "kotlin") function: String,
    warning: LintMessage
) {
    TestLintTask.lint()
        .files(
            TestFiles.kotlin(
                """

                annotation class SnapIt
                annotation class Composable

                $function
                """
            ).indented()
        )
        .allowCompilationErrors()
        .allowMissingSdk()
        .issues(SnapItIssues.ISSUE)
        .run()
        .expectContains(warning.msg)
}
