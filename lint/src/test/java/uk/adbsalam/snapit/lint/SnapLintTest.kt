package uk.adbsalam.snapit.lint

import org.junit.Test
import uk.adbsalam.snapit.lint.helper.ComposableRequiredMsg
import uk.adbsalam.snapit.lint.helper.NoWarning
import uk.adbsalam.snapit.lint.helper.PrivateModifierNotAllowed
import uk.adbsalam.snapit.lint.helper.SNAP_IT
import uk.adbsalam.snapit.lint.helper.ZeroArgumentRequiredMsg

class SnapLintTest {

    @Test
    fun `when correctly implemented, show not give any errors`() {
        validateFunction(
            function = functionNoErr,
            warning = NoWarning
        )
    }

    @Test
    fun `when private modifier added to function, show correct warning`() {
        validateFunction(
            function = functionPrivateErr,
            warning = PrivateModifierNotAllowed(SNAP_IT)
        )
    }

    @Test
    fun `when function with param, should, show correct warning`() {
        validateFunction(
            function = functionWithValue,
            warning = ZeroArgumentRequiredMsg(SNAP_IT)
        )
    }

    @Test
    fun `when function wihtout @Compose annotation, should, show correct warning`() {
        validateFunction(
            function = functionNoCompose,
            warning = ComposableRequiredMsg(SNAP_IT)
        )
    }

}
