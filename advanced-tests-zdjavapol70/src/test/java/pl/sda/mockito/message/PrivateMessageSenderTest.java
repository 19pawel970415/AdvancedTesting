package pl.sda.mockito.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.mockito.exception.SdaException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrivateMessageSenderTest {

    private static final String TEXT = "Hello there";
    private static final String AUTHOR_ID = "1";
    private static final String RECIPIENT_ID = "2";

    @Mock
    private MessageProvider messageProvider;

    @Mock
    private MessageValidator messageValidator;

    @InjectMocks
    private PrivateMessageSender privateMessageSender;

    @Test
    void shouldSendPrivateMessage() {
        when(messageValidator.isMessageValid(any())).thenReturn(true);
        when(messageValidator.isMessageRecipientReachable(RECIPIENT_ID)).thenReturn(true);
        Mockito.doNothing().when(messageProvider).send(any(), eq(MessageType.PRIVATE));

        privateMessageSender.sendPrivateMessage(TEXT, AUTHOR_ID, RECIPIENT_ID);

        Mockito.verify(messageValidator).isMessageValid((any()));
        Mockito.verify(messageValidator).isMessageRecipientReachable(RECIPIENT_ID);
        Mockito.verify(messageProvider).send(any(), eq(MessageType.PRIVATE));
    }

    @Captor
    ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);

    @Test
    void shouldSendPrivateMessageCapturing() {
        when(messageValidator.isMessageValid(any())).thenReturn(true);
        when(messageValidator.isMessageRecipientReachable(RECIPIENT_ID)).thenReturn(true);
        Mockito.doNothing().when(messageProvider).send(any(), eq(MessageType.PRIVATE));

        privateMessageSender.sendPrivateMessage(TEXT, AUTHOR_ID, RECIPIENT_ID);

        Mockito.verify(messageValidator).isMessageValid(messageArgumentCaptor.capture());
        Mockito.verify(messageValidator).isMessageRecipientReachable(RECIPIENT_ID);
        Mockito.verify(messageProvider).send(messageArgumentCaptor.capture(), eq(MessageType.PRIVATE));

        for (Message message : messageArgumentCaptor.getAllValues()) {
            assertNotNull(message.getId());
            assertEquals(TEXT, message.getValue());
            assertNotNull(message.getSendAt());
            assertEquals(AUTHOR_ID, message.getAuthor());
            assertEquals(RECIPIENT_ID, message.getRecipent());
        }
    }

    @Test
    void shouldThrowExceptionWhenSendPrivateMessageRecipientNotReachable() {
        when(messageValidator.isMessageValid(any())).thenReturn(true);
        when(messageValidator.isMessageRecipientReachable(RECIPIENT_ID)).thenReturn(false);

        assertThatExceptionOfType(SdaException.class)
                .isThrownBy(() -> privateMessageSender.sendPrivateMessage(TEXT, AUTHOR_ID, RECIPIENT_ID))
                .withMessage("Cannot send private message. Validation failed");

        Mockito.verify(messageValidator).isMessageValid(any());
        Mockito.verify(messageValidator).isMessageRecipientReachable(RECIPIENT_ID);
        Mockito.verifyNoInteractions(messageProvider);
    }

    @Test
    void shouldThrowExceptionWhenSendPrivateMessageNotValid() {
        when(messageValidator.isMessageValid(any())).thenReturn(false);

        assertThatExceptionOfType(SdaException.class)
                .isThrownBy(() -> privateMessageSender.sendPrivateMessage(TEXT, AUTHOR_ID, RECIPIENT_ID))
                .withMessage("Cannot send private message. Validation failed");

        Mockito.verify(messageValidator).isMessageValid(any());
        Mockito.verifyNoInteractions(messageProvider);
    }
}