package uk.adbsalam.snapit.utils

import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import org.intellij.lang.annotations.Language
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub

open class KPTest(private val caseDir: String) {

    private val testPackageName = "uk.co.test.name"

    /**
     *
     */
    val mockKsFunctionDeclarations = sequenceOf(
        mockkFunctionDeclaration(),
        mockkFunctionDeclaration()
    )

    /**
     *
     */
    internal fun kspCodeFromFile(file: String): String {
        @Language("kotlin")
        val fileContent = KPTest::class.java.getResource("/$caseDir/$file.txt")?.readText()
        return fileContent.orEmpty()
    }

    /**
     *
     */
    internal fun mockkFunctionDeclaration(): KSFunctionDeclaration {
        val ksName = Mockito.mock(KSName::class.java).stub {
            on { getShortName() } doReturn testPackageName
        }

        val ksFile = Mockito.mock(KSFile::class.java).stub {
            on { packageName } doReturn ksName
            on { packageName.asString() } doReturn testPackageName
        }

        val ksFunctionMock = Mockito.mock(KSFunctionDeclaration::class.java).stub {
            on { containingFile } doReturn ksFile
        }
        return ksFunctionMock
    }

}