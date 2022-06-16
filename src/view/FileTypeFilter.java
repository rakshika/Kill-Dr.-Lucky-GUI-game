package view;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Filters the file provided by the user to meet the requirements.
 */
public class FileTypeFilter extends FileFilter {

  private final String extension;
  private final String description;

  /**
   * Constructor.
   * 
   * @param extension   the extension of the file.
   * @param description the description of the file.
   * @throws IllegalArgumentException if extension or description are null or
   *                                  empty.
   */
  public FileTypeFilter(String extension, String description) {
    if (extension == null || "".equals(extension) || description == null
        || "".equals(description)) {
      throw new IllegalArgumentException("extension and description should be specified.");
    }
    this.extension = extension;
    this.description = description;
  }

  @Override
  public boolean accept(File f) {
    if (f == null) {
      throw new IllegalArgumentException("File cannot be null");
    }
    if (f.isDirectory()) {
      return true;
    }
    return f.getName().endsWith(extension);
  }

  @Override
  public String getDescription() {
    StringBuilder returnDescription = new StringBuilder();
    returnDescription.append(description);
    returnDescription.append(extension);
    return returnDescription.toString();
  }

}
