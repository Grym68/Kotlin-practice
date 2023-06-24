package co.uk.practice.shopinglisttesting.ui

import android.provider.SyncStateContract
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.uk.practice.shopinglisttesting.MainCoroutineRule
import co.uk.practice.shopinglisttesting.getOrAwaitValueTest
import co.uk.practice.shopinglisttesting.other.Constant
import co.uk.practice.shopinglisttesting.other.Status
import co.uk.practice.shopinglisttesting.repositories.FakeShoppingRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

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


    @Test
    fun insertsShoppingItemWithTooLongName_returnsError() {
        val string = buildString {
            for(i in 1..Constant.MAX_NAME_LENGTH + 1){
                append(1)
            }
        }
        viewModel.insertShoppingItem(string, "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertsShoppingItemWithTooLongPrice_returnsError() {
        val string = buildString {
            for(i in 1..Constant.MAX_PRICE_LENGTH + 1){
                append(1)
            }
        }
        viewModel.insertShoppingItem("name", "5", string)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertsShoppingItemWithTooHighAmount_returnsError() {
        viewModel.insertShoppingItem("name", (Long.MAX_VALUE+2).toString(), "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertsShoppingItemWithTooValidInput_returnsSuccess() {
        viewModel.insertShoppingItem("name", "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}
