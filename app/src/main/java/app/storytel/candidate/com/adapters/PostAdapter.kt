package app.storytel.candidate.com.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.R
import app.storytel.candidate.com.adapters.PostAdapter.ViewHolder
import app.storytel.candidate.com.databinding.PostItemBinding
import app.storytel.candidate.com.models.Post
import com.bumptech.glide.Glide

class PostAdapter(private val callback: Callback) : RecyclerView.Adapter<ViewHolder>() {
	
	private val posts: ArrayList<Post> = arrayListOf()
	
	override fun onCreateViewHolder(
			parent: ViewGroup,
			viewType: Int
	): ViewHolder {
		val holder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
		holder.binding.root.setOnClickListener {
			if (holder.post != null) {
				callback.onPostClicked(holder.post!!)
			}
		}
		return holder
	}
	
	override fun onBindViewHolder(
			holder: ViewHolder,
			position: Int
	) {
		holder.setData(posts[position])
	}
	
	override fun getItemCount(): Int = posts.size
	
	/**
	 * @param items Set list of posts and update data-set.
	 */
	fun setItems(items: List<Post>) {
		posts.clear()
		posts.addAll(items)
		notifyDataSetChanged()
	}
	
	/**
	 * Item view and data holder.
	 */
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		
		val binding: PostItemBinding = PostItemBinding.bind(itemView)
		
		var post: Post? = null
		
		/**
		 * @param post to be render on UI.
		 */
		fun setData(post: Post) {
			this.post = post
			Glide.with(itemView).load("${post.thumbnailUrl}.png").placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
					.into(binding.postImage)
			binding.titleText.text = post.title
			binding.bodyText.text = post.body
		}
	}
	
	interface Callback {
		
		fun onPostClicked(post: Post)
	}
}