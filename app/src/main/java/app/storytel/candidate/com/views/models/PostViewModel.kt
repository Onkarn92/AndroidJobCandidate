package app.storytel.candidate.com.views.models

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import app.storytel.candidate.com.App
import app.storytel.candidate.com.interfaces.HttpEventTracker
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.repositories.PostRepository
import okhttp3.ResponseBody

class PostViewModel(
		val app: App,
		private val repository: PostRepository
) : AndroidViewModel(app), HttpEventTracker<List<Post>> {
	
	private val postObservable = MutableLiveData<Triple<List<Post>, String?, String?>>()
	
	override fun onCallSuccess(response: List<Post>) {
		postObservable.postValue(Triple(response, null, null))
	}
	
	override fun onCallFail(
			cause: String,
			throwable: Throwable,
			responseBody: ResponseBody?
	) {
		postObservable.postValue(Triple(arrayListOf(), cause, throwable.localizedMessage))
	}
	
	fun getPostObservable() = postObservable
	
	fun fetchPosts() = repository.fetchPosts(this)
}