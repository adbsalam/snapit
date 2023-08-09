package uk.adbsalam.snapit.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class SnapIt(
    val name: String = "",
    val isScreen: Boolean = false,
    val preview: Boolean = false,
    val isDark: Boolean = false,
    val gif: Boolean = false,
    val start: Long = 0L,
    val end: Long = 500L,
    val fps: Int = 30
)