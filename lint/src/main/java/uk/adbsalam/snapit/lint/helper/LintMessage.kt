package uk.adbsalam.snapit.lint.helper

/**
 * Sealed class to generate lint messages
 */
sealed class LintMessage(val msg: String)

/**
 * error message to show Composable annotation is missing
 */
internal data class ComposableRequiredMsg(val annotation: String) : LintMessage(
    msg = """@$annotation requires @Composable annotation
        @$annotation can only be used with Composable function""".trimMargin()
)

/**
 * error message to show 0 param functions are required for SnapIt annotation
 */
internal data class ZeroArgumentRequiredMsg(val annotation: String) : LintMessage(
    msg = """function annotated with @$annotation cannot accept params
        @$annotation only accepts 0 param functions. Refactor function or remove annotation""".trimMargin()
)

/**
 * error message to show private modifier is not allowed when using SnapIt Annotation
 */
internal data class PrivateModifierNotAllowed(val annotation: String) : LintMessage(
    msg = """function annotated with @$annotation cannot be private
        @$annotation does not accept private functions, Refactor function or remove annotation""".trimMargin()
)

/**
 * No warning issued
 */
internal object NoWarning : LintMessage(
    msg = "No warnings."
)