// Stub for recyclerViewDoesNotContain matcher
/**
 * Matches a RecyclerView that does NOT contain any item (view holder) whose content
 * matches the provided itemMatcher.
 *
 * This matcher is the inverse of `withRecyclerViewItem`. It asserts that no item within
 * the RecyclerView satisfies the given `itemMatcher`.
 *
 * Usage:
 * onView(withId(R.id.my_recycler_view))
 * .check(matches(recyclerViewDoesNotContain(withText("Deleted Item"))))
 *
 * @param itemMatcher A matcher that would match an item if it were present.
 */
fun recyclerViewDoesNotContain(itemMatcher: Matcher<View>): Matcher<View> {
    // We can reuse the logic from withRecyclerViewItem and simply negate it.
    return not(withRecyclerViewItem(itemMatcher))
}

// Helper function from the previous immersive to allow 'not' to be used here
// In a real project, both withRecyclerViewItem and recyclerViewDoesNotContain would likely
// be in the same file or within a common set of RecyclerView matchers.
// For this example, we provide it here for self-contained use.
// Note: If withRecyclerViewItem is in a separate file and imported, this helper is not needed here.
private fun withRecyclerViewItem(itemMatcher: Matcher<View>): Matcher<View> =
    object : TypeSafeMatcher<View>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has RecyclerView item matching: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(recyclerView: View): Boolean {
            if (recyclerView !is RecyclerView) {
                return false
            }

            val adapter = recyclerView.adapter ?: return false

            for (i in 0 until adapter.itemCount) {
                val viewHolder = adapter.createViewHolder(recyclerView, adapter.getItemViewType(i))
                adapter.onBindViewHolder(viewHolder, i)
                if (itemMatcher.matches(viewHolder.itemView)) {
                    return true
                }
            }
            return false
        }
    }