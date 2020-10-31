package app.storytel.candidate.com.models

import android.os.Parcelable
import app.storytel.candidate.com.utilities.DEFAULT_INT
import app.storytel.candidate.com.utilities.EMPTY_STRING
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
		@SerializedName("userId") var userId: Int = DEFAULT_INT,
		@SerializedName("id") var id: Int = DEFAULT_INT,
		@SerializedName("title") var title: String? = EMPTY_STRING,
		@SerializedName("body") var body: String? = EMPTY_STRING,
		var url: String? = EMPTY_STRING,
		var thumbnailUrl: String? = EMPTY_STRING
) : Parcelable