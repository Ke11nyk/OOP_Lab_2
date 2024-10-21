package lab.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class InputReaderTest {

    @Mock
    private InputReader inputReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadIntValidInput() {
        when(inputReader.readInt(anyString())).thenReturn(42);
        assertEquals(42, inputReader.readInt("Enter a number:"));
    }

    @Test
    void testReadIntNegativeNumber() {
        when(inputReader.readInt(anyString())).thenReturn(-15);
        assertEquals(-15, inputReader.readInt("Enter a number:"));
    }

    @Test
    void testReadIntZero() {
        when(inputReader.readInt(anyString())).thenReturn(0);
        assertEquals(0, inputReader.readInt("Enter a number:"));
    }
}