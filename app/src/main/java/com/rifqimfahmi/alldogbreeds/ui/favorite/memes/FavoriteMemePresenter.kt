package com.rifqimfahmi.alldogbreeds.ui.favorite.memes

import com.rifqimfahmi.alldogbreeds.data.DataManager
import com.rifqimfahmi.alldogbreeds.ui.base.BasePresenter
import com.rifqimfahmi.alldogbreeds.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 07/03/18.
 */

class FavoriteMemePresenter<V : FavoriteMemeMvpView> @Inject constructor(dataManager: DataManager,
                                                     schedulerProvider: SchedulerProvider,
                                                     compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), FavoriteMemeMvpPresenter<V> {

}