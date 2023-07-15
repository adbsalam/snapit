package uk.adbsalam.snapit.lint.helper

import com.intellij.lang.jvm.JvmModifier
import org.jetbrains.uast.UMethod

/**
 * Annotation Name as String
 */
const val SNAP_IT = "SnapIt"

/**
 * Annotation Package name
 */
const val PACKAGE_NAME = "uk.adbsalam.snapit.annotations."

/**
 * Composable annotation full name as String
 */
const val COMPOSABLE = "androidx.compose.runtime.Composable"

/**
 * Return annotation name with package name
 */
internal fun String.byPackage() = PACKAGE_NAME + this
/**
 * @return true if method contains a SnapIt annotation
 */
internal fun UMethod.hasSnapIt(
): Boolean {
    return this.annotations.firstOrNull {
        println("---------------" + it.qualifiedName)
        it.qualifiedName == SNAP_IT.byPackage()
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
        !this.isComposable() -> ComposableRequiredMsg(SNAP_IT)
        this.hasParams() -> ZeroArgumentRequiredMsg(SNAP_IT)
        this.isPrivate() -> PrivateModifierNotAllowed(SNAP_IT)
        else -> null
    }
}