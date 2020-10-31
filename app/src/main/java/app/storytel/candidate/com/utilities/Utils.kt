package app.storytel.candidate.com.utilities

import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import app.storytel.candidate.com.App

object Utils {
	
	/**
	 * @return respective string resource.
	 */
	fun getString(@StringRes resId: Int): String = App.getContext().getString(resId)
	
	/**
	 * @return respective color resource.
	 */
	fun getColor(@ColorRes resId: Int) = ContextCompat.getColor(App.getContext(), resId)
	
	/**
	 * @return respective string-array resource.
	 */
	fun getStringArray(@ArrayRes resId: Int): Array<String> = App.getContext().resources.getStringArray(resId)
	
	/**
	 * @return respective int-array resource.
	 */
	fun getIntArray(@ArrayRes resId: Int): IntArray = App.getContext().resources.getIntArray(resId)
	
	/**
	 * @return respective color resource.
	 */
	fun getDrawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(App.getContext(), resId)
}