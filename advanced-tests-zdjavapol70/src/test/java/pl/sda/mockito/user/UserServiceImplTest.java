package pl.sda.mockito.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final long IDENTIFIER = 1L;
    private static final User USER = new User(IDENTIFIER, "Jan", "Kowaski");

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserServiceImpl userService;

//    @BeforeEach
//    public void setUp() {
//        userService = new UserServiceImpl(userRepository, userValidator);
//    }

    @Test
    public void shouldGetUserById() {
        //given
        Mockito.when(userRepository.findById(IDENTIFIER)).thenReturn(Optional.of(USER));

        //when
        User actual = userService.getUserById(IDENTIFIER);

        //then
        Assertions.assertThat(actual).isEqualTo(USER);
        Mockito.verify(userRepository).findById(IDENTIFIER);
        Mockito.verifyNoInteractions(userValidator);
    }

    //przypadek negatywny praca domowa

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreateInvalidUser() {

        //given
        Mockito.when(userValidator.isUserValid(USER)).thenReturn(false);

        //when
        //then
        Assertions
                .assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userService.createUser(USER))
                .withMessage("User is invalid");

        Mockito.verify(userValidator).isUserValid(USER);
        Mockito.verifyNoInteractions(userRepository);
        Mockito.verifyNoMoreInteractions(userValidator);
    }

    //przypadek pozytywny praca domowa

}