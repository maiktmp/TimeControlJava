package com.example.maiknight.timecontroljava;

import android.content.Context;

import com.example.maiknight.timecontroljava.Interactors.Interactor;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws InterruptedException {
        Object expectation = new Object();
        long userId = 1;
        Interactor interactor = Interactor.getInstance();
        interactor.getUserGroups(userId, groups -> {
            synchronized (expectation) {
                expectation.notify();
            }
            assertNotNull(groups);
        });
        synchronized (expectation) {
            expectation.wait();
        }
    }
}
