package app.storytel.candidate.com.views.models

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import app.storytel.candidate.com.App
import app.storytel.candidate.com.interfaces.HttpEventTracker
import app.storytel.candidate.com.models.Comment
import app.storytel.candidate.com.repositories.PostRepository
import okhttp3.ResponseBody

class PostDetailsViewModel(
		val app: App,
		private val repository: PostRepository
) : AndroidViewModel(app), HttpEventTracker<List<Comment>> {
	
	private val commentObservable = MutableLiveData<Triple<List<Comment>, String?, String?>>()
	
	override fun onCallSuccess(response: List<Comment>) {
		if (response.size > 3) {
			// Rendering only top 3 comments.
			commentObservable.postValue(Triple(response.subList(0, 3), null, null))
		} else {
			// Rendering all comments if count is less than or equal to 3
			commentObservable.postValue(Triple(response, null, null))
		}
	}
	
	override fun onCallFail(
			cause: String,
			throwable: Throwable,
			responseBody: ResponseBody?
	) {
		commentObservable.postValue(Triple(arrayListOf(), cause, throwable.localizedMessage))
	}
	
	fun getCommentObservable() = commentObservable
	
	fun fetchComments(postId: Int) = repository.fetchComments(postId, this)
}