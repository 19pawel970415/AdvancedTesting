package pl.sda.mockito.database;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class DataServiceImplTest {

    private static final Data DATA = new Data.DataBuilder().value("dane").build();

    @Mock
    private DatabaseConnection databaseConnection;

    @Spy
    private DataRepository dataRepository = new DataRepositoryImpl();

    @InjectMocks
    private DataServiceImpl dataService;

    @Test
    public void shouldAddDataForOpenedConnection() {
        //given
        Mockito.when(databaseConnection.isOpened()).thenReturn(true);

        //when
        Data actual = dataService.add(DATA);

        //then
        Assertions.assertThat(actual).isEqualTo(DATA);
        Mockito.verify(databaseConnection, Mockito.times(2)).isOpened();
        Mockito.verify(databaseConnection, Mockito.never()).open();
        Mockito.verify(databaseConnection).close();
        Mockito.verifyNoMoreInteractions(databaseConnection);

        Mockito.verify(dataRepository).add(DATA);
        Mockito.verifyNoMoreInteractions(dataRepository);

    }

    //pozostałe przypadki praca domowa

}