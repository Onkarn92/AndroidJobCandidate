package app.storytel.candidate.com.di

import androidx.lifecycle.ViewModelStoreOwner
import app.storytel.candidate.com.adapters.PostAdapter
import app.storytel.candidate.com.repositories.PostRepository
import app.storytel.candidate.com.views.PostActivity
import app.storytel.candidate.com.views.models.PostViewModel
import dagger.BindsInstance
import dagger.Component

/**
 * Dagger component implementation for [PostActivity]
 */
@Component(modules = [PostModule::class])
interface PostComponent {
	
	/**
	 * Inject [PostActivity] instance into dagger component.
	 */
	fun injectPostActivity(postActivity: PostActivity)
	
	/**
	 * Provides [PostAdapter] instance.
	 */
	fun getPostAdapter(): PostAdapter
	
	/**
	 * Provides [PostViewModel] instance.
	 */
	fun getViewModel(): PostViewModel
	
	/**
	 * Provides [PostRepository] instance used by view model factory.
	 */
	fun getPostRepository(): PostRepository
	
	@Component.Builder
	interface Builder {
		
		@BindsInstance
		fun withCallback(callback: PostAdapter.Callback): Builder
		
		@BindsInstance
		fun withOwner(owner: ViewModelStoreOwner): Builder
		
		fun build(): PostComponent
	}
}