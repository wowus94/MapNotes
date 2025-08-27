package ru.vlyashuk.mapnotes.sql

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import ru.vlyashuk.mapnotes.db.MyDatabase

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MyDatabase.Schema, "launch.db")
    }
}