/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package jdep.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class IOTest {

    @Test
    public void testClose() {
        byte[] buffer = new byte[100];
        ByteArrayInputStream is = new ByteArrayInputStream(buffer) {
            @Override
            public void close() throws IOException {
                throw new IOException();
            }
        };
        try {
            IO.close(is);
            Assert.fail("expectation of exception failed");
        } catch (IOException e) {
        }
    }

    @Test
    public void testCloseSilently() {
        byte[] buffer = new byte[100];
        ByteArrayInputStream is = new ByteArrayInputStream(buffer) {
            @Override
            public void close() throws IOException {
                throw new IOException();
            }
        };
        try {
            IO.closeSilently(is);
        } catch (Exception e) {
            Assert.fail("not excepted exception: " + e.getMessage());
        }
    }

}
