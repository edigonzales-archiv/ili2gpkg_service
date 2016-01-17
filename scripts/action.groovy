@Grab(group='commons-fileupload', module='commons-fileupload', version='1.3.1')
@Grab(group='commons-io', module='commons-io', version='2.4')

import java.util.logging.Logger
import java.nio.file.Path
import java.nio.file.Files
import org.apache.commons.io.IOUtils
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.FileUtils
import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.util.Streams
import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.apache.commons.fileupload.disk.DiskFileItemFactory

Logger logger = Logger.getLogger("action.groovy")
logger.setUseParentHandlers(true)
logger.info ("Starts at: " + new Date())

if (ServletFileUpload.isMultipartContent(request)) {
    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

    for (item in items) {
        if (item.isFormField()) { // 'normal' form fields
            String fieldName = item.getFieldName()
            String value = item.getString()

            logger.info fieldName.toString()
            logger.info value.toString()
            logger.info item.getClass().toString()

        } else { // files
            String fieldName = item.getFieldName()
            String fileName = FilenameUtils.getName(item.getName())

            // get the file as input stream
            InputStream fileContent = item.getInputStream()

            // create random temporary directory
            String tmpDirPrefix = "ili2gpkg_";
            Path tmpDir = Files.createTempDirectory(tmpDirPrefix);

            // copy input stream into target file
            String targetFileName = fileName
            File targetFile = new File(tmpDir.toString() + File.separator + targetFileName)

            // falls hier exception -> exit-funktion aufrufen mit bisschen html...

            FileUtils.copyInputStreamToFile(fileContent, targetFile)
            logger.info "Uploaded file: " + targetFile.toString()
            logger.info "Uploaded file size [KB]: " + (int) (targetFile.length() / 1024)
        }
    }
}


if (params.submit) {
    handle()
}

def handle() {
    logger.info "AAAAAAAAA"
}

def exitOnError() {
    // do something...
}


println ""
println "Hallo."

logger.info ("Stops at: " + new Date())
