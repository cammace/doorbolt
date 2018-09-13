package com.cammace.doorbolt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Injecting dependencies into our ViewModel doesn't work out of the box, therefore, we need to
 * create our own ViewModelFactory.
 */

@Singleton
class ViewModelFactory @Inject constructor(
  private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
  : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}