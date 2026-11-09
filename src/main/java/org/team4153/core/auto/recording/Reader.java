package org.team4153.core.auto.recording;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.CREATE;

import org.team4153.core.config.Log;

/** A serializer wrapper for reading data from the input file.
 *
 * @param <T> the type of object
 */
public class Reader<T extends Serializable> {
  /** The file to read and write to. */
  public final Path path;

  /** File output stream. please do not touch */
  public final InputStream fos;

  /** Object output stream wrapping the file output stream */
  public final ObjectInputStream ois;

  public Reader(Path path) throws IOException {
    this.path = path;

    fos = Files.newInputStream(path);
    ois = new ObjectInputStream(fos);
  }

  /** Write an object to the file */
  public T read() {
    try {
      @SuppressWarnings("unchecked")
      T obj = (T) ois.readObject();

      return obj;
    }
    catch (IOException | ClassNotFoundException e) {
      Log.error("Exception while recording object: " + e.getMessage(), e);
      return null;
    }
  }

  public void clear() {
    try {
      Files.write(
        path,
        new byte[0],
        CREATE,
        TRUNCATE_EXISTING
      );
    }
    catch (IOException e) {
      Log.error("Exception while clearing record file: " + e.getMessage(), e);
    }
  }
}
