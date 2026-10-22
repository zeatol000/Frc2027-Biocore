package org.team4153.core.auto.recording;

import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.team4153.core.config.Log;

/** A serializer wrapper for recording input data to the output file.
 *
 * @param <T> the type of object
 */
public class Recorder<T extends Serializable> {
  /** The file to read and write to. */
  public final Path path;

  /** File output stream. please do not touch */
  public final OutputStream fos;

  /** Object output stream wrapping the file output stream */
  public final ObjectOutputStream oos;

  public Recorder(Path path) throws IOException {
    this.path = path;

    fos = Files.newOutputStream(path);
    oos = new ObjectOutputStream(fos);
  }

  /** Write an object to the file */
  public void write(T obj) {
    try {
      oos.writeObject(obj);
    }
    catch (IOException e) {
       Log.error("IOException while recording object: " + e.getMessage(), e);
    }
  }
}
