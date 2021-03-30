package com.app.marveltestapp.utils

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxComposer {
    companion object {
        fun <T> applySingleSchedulers(): SingleTransformer<T, T> = SingleTransformer { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }

        fun <T> applyObservableSchedulers(): ObservableTransformer<T, T> = ObservableTransformer { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }

        fun <T> applyMaybeSchedulers(): MaybeTransformer<T, T> = MaybeTransformer { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }

        fun applyCompletableSchedulers(): CompletableTransformer = CompletableTransformer { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }
    }
}

fun Disposable?.safeDispose() {
    this?.let {
        if (!isDisposed) {
            dispose()
        }
    }
}

fun <T> Observable<T>.applyObservableSchedulers() = compose(RxComposer.applyObservableSchedulers<T>())

fun <T> Maybe<T>.applyMaybeSchedulers() = compose(RxComposer.applyMaybeSchedulers<T>())

fun Completable.applyCompletableSchedulers() = compose(RxComposer.applyCompletableSchedulers())

fun <T> Single<T>.applySingleSchedulers() = compose(RxComposer.applySingleSchedulers<T>())