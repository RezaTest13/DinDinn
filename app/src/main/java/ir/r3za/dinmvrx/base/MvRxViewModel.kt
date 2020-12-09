package ir.r3za.dinmvrx.base

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import ir.r3za.dinmvrx.BuildConfig

abstract class MvRxViewModel<S : MvRxState>(initialState: S) :
    BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG)