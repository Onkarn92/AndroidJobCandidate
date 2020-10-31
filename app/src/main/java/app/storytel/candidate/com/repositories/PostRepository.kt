package app.storytel.candidate.com.repositories

import app.storytel.candidate.com.interfaces.APIService
import app.storytel.candidate.com.interfaces.HttpEventTracker
import app.storytel.candidate.com.interfaces.HttpOperationCallback
import app.storytel.candidate.com.models.Comment
import app.storytel.candidate.com.models.Photo
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.network.HttpOperationWrapper
import app.storytel.candidate.com.utilities.NetworkUtils
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Responsible for a communication with network-layer and implement in-memory cache.
 */
class PostRepository {
	
	private val apiService: APIService by lazy {NetworkUtils.retrofit.create(APIService::class.java)}
	
	fun fetchPosts(eventTracker: HttpEventTracker<List<Post>>) {
		val httpOperationWrapper = HttpOperationWrapper<List<Post>>()
		httpOperationWrapper.initCall(apiService.getPosts(), object : HttpOperationCallback<List<Post>> {
			override fun onResponse(
					call: Call<List<Post>>,
					result: List<Post>?,
					errorPair: Pair<String, Throwable>,
					errorBody: ResponseBody?
			) {
				when {
					!result.isNullOrEmpty() -> {
						fetchPhotos(result, eventTracker)
					}
					else -> {
						eventTracker.onCallFail(errorPair.first, errorPair.second, errorBody)
					}
				}
			}
		})
	}
	
	fun fetchComments(
			postId: Int,
			eventTracker: HttpEventTracker<List<Comment>>
	) {
		val httpOperationWrapper = HttpOperationWrapper<List<Comment>>()
		httpOperationWrapper.initCall(apiService.getComments(postId), object : HttpOperationCallback<List<Comment>> {
			override fun onResponse(
					call: Call<List<Comment>>,
					result: List<Comment>?,
					errorPair: Pair<String, Throwable>,
					errorBody: ResponseBody?
			) {
				when {
					!result.isNullOrEmpty() -> {
						eventTracker.onCallSuccess(result)
					}
					else -> {
						eventTracker.onCallFail(errorPair.first, errorPair.second, errorBody)
					}
				}
			}
		})
	}
	
	private fun fetchPhotos(
			posts: List<Post>,
			eventTracker: HttpEventTracker<List<Post>>
	) {
		val httpOperationWrapper = HttpOperationWrapper<List<Photo>>()
		httpOperationWrapper.initCall(apiService.getPhotos(), object : HttpOperationCallback<List<Photo>> {
			override fun onResponse(
					call: Call<List<Photo>>,
					result: List<Photo>?,
					errorPair: Pair<String, Throwable>,
					errorBody: ResponseBody?
			) {
				when {
					!result.isNullOrEmpty() -> {
						val iterator = posts.listIterator()
						while (iterator.hasNext()) {
							val post = iterator.next()
							val photo = result.random()
							post.url = photo.url
							post.thumbnailUrl = photo.thumbnailUrl
						}
						eventTracker.onCallSuccess(posts)
					}
					else -> {
						eventTracker.onCallSuccess(posts)
					}
				}
			}
		})
	}
}