package uk.adbsalam.snapit.lint

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.intellij.lang.annotations.Language
import uk.adbsalam.snapit.lint.helper.LintMessage

/**
 * Function to generate no errors since this implementation is correct
 */
internal const val functionNoErr = """
                @SnapIt
                @Composable
                fun composeFunction(){}
            """

/**
 * Function to generate error for private modifier added to function
 */
internal const val functionPrivateErr = """
                @SnapIt
                @Composable
                private fun composeFunction(){}
            """

/**
 * Function to generate error for function contains param
 */
internal const val functionWithValue = """
                @SnapIt
                @Composable
                fun composeFunction(msg: String){}
            """

/**
 * Function to generate error for function do not have @Composable annotations
 */
internal const val functionNoCompose = """
                @SnapIt
                fun composeFunction(msg: String){}
            """

/**
 * Function with no @SnapIt annotation should not generate error
 */
internal const val functionNoSnapAnnotation = """
                @Composable
                fun composeFunction(msg: String){}
            """

/**
 * Function with noannotation should not generate error
 */
internal const val functionNoAnnotation = """
                fun composeFunction(msg: String){}
            """


/**
 * @param function function to be tested for validation
 * @param warning warning to show as lint message
 */
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
