package io.murilo.core.file;

import io.murilo.core.exceptions.GenericExceptionError;
import io.murilo.core.fileStorage.FileStorageServiceImpl;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FileStorageServiceTests {

    @InjectMocks
    private FileStorageServiceImpl service;

    protected Path root = Paths.get("uploads");
    private static String PATH_DE_IMAGEM = "uploads";
    protected Path rootWithFile = Paths.get("uploads/image.jpg");


    @Test
    public void Test_validarSeCriaPastaDeUpload() throws IOException {
        removerPasta();
        service.init();
        boolean exists = Files.isDirectory(root);// Files.exists(root);
        assertThat(exists).isTrue();
    }

    @Test
    public void Test_criarDiretorioParaUploadDeAquivosRepetidoGeraException() {
        service.init();
        assertThrows(GenericExceptionError.class, service::init,"Não foi possivel criar a pasta de upload");
    }

    @Test
    public void Test_naoPrecisaCriarDiretorioCasoExista() {
        boolean exists = Files.isDirectory(root);// Files.exists(root);
        if(exists) {
            assertThat(exists).isTrue();
        }
    }

    @Test
    public void Test_criarImagemDentroDoDiretorio() throws IOException {
        removerPasta();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file","image.jpg", MediaType.IMAGE_JPEG_VALUE, "Spring Boot".getBytes(StandardCharsets.UTF_8));
        service.init();
        service.save(mockMultipartFile);

        boolean exists = Files.exists(rootWithFile);
        assertThat(exists).isTrue();
    }

    @Test
    public void Test_DeletarECriarPastaDeUpload() throws IOException {
        removerPasta();
        service.init();
        boolean exists = Files.exists(root);
        assertThat(exists).isTrue();
    }

    @Test
    public void Test_DeletarTodosOsArquivos() throws IOException {
        removerPasta();
        MockMultipartFile mock1 = new MockMultipartFile("file","image.jpg", MediaType.IMAGE_JPEG_VALUE, "Spring Boot".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile mock2 = new MockMultipartFile("file","image1.jpg", MediaType.IMAGE_JPEG_VALUE, "Spring Boot".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile mock3 = new MockMultipartFile("file","image2.jpg", MediaType.IMAGE_JPEG_VALUE, "Spring Boot".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile mock4 = new MockMultipartFile("file","image3.jpg", MediaType.IMAGE_JPEG_VALUE, "Spring Boot".getBytes(StandardCharsets.UTF_8));

        service.init();
        service.save(mock1);
        service.save(mock2);
        service.save(mock3);
        service.save(mock4);

        service.deleteAll();

    }

    protected void removerPasta() throws IOException {
        FileUtils.deleteDirectory(new File("uploads"));

    }
}
