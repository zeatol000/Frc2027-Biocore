package org.team4153.core.auto.recording;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import static java.nio.charset.StandardCharsets.UTF_8;

/** Utility for memorizing recorded data formats. */
public class Reader {
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
   */
  protected final byte[] format;
  protected int formatIndex = 0;
  protected int byteIndex = 0;
  protected int bitIndex = 0;

  protected final RandomAccessFile raf;
  protected final FileChannel channel;
  protected final ByteBuffer buffer;

  protected Reader(byte[] format, RandomAccessFile raf) {
    this.format = format;
    this.raf = raf;
    this.channel = raf.getChannel();
    this.buffer = ByteBuffer.allocate(8);
  }

  /** Reads a byte from the file, does not include booleans. */
  public byte readByte() throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (fmt != 'B') {
      throw new IllegalCallerException("Attempt to read byte on non byte format: " + (char) fmt);
    }

    buffer.limit(1);

    int bytesRead = channel.read(buffer);
    if (bytesRead != 1) {
      throw new IOException("Attempt to read byte (1) but " + bytesRead + " bytes were read");
    }

    buffer.flip();

    byte value = buffer.get();

    buffer.clear();

    return value;
  }

  /** Reads a char from the file */
  public char readChar() throws IOException {
    return (char) readShort();
  }

  /** Reads a double from the file */
  public double readDouble() throws IOException {
    return Double.longBitsToDouble(readLong());
  }

  /** Reads a float from the file */
  public float readFloat() throws IOException {
    return Float.intBitsToFloat(readInt());
  }

  /** Reads an int from the file, including any 4-byte-length type. */
  public int readInt() throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (!is4(fmt)) {
      throw new IllegalCallerException("Attempt to read int (4) on non 4 byte format: " + (char) fmt);
    }

    buffer.limit(4);

    int bytesRead = channel.read(buffer);
    if (bytesRead != 4) {
      throw new IOException("Attempt to read int (4) but " + bytesRead + " bytes were read");
    }

    buffer.flip();

    int value = buffer.getInt();

    buffer.clear();

    return value;
  }

  /** Reads a long from the file, including any 8-byte-length type. */
  public long readLong() throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (!is8(fmt)) {
      throw new IllegalCallerException("Attempt to read long (8) on non 8 byte format: " + (char) fmt);
    }

    buffer.limit(8);

    int bytesRead = channel.read(buffer);
    if (bytesRead != 8) {
      throw new IOException("Attempt to read long (8) but " + bytesRead + " bytes were read");
    }

    buffer.flip();

    long value = buffer.getLong();

    buffer.clear();

    return value;
  }

  /** Reads a short from the file, including any 2-byte-length type. */
  public short readShort() throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (!is2(fmt)) {
      throw new IllegalCallerException("Attempt to read int (4) on non 4 byte format: " + (char) fmt);
    }

    buffer.limit(2);

    int bytesRead = channel.read(buffer);
    if (bytesRead != 2) {
      throw new IOException("Attempt to read short (2) but " + bytesRead + " bytes were read");
    }

    buffer.flip();

    short value = buffer.getShort();

    buffer.clear();

    return value;
  }
  
  /** Reads a UTF-8 string from the file. */
  public byte[] readUtf8() throws IOException {
    returnFromBoolean();

    byte fmt = format[formatIndex++];
    if (fmt != 'U') {
      throw new IllegalCallerException("Attempt to read UTF-8 on non 'U' byte format: " + (char) fmt);
    }

    int length = readInt();
    byte[] value = new byte[length];

    int bytesRead = channel.read(ByteBuffer.wrap(value));

    if (bytesRead != length) {
      throw new IOException("Attempt to read UTF-8 but " + bytesRead + " bytes were read");
    }

    return value;
  }

  /** Reads a boolean from the file. */
  public boolean readBoolean() throws IOException {
    if (bitIndex >= 8) {
      bitIndex = 0;
      byteIndex++;

      buffer.limit(1);

      int bytesRead = channel.read(buffer);
      if (bytesRead != 1) {
        throw new IOException("Attempt to read boolean start (1) but " + bytesRead + " bytes were read");
      }

      buffer.flip();
    }

    byte fmt = format[formatIndex++];
    if (fmt != 'Z') {
      throw new IllegalCallerException("Attempt to read boolean on non boolean byte format: " + (char) fmt);
    }

    buffer.rewind();

    byte fullByte = buffer.get();

    int bit = 1 << (7 - bitIndex++);
    //return (byte) (fullByte & (1 << (8 - bitIndex++)));
    return (fullByte & bit) != 0;
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

  public static boolean isDynamic(byte fmt) {
    return switch (fmt) {
      case '[' -> true; // array
      case 'U' -> true; // UTF-8
      default -> false;
    };
  }

  public static boolean is8(byte fmt) {
    return switch (fmt) {
      case 'D' -> true; // double
      case 'J' -> true; // long
      default -> false;
    };
  }

  public static boolean is4(byte fmt) {
    return switch (fmt) {
      case 'I' -> true; // int
      case 'F' -> true; // float
      default -> false;
    };
  }

  public static boolean is2(byte fmt) {
    return switch (fmt) {
      case 'C' -> true; // char
      case 'S' -> true; // short
      default -> false;
    };
  }

  public static boolean is1(byte fmt) {
    return switch (fmt) {
      case 'B' -> true; // byte
      case 'Z' -> true; // boolean
      default -> false;
    };
  }
}
