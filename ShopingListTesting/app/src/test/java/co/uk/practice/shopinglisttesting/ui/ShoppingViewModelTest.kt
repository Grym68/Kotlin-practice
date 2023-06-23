package co.uk.practice.shopinglisttesting.ui

import co.uk.practice.shopinglisttesting.getOrAwaitValue
import co.uk.practice.shopinglisttesting.getOrAwaitValueTest
import co.uk.practice.shopinglisttesting.other.Status
import co.uk.practice.shopinglisttesting.repositories.FakeShoppingRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ShoppingViewModelTest {

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun insertsShoppingItemWithEmptyField_returnsError() {
        viewModel.insertShoppingItem("name", "", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    fun 
}