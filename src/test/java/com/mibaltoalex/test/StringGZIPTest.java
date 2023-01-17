/*
 * Copyright (C) 2023  Miguel J. Carmona (MIBALTOALEX)
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

package com.mibaltoalex.test;

import com.mibaltoalex.common.LoremIpsumGenerator;
import com.mibaltoalex.io.StringGZIP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Miguel J. Carmona (MIBALTOALEX)
 */
public class StringGZIPTest {

    @Test
    public void TestStringGZIP() {
        String texto = LoremIpsumGenerator.generate();
        StringGZIP mensaje = new StringGZIP(texto);
        System.out.println("Texto original: " + texto);
        int lenOriginal = texto.length();
        System.out.println("Size original: " + lenOriginal);
        System.out.println("Texto GZIP: " + mensaje);
        int lenCompressed = mensaje.length();
        System.out.println("GZIP size: " + lenCompressed);
        String mensajeDescomprimido = mensaje.unwrap().orElse("Error");
        System.out.println("UnGZIP: " + mensajeDescomprimido);

        assertEquals(texto, mensajeDescomprimido);
    }
}
