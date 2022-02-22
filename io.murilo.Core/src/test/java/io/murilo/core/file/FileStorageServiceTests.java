package io.murilo.core.file;

import io.murilo.core.fileStorage.FileStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FileStorageServiceTests {

    @InjectMocks
    private FileStorageService fileStorageService;

    protected Path root = Paths.get("resources/curso-thumbl");

    @Test
    public void Test_criarPastaInicial() {
        fileStorageService.init();
        boolean exists = Files.exists(root);
        assertThat(exists).isTrue();
    }
}
