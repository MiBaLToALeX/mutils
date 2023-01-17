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

package com.mibaltoalex.common;

/**
 * @author Miguel J. Carmona (MIBALTOALEX) <miguel@mibaltoalex.com>
 */
public final class LoremIpsumGenerator {
    private LoremIpsumGenerator() {
    }

    public static String generate() {
        return """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Ut sodales quam in est fermentum, vitae commodo lacus sagittis.
                 Aliquam tincidunt, tortor eget sagittis finibus, lacus ante
                  efficitur ex, id tincidunt sem velit sed diam.
                Aliquam tempus nisl in odio mollis aliquam et eget risus.
                Phasellus pulvinar orci eu ornare fringilla. Nulla quis libero orci.
                Vivamus porttitor libero at purus luctus semper. In maximus tincidunt est et porta.
                Morbi id sapien ac est dignissim euismod. Praesent semper dui id suscipit rutrum.
                Mauris eros orci, vehicula sit amet augue id, tempus blandit urna.
                Suspendisse eget nulla eget nibh luctus gravida sed non arcu.
                Mauris suscipit libero nunc, a iaculis mi luctus vitae.\s
                """;
    }
}
