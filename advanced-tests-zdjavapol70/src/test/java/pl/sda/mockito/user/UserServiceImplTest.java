package pl.sda.mockito.user;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final long ID = 1L;
    private static final User USER = new User();

    @Mock
    UserRepository userRepository;

    @Mock
    UserValidator userValidator;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    // W większości przypadków w testach nie musimy wywoływać metody verify i verifyNoMoreInteractions. Nasze zamodelowane zachowania są automatycznie weryfikowane przez mockito.

    @Test
    void shouldGetUserById() {
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.of(USER));

        User actualUserById = userServiceImpl.getUserById(ID);

        assertEquals(actualUserById, USER);
//        optional:
//        Mockito.verify(userRepository).findById(ID);
//        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoInteractions(userValidator);
    }

    @Test
    void shouldThrowExceptionWhenUserIdIsEmpty() {
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userServiceImpl.getUserById(ID))
                .withMessage("No value present");
//        optional:
//        Mockito.verify(userRepository).findById(ID);
//        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoInteractions(userValidator);
    }

    @Test
    void shouldCreateUser() {
        Mockito.when(userValidator.isUserValid(USER)).thenReturn(true);
        Mockito.when(userRepository.addUser(USER)).thenReturn(USER);

        User actualUser = userServiceImpl.createUser(USER);

        assertEquals(USER, actualUser);
//        optional:
//        Mockito.verify(userRepository).addUser(USER);
//        Mockito.verifyNoMoreInteractions(userRepository);
//        Mockito.verify(userValidator).isUserValid(USER);
    }

    @Test
    void shouldThrowExceptionWhenCreateUserAndUserInvalid() {
        Mockito.when(userValidator.isUserValid(USER)).thenReturn(false);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userServiceImpl.createUser(USER))
                .withMessage("User is invalid");
//        optional:
//        Mockito.verify(userValidator).isUserValid(USER);
//        Mockito.verifyNoMoreInteractions(userValidator);
        Mockito.verifyNoInteractions(userRepository);
    }
}