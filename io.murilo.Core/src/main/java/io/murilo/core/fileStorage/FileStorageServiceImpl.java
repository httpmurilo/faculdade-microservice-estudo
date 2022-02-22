package io.murilo.core.fileStorage;

import io.murilo.core.exceptions.GenericExceptionError;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class FileStorageServiceImpl implements FileStorageService {

    protected Path root = Paths.get("resources/curso-thumbl");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new GenericExceptionError("Não foi possivel criar a pasta de upload");
        }

    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            throw new GenericExceptionError("Não foi possivel salvar o arquivo" + e.getMessage());
        }

    }

    @Override
    public Resource load(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw  new GenericExceptionError("Não foi possivel ler o arquivo");
            }
        } catch (MalformedURLException e) {
            throw new GenericExceptionError("Erro na montagem da leitura" + e.getMessage());
        }
    }

    /**
     * Sem uso, nesse cenário nao temos a necessidade de deletar todos os arquivos
     */
    @Override
    @Deprecated
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    /**
     * Sem uso, no angular teremos algum cenário de carrosel para ser necessário carregar todas as imagens? A DEFINIR...
     * @return
     */
    @Override
    @Deprecated
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new GenericExceptionError("Não foi possivel carregar todos os arquivos");
        }
    }
}
