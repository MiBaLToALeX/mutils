/*
 * Copyright (C) 2023  Miguel J. Carmona (MIBALTOALEX) <miguel@mibaltoalex.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.mibaltoalex.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Miguel J. Carmona (MIBALTOALEX) <miguel@mibaltoalex.com>
 * @since 1.0
 */
public final class StringGZIP implements java.io.Serializable, Comparable<String> {
    private static final int LEN_BUFFER = 4096;
    private final ByteBuffer value;

    public StringGZIP(String original) {
        Objects.requireNonNull(original);
        this.value = zipStr(original);
    }

    /**
     * El tamaño del mensaje comprimido
     *
     * @return el tamaño del mensaje comprimido
     */
    public int length() {
        return value.asCharBuffer().length();
    }

    @Override
    public String toString() {
        return "[compressed]";
    }

    /**
     * Descomprime el mensaje
     *
     * @return un {@code Optional} con el mensaje descomprimido o un empty si ha fallado
     */
    public Optional<String> unwrap() {
        try {
            return Optional.of(unzipStr(this.value));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    /**
     * Devuelve un array de bytes del mensaje comprimido
     *
     * @return El array de bytes
     * @throws ReadOnlyBufferException       Si el array devuelto es de solo lectura
     * @throws UnsupportedOperationException Si no es accesible el array
     */
    public byte[] getBytes() {
        return this.value.array();
    }


    /**
     * Comprime en GZIP una determinada cadena de texto
     *
     * @param input la cadena de texto
     * @return la cadena comprimida en GZIP
     */
    private ByteBuffer zipStr(String input) {
        try {
            byte[] compressed;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 GZIPOutputStream gos = new GZIPOutputStream(baos)) {
                gos.write(input.getBytes(UTF_8));
                gos.close();
                compressed = baos.toByteArray();
            }
            return ByteBuffer.allocate(compressed.length)
                    .put(compressed, 0, compressed.length).position(0);
        } catch (Exception e) {
            return ByteBuffer.allocate(0);
        }
    }

    /**
     * Descomprime una cadena de texto a partir de bytes
     *
     * @param input los bytes comprimidos
     * @return la cadena de texto descomprimida
     * @throws IOException es lanzado cuando la entrada es incorrecta
     */
    private String unzipStr(ByteBuffer input) throws IOException {
        try (InputStream bais = new ByteArrayInputStream(input.array());
             GZIPInputStream is = new GZIPInputStream(bais);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[LEN_BUFFER];
            int length;
            while ((length = is.read(buffer, 0, LEN_BUFFER)) != -1) {
                baos.write(buffer, 0, length);
            }

            return baos.toString(UTF_8);
        }
    }

    @Override
    public int compareTo(String o) {
        return zipStr(o).compareTo(this.value);
    }

}
