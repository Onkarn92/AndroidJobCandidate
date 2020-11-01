package app.storytel.candidate.com.views

import android.R.id
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.storytel.candidate.com.R
import app.storytel.candidate.com.adapters.CommentAdapter
import app.storytel.candidate.com.databinding.ActivityPostDetailsBinding
import app.storytel.candidate.com.di.DaggerPostDetailsComponent
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.utilities.DEFAULT_INT
import app.storytel.candidate.com.utilities.INTENT_KEY_POST
import app.storytel.candidate.com.utilities.Utils
import app.storytel.candidate.com.views.models.PostDetailsViewModel
import com.bumptech.glide.Glide
import javax.inject.Inject

/**
 * Post details view container.
 */
class PostDetailsActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityPostDetailsBinding
	private var post: Post? = null
	@Inject lateinit var viewModel: PostDetailsViewModel
	@Inject lateinit var adapter: CommentAdapter
	
	companion object {
		
		/**
		 * Provides new instance of [Intent] holding [PostDetailsActivity] as a target.
		 *
		 * @param context of the caller view.
		 * @param post   object to be serialize in intent bundle.
		 */
		fun newInstance(
				context: Context,
				post: Post
		): Intent {
			return Intent(context, PostDetailsActivity::class.java).putExtra(INTENT_KEY_POST, post)
		}
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityPostDetailsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		// Initialising the dagger component to inject all dependencies.
		DaggerPostDetailsComponent.builder().withOwner(this).build().injectPostDetailsActivity(this)
		
		post = intent.getParcelableExtra(INTENT_KEY_POST)
		if (post == null) {
			// Post object not available in intent bundle, so return.
			Toast.makeText(this, R.string.err_something_went_wrong, Toast.LENGTH_SHORT).show()
			finish()
			return
		}
		
		setupView()
		attachObserver()
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == id.home) {
			onBackPressed()
			return true
		}
		return super.onOptionsItemSelected(item)
	}
	
	/**
	 * Setup a view for [PostDetailsActivity] container.
	 */
	private fun setupView() {
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.setDisplayShowTitleEnabled(true)
		supportActionBar?.title = Utils.getString(R.string.title_activity_details).format(post?.id ?: DEFAULT_INT)
		
		Glide.with(this).load(post?.url).centerCrop().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
				.into(binding.postImage)
		
		with(binding) {
			postTitleText.text = post?.title
			postBodyText.text = post?.body
			commentRecycler.layoutManager = LinearLayoutManager(this@PostDetailsActivity)
			commentRecycler.adapter = adapter
		}
		
		viewModel.fetchComments(post!!.id)
	}
	
	/**
	 * Observe all existing and new comments and load it into adapter, as well as observer corresponding errors.
	 */
	private fun attachObserver() {
		viewModel.getCommentObservable().observe(this) {(comments, cause, message) ->
			when {
				comments.isNotEmpty() -> {
					adapter.setItems(comments)
				}
				else -> {
					Toast.makeText(this, "$cause - $message", Toast.LENGTH_LONG).show()
				}
			}
		}
	}
}