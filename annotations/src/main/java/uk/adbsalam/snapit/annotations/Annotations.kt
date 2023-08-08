package uk.adbsalam.snapit.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class SnapIt(
    val name: String = "",
    val isScreen : Boolean = false,
    val preview : Boolean = false,
    val isDark: Boolean = false
)