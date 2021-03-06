package info.allentang.placeknockoff;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    FirebaseDatabase testDatabase = FirebaseDatabase.getInstance();
    DatabaseReference zeroRef = testDatabase.getReference("Coordinates/0");
    @Test
    public void zeroWriteTest() throws Exception{
        final CountDownLatch testDelay = new CountDownLatch(3);
        zeroRef.child("0").setValue("Blue");
        zeroRef.child("1").setValue("Blue");
        zeroRef.child("2").setValue("Red");
        testDelay.await(2, TimeUnit.SECONDS);

    }

    public void zeroReadTest() throws Exception{
        final CountDownLatch testDelay = new CountDownLatch(3);
        zeroRef.child("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                assertEquals(dataSnapshot.getValue(), "Blue");
                testDelay.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        zeroRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                assertEquals(dataSnapshot.getValue(), "Blue");
                testDelay.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        zeroRef.child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                assertEquals(dataSnapshot.getValue(), "Red");
                testDelay.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        testDelay.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("info.allentang.placeknockoff", appContext.getPackageName());
    }
}
