package com.example.halagodainv.filebase;

import com.example.halagodainv.model.AttachFileEntity;
import com.example.halagodainv.repository.AttachFileRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;

@Component
public class FirebaseStoreManager {
    @Autowired
    private AttachFileRepository attachFileRepository;

    public FirebaseStoreManager() {
        try {
            InputStream inputStream = new ClassPathResource("ducanh-3725b.json").getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("http://console.firebase.google.com/project/ducanh-3725b/storage/ducanh-3725b.appspot.com")
                    .setStorageBucket("ducanh-3725b.appspot.com")
                    .build();
            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(MultipartFile file) throws IOException {
        String uploadFile = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (uploadFile.toUpperCase(Locale.ROOT).equals("PNG") || uploadFile.toUpperCase(Locale.ROOT).equals("JPG") || uploadFile.toUpperCase(Locale.ROOT).equals("SVG")) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            AttachFileEntity attachFileEntity = new AttachFileEntity();
            attachFileEntity.setCreatedDate(new Date());
            attachFileEntity.setStatus(200);
            attachFileEntity.setFileName(fileName);
            attachFileEntity.setTypeFIle(file.getContentType());
            attachFileEntity.setUrlFile(file.getBytes());
            Bucket bucket = StorageClient.getInstance().bucket();
            System.out.println(bucket.getName());
            String fileNameok = new Date().getTime() + "-" + file.getOriginalFilename();
            bucket.create(fileNameok, file.getInputStream(), file.getContentType());
            attachFileRepository.save(attachFileEntity);

        }
    }

    public byte[] getImage(String name) {
        Bucket bucket = StorageClient.getInstance().bucket();
        return bucket.get(name).getContent();
    }
}
