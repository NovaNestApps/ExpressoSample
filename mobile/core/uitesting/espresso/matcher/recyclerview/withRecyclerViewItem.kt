// Stub for withRecyclerViewItem matcher
/**
 * Matches a RecyclerView that contains at least one item (view holder) whose content
 * matches the provided itemMatcher.
 *
 * This matcher iterates through all child views of a RecyclerView and checks if any
 * of them match the `itemMatcher`. It's useful for verifying the presence of a specific
 * item within a RecyclerView without knowing its exact position.
 *
 * Usage:
 * onView(withId(R.id.my_recycler_view))
 * .check(matches(withRecyclerViewItem(withText("Item Text"))))
 *
 * @param itemMatcher A matcher that will be applied to each item view within the RecyclerView.
 */
fun withRecyclerViewItem(itemMatcher: Matcher<View>): Matcher<View> =
    object : TypeSafeMatcher<View>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has RecyclerView item matching: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(recyclerView: View): Boolean {
            // Ensure the view is a RecyclerView
            if (recyclerView !is RecyclerView) {
                return false
            }

            val adapter = recyclerView.adapter ?: return false

            // Iterate through all items visible or not, by getting the view holder for each position
            // This is a common pattern for inspecting RecyclerView content comprehensively.
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
