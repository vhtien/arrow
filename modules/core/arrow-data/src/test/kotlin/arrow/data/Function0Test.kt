package arrow.data

import arrow.Kind
import arrow.test.UnitSpec
import arrow.test.laws.ComonadLaws.laws
import arrow.test.laws.MonadLaws
import arrow.typeclasses.Eq
import io.kotlintest.KTestJUnitRunner
import org.junit.runner.RunWith

@RunWith(KTestJUnitRunner::class)
class Function0Test : UnitSpec() {
    val EQ: Eq<Kind<ForFunction0, Int>> = Eq { a, b ->
        a() == b()
    }

    init {
        testLaws(
            MonadLaws.laws(Function0.monad(), EQ),
            Function0.comonad().laws({ { it }.k() }, EQ)
        )
    }
}
