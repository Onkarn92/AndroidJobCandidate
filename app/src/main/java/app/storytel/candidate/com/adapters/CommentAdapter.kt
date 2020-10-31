package app.storytel.candidate.com.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.R
import app.storytel.candidate.com.adapters.CommentAdapter.ViewHolder
import app.storytel.candidate.com.databinding.ItemCommentBinding
import app.storytel.candidate.com.models.Comment

class CommentAdapter : RecyclerView.Adapter<ViewHolder>() {
	
	private val comments: ArrayList<Comment> = arrayListOf()
	
	override fun onCreateViewHolder(
			parent: ViewGroup,
			viewType: Int
	): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))
	
	override fun onBindViewHolder(
			holder: ViewHolder,
			position: Int
	) {
		holder.setData(comments[position])
	}
	
	override fun getItemCount(): Int = comments.size
	
	/**
	 * @param items Set list of comments and update data-set.
	 */
	fun setItems(items: List<Comment>) {
		comments.clear()
		comments.addAll(items)
		notifyDataSetChanged()
	}
	
	/**
	 * Item view and data holder.
	 */
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		
		private val binding: ItemCommentBinding = ItemCommentBinding.bind(itemView)
		
		/**
		 * @param comment to be render on UI.
		 */
		fun setData(comment: Comment) {
			with(binding) {
				commentBodyText.text = comment.body
				commentNameText.text = comment.name
				commentEmailText.text = comment.email
			}
		}
	}
}