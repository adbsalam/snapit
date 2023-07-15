package uk.adbsalam.snapit.lint.helper

import com.intellij.lang.jvm.JvmModifier
import org.jetbrains.uast.UMethod
import uk.adbsalam.snapit.annotations.SnapAnnotation
import uk.adbsalam.snapit.annotations.SnapAnnotation.SNAP_IT

/**
 * Composable annotation full name as String
 */
const val COMPOSABLE = "androidx.compose.runtime.Composable"

/**
 * @return true if method contains a SnapIt annotation
 */
internal fun UMethod.hasSnapIt(
): Boolean {
    return this.annotations.firstOrNull {
        it.qualifiedName == SNAP_IT.id
    } != null
}

/**
 * @return true if method contains Composable annotation
 */
internal fun UMethod.isComposable(): Boolean {
    return this.annotations.firstOrNull {
        it.qualifiedName == COMPOSABLE
    } != null
}

/**
 * @return true if function have 1 or more params
 */
internal fun UMethod.hasParams(): Boolean {
    return this.hasParameters()
}

/**
 * @return true if function have private modifier
 */
internal fun UMethod.isPrivate(): Boolean {
    return this.hasModifier(JvmModifier.PRIVATE)
}

/**
 * @return LintError to be shown on Editor
 */
internal fun UMethod.errors(): LintMessage? {
    return when {
        !this.isComposable() -> ComposableRequiredMsg(SNAP_IT.annotation)
        this.hasParams() -> ZeroArgumentRequiredMsg(SNAP_IT.annotation)
        this.isPrivate() -> PrivateModifierNotAllowed(SNAP_IT.annotation)
        else -> null
    }
}