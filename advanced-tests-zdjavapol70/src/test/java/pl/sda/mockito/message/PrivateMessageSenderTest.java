package pl.sda.mockito.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class PrivateMessageSenderTest {

    private static final String TEXT = "Hello";

    private static final String AUTHOR_ID = "Andrzej123";

    private static final String RECIPIENT_ID = "Anna321";

    @Mock
    private MessageProvider messageProvider;

    @Mock
    private MessageValidator messageValidator;

    @InjectMocks
    private PrivateMessageSender messageSender;

    @Captor
    private ArgumentCaptor<Message> messageCaptor;

    @Test
    public void shouldSendPrivateMessage() {
        //given
        Mockito.when(messageValidator.isMessageValid(any())).thenReturn(true);
        Mockito.when(messageValidator.isMessageRecipientReachable(RECIPIENT_ID)).thenReturn(true);

        Mockito.doNothing().when(messageProvider).send(any(Message.class), eq(MessageType.PRIVATE));

        //when
        messageSender.sendPrivateMessage(TEXT, AUTHOR_ID, RECIPIENT_ID);

        //then
        Mockito.verify(messageValidator).isMessageValid(any());
        Mockito.verify(messageValidator).isMessageRecipientReachable(RECIPIENT_ID);
        Mockito.verify(messageProvider).send(any(), eq(MessageType.PRIVATE));
    }


    @Test
    public void shouldSendPrivateMessageWithArgumentCaptor() {

        //given
        Mockito.when(messageValidator.isMessageValid(any())).thenReturn(true);
        Mockito.when(messageValidator.isMessageRecipientReachable(RECIPIENT_ID)).thenReturn(true);

        Mockito.doNothing().when(messageProvider).send(any(Message.class), eq(MessageType.PRIVATE));

        //when
        messageSender.sendPrivateMessage(TEXT, AUTHOR_ID, RECIPIENT_ID);

        //then
        Mockito.verify(messageValidator).isMessageValid(messageCaptor.capture());
        Mockito.verify(messageValidator).isMessageRecipientReachable(RECIPIENT_ID);
        Mockito.verify(messageProvider).send(messageCaptor.capture(), eq(MessageType.PRIVATE));

        for (Message message : messageCaptor.getAllValues()) {
            Assertions.assertEquals(TEXT, message.getValue());
            Assertions.assertEquals(AUTHOR_ID, message.getAuthor());
            Assertions.assertEquals(RECIPIENT_ID, message.getRecipent());
            Assertions.assertNotNull(message.getId());
            Assertions.assertNotNull(message.getSendAt());
            Assertions.assertTrue(message.getSendAt().isBefore(LocalDateTime.now()));
        }

    }

    //przypadek negatywny praca domowa


}