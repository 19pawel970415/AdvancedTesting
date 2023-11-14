package pl.sda.mockito.database;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DataRepositoryImplTest {

    private static final Data DATA = new Data.DataBuilder().value("data").build();

    @Mock
    private DatabaseConnection databaseConnection;

    @Spy
    DataRepository dataRepository = new DataRepositoryImpl();

    @InjectMocks
    DataServiceImpl dataServiceImpl;

    @Test
    void shouldAdd() {
        Mockito.when(databaseConnection.isOpened()).thenReturn(true);

        Data actual = dataServiceImpl.add(DATA);

        assertEquals(DATA, actual);
        Mockito.verify(databaseConnection, Mockito.times(2)).isOpened();
        Mockito.verify(databaseConnection, Mockito.never()).open();
        Mockito.verify(databaseConnection).close();
        Mockito.verifyNoMoreInteractions(databaseConnection);

        Mockito.verify(dataRepository).add(DATA);
        Mockito.verifyNoMoreInteractions(dataRepository);
    }
}