package com.kinetx.moneymanager.database

import androidx.lifecycle.LiveData
import com.kinetx.moneymanager.dataclass.TransactionListClass
import com.kinetx.moneymanager.enums.TransactionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepository (private val databaseDao: DatabaseDao) {

    val readAllIncomeCategory : LiveData<List<CategoryDatabase>> = databaseDao.getAllIncomeCategory()
    val readAllExpenseCategory : LiveData<List<CategoryDatabase>> = databaseDao.getAllExpenseCategory()
    val readAllAccountCategory : LiveData<List<CategoryDatabase>> = databaseDao.getAllAccountCategory()




    suspend fun insertCategory(category: CategoryDatabase)
    {
        databaseDao.insertCategory(category)
    }

    suspend fun updateCategory(category: CategoryDatabase)
    {
        databaseDao.updateCategory(category)
    }

    suspend fun deleteCategory(category: CategoryDatabase)
    {
        databaseDao.deleteCategory(category)
    }

    fun getCategoryByName(categoryName : String) : CategoryDatabase?
    {
        return databaseDao.getCategoryByName(categoryName)
    }

    suspend fun getCategoryNameById(categoryId : Long) : String
    {
        return databaseDao.getCategoryNameById(categoryId)
    }


    suspend fun insertTransaction(transaction : TransactionDatabase)
    {
        databaseDao.insertTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: TransactionDatabase)
    {
        databaseDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: TransactionDatabase)
    {
        databaseDao.deleteTransaction(transaction)
    }

    fun getTransactionsAllAccountsAllCategories(transactionType :TransactionType, dateStart :Long, dateEnd: Long) : LiveData<List<TransactionListClass>>
    {
       return databaseDao.getTransactionsAllAccountsAllCategories(transactionType, dateStart, dateEnd)
    }

    fun getTransactionsAllAccountWithCategory(transactionType: TransactionType, categoryId: Long, dateStart: Long, dateEnd : Long) : LiveData<List<TransactionListClass>>
    {
        return databaseDao.getTransactionsAllAccountWithCategory(transactionType,categoryId,dateStart,dateEnd)
    }

    fun getTransactionsWithAccountAllCategory(transactionType: TransactionType, accountId: Long, dateStart: Long, dateEnd : Long) : LiveData<List<TransactionListClass>>
    {
        return databaseDao.getTransactionsWithAccountAllCategory(transactionType,accountId,dateStart,dateEnd)
    }

    fun getTransactionsWithAccountWithCategory(transactionType: TransactionType, accountId: Long, categoryId: Long, dateStart: Long, dateEnd : Long) : LiveData<List<TransactionListClass>>
    {
        return databaseDao.getTransactionsWithAccountWithCategory(transactionType,accountId,categoryId,dateStart,dateEnd)
    }

    fun testingQuery() : LiveData<List<TransactionListClass>>
    {
        return databaseDao.testingQuery()
    }
}