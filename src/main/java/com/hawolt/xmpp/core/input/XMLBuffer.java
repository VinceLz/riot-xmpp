package com.hawolt.xmpp.core.input;

import java.util.Arrays;

/**
 * Created: 10/04/2022 14:15
 * Author: Twitter @hawolt, Github @DamianSz2000
 **/

public class XMLBuffer {
    private byte[] buffer = new byte[1024];
    private int bufferIndex = 0;

    private final InputCallback callback;
    private String breakpoint;

    public XMLBuffer(InputCallback callback) {
        this.callback = callback;
    }

    public void append(int character) {
        if (bufferIndex == buffer.length) {
            buffer = Arrays.copyOf(buffer, buffer.length * 2);
        }
        buffer[bufferIndex++] = (byte) character;
        if (character == '>') check();
    }

    private void check() {
        String buffered = new String(buffer, 0, bufferIndex);
        if (buffered.startsWith("</")) {
            reset();
        } else if (buffered.startsWith("<?")) {
            if (breakpoint == null && buffered.endsWith("?>")) {
                int first = buffered.indexOf('?');
                int second = buffered.indexOf('?', first + 1);
                breakpoint = buffered.substring(first, second).split(" ")[1] + ">";
            }
            if (breakpoint != null) {
                if (buffered.endsWith(breakpoint)) {
                    dispatch(buffered);
                }
            }
        } else if (buffered.startsWith("<")) {
            if (breakpoint == null && buffered.endsWith("/>")) {
                breakpoint = "/>";
            } else if (breakpoint == null && buffered.endsWith(">")) {
                int spaceIndex = buffered.indexOf(' ');
                if (spaceIndex == -1) {
                    int first = buffered.indexOf('>');
                    breakpoint = "</" + buffered.substring(1, first) + ">";
                } else {
                    breakpoint = "</" + buffered.substring(1, spaceIndex) + ">";
                }
            }
            if (breakpoint != null) {
                if (buffered.endsWith(breakpoint)) {
                    dispatch(buffered);
                }
            }
        }
    }

    private void dispatch(String line) {
        callback.onInput(line);
        reset();
    }

    private void reset() {
        this.bufferIndex = 0;
        this.breakpoint = null;
        this.buffer = new byte[1024];
    }
}