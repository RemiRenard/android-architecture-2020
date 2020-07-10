package remi.renard.singleactivitysample.di

import dagger.Component
import remi.renard.singleactivitysample.di.scope.UserScope

@Component(dependencies = [ApplicationComponent::class])
@UserScope
interface UserComponent {

    @Component.Builder
    interface Builder {

        fun appComponent(component: ApplicationComponent): Builder
        fun build(): UserComponent
    }
}
