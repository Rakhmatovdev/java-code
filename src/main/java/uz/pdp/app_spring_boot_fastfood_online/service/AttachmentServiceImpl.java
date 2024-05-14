package uz.pdp.app_spring_boot_fastfood_online.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import uz.pdp.app_spring_boot_fastfood_online.entity.Attachment;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.AttachmentMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.AttachmentDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.AttachmentRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Value("/Users/nurmukhammadsobirov/IdeaProjects/app_spring_boot_fastfood_online/files")
    private  String  filesPath;

    private final AttachmentMapper attachmentMapper;

    @Override
    public ApiResult<AttachmentDTO> upload(HttpServletRequest request) {

        try {
            Part part = request.getPart("file");

            String submittedName = part.getSubmittedFileName();
            long size = part.getSize();
            String contentType = part.getContentType();

            String[] strings = submittedName.split("\\.");

            String extension = strings[strings.length - 1];

            String name = UUID.randomUUID() + "." + extension;

            InputStream inputStream = part.getInputStream();

            Path targetPath = Paths.get(filesPath + "/" + name);

            Files.copy(inputStream, targetPath);

            Attachment attachment = new Attachment(
                    submittedName,
                    name,
                    contentType,
                    size,
                    targetPath.toString()
            );

            Attachment save = attachmentRepository.save(attachment);


            return ApiResult.success(attachmentMapper.toDTO(save));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RestException(e.getMessage());
        }

    }

    @Override
    public void downloadOne(Long id, HttpServletResponse response) {

        Attachment attachment = attachmentRepository.findById(id).orElseThrow(()-> RestException.restThrow("Attachment not found"));

        File file = new File(attachment.getPath());

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ServletOutputStream outputStream = response.getOutputStream()) {

            String contentType = attachment.getContentType();

            response.setContentType(contentType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", id));
            response.setContentLengthLong(attachment.getSize());

            FileCopyUtils.copy(fileInputStream, outputStream);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
