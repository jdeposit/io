/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package jdep.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class collects useful utility methods for streams.
 */
public class IO {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    public static void copy(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while (is.read(buffer) > 0) {
            os.write(buffer);
        }
    }

    public static void copyAndClose(InputStream is, OutputStream os) throws IOException {
        try {
            copy(is, os);
        } finally {
            closeSilently(is, os);
        }
    }

    public static void close(Closeable ...resources) throws IOException {
        for (Closeable resource : resources) {
            if (resource == null) {
                continue;
            }

            resource.close();
        }
    }

    public static void closeSilently(Closeable ...resources) {
        for (Closeable resource : resources) {
            if (resource == null) {
                continue;
            }

            try {
                resource.close();
            } catch (IOException e) {
                // ignore exception
            }
        }
    }

}
