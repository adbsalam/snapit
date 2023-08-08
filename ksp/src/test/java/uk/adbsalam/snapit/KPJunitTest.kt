package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.jUnitClass
import uk.adbsalam.snapit.utils.KPTest
import uk.adbsalam.snapit.utils.MockType
import uk.adbsalam.snapit.utils.mockDarkFun
import uk.adbsalam.snapit.utils.mockFunctions

@RunWith(JUnit4::class)
class KPJunitTest: KPTest("kjunit_test_case") {

    @Test
    fun `jUnitClass - when no preview functions - should generate code correctly`(){

        val jUnitClass = jUnitClass(
            fileName = "TestFile",
            symbols = mockFunctions(MockType.NONE_PREVIEW),
            annotation = AnnotationType.LIGHT_COMPONENT
        )
        val actual = kspCodeFromFile("kjunit_no_preview_funs_class")
        Assert.assertEquals(jUnitClass.toString(), actual)
    }

    @Test
    fun `jUnitClass - when all preview functions - should generate code correctly`(){

        val jUnitClass = jUnitClass(
            fileName = "TestFile",
            symbols = mockFunctions(MockType.ALL_PREVIEW),
            annotation = AnnotationType.LIGHT_COMPONENT
        )
        val actual = kspCodeFromFile("kjunit_all_preview_funs_class")
        Assert.assertEquals(jUnitClass.toString(), actual)
    }

    @Test
    fun `jUnitClass - when random preview functions - should generate code correctly`(){

        val jUnitClass = jUnitClass(
            fileName = "TestFile",
            symbols = mockFunctions(MockType.RANDOM),
            annotation = AnnotationType.LIGHT_COMPONENT
        )
        val actual = kspCodeFromFile("kjunit_random_preview_funs_class")
        Assert.assertEquals(jUnitClass.toString(), actual)
    }

    @Test
    fun `jUnitClass - when dark mode and screen - should generate code correctly`(){

        val jUnitClass = jUnitClass(
            fileName = "TestFile",
            symbols = mockDarkFun(true),
            annotation = AnnotationType.DARK_SCREEN
        )
        val actual = kspCodeFromFile("kjunit_dark_screen")
        Assert.assertEquals(jUnitClass.toString(), actual)
    }

    @Test
    fun `jUnitClass - when dark mode and component - should generate code correctly`(){

        val jUnitClass = jUnitClass(
            fileName = "TestFile",
            symbols = mockDarkFun(false),
            annotation = AnnotationType.DARK_COMPONENT
        )
        val actual = kspCodeFromFile("kjunit_dark_component")
        Assert.assertEquals(jUnitClass.toString(), actual)
    }

}