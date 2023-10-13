package vn.edu.tdtu.lab4.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {
    private HashMap<String, String> userList;

    private final String UPLOAD_DIRECTORY = "WEB-INF/uploads";
    private final Integer MAX_FILE_SIZE = 1024 * 1024 * 5;
    private final Integer MAX_REQUEST_SIZE = 1024 * 1024 * 5 * 5;
    private final Integer MEMORY_THRESHOLD = 1024 * 1024;

    private final String[] EXTENSION_LIST = {"txt", "pdf", "rar", "doc", "docx", "img", "zip"};

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Upload Servlet!!!");
        String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
    }

    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    private boolean validateFileName(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        return Arrays.asList(EXTENSION_LIST).contains(extension);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                String fileName = null;
                Boolean overwrite = false;
                Boolean error = false;
                List<FileItem> formItems = upload.parseRequest(request);

                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (item.isFormField() && item.getFieldName().equals("fileName")) {
                            fileName = item.getString();
                            if (!validateFileName(fileName)) {
                                request.setAttribute("message", "Unsupported file extension!!");
                                error = true;
                                break;
                            }
                        }
                        if (item.isFormField() && item.getFieldName().equals("overwrite")) {
                            if (item.getString().equals("YES"))
                                overwrite = true;
                        }
                    }
                    if (!error) {
                        for (FileItem item : formItems) {
                            if (!item.isFormField()) {
                                String uploadedFileName = new File(item.getName()).getName();
                                if (!validateFileName(uploadedFileName)) {
                                    request.setAttribute("message", "Unsupported file extension!!");
                                    break;
                                }
                                if (fileName == null) fileName = uploadedFileName;
                                String filePath = uploadPath + File.separator + fileName;
                                File storeFile = new File(filePath);
                                if (storeFile.exists()) {
                                    if (overwrite) {
                                        item.write(storeFile);
                                        request.setAttribute("message", "File has been overwritten!");
                                        break;
                                    } else {
                                        request.setAttribute("message", "File already exists!");
                                        break;
                                    }
                                }
                                item.write(storeFile);
                                request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                request.setAttribute("message", "There was an error: " + ex.getMessage());
            }
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
    }
}