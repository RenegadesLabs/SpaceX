package com.renegades.labs.spacex.ui.tabs.list


import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.renegades.labs.spacex.datasource.launch.paging.LaunchDataSourceFactory
import com.renegades.labs.spacex.entity.launch.Launch
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import io.reactivex.subjects.Subject
import org.koin.core.KoinComponent
import org.koin.core.inject


class ListViewModel : ViewModel(), KoinComponent {

    val pagedLaunches: Subject<PagedList<Launch>> = ReplaySubject.create()

    private val dataSourceFactory by inject<LaunchDataSourceFactory>()
    private var disposable: Disposable? = null
    private val launchesObservable: Observable<PagedList<Launch>> = RxPagedListBuilder(dataSourceFactory, PAGE_SIZE)
        .setFetchScheduler(Schedulers.io())
        .setNotifyScheduler(AndroidSchedulers.mainThread())
        .buildObservable()

    init {
        disposable = launchesObservable.subscribe({ pagedLaunches.onNext(it) }, { pagedLaunches.onError(it) })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    companion object {
        private const val PAGE_SIZE = 10
    }

}
