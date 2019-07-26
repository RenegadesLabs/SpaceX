package com.renegades.labs.spacex

import androidx.paging.PagedList
import com.renegades.labs.spacex.datasource.repo.LaunchRepo
import com.renegades.labs.spacex.di.module
import com.renegades.labs.spacex.entity.Result
import com.renegades.labs.spacex.entity.launch.Launch
import com.renegades.labs.spacex.ui.main.MainViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.UnstableDefault
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.BDDMockito.given


@UnstableDefault
class MainViewModelTest : KoinTest {

    private val firstLaunches =
        mutableListOf<Launch>().apply {
            repeat(30) {
                add(Launch("$it"))
            }
        }

    @Test
    fun `test MainViewModel success`() {
        declareMock<LaunchRepo> {
            given(this.getLaunches(anyInt(), anyInt())).willReturn(Result.success(firstLaunches))
        }

        val viewModel = MainViewModel()
        val testObserver = TestObserver<PagedList<Launch>>()

        viewModel.pagedLaunches
            .subscribeWith(testObserver)

        testObserver.awaitCount(1)
        testObserver.assertNoErrors()
        testObserver.assertNotComplete()
        testObserver.assertValueCount(1)
        testObserver.assertValue { pagedList ->
            pagedList.snapshot().filterNotNull() == firstLaunches
        }
    }

    @Test
    fun `test MainViewModel failure`() {
        val exception = RuntimeException("Test exception")
        declareMock<LaunchRepo> {
            given(this.getLaunches(anyInt(), anyInt())).willReturn(Result.failure(exception))
        }

        val viewModel = MainViewModel()
        val testObserver = TestObserver<PagedList<Launch>>()

        viewModel.pagedLaunches
            .subscribeWith(testObserver)

        testObserver.await()
        testObserver.assertError(exception)
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupTest() {
            startKoin { modules(module) }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }
}