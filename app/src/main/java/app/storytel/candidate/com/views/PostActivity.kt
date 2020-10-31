package app.storytel.candidate.com.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.storytel.candidate.com.R
import app.storytel.candidate.com.adapters.PostAdapter
import app.storytel.candidate.com.databinding.ActivityPostBinding
import app.storytel.candidate.com.di.DaggerPostComponent
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.utilities.Utils
import app.storytel.candidate.com.views.models.PostViewModel
import javax.inject.Inject

class PostActivity : AppCompatActivity(), PostAdapter.Callback {
	
	private lateinit var binding: ActivityPostBinding
	@Inject lateinit var viewModel: PostViewModel
	@Inject lateinit var adapter: PostAdapter
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityPostBinding.inflate(layoutInflater)
		setContentView(binding.root)
		// Initialising the dagger component to inject all dependencies.
		DaggerPostComponent.builder().withCallback(this).withOwner(this).build().injectPostActivity(this)
		setupView()
		attachedObserver()
		loadPosts()
	}
	
	override fun onPostClicked(post: Post) {
		startActivity(PostDetailsActivity.newInstance(this, post))
	}
	
	private fun setupView() {
		supportActionBar?.setDisplayShowTitleEnabled(true)
		supportActionBar?.title = Utils.getString(R.string.app_name)
		with(binding) {
			postRecycler.addItemDecoration(DividerItemDecoration(this@PostActivity, DividerItemDecoration.VERTICAL))
			postRecycler.layoutManager = LinearLayoutManager(this@PostActivity)
			postRecycler.adapter = adapter
			errorLayout.retryBtn.setOnClickListener {loadPosts()}
		}
	}
	
	private fun attachedObserver() {
		viewModel.getPostObservable().observe(this) {(posts, cause, message) ->
			when {
				posts.isNotEmpty() -> {
					// Posts loaded successfully.
					with(binding) {
						postRecycler.visibility = View.VISIBLE
						progress.visibility = View.GONE
						errorLayout.root.visibility = View.GONE
					}
					adapter.setItems(posts)
				}
				else -> {
					// Error occurred while loading posts.
					with(binding) {
						postRecycler.visibility = View.GONE
						progress.visibility = View.GONE
						errorLayout.root.visibility = View.VISIBLE
						errorLayout.errorTitleText.text = cause
						errorLayout.errorMessageText.text = message
					}
				}
			}
		}
	}
	
	private fun loadPosts() {
		with(binding) {
			postRecycler.visibility = View.GONE
			progress.visibility = View.VISIBLE
			errorLayout.root.visibility = View.GONE
		}
		viewModel.fetchPosts()
	}
}