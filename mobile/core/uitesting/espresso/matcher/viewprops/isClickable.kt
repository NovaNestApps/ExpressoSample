// Stub for isClickable matcher
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
/**
 * Matches a View that is clickable.
 */
fun isClickable(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("is clickable")
    }

    override fun matchesSafely(item: View): Boolean = item.isClickable
}