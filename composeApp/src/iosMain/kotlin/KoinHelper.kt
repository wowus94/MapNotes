import org.koin.dsl.module
import ru.vlyashuk.mapnotes.sql.DatabaseDriverFactory
import ru.vlyashuk.mapnotes.sql.IOSDatabaseDriverFactory
import ru.vlyashuk.mapnotes.sql.NotesRepository
import ru.vlyashuk.mapnotes.ui.viewmodels.NotesViewModel

val iosModule = module {
    single<DatabaseDriverFactory> { IOSDatabaseDriverFactory() }
    single { NotesRepository(get()) }
    factory { NotesViewModel(get()) }
}