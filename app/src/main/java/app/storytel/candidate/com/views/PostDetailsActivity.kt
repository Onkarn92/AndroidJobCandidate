package app.storytel.candidate.com.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.storytel.candidate.com.R
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.utilities.INTENT_KEY_POST

class PostDetailsActivity : AppCompatActivity() {
	
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
		setContentView(R.layout.activity_post_details)
	}
}