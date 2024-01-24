package com.gravatar

import com.gravatar.di.container.GravatarSdkContainer
import com.gravatar.logger.Logger
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@OptIn(ExperimentalCoroutinesApi::class)
class GravatarSdkContainerRule : TestRule {
    val testDispatcher = UnconfinedTestDispatcher()

    var gravatarSdkContainerMock = mockk<GravatarSdkContainer>()
    var gravatarApiServiceMock = mockk<GravatarApiService>()
    var logger = mockk<Logger>(relaxed = true)

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                gravatarSdkContainerMock = mockk<GravatarSdkContainer>()
                gravatarApiServiceMock = mockk<GravatarApiService>(relaxed = true)
                mockkObject(GravatarSdkContainer)
                every { GravatarSdkContainer.instance } returns gravatarSdkContainerMock
                every { gravatarSdkContainerMock.dispatcherDefault } returns testDispatcher
                every { gravatarSdkContainerMock.getGravatarApiService(any()) } returns gravatarApiServiceMock
                every { gravatarSdkContainerMock.logger } returns logger

                base.evaluate()
            }
        }
    }
}
