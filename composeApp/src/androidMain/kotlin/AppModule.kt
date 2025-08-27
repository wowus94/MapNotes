import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.vlyashuk.mapnotes.sql.AndroidDatabaseDriverFactory
import ru.vlyashuk.mapnotes.sql.DatabaseDriverFactory
import ru.vlyashuk.mapnotes.sql.NotesRepository
import ru.vlyashuk.mapnotes.ui.viewmodels.NotesViewModel

val appModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
    single { NotesRepository(get()) }
    factory { NotesViewModel(get()) }
}