package arrow.data

import arrow.core.*
import arrow.test.UnitSpec
import arrow.test.laws.ComonadLaws.laws
import arrow.test.laws.EqLaws
import arrow.test.laws.MonadLaws
import arrow.test.laws.ShowLaws
import arrow.test.laws.TraverseLaws
import arrow.typeclasses.*
import io.kotlintest.KTestJUnitRunner
import io.kotlintest.matchers.shouldNotBe
import org.junit.runner.RunWith

@RunWith(KTestJUnitRunner::class)
class IdTest : UnitSpec() {
    init {

        "instances can be resolved implicitly" {
            functor<IdOf<Int>>() shouldNotBe null
            applicative<IdOf<Int>>() shouldNotBe null
            monad<IdOf<Int>>() shouldNotBe null
            foldable<IdOf<Int>>() shouldNotBe null
            traverse<IdOf<Int>>() shouldNotBe null
            eq<Id<Int>>() shouldNotBe null
            show<Id<Int>>() shouldNotBe null
        }

        testLaws(
            EqLaws.laws { Id(it) },
            ShowLaws.laws { Id(it) },
            MonadLaws.laws(Id.monad(), Eq.any()),
            TraverseLaws.laws(Id.traverse(), Id.functor(), ::Id),
            Id.comonad().laws(::Id, Eq.any())
        )
    }
}
