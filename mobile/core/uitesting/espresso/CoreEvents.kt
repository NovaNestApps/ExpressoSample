/**
 * Interface defining core Espresso UI interaction events for test automation.
 */
interface CoreEvents {

    /**
     * Clicks on a view with the specified view ID.
     *
     * @param viewId The resource ID of the view to be clicked.
     */
    fun selectViewWithId(@IdRes viewId: Int)

    /**
     * Clicks on a view inside a specific container view.
     *
     * @param viewId The resource ID of the view to be clicked.
     * @param containerId The resource ID of the container view.
     */
    fun selectViewWithId(@IdRes viewId: Int, @IdRes containerId: Int)

    /**
     * Clicks on a container view that contains a visible descendant view.
     *
     * @param containerViewId The container view's resource ID.
     * @param descendantViewId The descendant view's resource ID that must be visible.
     */
    fun selectContainerViewWithVisibleDescendantView(
        @IdRes containerViewId: Int,
        @IdRes descendantViewId: Int
    )

    /**
     * Performs a long click on a view with the given ID.
     *
     * @param viewId The resource ID of the view to long-click.
     */
    fun longClickOnViewWithId(@IdRes viewId: Int)

    /**
     * Selects a view that is currently displayed on the screen.
     *
     * @param viewId The resource ID of the view.
     */
    fun selectViewWithIdIfDisplayed(@IdRes viewId: Int)

    /**
     * Selects a view with the specified text.
     *
     * @param text The text shown in the target view.
     */
    fun selectViewWithText(text: String)

    /**
     * Scrolls to a view with the given ID.
     *
     * @param viewId The resource ID of the view to scroll to.
     */
    fun scrollToViewWithId(@IdRes viewId: Int)

    /**
     * Scrolls to a view using a string resource.
     *
     * @param stringResId The string resource ID whose text is displayed in the view.
     */
    fun scrollToViewWithStringResId(@StringRes stringResId: Int)

    /**
     * Replaces the current text in an EditText view.
     *
     * @param editTextId The resource ID of the EditText view.
     * @param text The text to replace the current content with.
     * @param innerEditTextClass The class of the EditText view.
     */
    fun replaceTextInEditText(
        @IdRes editTextId: Int,
        text: String?,
        innerEditTextClass: Class<out View> = EditText::class.java
    )

    /**
     * Types text into an EditText view.
     *
     * @param editTextId The resource ID of the EditText view.
     * @param text The text to type into the view.
     * @param innerEditTextClass The class of the EditText view.
     */
    fun typeTextInEditText(
        @IdRes editTextId: Int,
        text: String?,
        innerEditTextClass: Class<out View> = EditText::class.java
    )

    /**
     * Asserts that a view with the given ID is displayed.
     *
     * @param viewId The resource ID of the view.
     */
    fun assertViewWithIdIsDisplayed(@IdRes viewId: Int)

    /**
     * Scrolls and asserts a view is displayed.
     *
     * @param viewId The resource ID of the view.
     */
    fun scrollAndAssertViewWithIdIsDisplayed(@IdRes viewId: Int)

    /**
     * Asserts that a view has effective visibility.
     *
     * @param viewId The resource ID of the view.
     */
    fun assertViewWithIdHasEffectiveVisibility(@IdRes viewId: Int)

    /**
     * Asserts that a view with ID and text has effective visibility.
     *
     * @param viewId The resource ID of the view.
     * @param text The expected text in the view.
     */
    fun assertViewWithIdAndTextHasEffectiveVisibility(
        @IdRes viewId: Int,
        @StringRes text: Int
    )

    /**
     * Asserts that a view is not displayed.
     *
     * @param viewId The resource ID of the view.
     */
    fun assertViewWithIdIsNotDisplayed(@IdRes viewId: Int)

    /**
     * Asserts that an input field masks its content.
     *
     * @param viewId The resource ID of the input view.
     */
    fun assertInputOnViewIsMasked(@IdRes viewId: Int)

    /**
     * Asserts that a descendant view masks its content inside a container.
     *
     * @param viewId The container view ID.
     * @param descendantOfId The descendant view ID.
     */
    fun assertInputOnViewIsMasked(
        @IdRes viewId: Int,
        @IdRes descendantOfId: Int
    )

    /**
     * Simulates key presses on a virtual keyboard.
     *
     * @param keyboardViewId The view ID of the keyboard.
     * @param numberOfKeyPresses Number of times to simulate key presses.
     */
    fun clickKeysOnKeyboard(keyboardViewId: Int, numberOfKeyPresses: Int)

    /**
     * Populates a quick stub view by selecting a specific scenario.
     *
     * @param quickStubEntryViewId The resource ID of the stub entry view.
     * @param quickStubScenario The scenario name to populate.
     */
    fun populateQuickLaunch(
        quickStubEntryViewId: Int,
        quickStubScenario: String?
    )

    /**
     * Selects a string from a list based on its position.
     *
     * @param position The position in the list.
     * @param viewId The resource ID of the list view.
     */
    fun selectPositionFromAListOfStrings(
        position: Int,
        @IdRes viewId: Int
    )

    /**
     * Mocks and stubs an intent action with specific data.
     *
     * @param action The intent action.
     * @param data The intent data.
     */
    fun assertIntentActionFiredWithData(action: String, data: String)

    /**
     * Verifies that a specific intent action was fired.
     *
     * @param action The intent action name.
     */
    fun assertIntentActionFired(action: String)

    /**
     * Stubs an intent with a specific action and result.
     *
     * @param action The intent action.
     * @param resultFunction The function to return a mock ActivityResult.
     */
    fun stubFireIntent(action: String, resultFunction: Instrumentation.ActivityResult)

        /**
     * Asserts that a view inside a dialog is displayed.
     *
     * @param viewId The resource ID of the view inside the dialog.
     */
    fun assertViewInDialogWithIdIsDisplayed(@IdRes viewId: Int)

    /**
     * Asserts that a view is disabled.
     *
     * @param viewId The resource ID of the view.
     */
    fun assertViewIsDisabled(@IdRes viewId: Int)

    /**
     * Asserts that the parent view containing the given text is disabled.
     *
     * @param text The text inside the parent view.
     */
    fun assertParentViewWithTextIsDisabled(text: String?)

    /**
     * Asserts that the parent view containing the given text is enabled.
     *
     * @param text The text inside the parent view.
     */
    fun assertParentViewWithTextIsEnabled(text: String?)

    /**
     * Scrolls and selects a view by its ID, with timeout support.
     *
     * @param viewId The resource ID of the view.
     * @param timeoutMillis Timeout duration in milliseconds. Defaults to 1000ms.
     */
    fun scrollToAndSelectViewWithId(@IdRes viewId: Int, timeoutMillis: Long = 1000)

    /**
     * Asserts that a view containing the given text resource is displayed.
     *
     * @param textResourceId The resource ID of the expected string.
     */
    fun assertViewWithTextResourceIdIsDisplayed(@StringRes textResourceId: Int)

    /**
     * Asserts that a view with the exact text is displayed.
     *
     * @param text The exact text expected in the view.
     */
    fun assertViewWithTextIsDisplayed(text: String)

    /**
     * Asserts that a view with partial text is displayed.
     *
     * @param text The partial text to be matched in the view.
     */
    fun assertViewWithPartialTextIsDisplayed(text: String)

    /**
     * Asserts that a view with the specified text is not displayed.
     *
     * @param text The text expected not to be displayed.
     */
    fun assertViewWithTextIsNotDisplayed(text: String)

    /**
     * Asserts that a view with the given accessibility description is not displayed.
     *
     * @param accessibilityString The string resource ID of the content description.
     */
    fun assertViewWithContentDescriptionIsNotDisplayed(@StringRes accessibilityString: Int)

    /**
     * Asserts that a view with both ID and text is displayed.
     *
     * @param viewId The resource ID of the view.
     * @param text The string resource ID expected in the view.
     */
    fun assertViewWithIdAndTextIsDisplayed(@IdRes viewId: Int, @StringRes text: Int)

    /**
     * Asserts that a view with both ID and text is displayed in Jetpack Compose.
     *
     * @param viewId The resource ID of the view.
     * @param text The string resource ID expected.
     */
    fun assertComposeViewWithIdAndTextIsDisplayed(@IdRes viewId: Int, @StringRes text: Int)

    /**
     * Selects a focused view by its ID.
     *
     * @param viewId The resource ID of the view.
     */
    fun selectFocusedView(@IdRes viewId: Int)

    /**
     * Selects a view with a specific text resource ID.
     *
     * @param textResourceId The string resource ID.
     */
    fun selectViewWithTextResourceId(@IdRes textResourceId: Int)

    /**
     * Selects a view with a specific content description.
     *
     * @param accessibilityString The string resource ID of the content description.
     */
    fun selectViewWithContentDescription(@StringRes accessibilityString: Int)

    /**
     * Selects a Spannable text view using its string resource ID.
     *
     * @param viewId The view's resource ID.
     * @param spannableTextResourceId The resource ID of the spannable string.
     */
    fun selectSpannableTextInView(@IdRes viewId: Int, @StringRes spannableTextResourceId: Int)

    /**
     * Asserts that a view is selected.
     *
     * @param viewId The resource ID of the view.
     */
    fun assertViewWithIdIsSelected(@IdRes viewId: Int)

    /**
     * Asserts that a view is not selected.
     *
     * @param viewId The resource ID of the view.
     */
    fun assertViewWithIdIsNotSelected(@IdRes viewId: Int)

    /**
     * Collapses an AppBarLayout.
     *
     * @param viewId The resource ID of the AppBarLayout view.
     */
    fun collapseAppbarLayout(@IdRes viewId: Int)

    /**
     * Performs a swipe-right gesture on a ViewPager.
     *
     * @param viewPagerId The resource ID of the ViewPager.
     */
    fun swipeRightViewPager(@IdRes viewPagerId: Int)

    /**
     * Performs a swipe-left gesture on a ViewPager.
     *
     * @param viewPagerId The resource ID of the ViewPager.
     */
    fun swipeLeftViewPager(@IdRes viewPagerId: Int)

    /**
     * Closes the soft keyboard from the current focus or given view.
     *
     * @param forViewId The resource ID of the target view.
     */
    fun closeSoftKeyboard(@IdRes forViewId: Int)
    /**
     * Returns the currently active fragment activity.
     *
     * @return The current [FragmentActivity] instance.
     */
    fun getCurrentActivity(): FragmentActivity

    /**
     * Waits until a view with the given ID is visible on the screen.
     *
     * @param viewId The resource ID of the view to wait for.
     */
    fun waitForView(@IdRes viewId: Int)

    /**
     * Waits for a view to match the provided criteria within a timeout.
     *
     * @param viewId The resource ID of the view.
     * @param matchType The matching strategy or type.
     * @param timeoutMillis Timeout duration in milliseconds.
     * @param callback Callback to be invoked once the condition is met.
     */
    fun waitForMatch(
        @IdRes viewId: Int,
        matchType: Match,
        timeoutMillis: Long,
        callback: (Int) -> Unit
    )

    /**
     * Waits until a ViewPager has completed the swipe animation.
     *
     * @param viewPagerId The resource ID of the ViewPager.
     * @param callback Callback to be triggered after swipe is complete.
     */
    fun waitForViewPagerSwipe(@IdRes viewPagerId: Int, callback: (Int) -> Unit)

    /**
     * Swipes the view to its end.
     *
     * @param viewId The resource ID of the view.
     */
    fun swipeViewToEnd(@IdRes viewId: Int)

    /**
     * Asserts that the app bar has collapsed.
     */
    fun assertAppHasClosed()

    /**
     * Scrolls to a specific item in a RecyclerView by its container and view ID.
     *
     * @param containerId The resource ID of the parent container view.
     * @param viewId The resource ID of the view to scroll to.
     */
    fun scrollToItemInRecyclerWithId(@IdRes containerId: Int, @IdRes viewId: Int)

    /**
     * Scrolls to a position in a RecyclerView.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param position The adapter position to scroll to.
     */
    fun scrollToItemInRecyclerViewAtPosition(@IdRes recyclerViewId: Int, position: Int)

    /**
     * Scrolls the RecyclerView to a specific ViewHolder type.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param holderClass The class type of the expected ViewHolder.
     */
    fun <VH : RecyclerView.ViewHolder> scrollRecyclerViewToViewHolder(
        @IdRes recyclerViewId: Int,
        holderClass: Class<VH>
    )

    /**
     * Selects a specific child view from a RecyclerView at a given position.
     *
     * @param position The adapter position of the item.
     * @param viewId The resource ID of the child view inside the item.
     */
    fun <VH : RecyclerView.ViewHolder> selectChildViewFromRecyclerView(
        position: Int,
        @IdRes viewId: Int
    )

    /**
     * Selects a RecyclerView item based on a string resource.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param itemTextRes The string resource representing the item text.
     */
    fun <VH : RecyclerView.ViewHolder> selectItemFromRecyclerViewListWithTextRes(
        @IdRes recyclerViewId: Int,
        @StringRes itemTextRes: Int
    )

    /**
     * Selects an item in a RecyclerView with a minimum child count at a given position.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param minimumChildCount The minimum number of children expected.
     * @param position The adapter position to check.
     */
    fun selectItemInRecyclerViewWithMinimumChildCountAtPosition(
        @IdRes recyclerViewId: Int,
        minimumChildCount: Int,
        position: Int
    )

    /**
     * Asserts that a text string resource exists in the RecyclerView.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param textRes The string resource expected in the list.
     */
    fun assertViewAvailableInRecyclerViewListWithTextStringRes(
        @IdRes recyclerViewId: Int,
        @StringRes textRes: Int
    )

    /**
     * Asserts that a plain string text is present in the RecyclerView list.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param itemText The expected text of the item.
     */
    fun assertViewAvailableInRecyclerViewListWithTextString(
        @IdRes recyclerViewId: Int,
        itemText: String
    )

    /**
     * Asserts that a given item ID is present in the RecyclerView.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param itemId The resource ID of the view/item to assert.
     */
    fun assertViewAvailableInRecyclerViewList(
        @IdRes recyclerViewId: Int,
        @IdRes itemId: Int
    )

    /**
     * Selects a RecyclerView item with the given string text.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param itemTitleText The item title to match.
     */
    fun <VH : RecyclerView.ViewHolder> selectItemFromRecyclerViewListWithTextString(
        @IdRes recyclerViewId: Int,
        itemTitleText: String
    )

    /**
     * Selects a RecyclerView item at a specific adapter position.
     *
     * @param recyclerViewId The resource ID of the RecyclerView.
     * @param position The adapter position of the item.
     */
    fun <VH : RecyclerView.ViewHolder> selectItemInRecyclerViewAtPosition(
        @IdRes recyclerViewId: Int,
        position: Int
    )

    /**
     * Asserts that an item in the RecyclerView is disabled.
     *
     * @param recyclerViewId The RecyclerView resource ID.
     * @param position The adapter position of the item.
     */
    fun assertItemInRecyclerViewIsDisabled(
        @IdRes recyclerViewId: Int,
        position: Int
    )

    /**
     * Selects a child view from a specific parent view.
     *
     * @param parentViewId The parent view's resource ID.
     * @param childViewId The child view's resource ID.
     */
    fun selectChildViewFromParentViewWithId(
        @IdRes parentViewId: Int,
        @IdRes childViewId: Int
    )

    /**
     * Selects the first item from a RecyclerView.
     *
     * @param containerId The container holding the RecyclerView.
     * @param viewId The resource ID of the view to interact with.
     */
    fun selectFirstItemInRecyclerViewWithId(
        @IdRes containerId: Int,
        @IdRes viewId: Int
    )

    /**
     * Asserts that the view with the given ID is disabled.
     *
     * @param viewId The resource ID of the view.
     */
    fun assertViewWithIdIsDisabled(@IdRes viewId: Int)

    /**
     * Mocks and fires an intent action with accompanying data.
     *
     * @param action The action string of the intent.
     * @param url The URL string to include in the intent data.
     * @param respondWith The stubbed result to return from the intent.
     */
    fun mockIntentActionFiredWithData(
        action: String,
        url: String,
        respondWith: Instrumentation.ActivityResult
    )

}