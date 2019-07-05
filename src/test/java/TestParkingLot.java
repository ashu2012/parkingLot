import com.gojek.parkingLot.AccessInterface;
import com.gojek.parkingLot.ParkingFullException;
import com.gojek.parkingLot.ParkingLot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class TestParkingLot {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    ParkingLot parkingLotObj= ParkingLot.getInstance();

    @Before
    public void setUpStreams() {
        parkingLotObj.setParkingSpots(1).build();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));


    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);

    }


    //
    @Test
    public void parkSuccess() {

        //System.out.print("hello");
        try {
            parkingLotObj.getNewParkingTicket("abcd","white");
        } catch (ParkingFullException e) {
            e.printStackTrace();
        }
        //System.err(outContent.toString().trim());
        assertEquals("Allocated slot number: 1", outContent.toString().trim());
    }

    @Test
    public void queryMatch() {
        //System.out.print("hello");


            parkingLotObj.getSlotNumberByReg("xuzw");

        assertEquals("Not Found", outContent.toString().trim());
    }


    @Test
    public void out() {
        System.out.print("Park");
        assertEquals("Park", outContent.toString());
    }

    @Test
    public void err() {
        System.err.print("leave");
        assertEquals("leave", errContent.toString());
    }

}
