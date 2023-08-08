package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.getFileName
import uk.adbsalam.snapit.ksp.codewriter.replaceExtras
import uk.adbsalam.snapit.utils.KPTest
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class KPHelperTest : KPTest("remove_extras_test_case") {

    @Test
    fun `when getFileName() is called, correct file name should return`() {
        val componentFileName = getFileName(
            file = "Test.kt",
            annotation = AnnotationType.LIGHT_COMPONENT
        )
        Assert.assertEquals(componentFileName, "TestComponentTest")

        val screenFileName = getFileName(
            file = "Test.kt",
            annotation = AnnotationType.LIGHT_SCREEN
        )
        Assert.assertEquals(screenFileName, "TestScreenTest")

        val darkComponentFileName = getFileName(
            file = "Test.kt",
            annotation = AnnotationType.DARK_COMPONENT
        )
        Assert.assertEquals(darkComponentFileName, "TestDarkComponentTest")

        val darkScreenFileName = getFileName(
            file = "Test.kt",
            annotation = AnnotationType.DARK_SCREEN
        )
        Assert.assertEquals(darkScreenFileName, "TestDarkScreenTest")
    }

    @Test
    fun `when replaceExtras() called, should remove all extra code`() {
        val codeWithExtras = kspCodeFromFile("code_with_extras")
        val codeWithoutExtras = kspCodeFromFile("code_with_no_extras")
        val actual = codeWithExtras.replaceExtras()
        assertEquals(codeWithoutExtras, actual)
    }

}
