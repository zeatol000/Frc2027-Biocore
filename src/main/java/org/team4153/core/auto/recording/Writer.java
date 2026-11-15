package org.team4153.core.auto.recording;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.team4153.core.auto.recording.Reader.*;

/** Utility for recalling recorded data formats. */
public class Writer {
  /** An array of formatting characters.
   * B - byte (1 byte)
   * C - char (2 bytes)
   * D - double (8 bytes)
   * F - float (4 bytes)
   * I - int (4 bytes)
   * J - long (8 bytes)
   * S - short (2 bytes)
   * U - UTF-8 (variable length)
   * Z - boolean (1 bit*)
   *
   * UTF-8 {
   *   int length;
   *   byte bytes[length];
   * }
   *
   * Booleans are special as they are optimized to minimize space usage.
   * 8 booleans are compacted into a byte. However, if the boolean array is not
   * a multiple of 8, the remaining bits are padded with 0s.
   *
   * For example, the array: true, true, false, true, false; would be written as:
   * 0b11010000.
   */
  protected final byte[] format;
  protected int formatIndex = 0;
  protected int byteIndex = 0;
  protected int bitIndex = 0;

  protected final RandomAccessFile raf;
  protected final FileChannel channel;
  protected final ByteBuffer buffer;

  protected Writer(byte[] format, RandomAccessFile raf) {
    this.format = format;
    this.raf = raf;
    this.channel = raf.getChannel();
    this.buffer = ByteBuffer.allocate(8);
  }

  /** Write a byte to the file, does not include booleans. */
  public void writeByte(byte value) throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (fmt != 'B') {
      throw new IllegalCallerException("Attempt to write byte on non byte format: " + (char) fmt);
    }

    buffer.limit(1);
    buffer.put(value);
    buffer.flip();

    int bytesWritten = channel.write(buffer);
    if (bytesWritten != 1) {
      throw new IOException("Attempt to write byte (1) but " + bytesWritten + " bytes were written");
    }

    buffer.clear();
  }

  /** Write a char to the file */
  public void writeChar(char value) throws IOException {
    writeShort((short) value);
  }

  /** Writes a double to the file */
  public void writeDouble(double value) throws IOException {
    writeLong(Double.doubleToLongBits(value));
  }

  /** Writes a float to the file */
  public void writeFloat(float value) throws IOException {
    writeInt(Float.floatToIntBits(value));
  }

  /** Writes an int to the file, including any 4-byte-length type. */
  public void writeInt(int value) throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (!is4(fmt)) {
      throw new IllegalCallerException("Attempt to write int (4) on non 4 byte format: " + (char) fmt);
    }

    buffer.limit(4);
    buffer.putInt(value);
    buffer.flip();

    int bytesWritten = channel.write(buffer);
    if (bytesWritten != 4) {
      throw new IOException("Attempt to write int (4) but " + bytesWritten + " bytes were written");
    }

    buffer.clear();
  }

  /** Writes a long to the file, including any 8-byte-length type. */
  public void writeLong(long value) throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (!is8(fmt)) {
      throw new IllegalCallerException("Attempt to write long (8) on non 8 byte format: " + (char) fmt);
    }

    buffer.limit(8);
    buffer.putLong(value);
    buffer.flip();

    int bytesWritten = channel.write(buffer);
    if (bytesWritten != 8) {
      throw new IOException("Attempt to write long (8) but " + bytesWritten + " bytes were written");
    }

    buffer.clear();
  }

  /** Writes a short to the file, including any 2-byte-length type. */
  public void writeShort(short value) throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (!is2(fmt)) {
      throw new IllegalCallerException("Attempt to write short (2) on non 2 byte format: " + (char) fmt);
    }

    buffer.limit(2);
    buffer.putShort(value);
    buffer.flip();

    int bytesWritten = channel.write(buffer);
    if (bytesWritten != 2) {
      throw new IOException("Attempt to write short (2) but " + bytesWritten + " bytes were written");
    }

    buffer.clear();
  }

  /** Writes a UTF-8 string to the file. */
  public void writeUtf8(byte[] value) throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (fmt != 'U') {
      throw new IllegalCallerException("Attempt to write UTF-8 on non 'U' byte format: " + (char) fmt);
    }

    writeInt(value.length);
    int bytesWritten = channel.write(ByteBuffer.wrap(value));
    if (bytesWritten != value.length) {
      throw new IOException("Attempt to write UTF-8 of " + value.length + " bytes but " + bytesWritten + " bytes were written");
    }

    buffer.clear();
  }

  /** Writes a boolean to the file. */
  public void writeBoolean(boolean value) throws IOException {
    if (bitIndex >= 8) {
      bitIndex = 0;
      byteIndex++;

      buffer.limit(1);

      int bytesRead = channel.write(buffer);
      if (bytesRead != 1) {
        throw new IOException("Attempt to write boolean full (1) but " + bytesRead + " bytes were written");
      }

      buffer.flip();
    }

    byte fmt = format[formatIndex++];
    if (fmt != 'Z') {
      throw new IllegalCallerException("Attempt to write boolean on non boolean byte format: " + (char) fmt);
    }

    buffer.rewind();

    byte fullByte = buffer.get();
    int bit = 1 << (7 - bitIndex++);

    // if value, flip the respective bit to 1. else, no change
    int newValue = value ? (fullByte | bit) : fullByte;

    buffer.flip();
    buffer.put((byte) newValue);
  }


  /** Reset all pointers for the next read element. */
  public void done() {
    bitIndex = 0;
    formatIndex = 0;
    buffer.clear();
  }

  protected void returnFromBoolean() {
    if (bitIndex == 0) return;

    bitIndex = 0;
    byteIndex++;
    buffer.clear();
  }
}
