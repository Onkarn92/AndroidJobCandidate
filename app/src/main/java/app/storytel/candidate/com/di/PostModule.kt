package app.storytel.candidate.com.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import app.storytel.candidate.com.adapters.CommentAdapter
import app.storytel.candidate.com.adapters.PostAdapter
import app.storytel.candidate.com.repositories.PostRepository
import app.storytel.candidate.com.views.models.Factory
import app.storytel.candidate.com.views.models.PostDetailsViewModel
import app.storytel.candidate.com.views.models.PostViewModel
import dagger.Module
import dagger.Provides

/**
 * Dagger module implementation for posts and comments.
 */
@Module
class PostModule {
	
	@Provides
	fun providePostAdapter(callback: PostAdapter.Callback): PostAdapter = PostAdapter(callback)
	
	@Provides
	fun provideCommentAdapter(): CommentAdapter = CommentAdapter()
	
	@Provides
	fun providePostViewModel(
			owner: ViewModelStoreOwner,
			repository: PostRepository
	): PostViewModel = ViewModelProvider(owner, Factory(repository))[PostViewModel::class.java]
	
	@Provides
	fun providePostDetailsViewModel(
			owner: ViewModelStoreOwner,
			repository: PostRepository
	): PostDetailsViewModel = ViewModelProvider(owner, Factory(repository))[PostDetailsViewModel::class.java]
	
	@Provides
	fun providePostRepository(): PostRepository = PostRepository()
}