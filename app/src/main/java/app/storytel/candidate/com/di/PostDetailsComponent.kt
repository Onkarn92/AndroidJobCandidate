package app.storytel.candidate.com.di

import androidx.lifecycle.ViewModelStoreOwner
import app.storytel.candidate.com.adapters.CommentAdapter
import app.storytel.candidate.com.repositories.PostRepository
import app.storytel.candidate.com.views.PostDetailsActivity
import app.storytel.candidate.com.views.models.PostDetailsViewModel
import dagger.BindsInstance
import dagger.Component

/**
 * Dagger component implementation for [PostDetailsActivity]
 */
@Component(modules = [PostModule::class])
interface PostDetailsComponent {
	
	/**
	 * Inject [PostDetailsActivity] instance into dagger component.
	 */
	fun injectPostDetailsActivity(postDetailsActivity: PostDetailsActivity)
	
	/**
	 * Provides [CommentAdapter] instance.
	 */
	fun getCommentAdapter(): CommentAdapter
	
	/**
	 * Provides [PostDetailsViewModel] instance.
	 */
	fun getPostDetailsViewModel(): PostDetailsViewModel
	
	/**
	 * Provides [PostRepository] instance used by view model factory.
	 */
	fun getPostRepository(): PostRepository
	
	@Component.Builder
	interface Builder {
		
		@BindsInstance
		fun withOwner(owner: ViewModelStoreOwner): Builder
		
		fun build(): PostDetailsComponent
	}
}