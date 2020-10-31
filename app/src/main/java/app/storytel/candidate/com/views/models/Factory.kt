package app.storytel.candidate.com.views.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.storytel.candidate.com.App
import app.storytel.candidate.com.repositories.PostRepository

/**
 * Custom ViewModel factory which provides current [App] context and [PostRepository].
 */
class Factory(private val repository: PostRepository) : ViewModelProvider.NewInstanceFactory() {
	
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return when {
			modelClass.isAssignableFrom(PostViewModel::class.java) -> {
				(PostViewModel(App.getApplication(), repository) as T)
			}
			modelClass.isAssignableFrom(PostDetailsViewModel::class.java) -> {
				(PostDetailsViewModel(App.getApplication(), repository) as T)
			}
			else -> {
				super.create(modelClass)
			}
		}
	}
}